#  Infrastructure Security

## KMS Part 1

AWS Key Management Service - need to know this inside and out to pass the exam.

AWS KMS is a managed service that makes it easy for you to create and control the encryption keys used to encrypt your data, and uses HSMs to protec the security of your keys.

AWS KMS - sites on multitenant hardware.

KMS - console > IAM > Encryption keys

* IAM - global, encryption keys are regional

Scenario: CFO, creating financial reports, store in an s3 bucket only they should have access to.

* Create users - sys admin, financial controller (CFO), accounts team member
* Create two groups - administrators (Admin access) and finance (s3 full access console read only access)
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