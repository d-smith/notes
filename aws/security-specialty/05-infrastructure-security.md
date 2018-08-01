#  Infrastructure Security

## KMS Part 1

AWS Key Management Service - need to know this inside and out to pass the exam.

AWS KMS is a managed service that makes it easy for you to create and control the encryption keys used to encrypt your data, and uses HSMs to protec the security of your keys.

AWS KMS - sites on multitenant hardware.

KMS - console > IAM > Encryption keys

* IAM - global, encryption keys are regional

Scenario: CFO, creating financial reports, store in an s3 bucket only they should have access to.

* Create users - sys admin, financial controller (CFO), accounts team member
* Create two groups - administrators (AdministratorAccess) and finance (S3FullAccess,  ConsoleReadOnlyAccess)
* Create an encryption key for the CFO only - key material from KMS
    * Tag key - finance, owner
    * Key admin permissions - CFO (note you can grant to roles too), allow key administrators to delete the key
    * Permissions - grant to the CFO only
* Create a bucket
* Log in as the CFO
    * Upload - pick a couple files to s3 (pay slip, ether wallet, ether wallet json)
    * Next - under encryption, KMS key under ecryption (bucket in same region as key)
    * Upload
    * Make pay slip public - can't get it because don't have key
    * Change to AES 256 aws enncrypted - can now view public
    * CFO can open/view
* Log in as accounts team member (no access to key)
    * Can see key in IAM/KMS but can't edit/change anything
    * Go to the s3 bucket
        * Can see the public payslip (AWS key)
        * Can't view or open the other docs (public facing or through console), can't download it either.
* Log in ad admin (full administrator access) - to be continued in part 2

Key Policy - administer, use, delete

## KMS Part 2

Scenario, continued

* Log in as administrator (full administrator access)
* Can't access file via link - no key access, but... can open it, and download it (and it is encrypted)
* Can also become the key administrator, key owner, key user, etc.
* What if we downgrade the admin to SystemAdministrator?
  * Try to add yourself as administrator or user? Can't add users, just roles.
  * Go to s3 - click link, no access (like before), open or download - no access.

  What is the CFO leaves and deletes the encryption key?

  * Log in as CFO, delete the key
  * Can only disable, and schedule key deletion
  * Minimum 7 day delay to delete the key
  * Log in as root account owner/admin
    * Go to s3 - no link access, no download, no open


  Be careful who gets full AdministratorAccess and use CloudTrail to monitor key activity

  ## KMS Part 3 - Use your own key material

  * Create a new key
    * Advanced options - external key material origin

Have to download wrapping key and import token before you can upload your key material

Use the 'sha1' version for this example.

Download openssl

Follow instructions in [the AWS documenation](https://docs.aws.amazon.com/kms/latest/developerguide/importing-keys-encrypt-key-material.html)

Import the key material - now you can use your key.

What happens if you create another key using the same key material?

* Use same encrypted key material, same import token
* Gives an error - import token already used, so download wrapping key
* Do it again... invalid cypher text - material encrypted using different wrapping key.
* Can't do this!

Cannot enable automatic key rotation when you provide your own key material.

*Ciphertexts are not portable between CMKs* - encrypt data under a KMS CMK, cipher text cannot be decrypted with another CMK.

You can delete the key material right away.


## KMS Part 4 - Summary

* Must know KMS in depth to pass the exam
* Know what services it is integrated with

Customer Master Key - two types: AWS and customer managed

CMK

* Alias, creation date, description, key state (enabled, disabled, waiting for import of key material, scheduled for deletion, etc), key material
* Can NEVER be exported
* AWS-managed CMK for each service that is integrated with AWS KMS, or you can have a customer-managed CMK you generate by providing AWS with key material.

Set up a CMK:

* Create alias and description
* Choose key material option
* Define key administrative permissions
  * IAM users/roles that can administer (but not use) the key through the KMS API
* Define key usage permissions
  * IAM users/roles that can use the key to encrypt and decryt data

Key material

* You can import a symmetric 256-bit key from your key management infrastructure into KMS and use it like and other customer master key.

Why provide your own key material:

* Prove that randomness meets your requirements (compliance)
* Extend your existing processes to AWS
* To be able to delete key material without a 7 - 30 day wait. Then be able to import them again.
* To be resilient to AWS failure by storing keys outside AWS

Key Material Import

* Create CMK with no key material
* Download a public key (wrapping key) and import token
* Encrypt the key material
* Import the key material

Considerations for imported key material

* Availability and durability is different
* Secure key generation is up to you
* No automatic rotation
* Ciphertexts are not portable between CMKs

Monitor key activity using cloud trail plus events and alerts, or AWS config with alerts

READ THE KMS FAQs

