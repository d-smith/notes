# Incidence Response and AWS in the Real World

## DDoS - Distributed Denial of Service

Based on the whitepaper.

TODO: Read the whitepaper, keep shield in mind if the whitepaper has not been updated.

What is a DDoS attack

* Attack that attempts to make your website or appication unavailable to your end users.
* Can be achived via multiple mechanisms, such as
  * Large packet floods
  * Combination of amplication and reflection techniques
  * Using large botnets

Applification/Reflection Attacks

* Uses things like NTP, SSDP, DNS, Chargen, SNMP, etc.
* Attacker sends third party server request using a spoofed IP address
* Server responds to the request with a greater payload than the request (28x - 58x greater) to the spoofed address
  * For example 64 byte NTP request returns 3,456 bytes
* Attackers can coordinate and use multiple third party servers to send legitimate traffic to the target.

Layer 7 Attacks

* Send multiple get requests
* Slowloris - open multiple connections to the server, partial http requests that do no complete

How to Mitigate DDoS

* Minimize the attack surface area
* Be ready to scale to absorb the attack
* Safeguard exposed resources
* Learn normal behavior
* Create a plan for attacks

Minimize attack surface area

* Avoid having lots of access points - direct ssh or rdp to web servers, application servers, and db servers for management
* Minimize using bastion hosts/jump boxes scoped to known IP addresses, move web, app, and db servers to private subnets.
* Limit exposure to a few hardened entry points

Be Ready to Absorb the Attack

* The key strategy behind a DDoS attack is to bring infrastructure to breaking point. This strategy assumes one thing: that you can't scale to meet the attack.
* To defeat this strategy, design infrastructure to scale as, and when, it is needed.
* Look at both vertical and horizontal scale options

Scaling Benefits

* The attack is spread over a larger area
* Attackers have to counter attack, which uses up more of their resources
* Scaling buys you time to analyze the attack
* Scalaing has the added benefit of providing you with additional levels of redundancy

Safeguard Exposed Resources

* In situations where you cannot eliminate internet entry points to your applications, you'll need to take additional measures to restrict access and protect those entry points without interrupting legitimate end user traffic.
* Three that can provide this control and flexibility
  * CloudFront
  * Route 53
  * Web Application Firewalls

Cloud Front

* Geo restriction and blocking
* Origin access identity

Route 53 

* Alias record sets - immediately redirect your traffic to a cloud front distribution, or a different elb load balancer with higher capacity ec2 instances running WAFs or your own security tools. No DNS changes, no need to worry about propagation
* Private DNS 0 allows you to manage internal DNS names for your application resources without exposing this information to the public internet

WAFs

* Use WAFs to protect against layer 7 attacks
* Use AWS WAF or providers from the marketplace

Learn Normal Behavior

* Be aware of normal and unusual behavior
  * Know the different types of traffic and what normal levels of this traffic should be 
  * Understand expected and unexpected resource spikes
* What are the benefits?
  * Allows you to spot abnormalities fast
  * You can create alarms to alert you of abnormal behavior
  * Helps you collect forensic data to understand the attack

Create a Plan for Attacks

* Having a plan in place before an attack ensures that:
  * You've validated the design of your architecture
  * You understand the costs for your increased resiliency and already know what techniques to employ when you come under attack
  * You know who to contact when an attack happens

AWS Shield

* Free service that protects all AWS customers on ELC, CLoudFront, and Route 53
* Protects against syn/udp floods, reflection attacks, and other layer 3/layer 4 attacks
* Advanced provides enhanced protections
  * Always on, flow based monitoring of network traffic and active application monitoring to provide near real-time notifications of DDoS attacks
  * DDoS respons team 24x7 to manage and mitigate application layer DDoS attacks
  * Protects you AWS bill against higher fees due to ELB, CLoudFront, and Route 53 usage spikes during a DDoS attack

Exam Tips

* Read the white paper - https://d1.awsstatic.com/whitepapers/Security/DDoS_White_Paper.pdf
* Remember the technologies used to mitigate a DDoS attack
  * CloudFront
  * Route 53
  * ELBs
  * WAFs
  * Autoscaling (both for web servers and WAFs)
  * CloudWatch

