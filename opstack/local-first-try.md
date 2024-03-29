## Local Install

git clone https://github.com/ethereum-optimism/optimism.git
cd optimism

cd ops

docker compose pull

Then as per the read me, `make up` or `make down`

### Getting Started

Updated stack docs - this is the turtorial - https://stack.optimism.io/docs/build/getting-started/

nvm use 16.19.0
git clone https://github.com/ethereum-optimism/optimism.git op-stack

yarn install
yarn build

rekey output - see [gist](https://gist.github.com/d-smith/235222ac3a245ffd373073464d766c63) 


### Deploy L1 contects

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

Note on smart contract deployment

* If you need to restart and redeploy to L1 you have to clean out your deployments, e.g. `rm -rf deployments/getting-started/` in the build contracts-bedrock directory.

### Generating L2 config files

go run cmd/main.go genesis l2 \
    --deploy-config ../packages/contracts-bedrock/deploy-config/getting-started.json \
    --deployment-dir ../packages/contracts-bedrock/deployments/getting-started/ \
    --outfile.l2 genesis.json \
    --outfile.rollup rollup.json \
    --l1-rpc http://localhost:8545

This generates genesis.json and rollup.json

cp genesis.json ~/code/op-geth
cp jwt.txt ~/code/op-geth

### Initialize op-geth

Sequencer key from rekey output.

### Run op-geth

export SEQADDR=0x45740d200b2e27a62cbe81824337a6930c8463d5

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
	--miner.etherbase=$SEQADDR \
	--unlock=$SEQADDR \
    --http.port 9545

Http port needed to avoid conflict with anvil


### op-node

./bin/op-node --l2=http://localhost:8551 --l2.jwt-secret=./jwt.txt --sequencer.enabled --sequencer.l1-confs=3 --verifier.l1-confs=3 --rollup.config=./rollup.json --rpc.addr=0.0.0.0 --rpc.port=8547 --p2p.disable --rpc.enable-admin --p2p.sequencer.key=$SEQKEY --l1=http://localhost:8545 --l1.rpckind=basic

Hmmmm....does not initialize correctly. Probably need to try straight up instructions before localhost.

==> Try local like this:

anvil --fork-url $GOERLI_URL --fork-block-number 871635 -p 9545