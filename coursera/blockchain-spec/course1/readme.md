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

