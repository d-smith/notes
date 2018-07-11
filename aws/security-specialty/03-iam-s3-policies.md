# Identity Access Management, S3 & Security Policies

## IAM Recap

For this speciailty, key to passing exam is understanding IAM and policies inside and out, and know how they result to s3 policies and access policies.

IAM - allows you to manage users and their level of access to the AWS console.

IAM gives you:

* Centralized control of your AWS account
* Shared access to your AWS account
* Granular permissions
* Identity federation (including AD, facebook, linked in, etc.)
* Multifactor authentication
* Provide temp access for users/devices and services where needed
* Set up you own password rotation policy
* Integrates with many different AWS services
* Supports PCI DSS compliance


Terms

* Users - end users
* Groups - collection of users under one set of permissions
* Roles - create then assign to AWS resources
* Policies - a document that defines one or more permissions

1st access to AWS console: root access

## IAM Root User Scenario

Scenario - you are taking over for a previous admin who has left on bad terms, and who used the root login for all their work

* Small company, small group of admins
* Root level access keys, MFA

Steps

* Log into the console - note IAM is global
* Under security status, or via My Security Credentials - prompt to work at the root level or at the IAM - go to root level (security credentials)
* Change the password, disable MFA, then re-enable MFA.
* Look at access keys - delete them. Don't use access keys for root account.
* Check on users - look at last activity


Hint: take a photo of your QR code, store it in s3 - if you lose your smart phone you can re-activate an authenticator method using the QR code. Google authenticator - add a new method using the QR code you saved.


Resetting Root Users

* Createe a new root user password and strong password policy
* Delete previous 2FA and re-create
* Check if root has an access key id and secret access key - if so delete
* Check other user accounts, delete if not legit

## IAM Policies 101

* IAM policies specify what you are allowed to do with any AWS resource.
* Global, applies to all areas of AWS.
* Attached to IAM users, groups, or roles.
* Define what a principal can do in your AWS environment


3 Types

* AWS managed
    * Administered by AWS
    * Note these can be updated by AWS
* Customer managed
    * Standalone policy you administer in your account
* Inline
    * Useful to maintain a strict one to one relationship between a policy and the principal entity that it's applied to.
    * Useful to ensure you don't inadvertantly associate the policy with anyone else.

Power Users do not have access to IAM.


## S3 Bucket Policies

* Attached only to s3 buckets
* Specify what actions are allowed or denied on the bucket, can be broken down to the user level

Use Cases

* Simple way to grant cross account access to s3
* Use when IAM policies bump up against size limits (2kb for users, 5 kb for groups, 10kb for roles). S3 suuports bucket policies up to 20 kb.
* You prefer to keep access control policies in the s3 environment. Think denying access to a specific bucket in a 40,000 user org.

Scenario

* S3 bucket policy denies user access, IAM policy grants read/write/delete to the IAM user => s3 bucket policy wins here

Steps

1. login with admin privileges, create two buckets
2. user IAM allows read and write, apply a bucket policy that allows delete to one of the buckets => policy generator does not supply the '/*' needed by some actions, like delete. You must know this for the exam
3. Normal bucket - can't delete
4. Bucket with delete policy - can delete
5. Admin back to bucket, add policy with deny all actions to the user arn
6. Now add access for a user as a second policy allow on the bucket. Policy has a deny all, plus an allow for one specific user. => *Explicit deny always overrides an allow*!

Explicit deny always overrides and allow

* Deny in IAM, allow in bucket policy => Deny takes precedent

## S3 ACLs

ACLs are a legacy access control mechanism that predates IAM.

* Use when you must apply policies on individual objects.
* Can use as a work around if bucket policy is too large.

CLI or API - need account number and canonical user id. `aws s3api list-buckets` - gives canonical id for individual user in the output

Access for the account, individual users (CLI or API), other accounts, public

Scenario:

1. Make an object pubich from an ACL point of view
2. Go to IAM, edit policy, full read, limited write, edit json and change allow to deny. All read plus one write - Deny.
3. Log in as IAM user - can read the bucket object via the link, no IAM in scope. IAM user access - deny applies.