# Blockchain Basics - University at Buffalo

## Week 1

Learning objectives:

* Explain the three fundamental characteristics that define a Blockchain using Bitcoin Blockchain.
* Discuss the important features of Ethereum Blockchain that is used as reference implementation in many newer Blockchains.
* Outline methods for realizing trust in the Blockchain.

### Blockchain Defined

Readings:

http://www.bitcoin.org/bitcoin.pdf

https://queue.acm.org/detail.cfm?id=3136559

https://blockgeeks.com/guides/what-is-blockchain-technology/

https://www.pcmag.com/article/351486/blockchain-the-invisible-technology-thats-changing-the-wor

 
Blockchain projects are built around 3 core properties: decentralization, scalability, and security.

### Blockchain Structure

[UTXO](https://smithandcrown.com/glossary/unspent-transaction-outputs-utxo/) - unspent transaction output


### Basic Operations

Operations in a blockchain – validation, gathering transactions, broadcast of transctions, selection of next block

Participants

* Those that initiate transfer of value (txns)
* Miners (verify transactions, broadcast transactions, compete to create block, reach consensus by validating block, broadcast new block, confirm transactions)

 

Transaction validation – < 20 criteria to validate

* Includes : Referenced Input Unspent Transaction Output, UTXOs are valid, recall, UTXO is well-defined earlier in lesson two, reference output UTXOs are correct, reference input amount and output amount matched sufficiently, invalid transactions are rejected and will not be broadcast.

Consensus alg: proof of work

Transaction zero, index zero of the confirmed block is created by the miner of the block. It has a special UTXO and does not have any input UTXO. It is called the coinbase transaction that generates a minor's fees for the block creation. Currently, the minor's fees is 12.5 BTC for a bitcoin.

Linkage:


https://medium.com/blockchain-review/how-does-the-blockchain-work-for-dummies-explained-simply-9f94d386e093

The basics:

* blockchain is a distributed ledger that keeps track of who owns what
* txns grouped with others that have occured in the last 10 minutes into a crytographically protected block, and sent to the entire network.
* miners compete to validate the block by solving a complex problem
* validated block is timestamped then added to the chain of blocks

https://medium.com/@micheledaliessi/how-does-the-blockchain-work-98c8cd01d2ae

https://smartereum.com/8970/how-do-bitcoin-nodes-verify-transactions/

### Beyond Bitcoin

Bitcoin Open Source - github.com/bitcoin/bitcoin

Bitcoint scripts - conditional transfer of value

Ethereum extended the idea of script concept to smart contracts, which are code execution to embed business logic on blockchain

Three types of blockchains:

* Type 1 - coins and currency, e.g. bitcoin
* Type 2 - cryptocurrency and business logic, e.g. Ethereum
* Type 3 - No currency, but smart contract support, e.g. Hyperledger

Three types of access

* Public
* Private - selected participants
* Permission - consortium of collaborating parties to transact, provides provenance, governance, accountability


Linkage:

https://bitsonblocks.net/2015/09/09/a-gentle-introduction-to-blockchain-technology/

https://blog.ethereum.org/2015/08/07/on-public-and-private-blockchains/

https://cointelegraph.com/bitcoin-for-beginners/what-are-cryptocurrencies#accept-as-payment-for-business

https://www.coindesk.com/2017-bitcoins-year-2018-will-ethereums/

https://blockgeeks.com/guides/what-is-cryptocurrency/

https://www.coindesk.com/learn/how-does-blockchain-technology-work/

https://blockchain.info/

https://blockexplorer.com/

https://etherscan.io/

## Week 2

Learning objectives

* Discuss, at a conceptual level, the innovation of the Ethereum Blockchain, namely, the smart contract.
* Illustrate Ethereum Blockchain protocol: structural elements and operational aspects.
* Demonstrate the concept of gas, the fuel or payment model for code execution, and the incentive model of Ethereum Blockchain.

Ethereum Stack

* verticals - end user apps
* app framework - smart contracts
* eth blockchain and evm
* Peer to peer network and operating systems
* Hardware

### Smart Contracts

 Smart contract

 * A smart contract is a piece of code deployed in the blockchain node.
* Execution of a smart contract is initiated by a message embedded in the transaction.

More sophisticated logic can be enabled

* e.g. An auction bidding smart contract could execute this logic. If the age of a bidder is greater than 18 and the bid is greater than the minimum bid, then, accept the bid, or else reject the bid.

Solidity

* class-like structure with data and functions

Code execution

* ethereum virtual machine - run anywhere abstraction for contract code

Linkage 

* http://ethdocs.org/en/latest/introduction/what-is-ethereum.html
* https://blockgeeks.com/guides/smart-contracts/
* http://solidity.readthedocs.io/en/develop/introduction-to-smart-contracts.html

### Ethereum Structure

Bitcoin block state was defined in terms of unspent transaction outputs UTXOs and a reference implementation of the Wallet application that held the account reference.

 Ethereum formally introduce the concept of an account as a part of the protocol.

* The account is the originator and the target of a transaction.
* A transaction directly updates the account balances

 There are two types of accounts
* Externally Owned Accounts
    * controlled by private keys.
    * An externally owned account is needed to participate in the Ethereum network. It interacts with the blockchain using transactions.
* Contract Accounts
    * Contract Accounts or CA are controlled by the code and can be activated only by an EOA.
    * A Contract Account represents a smart contract.

Every account has a coin balance. The participant node can send transaction for Ether transfer or it can send transaction to invoke a smart contract code or both. Both types of transaction require fees. An account must have sufficient balance to meet the fees needed for the transactions activated.

Fees are paid in Wei.

* Wei is a lower denomination of Ether.
* One Ether 10 to the power of 18 Weis.

Transactions

* A transaction in Ethereum includes the recipient of the message, digital signature of the sender authorizing the transfer, amount of Wei to transfer, an optional data field or payload that contains a message to a contract, STARTGAS which is a value representing the maximum number of computational steps the transaction is allowed.
* Gas price a value representing the fee sender is willing to pay for the computations.

Ethereum transaction

* Observe transaction hash, height of the chain, timestamp, from and to accounts, value transport, gas limit, gas used, transaction receipt, success in this case and nonce. T

 Ethereum block structure

* has a header, transaction, and runner-up block headers.

 Block details.

* the height, timestamp, block hash, previous hash, difficulty and total difficulty, size, gas used, gas limit, nonce and block reward.

Linkage 

* https://ethereum.org/en/whitepaper/
* http://ethdocs.org/en/latest/account-management.html
* https://geth.ethereum.org/docs/dapp/native-accounts


### Ehtereum Operations

For a simple Ether transfer, the amount to transfer and the target address are specified along with the fees or gas points.The amount and the fees are transferred to their respective accounts.

Ethereum node - computational system representing a business entity or an individual participant. An Ethereum full node hosts the software needed for transaction initiation, validation, mining, block creation, smart contract execution and the Ethereum Virtual Machine, EVM.

Smart contract is designed, developed, compiled and deployed on the EVM that can be more than one smart contract in an EVM. When the target address in a transaction is a smart contract, the execution code corresponding to the smart contract is activated and executed on the EVM. The input needed for this execution is extracted from the payload field of the transaction. Current state of the smart contract is the values of the variables defined in it. The state of the smart contract may be updated by this execution. Results of this execution is told in the receipts. A blockchain maintains both the state hash and the receipt hash.

All the transactions generated are validated. Transaction validation involves checking the time-stamp and the nonce combination to be valid and the availability of sufficient fees for execution. Miner nodes in the network receive, verify, gather and execute transactions. The in-work smart contract code are executed by all miners. Validated transactions are broadcast and gathered for block creation.

Lnkage:

* https://ethereum.stackexchange.com/questions/3/what-is-meant-by-the-term-gas

### Incentive Model

 
Mining is the process used to secure the network by validating the computations, collecting them to form a block,
verifying them, and broadcasting it. Ethereum also uses a incentive-based
model for block creation.

 

Every action in Ethereum
requires crypto fuel, or gas. Gas points are used to specify
the fees inside of Ether, for ease of computation using standard values. Gas points allow for cryptocurrency independent valuation of
the transaction fee and computation fees. Ether, as a cryptocurrency,
varies in value with market swings, but gas points do not vary. Ethereum has specified gas points for
each type of operation. Mining process computes gas points
required for execution of a transaction. If the fee specified and the gas point in the transaction
are not sufficient, it is rejected.

 

The gas points needed for execution
must be in the account balance for the execution to happen. If there is any amount left over
after the execution of a transaction, it is returned to the originating account.

Gas limit is the amount of gas points
available for a block to spend. For example, if a block specifies
a limit of 1 million 5 hundred thousand units of gas, and
a basic Ether transaction fee is 21,000, this particular Ethereum block can fit
about 70 plain Ether transactions. If we add smart contract
transactions also into this block, that usually requires more gas,
and the number of transactions for this block will likely be lower.

Gas spent is the actual amount of gas spent at the completion
of the block creation.

 

The proof of work puzzle winner,
miner that creates a new block, is incentivized with the base
fees of three Ethers, and the transaction fees in
Ethereum blockchain. The winning miner also gets the fees,
gas points for execution of a smart
contract transactions. That there may be other miners who also
solve the puzzle besides the winner. These miners will solve the puzzle, but
didn't win the block are called Ommers. The blocks created by them
are called Ommer Blocks. These are added as Ommer Blocks, or
side blocks, to the main chain. Ommer miners also get a small
percentage of the total gas points as a consolation and for network security.

Summarizing, any transaction in Ethereum,
including transfer of Ethers, requires fees or gas points to be
specified in the transactions. Miners are paid fees for
security, validation, execution of smart contract,
as well as for creation of blocks.

Linkage

* https://www.coindesk.com/vitalik-buterin-doubles-ethereum-incentive-strategy/
* https://www.ethereum.org/ether
* https://blockgeeks.com/guides/proof-of-work-vs-proof-of-stake/

## Week 3 - Algorithms and Techniques

 

Learning objectives

* Summarize the working of public key cryptography.
* Explain simple hashing & Merkle tree hash.
* Explore the application of hashing and cryptography in protecting the Blockchain.


### Public Key cryptography

Two techniques are predominantly used for securing the chain and for efficient validation and verification. Hashing and asymmetric key encryption.

Recall that blockchains decentralized network participants, are not necessarily known to each other. Credentials cannot be checked by the conventional means such as verifying who you are with your driver's license. Participants can join and leave the chain as they wish. They operate beyond the boundaries of trust. Given this context. how do you identify the peer participants? How do you authorize and authenticate the transactions? How do you detect forged or faulty transactions?

We can do these things by using Public-key cryptography algorithm that we'll discuss in this lesson. Let's begin by examining simple symmetric key encryption. The same key is used for encryption and decryption, so it is called symmetric key.

You shift by three the S key value of every letter to encrypt it, and your receiver decrypts it using the same three as the key. Shift the other way every character to view the original message. Three is the key in this trivial example. Since the same key is used for encryption and decryption, it is a symmetric key.

 Note that symmetric key encryption has issues. Number one, it is easy to derive the secret key from the encrypted data. And number two, the key distribution, how do you pass the key to the participant transacting? These issues are further exasperated in a block chain decentralized network where participants are unknown to each other.

Let's now examine how Public-key cryptography addresses these issues. Instead of a single secret key, it employs two different keys that take care of both the issues of symmetric key encryption. Let, lowercase b uppercase B be the private public-key pair for a participant in Buffalo New York USA. Let lowercase k and uppercase K be the pair of keys for the participant and Kathmandu Nepal. Public-key is published, private key is kept safe and locked. Typically using a passphrase and the pair works as follows; encrypting function holds two properties with a key pair. The public-key private key pair has the unique quality that even though a data is encrypted with the private key, it can be decrypted with the corresponding public-key and vice versa. Now let's look at an example, authenticate the sender and the receiver. We'll examine just one common use of a symmetric key encryption. Let's say a participant in Buffalo wants to transact with the participant in Kathmandu. Instead of sending just a simple message, a participant in Buffalo will send a transaction data encrypted by Buffalo's private key, and then encrypted by Kathmandu's public key. Kathmandu will first decrypt the data using its own private key, then use Buffalo's public key to decrypt assigned transaction data. This ensures that only Kathmandu can decrypt and receive the data and that only Buffalo could have sent the data.

A popular implementation of public key, private key is the Rivest Shamir Adleman (RSA) algorithm. Common application of RSA is the passwordless user authentication, for example for accessing a virtual machine on Amazon cloud. Though RSA is very commonly used in many applications, block chains need a more efficient and stronger algorithm. Efficiency is a critical requirement since public key pair is frequently used in many different operations in block chain protocol.

Elliptic Curve Cryptography, ECC family of algorithms is used in the bitcoin as well as an Ethereum block chain for generating the key pair. Why ECC not RSA? ECC is stronger than RSA for a given number of bits. Did you know that 256 bit ECC key pair is equal in strength to about 3072 bits of RSA key pair. Both bitcoin and Ethereum use ECC based algorithms for their encryption needs.

Linkage:

* https://www.globalsign.com/en/ssl-information-center/what-is-public-key-cryptography/
* http://searchsecurity.techtarget.com/definition/asymmetric-cryptography
* https://www.youtube.com/watch?v=GSIDS_lvRv4
* https://qvault.io/cryptography/elliptic-curve-cryptography/

### Hashing

Hashing

 

A hash function or hashing transforms and maps an arbitrary length of input data value to a unique fixed length value. Input data can be a document, tree data, or a block data. Even a slight difference in the input data would produce a totally different hash output value.

The following are two basic requirements of a hash function.

* The algorithm chosen for the hash function should be a one-way function and it should be collision free, or exhibit extremely low probability of collision. The first requirement is to make certain that no one can derive the original items hashed from the hash value.
* The second requirement is to make sure that the hash value uniquely represents the original items hashed. There should be extremely low probability that two different datasets map onto the same hash value. These requirements are achieved by choosing a strong algorithm such as secure hash, and by using appropriately large number of bits in the hash value.

Most common hash size now is 256 bits and the common functions are SHA-3, SHA-256 and Keccak. Hash value space, how good is 256 bits hash? A 256-bit hash value space is indeed very large. 2 to the power of 256 possible combinations of values. That is approximately 10 to the power of 77. That is 10 followed by 77 zeros.

In the simple hash approach, all the data items are linearly arranged and hashed. In a tree-structured approach, the data is at the leaf nodes of the tree, leaves are pairwise hash to arrive at the same hash value as a simple hash. When is a tree-structured hash used? When is a simple hash used? When we have a fixed number of items to be hashed, such as the items in a block header, and we are verifying the composite block integrity and not the individual item integrity, we use simple hash. When the number of items differ from block to block, for example, number of transactions, number of states, number of receipts, we use the tree structure for computing the hash.

Note that the state is a variable that may be modified by a smart contract execution, and the result of the execution may be returned in a receipt. Tree structure helps the efficiency of repeated operations, such as transaction modification and the state changes from one block to the next. Log N versus N.

Summarizing, in Ethereum, hashing functions are used for generating account addresses, digital signatures, transaction hash, state hash, receipt hash and block header hash. SHA-3, SHA-256, Keccak-256 are some of the algorithms commonly used by hash generation in blockchains.

Linkage:

* https://blockgeeks.com/guides/what-is-hashing/
* https://www.cs.hmc.edu/~geoff/classes/hmc.cs070.200101/homework10/hashfuncs.html
* https://www.youtube.com/watch?v=DMtFhACPnTY
* https://anders.com/blockchain/hash

### Transaction Integrity

To manage the integrity of a transaction we need: number one, secure a unique account address. We need a standard approach to uniquely identify the participants in the decentralized network. Number two, authorization of the transaction by the sender through digital signing. And number three, verification that the content of that transaction is not modified.

We use a combination of hashing and public key cryptography, that we learned in the last two lessons to solve these problems. Let's start with the address of the accounts.

Addresses of accounts are generated using public key, private key pair.

Step 1, at 256-bit random number is generated, and designated as the private key. Kept secure and locked using a passphrase.

Step 2, an ECC algorithm is applied to the private key, to get a unique public key. This is the private public key pair.

Step 3. Then a hashing function is applied to the public key to obtain account address. The address is shorter in size, only 20 bytes or 160 bits.

Now that we have the account address, let's look at the transaction initiated by this address. A transaction for transferring assets will have to be authorized, it has to be non-repudiable, and unmodifiable. They first examined, the digital signing process, and then apply it to that transaction. Data is hashed and encrypted. This is the digital signature. The receiver gets the original data, and the secure hash digitally signed. Receiver can recompute the hash of the original data received, and compare it with the received hash to verify the integrity of the document. Now, consider the transaction to be that data. Step number 1, find the hash of the data fields of the transaction. Step number 2, encrypt that hash using the private key of the participant originating the transaction. Thus, digitally signing the transaction to authorize and making the transaction non-repudiable. Step number 3, this hash just added to the transaction. It can be verified by others decryiptng it using the public key of the sender of the transaction, and recomputing the hash of the transaction. Then, compare the computed hash, and the hash received at the digital signature. If that is a match, accept the transaction. Otherwise, reject it. Note that for the complete transaction verification, the timestamp, nons, account balances, and sufficiency of fees are also verified.

 

Linkage:

* https://hbr.org/2017/03/how-safe-are-blockchains-it-depends
* https://infospectives.co.uk/blockchains-embedding-integrity/

