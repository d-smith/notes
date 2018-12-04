# study approach

Assemble Notes from Two Aspects

* Add notes and links using domain outline format
* Layer in service notes into the domains

Services:

* Macie
* Inspector
* GuardDuty
* Trusted Advisor
* Systems Manager
* Config
* KMS
* CloudHSM
* VPC Endpoints
* IAM
* Organizations
* Artifact
* Athena
* CloudTrail

Scenarios:

* Cross account access
* Forensics
* Key rotation - automatic and manual
* DNS - using your own not Amazon's
* CloudTrail set up/troubleshooting
* Basic ports - ssh, sftp, smtp
* Recover data from a deleted CMK
* Policy - write once read many archiving
* 


## Domain Outline

### Domain 1: Incident Response

1.1 Given an AWS abuse notice, evaluate the suspected compromised instance or exposed access keys.

SEC389 - Detecting Credential Compromise in AWS [video](https://youtu.be/pagHGaercLs)

[Preventing Credential Compromise](https://medium.com/netflix-techblog/netflix-information-security-preventing-credential-compromise-in-aws-41b112c15179)

1.2 Verify that the Incident Response plan includes relevant AWS services.

SEC327 - AWS Security in Your Sleep: Build End-to-End Automation for IR Workflows [ppt](https://www.slideshare.net/AmazonWebServices/aws-security-in-your-sleep-build-endtoend-automation-for-ir-workflows-sec327-aws-reinvent-2018)

1.3 Evaluate the configuration of automated alerting, and execute possible remediation of security-related
incidents and emerging issues.

SEC322 Using AWS Lambda as a Security Team [video](https://youtu.be/ecT4eyy0CyU)

SEC403 - Five New Security Automations Using AWS Security Services & Open Source [video](https://youtu.be/M5yQpegaYF8)

Why automation?

* More than reactive auto remediation
* Provide proactive support to reactive humans in time of crisis, including information gathering

Services used in security: lambda, step functions, cloud trail, cloud trail events, parameter store, more

* Not just guard duty, inspector, macie, secrets manager, etc.

Project: SignalHub

* Centralized message hub for alerts and other notification needs
* Runs as per account or per organization Lambda function
* Send notifications to multiple destinations

Project: LambdaCanary

Lambda-based canary to verify the existence of cross account IR functions

* Do validation of expected Lambda functions
* Validate expected code with checksum
* Integrate with alert function if incorrect code or errors occur

Project: [GuardDuty Multi-Account Manager](https://github.com/mozilla/guardduty-multi-account-manager)

* Cross account role manager and configuration assurance for AWS GuardDuty
* Enable GuardDuty masters in all AWS Regions present and future
* Aggregate all findings in one place, put in s3
* Uses [cloudformation cross account outputs](https://github.com/mozilla/cloudformation-cross-account-outputs/) service

### Domain 2: Logging and Monitoring

2.1 Design and implement security monitoring and alerting.

ENT332-R - Best Practices for Centrally Monitoring Resource Configuration & Compliance [video](https://youtu.be/kErRv4YB_T4)

2.2 Troubleshoot security monitoring and alerting.
2.3 Design and implement a logging solution.

SEC323-R - Augmenting Security Posture and Improving Operational Health with AWS CloudTrail [video](https://youtu.be/YWzmoDzzg4U)

2.4 Troubleshoot logging solutions.

### Domain 3: Infrastructure Security

3.1 Design edge security on AWS.

SEC312-S - The Perimeter is Dead. Long Live the Perimeters. [video](https://youtu.be/nvVI3azDmOQ)

SEC326 - Orchestrate Perimeter Security Across Distributed Applications [video](https://youtu.be/ELIiF-jE0y8)

CTD201 Layered Perimeter Protection for Apps Running on AWS [video](https://youtu.be/MeFXknWDN3E)

3.2 Design and implement a secure network infrastructure. 

NET 202 - Securing Your Virtual Data Center in the Cloud [vide](https://youtu.be/2DF_EXmxbLM)

3.3 Troubleshoot a secure network infrastructure.
3.4 Design and implement host-based security.

SEC391 - Inventory, Track, and Respond to AWS Asset Changes within Seconds at Scale [video](https://youtu.be/_O6dIG5uqGg)

### Domain 4: Identity and Access Management

4.1 Design and implement a scalable authorization and authentication system to access AWS resources.
4.2 Troubleshoot an authorization and authentication system to access AWS resources.

### Domain 5: Data Protection

5.1 Design and implement key management and use.

SEC304 - AWS Secrets Manager: Best Practices for Managing, Retrieving, and Rotating Secrets at Scale [video](https://youtu.be/qoxxRlwJKZ4)

5.2 Troubleshoot key management.
5.3 Design and implement a data encryption solution for data at rest and data in transit. 

SEC325-R - Data Protection: Encryption, Availability, Resiliency, and Durability [video](https://youtu.be/FH6AXreSQWQ)