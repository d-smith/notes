# MPC - Coinbase Wallet as a Service

Notes from [this paper](https://coinbase.bynder.com/m/687ea39fd77aa80e/original/CB-MPC-Whitepaper.pdf)

## Section 1: The Self-Custody Impediment to Broad Wallet Adoption

Classic approaches

- **Self-custody**: user has full control over their private keys
    - User is responsible for securing their private keys
    - User is responsible for backing up their private keys
- **Custodial**: user does not have control over their private keys
    - User assumes counter party risk

MPC approach attempts to blend the best of both worlds

Self-custody and usability

* Mnemonic export and safe storage problematic
* Securing the seed phrase is especially difficult for non-technical users

Tension between backup and security

> In fact, the better the backup of the mnemonic in terms
of preventing loss, the higher the threat of theft. For example, backing it up in multiple locations
provides multiple points of breach for theft. Storing it in a cloud storage account encrypted under
a user password is risky since users still frequently use weak passwords or reuse their password from
other services. Furthermore, users often forget their passwords (and the ability to remember any
password that isn’t vulnerable to an offline dictionary attack is questionable in any case1
)

MPC goal: provide users full control over their keys but with the usability and security of a custodial wallet

Coinbase

* 2 shares - one for customer, one held by provider
* Special self-custody backup for secure key recovery and export
* Simple restore from backup to cooperatively restore from backup

## Section 2: Secure MPC and Crypto Wallets

MPC fundamentals - see [this paper](https://eprint.iacr.org/2020/300.pdf)

Fundamental properties of MPC protocols in an adversarial model:

* **Privacy**: no party learns anything about the inputs of other parties
* **Correctness**: the output of the protocol is the correct function of the inputs

For MPC for wallets, the function to be computed is the signing of a transaction, e.g. digital signatures

> In order to sign on a
transaction, the two parties interact in an MPC protocol – using their shares and the transaction
3
that they wish to sign upon – in order to generate a signature. In this context, privacy ensures
that only the digital signature is learned and nothing else. In particular, this means that nothing
is learned about the parties’ private shares, and so it isn’t possible for one of the parties who
may be maliciously corrupted to sign in the future without the other. Furthermore, correctness
means (among other things) that the signature generated is on the approved transaction only, and
a malicious party cannot change the amount, recipient address or anything else. Importantly, since
the MPC protocol requires participation from both sides, both need to approve and so it is possible
to implement controls that are enforced on both sides

Current implementation

* Two-party protocol, with argument that this can be arbitrarily extended to more parties as some point as desired

MPC signing process

* MPC protocol involving wallet provider server and user device
* Can layer on authorization before initiation of MPC protocol
* Can leverage secure enclave on user device for additional security, biometrics for user key access, etc.
* Can include policies as well regarding daily spend, transaction amounts, etc

Refreshing shares

* Shares refreshed after each transaction

HD Wallets

* All keys derived from an initial high entropy seed
* Simplifies the back up process, e.g. just backup to initial seed
* Coinbase used a different method to achieve the goals of HD key derivation that is better suited to MPC


Coinbase uses a threshold verifiable random function to derive keys 

From ChatGPT:

> A Threshold Verifiable Random Function (Threshold VRF) is a cryptographic primitive that combines the properties of both Verifiable Random Functions (VRFs) and threshold cryptography. Let's break down these components to understand what a Threshold VRF is:
>
>1. **Verifiable Random Function (VRF)**:
>   - A VRF is a cryptographic function that takes an input (usually a secret key and a message) and produces two outputs: a random output called the "randomness" and a proof that can be verified by anyone. The key property of a VRF is that it is deterministic, meaning for the same input, it always produces the same randomness and proof.
>   - VRFs have applications in various cryptographic protocols, including randomness generation, key derivation, and cryptographic sortition (random selection of participants in a fair and verifiable manner).
>
>2. **Threshold Cryptography**:
>   - Threshold cryptography is a cryptographic technique that involves distributing a cryptographic key or operation among multiple parties (threshold participants) in such a way that a certain threshold of participants is required to perform the operation.
>   - In threshold cryptography, the key or operation is divided into shares, and each participant holds one share. A cryptographic operation can only be performed if a sufficient number of participants (the threshold) combine their shares.
>
>Now, putting these concepts together, a Threshold VRF is a VRF that is implemented in a way that requires a threshold of participants to produce the random output and proof. In other words, multiple parties jointly compute the randomness and proof in a distributed and threshold manner.
>
>The key advantages of a Threshold VRF are:
>
>- **Security**: By distributing the VRF computation among multiple participants, it becomes more resistant to single points of failure or malicious actors.
>- **Verifiability**: The resulting randomness and proof can be publicly verified, providing transparency and trust in the random output.
>- **Threshold Control**: A threshold approach allows multiple parties to collectively control and use the VRF, which can be useful in various applications where trust and fairness are important.
>
>Threshold VRFs find applications in decentralized systems, blockchain networks, and other scenarios where randomness needs to be generated in a secure, verifiable, and threshold-controlled manner. These systems often require a high degree of randomness for various cryptographic operations and protocols.

### Difficulty of backup

See quote above. Additionally...

> Furthermore,
the standard approach to data backup of redundancy – storing the backup in many different places
– cannot be naively deployed in this context due to the risk of theft.


Coinbase assisted backup:

> Coinbase-assisted backup: This backup is used to easily restore the user’s key shares to their
mobile phone in the case that it is lost. In order to achieve this, when a wallet is generated,
shares of the master key and VRF key (as described above) are generated in MPC. In addition,
the parties generate an ECIES key in MPC, with each party holding a share.6 Then, each
party encrypts their shares under the shared ECIES key, achieving the property that neither
Coinbase nor the user can decrypt the backup ciphertexts without the other’s cooperation.
All the ciphertexts are then stored by Coinbase in a secure and reliable way. Furthermore,
Coinbase stores their share of the ECIES private key, and the user’s share of the ECIES
private key is either stored by the user in their own cloud storage (Google Drive or iCloud,
etc.) or by the service provider deploying the application (as desired). We remark that the
ECIES key for backup that is shared between the user and Coinbase can also be refreshed,
as described above.
In order to restore from backup, the user first authenticates (to the service provider, or
potentially to the Coinbase server) in order to authorize the operation. After this, all that
is required is for the user and Coinbase to run MPC decryption on the backup ciphertexts,
with the result being that the original shares are restored at both Coinbase and the user.

Self-custody backup:

>  Self-custody backup: In addition to the above backup, when the wallet is generated, both the
shares are encrypted under a backup public keys that is owned solely by the user. Specifically,
upon generating a wallet, the user provides a backup public key to the server, and both the
Coinbase and user shares are encrypted with this public key and stored on the user’s device.
This public key can be in the mobile’s enclave and protected with a biometric and/or passcode,
and in the future it could be an external key (e.g., from a YubiKey). We stress that these
ciphertexts (from Coinbase and from the user side) are stored for this backup by the user. This
ensures that the user can obtain their keys independently of Coinbase (or even the service
provider). Observe that although the user has full control over this backup, it is generated
without the key ever being exposed. The security of this backup clearly relies on the security
of the backup key; since this never needs to be used in ongoing operations, it can be strongly
protected.

How to ensure the integrity of the backup?

> However, how do we know
that at the time of wallet generation, the parties encrypt the correct values to backup? If one of
the parties is corrupted (breached), then they may write garbage to backup. Since the backup is
encrypted, this will go undetected.

Coinbase solves for this using publicly-verifiable encryption

> Informally speaking, a publicly verifiable encryption of a secret share is an encryption together with a zero-knowledge proof that
the encryption is valid

## MPC Implmentation at Coinbase

Experts plus process

Multistage process

* Stage 1 theoretical protocol
* Stage 2 specification
* Stage 3 code reviews
* Stage 4 independent reviews

Defense in depth, zero-trust





