# Data Protection with VPCs

## VPC Overview

Amazon virtual private cloud (VPC) lets you provision a logically isolated section of the AWS cloud wher you can launch AWS resources in a virtual network you define. You have complete control over your virtual networking environment, including selection of your own IP address range, creation of subnets, and configuration of route tables and network gateways.

Fundamental Components

* Region - contains VPC, address range - /16 to /28
* Access into VPC - Internet Gateway (internet) or Virtual Private Gateway (VPNs).
* Router - route tables
* Network ACLs (stateless)
* Security Groups
* Public and private subnets

traffic -> gateway -> router -> route table -> network acl -> security group -> instance

Cool site - http://cidr.xyz/

What can you do with a VPC?

* Launch instances into a subnet of your choosing
* Assign custom IP address ranges in each subnet
* Configure route tables between subnets
* Create internet gateway and attach it to our VPC
* Create much better security control over your AWS resources
* Instance security groups
* Subnet network access control lists

Default vs Custom

* Default - immediate access
* Default - all subnets have route to internet
* Default - each ec2 instance has both public and private addresses

VPC Peering

* Allows you to connect one vpc to another via a direct network route using private IP addresses
* Instances behave as if they were on the same private network
* You can peer VPCs with other AWS accounts as well as with other VPCs in the same account
* Peering in in a star configuration - no transitive peering

Exam Tips

* Think of VPC as a logical data center in AWS
* Remember the coponents
* 1 subnet = 1 AZ
* Security groups are stateful, ACLs are stateless
* No transitive peering

https://aws.amazon.com/answers/networking/aws-global-transit-network

## VPC Set Up - Part 1

Lab

* Pick a region where CloudHSM is available - create the VPC where we can use CloudHSM.
* Create a new VPC, use 10.0.0.0/16 for the address range, pick amazon provide IPV6 cidr block, default tenancy.
  * A default route table is created
  * A default network ACL is created
  * A default security group is created
* Create some subnets
  * pick address range, availability zone in your vpc
    * 10.0.1.0/24 and 10.0.2.0/24 in different AZ
  * In a subnet first four and last are reserved by AWS
  * For example, in 10.0.0.0/24
    * 10.0.0.0 is the network address
    * 10.0.0.1 is reserved for the vpc router
    * 10.0.0.2 - in a VPC, the base address plus 2 is the DNS server address, but plus two is also reserved in each subnet
    * 10.0.0.3 is reserved by AWS for future use
    * 10.0.0.255 is the network broadcast address - broadcast is not supported in VPC, so AWS reserves this address
* Create an internet gateway (IGW) and attach it to your VPC
    * Can can have only one IGW per VPC
* Route table
    * Two rules - allow subnets to communicate with each other (IPv4 and IPv6)
    * By default subnets when created are associated with the default route table, which means you don't want the default route table to have a route to the internet
    * Create a new route table for routes to the internet, associate to the VPC
      * Enable internet access - 0.0.0.0/0 destintation, target is IGW
      * Can ipv6 route as well ::0
      * Associate a subnet with it, which makes it a public route table
      * Public subnet - go to subnet action, modify auto-assign IP settings, auto assign IPv4 public addresses

## VPC Lab Part II

* Ping private from public subnet - ICMP in the security group, source public IP CIDR block or VPC private address space.
* Can enable ssh too
* What if you want to yum update? Need internet access for istances in the private subnet.

## NAT Instances & NAT Gateways

[NAT instance vs NAT gateway](https://docs.aws.amazon.com/AmazonVPC/latest/UserGuide/vpc-nat-comparison.html) 


NAT instances

* Launch instance - community AMIs, search for NAT (Amazon NAT AMI)
* Network - disable source and destination checks, NAT doesn't work if it is enabled
* Go to VPC, edit the route table, 0.0.0.0/0 outbound destination, NAT instance as the target
* What happens if the NAT instance crashes? No internet access.

NAT Gateway

* Create a NAT gateway (IPv4), exgress only gateways (IPv6)
  * Select the public subnet
  * Need a new elastic IP address
  * Once provisioned, go to route tables, add route 0.0.0.0/0 with NAT gateway as the target

Exam Tips - NAT instances

* WHen creating, disable source/destination check on the instance
* NAT instances must be in a public subnet
* Add routes out from private subnets to the NAT instance
* Amount of traffic supported depends on instance size - if bottlenecked, increase the size
* Create HA using autoscaling groups, multiple subnets in different Az, and a script to automate failover.
* Behind a security group.

Exam Tips - NAT Gateways

* Preferred by the enterprise
* Scale automatically up to 10 Gbps
* No need to patch
* Not assocaited with security groups
* Automatically assigned a public IP address
* Remember to update your route tables.
* More secure - no ssh access, no need to patch, install or maintain antivirus software, etc




