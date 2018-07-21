# Logging and Monitoring

## AWS Cloud Trail

* Records API calls for your account and delivers log files to you.
* For all AWS

```console
        control  
user -> plane   -> AWS cloud
        (API)
```

* Logs the API calls, but not the things you do on an EC2 instance via ssh for instance.

Enables:

* After the fact incident investigation
* Near real time intrusion detection
* Industry & regulatory complaince

Provides:

* Logs of API call details for supported services

What is logged?

* Metadata around the API call
* The identity of the caller
* The time of the API call
* The source IP address of the caller
* The request parameters
* The response elements returned by the service

CloudTrail Event Log:

* Sent to an S3 bucket
* You manage retention
* Logs delivered every 5 (active) minutes with up to a 15 minute delay
* Notifications available
* Can be aggregated across regions
* Can be aggregated across accounts

Set Up:

* Enabled by default for 7 days
* Provision it for longer
    * Create a new cloud trail, apply it to all regions
    * Select event types (pick all)
    * Pick the bucket to store it in

Digest Files

* Mechanism to determine if someone has tampered with them, or if they were deleted.
* Enabled via 'log file integrity checking' when provisoning cloud trail
* Can validate using the command line
* SHA-256 Hashing, SHA-256 wutg RSA for digital signing

## CloudTrail - Protecting Your Logs

Why secure?

* Can include PII like users names and team membership
* Can include detail config info like dynamodb table and key names
* Could prove valuable to an attacker

How to prevent unauthorized access?

* Place employees who have a security role into an IAM group with attached policies that enable access to the logs.
        * IAM - create two groups - CloudTrailAdmin 
        * Admin - grant AWSCloudTrailFull access AWS 
        * Auditors - AWSCloudTrailReadOnlyAccess

* How can we be notified that a log file has been created, then validate it has not been modified?
        * Configure SNS notifications and log file validation on the 'Trail'
        * Consume the notification with a solution that will validate the logs using the provided digest file.
        * Probably better to look for mods to files in the bucket via a lambda.

* How can we prevent logs from being deleted?
        * Restrict delete access with IAM and bucket policies.
        * Use s3 MFA delete
        * Validate logs have not been deleted using log file validation.

* How can we ensure that logs are retained for X years in accordance with our compliance standards?
        * Use S3 object lifecycle management

Exam tips:
        * API calls to control plane are logged by CloudTrail
        * Metadata around calls is logged (see above)
        * Events sent to s3 bucket - use s3 to manage the retention and archiving and purging of the files.
        * Delivered event 5 active minutes, can be a 15 minute delay
        * Notifications available
        * Can aggregate logs across regions, and aggregate across accounts
        * Can validate log file integrity (digests on the hour, every hour, AWS has the private key)
        * Secure the logs, check their integrity

## AWS CloudWatch

Amazon CloudWatch is a monitoring service for AWS cloud resources and the applications you run on AWS.

Enables:

* Resource utilization, operational performance monitoring
* Log aggregation and basic analysis

Provides:

* Real-time monitoring within AWS for resources and applications
* Hooks to event triggers

Real-time: standard every 5 minutes, detailed every minute

Alarms - for metrics, threshold and periods => action
Custom metrics can be created

CloudWatch Logs

* From some service, from your applications/systens
* Metrics from log entry metrics
* Stored indefinitely

Can install agents to forward logging to cloud watch logs

CloudTrail can be a cloud watch event source, use cloud watch event rules to send events to event target.


CloudWatch Events

* Near real-time stream of system events
* Events
        * AWS resource state changes
        * AWS CloudTrail events
        * Custom events (code)
        * Scheduled
* Rules - match events and route them to targets
* Targets - lambda, SNS, SQS, kinesis streams

Exam Hint: Read the AWS CloudWatch FAQs (CloudWath + CloudTrail are a big part of the exam)

Exam tips:

* Remember the key components: CloudWatch, CloudWatch Logs, CloudWatch Events
* CloudWatch - real time, metrics, alarms, notifications, custom metrics
* CloudWatch Logs - push from some service, from applications and systems, metrics from log entries, stored indefinitely
* CloudWath events - near real time stream of system events, state changes, cloud trail logs, custom events, scheduled, rules, targets => event driven security

## AWS Config