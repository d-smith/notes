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

