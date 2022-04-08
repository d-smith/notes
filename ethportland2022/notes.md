# ETH Portland 2022

## Day 1

Discord channel - shadowy frenz

[UMA Project](https://umaproject.org/)
[Transak](https://transak.com/)
[Skale](https://skale.network/[])
[Distopia Labs](https://dystopialabs.com/)
[DFX](https://dfx.finance/)
[Hummingbot](https://github.com/hummingbot/hummingbot)
Cryptosat
Rally
Harmony


### Defense Against the Dark Arts

Coinbase

John Naulty

Measuring and improving the security posture of open source projects

Hashbang - digital privacy
OpenSSF - open supply chain security foundation

why measure the security posture of your supply chain?

The nebraska problem - xkcd 2347

Loss - undesried + unplanned event w neg impact
Safety - prevent loss unintential action benevelont actors
Security - intentional actions - malevelant actors

How to measure security posuture with OpenSSF scorecard

* Look at google's deps.dev
* Run local, enable via github actions

Important measures

* binary-artifacts
* branch-protection
* code-review
* contributors
* dependency-update-tool - dependabot may motivate you to update to something with a problem
* maintained
* pinned-dependencies
* Signed releases
* vulnerabilities
* security policy

How to improve

* visibility and risk monitoring
    * Evaluate risk against your risk appetite
* engage with open source projects (go upstream)
    * the 4 bees - be resourceful, mindful, clear, polite

Why Improve?

* Communal effort to identiry and remove dependency risks
    * identify your risks
    * act
    * help others identify and reduce risk

openssf.org
hashbang.sh



Matrix Protocol

### How to Deploy on Skale

Chadwick Strange 

Ethereum dApps to scale smart contracts with eth natice skidechains

Endpoint - ethportland.skale.network

* Use standard tools
* Network of chains

Randomness Rotation Incentives (160 nodes), 48 validator orgs, $3.5 staked

Scale manager - ask it for a chain, selects 16 nodes at random, that's your chain

* File storage on the chain
* Native bridge for TOA
* Nodes get swapped in and out - eliminate collusion

Blockchains on demand

* on demand - can do 2000 TPS
* SKALEVerse - can customize the bridge capabilities
* Skale hubs - service layer to service multiple chains, echange hub, nft market place, liquidity ramp
* dApp chains and leverage services running in scale hibs

What can you do on skale?

* Gas free transactions with sFUEL - you get a lot of sfeul when your get your chain, hand out to your users
* 160 mill block gas kimir
* 32 KB code side limit (for hackathon)
* Filestorage
* RNG endpoint
* Oracle API
* IMA bridge

Oracle API

* JSON rpc calls, all 16 nodes call the API, consensus used to decide a truthfull results

PRojects

* Ivy Cash - QR code, scan at events, mints an NFT on the skale network in a gas free way
* Clet name service - human readable crypto currency wallet names, etherscan can resolve the name to the address
* Ruby exchange - gas free liqudity exchange

Fast finality - media paywalls, authentications, oracles
No Gas Fees - NFTs, DAOs
High Throughput - Immersive/AR, gaming

$100 Milion USD Value Ecosystem Grant Program

### Bots and Exchanges

What is an exchange - bazaar analogy

* market makers, market takers
* in crypto, digital pure play, an exchange is just code you deploy to the chain

What is a trading bot?

* A process, reads exchange data, makes a decision/executes a transaction
* Bots should be local
    * (But what about a custodial entity)?

How bots interact with exchanges

* Order book exchange - makers - bots, takers = traders
    * market maker bot - fetch order book, refresh orders
* AMM exchange - automated market maker exchange
    * Any amount you want to buy or sell is known, contract does all the work
    * makers = traders
    * takers = bots
    * only way prices can change is arbitrage
    * fetch 3 echange prices
    * buy low sell high?

More bots - mirroring, liquidation, smart order routing, rebalancing, NFTs

Hummingbot white paper - coinalpha.com - 2018

Bridge dex and cex

hummingbot foundation - open source foundation expand ecosystem horizonatally
coinalpha - for-profit company grows ecosystem vertically

Like the relationship between apache kafka and confluent

Hummingbird - Prime prototype 

Mission - open source high frequency trading

skale

https://portland.skalenodes.com/v1/echoing-alrai
wss://portland.skalenodes.com/v1/ws/echoing-alrai
https://portland.skalenodes.com/fs/echoing-alrai
0x7e568a7d6d823

https://trufflesuite.com/boxes/skale-box/

rpc https://portland.skalenodes.com/v1/handsome-enif
network id 0x4a75965a9efef
wallet 0x96f29A5e24D9eE2457D9Fca8245D7F41e33D31BB


### ETH Build

Tip Party
ENS Names

Scaffle-ETH

https://github.com/scaffold-eth/scaffold-eth


speed run ethereum - quests
look at the branches on scaffold-eth
nifty.ink - build using scaffle-eth

punkwallet.io - built using scaffle eth

### Security by Design

Smart contact execution - think about the execution envronment

* All data is publish
* Ethereum - sha3

Transaction model

* local create of transaction
* broadcast it to network
    * miners mine blocks and might include the transaction in the block
    * blocks are scarce, size is limited
    * miners can change the order of transactions
        * front running - miners are in a more priviledge position
        * MEV and flashboats can do that

SoK: transparent dishonesty: front running  - https://arxiv.org/abs/1902.05164

Commitment schemes

* Solution to front running apps
* send commitment transaction with hash of the data
* reveal the data referencing the commitment transation hash


Auditing

* manual code review by experts crucial

Real world example - ethereum name service
on-chain trnsaction to purchase domains

EthRegister

* original did not include the owner

Shayan.es

Survey on Ethereum Security - https://dl.acm.org/doi/fullHtml/10.1145/3391195

Use vscode with solidity and solidity visual developer

ethviewer.live
txstreet.com/v/eth


### Developing Zero Knowledge on Harmony

What is a zero knowledge proof?

* prove to verifier the prover knows the secret without revealing the secret
* completeness
* soundness
* zero knowledge

Uses

* identification
* network privacy - keep identities private but you can prove you were a message sender
* scaling blockchains - do computation on layer 2, do security on layer 1 - proof is smaller than the data
* Two schemes
    * Snark - succint, non-interactive, argument of knowledge
    * Stark - transparent

Harmony - believes ZK on level 2 rolled up on level 1 is the future for scaling

* ZK university ZKU - 7 week intensive course
* https://zku.one/
    * Has a prerequisite course too


### Another Talk

blocknative - prechain layer

@samsun comic polygon onboarding

reactdemo.blocknative.com

py-etherscan-api