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


https://www.ccn.com/bitcoin-transaction-really-works/

https://medium.com/blockchain-review/how-does-the-blockchain-work-for-dummies-explained-simply-9f94d386e093

https://medium.com/@micheledaliessi/how-does-the-blockchain-work-98c8cd01d2ae

https://smartereum.com/8970/how-do-bitcoin-nodes-verify-transactions/