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

