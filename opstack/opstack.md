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

