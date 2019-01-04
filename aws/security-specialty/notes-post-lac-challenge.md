# Notes - Post Linux Academy Challenge


Topics

* Incident response plans - be clear on the goals of frequently testing and simulating incident response plans
* What do you do after creating a new account to avoid issues with root credentials?
* Understand AWS Config and its before and after capabilities, including IAM permissions.
* Compromised EC2 instance - what to do?
* Keys in a public repo - what to do?
* Default encryption on s3 objects
* AWS Config Rules - check for things like logging enabled on all buckets
* Review bucket policies, bucket ACLs, and use cases - including bucket policies for unauthenticated users.
* Review basic capabilities of security related services
* Create a private, isolated s3 bucket
* Enforce requirement that all objects upload to s3 use SSE-S3 using AWS_256
* Long term credentials for service accounts - IAM user
* KMS API operations include re-encrypt
* Key rotation details for KMS
* FIPS levels for KMS, CloudHSM
* CMK resource policy
* KMS throttling errors, encryption SDK key caching
* CloudHSM JCE compatibility
* Know the distinction of CMKs and DEKs and what KMS can manage and generate.