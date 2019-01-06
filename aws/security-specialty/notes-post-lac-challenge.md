# Notes - Post Linux Academy Challenge


Topics

* Incident response plans - be clear on the goals of frequently testing and simulating incident response plans
* What do you do after creating a new account to avoid issues with root credentials?
    * IAM best practices describes [locking away root user keys](https://docs.aws.amazon.com/IAM/latest/UserGuide/best-practices.html#lock-away-credentials) - protect the password, use MFA, delete access keys, use strong password, etc
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

## Notes

### Policies

*Identity-based* policies control what actions the identity can perform, on what resources, and under what conditions. These are permissions policies you attach to an IAM identity such as a user, group, or role.

* Managed and inline variants.

*Resource-based policies* control what actions a specified principal can perform on that resource and under what conditions. These are permissions policies you attach to a resource such as an S3 bucket or an IAM role trust policy.

* Inline only
* To enable cross account access, you can specify an entire account or IAM entities in another account as the principal in a resource based policy.

