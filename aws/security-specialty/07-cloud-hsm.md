# Cloud HSM Deep Dive

## Cloud HSM

Review overview and diffs between KMS and Cloud HSM - see part 4 notes.

Create a cluster and HSM:

* create vpc
* create a private and public subnet
* create the cluster
* verify the hsm identity
* initialize the cluster
* launch a client instance
* install and configure the client
* activate the cluster
* setup users
* generate keys

HSM cluster

* create a cluster in a vpc, put in private subnets in at least two availability zones
* after cluster created, initialize the cluster
    * pick an AZ, create
    * download cluster csr, hsm certificate, aws certificate, manufacturer certificate
    * learn more > how to get the root certificates (aws and manufacturer), download and verify

## Cloud HSM - Verifying Our Certs

```console

```
