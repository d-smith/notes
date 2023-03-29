# OP Stack

Linkage

* [Introducing the OP Stack](https://optimism.mirror.xyz/fLk5UGjZDiXFuvQh6R_HscMQuuY9ABYNF7PI76-qJYs)
* [Optimism Bedrock](https://dev.optimism.io/introducing-optimism-bedrock/)
* [Super Chain](https://optimism.mirror.xyz/2jk3D1Y8-hid8YOCUUa6yXmsyzNCYYyFJP0Nhaey9x0)
* [OP Stack Docs](https://stack.optimism.io/)


Op Stack - Layers

* Governance
* Chain
* L1

Chain Layers

* Consensus
    * Rollup (bedrock)
    * Plasma (WIP)
* Execution
    * EVM (bedrock)
    * Pokemon (?)
* Settlement
    * Governance (bedrock)
    * Fault Proof (wip)
    * zk-Proof (wip)


Modules at each stack conform to an API specification, enabling swappable components.

Chain to chain communication via message passing.

Shared sequencers - cross chain composibility

## Local Instance

git clone https://github.com/ethereum-optimism/optimism.git
cd optimism

cd ops

docker compose pull

Then as per the read me, `make up` or `make down`

## Getting Started

Updated stack docs - this is the turtorial - https://stack.optimism.io/docs/build/getting-started/

nvm use 16.19.0
git clone https://github.com/ethereum-optimism/optimism.git op-stack

yarn install
yarn build

rekey output - see [gist](https://gist.github.com/d-smith/235222ac3a245ffd373073464d766c63) 


L1 Block for rollup starting point

For local dev, can run anvil from a predetermined point, e.g.

```
anvil --fork-url $GOERLI_URL --fork-block-number 871635
```

Then you can reliably create a rollup starting point, e.g. the following output is reproducable:

```
$ cast block finalized --rpc-url http://localhost:8545 |  grep -E "(timestamp|hash|number)"
hash                 0xdf00adf5e973eec33c2493e1e1deab9dc541e4f5d96e7134cd0e20346607ed50
number               8716288
timestamp            1679758272
```

Fund accounts 

Admin, Proposer, and Batcher need eth funds

```
cast balance 0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266
cast send --private-key $ACCT1PK $ADMIN --value 1ether
cast balance $ADMIN

cast send --private-key $ACCT1PK $PROPOSER --value 1ether
cast balance $PROPOSER

cast send --private-key $ACCT1PK $BATCHER --value 1ether
cast balance $BATCHER
```

Deploy config

* ADMIN, PROPOSER, BATCHER, SEQUENCER as per rekey addresses
* BLOCKHASH, TIMESTAMP as above
* edit deploy-config/getting-started.json

Running that errors out after the warning.

0x666c27ce5c8e5ef69d81e0a8197c4aa072298b3a
WARNING: setting OptimismPortal.GUARDIAN to 0x666c27ce5c8e5ef69d81e0a8197c4aa072298b3a and it has no code

What if we use the first 'named' account for the anvil local?

0x666... => 0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266

Doing so allows the deployment to local to complete with a warning, e.g.

WARNING: setting OptimismPortal.GUARDIAN to 0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266 and it has no code

Sidebar - proxy troubleshooting builds

Look for the package.json used for the yarn hardhat build, add the --verbose flag to the hardhat compile command. Now when you run the build it will tell you what it is trying to download and where it wants to cache it. YOu can then do the downloads manually to get on with the build.

