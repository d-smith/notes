# Cosmos

Developer Portal - https://tutorials.cosmos.network/

Some history

* 2008 - Bitcoin
* 2013 - Ethereum - expansion of decentralized peer to peer networks to handle more uses cases via smart contract functionality

Challenges with public, general purposes blockchains:

 * Low flexibility for devs
 * Speed - transactions speed
 * Throughput
 * Scalability
 * State finality -  Finality describes whether and when committed blocks with transactions can no longer be reverted/revoked. It is important to differentiate between probabilistic and absolute finality.
 * Sovereignty

Interchain network - founded by Tendermint - https://tendermint.com/

Cosmos whitepaper - foundation of the interchain network - https://v1.cosmos.network/resources/whitepaper

The Interchain

> The interchain is a network of independent blockchains, which are:
>
> * All powered by consensus algorithms with Byzantine Fault-Tolerance (BFT).
> * All connected through the Inter-Blockchain Communication Protocol (IBC), which enables value transfers, token transfers, and other communication between chains, all without the need to involve exchanges or make compromises regarding the sovereignty of each chain.
>
> The interchain is also a blockchain ecosystem complete with protocols, SDK, tokens, wallets, applications, repositories, services, and tools.

Interchain ecosystem - https://cosmos.network/ecosystem/apps/?ref=cosmonautsworld

Cosmos SDK - used to create new application specific blockchains.

Cosmos SDK - object capability model - https://docs.cosmos.network/v0.45/core/ocap.html

Cosmos SDK presentation - https://youtu.be/1_ottIKPfI4

App-Specific Blockchain Architecture - https://tutorials.cosmos.network/academy/2-cosmos-concepts/1-architecture.html

IBC - inter-blockchain communication protocol - https://ibcprotocol.dev/

The interchain implements a modular architecture with two blockchain classes: hubs and zones.

> Zones are heterogeneous blockchains carrying out the authentication of accounts and transactions, the creation and distribution of tokens, and the execution of changes to the chain. Hubs are blockchains designed to connect the so-called zones. Once a zone connects to a hub through an IBC connection, it gets automatic access to the other zones connected to that hub. At this point, data and value can be sent and received between the zones without risk of, for example, double-spending tokens. This helps reduce the number of chain-to-chain connections that need to be established for interoperability.

* IBC can connect to non Tendermint-based chains
    * Fast finality consensus chains can connect via adapting the IBC interface
    * Probabilistic finality chains can connect via a peg zone, which is a fast finality proxy of the probabilistic finality chain

[Ignite CLI](https://docs.ignite.com/)

* Tool used to build, test, and launch a chain



[Ethermint](https://github.com/tharsis/ethermint) - extend the Cosmos SDK to use the EVM as a Cosmos SDK module. Ethermint docs [here](https://github.com/cosmos/ethermint)


