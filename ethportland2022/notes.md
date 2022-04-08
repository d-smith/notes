# ETH Portland 2022

https://2022.ethportland.com/

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

## Day 2

### Ethereum Security Incidents & Lessons Learned

Michael Lewellin - Open Zepplin

Open zep

* contracts, audits, defender

The DAO Hack - 2016

* [Analysis of the DAO Exploit](https://hackingdistributed.com/2016/06/18/analysis-of-the-dao-exploit/)
* [Reentrancy After Istabbul](https://blog.openzeppelin.com/reentrancy-after-istanbul/)

* must account for malicious calls from external contracts and accounts
* update the state and balances of a contract before sending funds or perf ext calls
* forming a protocol to reverse attacks is possible but heavy damage to community
* better security standards and more secure solidity implementations are needed to prevent exploits

crypto-comics.com

OpenZepplin contracts

* token standard, extensions (pausable, snapshots, etc), Proxies, Access Control, Utilities, Governance, Updates Plugins, Security models (reentrency guard, pull payments, etc)


Parity Multisig Hack

* [The Multi-sig Hack: a Postmortum](https://www.parity.io/blog/the-multi-sig-hack-a-postmortem)
* [A Postmortem on the Parity Multi-Sig Library Self-Destruct](https://www.parity.io/blog/a-postmortem-on-the-parity-multi-sig-library-self-destruct/)
* [The Parity Wallet Hack Explained](https://blog.openzeppelin.com/on-the-parity-wallet-multisig-hack-405a8c12e8f7/)



pro tip - recurrent audits with clear git logs are paramount

QA

* Use [NatSpec](https://docs.soliditylang.org/en/v0.8.10/natspec-format.html)
* hard hat, boundary for testing

openzep security-audits readyness-guide

Yearn yDAI Vault Exploit

* [Yearn Disclosure](https://github.com/yearn/yearn-security/blob/master/disclosures/2021-02-04.md)
* [Inside the Yearn yDAI Hack](https://halborn.com/explained-the-yearn-v1-ydai-hack-feb-2021/)


* Flash loan attack - uncollateralized defi lending where liquidity is borrow instantly and repaid in the same transaction block (useful for scenarios like arbitrage, first offered by aave 2020)


* Economics attacks
* dependencies on DEXs and other on-chain pricing can expose a protocol to flash loan attacks
* consider multi-block flows

[OpenZeppelin Defender](https://openzeppelin.com/defender/) - security ops

[Forta Network](https://forta.org/) - real time threat detection

* decentralized, community based security network
* explorer.forta.network

Smart contract security

* pre-deplyment (libs, testing & docs, audit)
* post (bug bounties, real time monitoring, incident response management)

L2Beat - https://l2beat.com/

### Gas free ECR20 Delegation

a governance use case with OZ Defenderr

Delegate with signature

Uses EIP712 Structured Data Signature

Defender - Relayer manage keys, sigs
AutoTask - lambda

gasless-delegator

https://github.com/ernestognw/gasless-delegator

@smpalladino - youtube video for handling broader meta transactions

zpl.in/join

### Let anyone and everyone run your DAO

UMA - optiistic oracle that can provide and verify any arbitrary data

Oracle 

* trusted or trustless source of information that smart contracts can be used to trigger events
* trend is to decentralized versions

price feed oracles

* verify correctness in advance
* good for getting processes on-chain
* scalability problems, ineffiecient, lacks flexibility

optimistic oracle: enforce correctness after dispute

* requestor, proposer,disputer network
dispute resolution proces
a documented methodology of truth
economic guarantees - proposers post a bond, lose posted if found incorrect

Use cases

* prediction markets, defi insurance, optimistic governance, rewards as a service, kpi options,  success tokens
* Polymarket
* Sherlock - defi insurance
* Across - cross team bridge
* Shapeshift - success token
* Boba - kpi options

opt gov

* publish rules off chain
* ref the rules in a proposer contract linked to the oracle
* anyone can propose, anyone can dispute
* oracle checks if the tranasction follow the rules

Gnosis Safe - multisig wallet used by DAO trasuries
Zodiac - dev modules to add new func to Gnosis Safe

* Optimistic Oracle Zodiac Module - Uma proposal proposal
 
 IPFS - use this to keep the rules online

 * https://ipfs.io/

### Onramping Mainstream Users Globally to Web3

Transak - Fiat On-ramp

What about the retail user?

* find the platform
* buy cryoto
* create a new wallet and transfer crypto
* app

Transak looks to simplify

Case study - Zed Run

Model - let devs and builders (buidler) dictate the experience, transak handles all the LRC stuff

register dashboard.transak.com, integrated.transak.com, test/stage/deploy

Christian Flagg


### Cryptosat

trusted party in space for blockchain protocols

https://cryptosat.io/

Cubesat device

Use cases

* random beacon
* verifiable delay
* trusted setup/ceremonies

DRAND - distributed random beacon

crytosatboat - send back a 'cert' signed in space
cryptosat simulator https://simulator.cryptosat.io/#/getting-started/overview

github - https://github.com/cryptosat

SpaceTEE - https://arxiv.org/abs/1710.01430

### Not Quite Water Under the Bridge: Review of Cross-Chain Bridge Hacks

[Quantstamp](https://quantstamp.com/)

Bridge - move assets from one chain to another

* THey don't really move...
    * Customdian locks the assets
    * Communicator to talk to the other chain
    * Debt issuer -issue, burn on withdra

    asset custodian
    debt issuer

    off chain - communicator (oracle)

attack surfaces

* custodian
    * call relay attack
    * transaction replay attack, e.g. replay modified proof of burn multiple times releasting funds multiple time
* debt issuers
    * fake verification attack
* communicator
    * fake events attack (pollute the data source of an oracle)
* interfaces
    * infinite approval attack (not bridge specific), typically via unsanitized inputs
    * permit attack
* network
    * launch a 51% attack on the L1 chain

crypto51.app

### The Student Coffee Shop DAO

JosephALChami

Figment - nodes, validators

incentives & slashing

### Tips on Pitching

Race Capital

Chris McCann

Goals

* Get next meeting - starts with a 30 min zoom, ask questions, 
* Understand fit - is there a good fit?
* Get to terms - get the SAFE-T


Pitch Narrative structure - can you do this in 10 - 15 minutes?

* team
* problem
* product
* demo - idea realized via an mvp
* traction - your numbers
* big picture 
* competition (comparables)
* investment - your ask
* what else are you looking for?

Example investment flow

* intro call (30m)
* second meeting (45 -1)
* basic due filigence
* third meeting
* megtiate on terms 
* term sheet
* final dd
* countersign and wire

Tips

* don't share your full life story
* demo speaks 1000 words
* demonstrable traction
* be clear on customer segment
* be concise, leave time for Q&A
* don't induce fomo
* leading/following - who will lead with the funding who will follow
* terms - amount raided, valuation, vesting, structure
* pick the right partner

Book - Venture Deals

### Scaling dApps: From Hackathons to Millions of Users

daws.eth - earnifi

launch problems

* crowded
* confusing
* unscalable

want: easy to use, stand out, welcoming

first try - don't start with a metamask wallet link requirement
* one box, enter address see if there's an airdrop

* make it immediately usable
* super simple - example was one box no button, take down every single barrier on our page (e.g. metamask bounce rate 80%)
* find fans - twitter

first try 
* Ugly
* Not stick - nothing to come back

next: images - click to claim


WIth each iteration - what's good, what's bad

next: get notify when there are new airdrops
* pink - playful, standouts
* good ux - 

Hackathon - ETHGlobal - online

Next - what was missing?
* ENS support... but resolve them without metamask
* Infura

design stuff that is fast and fast for you to do

getting some traction...
* deal with copyright
* income

How to bring in income?
* paywall - but... only put it up if there's a claimable airdrop

UX/premium tip - social validation (e.g. xxx paid users)

Now we 100K visits...

* performance - backend, web bundle
* education

ethers

Split off code and open source it where you can.

isomorphic typescript

Avoid

* discord
* overly-needy customers
* tech debt

## Misc

mirror protocol whitepaper - https://docsend.com/view/kcsm42mqiyu5t6ej

mirror participants - minter - mint mAssets, take the opposite position of the assets' natural direction




