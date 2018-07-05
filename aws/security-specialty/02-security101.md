## Shared Responsibility Model

See [here](https://aws.amazon.com/compliance/shared-responsibility-model/)

* Amazon - security of the cloud
    * global infrastructure
    * hardware, software, etc.
    * managed services
* Customer - security in the cloud
    * iaas
    * updates, security patches
    * AWS firewall (security groups, network ACLs, etc)

Different service types:

* Infrastructure
    * compute like ec2, ebs, autoscale, vpc
    * architect and build systems like on prem
    * you control the OS, config, etc
    * EC2 - customer responsible for AMIs, OS, applications, data in transit, data at rest, data stores, credentials, policies and config, etc.
* Container
    * network setup and controls/firewall rules, platform level identity and access  management apart from IAM. Examples: elastic beanstalk, EMR, RDS
* Abstracted
    * High level storage, messaging, and database services like S3, Glacier, DynamoDB, SQS, SES
    * Abstract the platform or management layer

Example: S3 - you are responsible for bucket policies