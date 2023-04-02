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


# OP Stack Install Notes

***Instructions work with goerli. To run with local geth see local-geth.md***

20230330

## Build - Source Code

https://stack.optimism.io/docs/build/getting-started/#build-the-source-code

## Generate Some Keys

Admin: 0x666c27ce5c8e5ef69d81e0a8197c4aa072298b3a
Proposer: 0xe41db9746decf7270f023c38dc411d461c5c69fb
Batcher: 0x86c91412b9287b4e45e193507a2ad01292978c3d
Sequencer: 0x45740d200b2e27a62cbe81824337a6930c8463d5
 


Fund the accounts...

On goerli via metamask wallet

To [admin](https://goerli.etherscan.io/tx/0x61d5cde449200719f09fa41bffcb5e8949062d0da4e96e10369d661abb29ca99)
To [proposer](https://goerli.etherscan.io/tx/0xb380f6bbd4c86cfa42b01563e9fca269979842144f6214a4e403a20d7e42780f)
To [batcher](https://goerli.etherscan.io/tx/0x127db084bb4e2083148a17e8fb0f3538da62705668a29df280e0bd8244db551e)

## Configure Network

$ cast block finalized --rpc-url $GOERLI_URL | grep -E "(timestamp|hash|number)"
hash                 0x8dd919043931139443c6a984235340cb6f0cc184cce58fd28966ffda8b364314
number               8745095
timestamp            1680196320

## Deploy L1 contracts

Worked as advertized, but the gas fees are much higher than documented, fund the admin account appropriately.

./build/bin/geth \
	--datadir ./datadir \
	--http \
	--http.corsdomain="*" \
	--http.vhosts="*" \
	--http.addr=0.0.0.0 \
	--http.api=web3,debug,eth,txpool,net,engine \
	--ws \
	--ws.addr=0.0.0.0 \
	--ws.port=8546 \
	--ws.origins="*" \
	--ws.api=debug,eth,txpool,net,engine \
	--syncmode=full \
	--gcmode=full \
	--nodiscover \
	--maxpeers=0 \
	--networkid=42069 \
	--authrpc.vhosts="*" \
	--authrpc.addr=0.0.0.0 \
	--authrpc.port=8551 \
	--authrpc.jwtsecret=./jwt.txt \
	--rollup.disabletxpoolgossip=true \
	--password=./datadir/password \
	--allow-insecure-unlock \
	--mine \
	--miner.etherbase=0x45740d200b2e27a62cbe81824337a6930c8463d5 \
	--unlock=0x45740d200b2e27a62cbe81824337a6930c8463d5


### Run op-node

./bin/op-node \
	--l2=http://localhost:8551 \
	--l2.jwt-secret=./jwt.txt \
	--sequencer.enabled \
	--sequencer.l1-confs=3 \
	--verifier.l1-confs=3 \
	--rollup.config=./rollup.json \
	--rpc.addr=0.0.0.0 \
	--rpc.port=8547 \
	--p2p.disable \
	--rpc.enable-admin \
	--p2p.sequencer.key=$SEQUENCERKEY \
	--l1=$GOERLI_URL \
	--l1.rpckind=alchemy


### Batcher

./bin/op-batcher \
    --l2-eth-rpc=http://localhost:8545 \
    --rollup-rpc=http://localhost:8547 \
    --poll-interval=1s \
    --sub-safety-margin=6 \
    --num-confirmations=1 \
    --safe-abort-nonce-too-low-count=3 \
    --resubmission-timeout=30s \
    --rpc.addr=0.0.0.0 \
    --rpc.port=8548 \
    --rpc.enable-admin \
    --max-channel-duration=1 \
    --target-l1-tx-size-bytes=2048 \
    --l1-eth-rpc=$GOERLI_URL \
    --private-key=$BATCHERKEY

Rollup account - see .secret file

Create a wallet via cast wallet new

And... L1BRIDGEPROXY is in .secretenv
