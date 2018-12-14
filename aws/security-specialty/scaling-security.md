https://www.youtube.com/watch?v=Rmf91qNcN8w

Cloud Adoption Framework - [security perspective](https://d0.awsstatic.com/whitepapers/AWS_CAF_Security_Perspective.pdf)

Basics - Getting Started

* New account - create first IAM user, MFA, stop use of root user
* Users and groups -least privileged access
* VPCs, security groups, appropriate restrictions - apply naming and tagging conventions
* Enable CloudTrail for all regions
* Use CloudWatch to understand your performance and application patterns, use to detect unusual activity

MVP gaining traction

* Address failover, introduce fault tolerance, multiple AZs
* Elastic load balancing, autoscaling
* Protect data with KMS, snapshots, S3, RDS, etc
* Extends IAM model to support KMS roles, etc