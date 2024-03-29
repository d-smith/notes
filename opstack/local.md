# op stack - local

## Local L1

 ganache --fork --fork.network goerli -p 9545 --fork.preLatestConfirmations 100

 cast block 8757411 --rpc-url http://localhost:9545 | grep -E "(timestamp|hash|number)"

$  cast block 8757411 --rpc-url http://localhost:9545 | grep -E "(timestamp|hash|number)"
hash                 0x63b58a118e467246b695283fec7b7c80d58ed6687edf62cf46e0121b76f21dfa
number               8757411
timestamp            1680386040

Generate accounts

```
$ npx hardhat rekey
Mnemonic: version color below disease chronic genuine spend blur arrange hover warm violin

Admin: 0xc75d791ad01d7411acbb98e892448ce8c609092d
Private Key: 3ce9715b584490ac0809b8ec7cff24d78e0bf2e5eba22504937a461ce109b44e

Proposer: 0xd663cbb4ffbcbb8e25c81f22383d73c37c6bbb3c
Private Key: 6c7e4305002d4944ec21de3462ad9658b7ba795d1c050b83ae5d098922a977f3

Batcher: 0x590f96f352415cab6f07cb6f2871e6aaf3ce2c51
Private Key: f5c7a01c600701d000ef4d847029d41ebb5ed91fdb685b8888ba7d87d1f087f2

Sequencer: 0x9ff3f67a61041829569e244579adf8d049ccd291
Private Key: 47afecc02e839aed01864c196ad24615d449325c9ebdb1e0641e2105ceb30959
```

Fund some accounts

export ACCT1PK=from ganache # Anvil default account 0
cast send --rpc-url http://localhost:9545 --private-key $ACCT1PK 0xc75d791ad01d7411acbb98e892448ce8c609092d --value 1ether
cast send --rpc-url http://localhost:9545 --private-key $ACCT1PK 0xd663cbb4ffbcbb8e25c81f22383d73c37c6bbb3c --value 1ether
cast send --rpc-url http://localhost:9545 --private-key $ACCT1PK 0x590f96f352415cab6f07cb6f2871e6aaf3ce2c51 --value 1ether

Update anvil.json with the hash and timestamp

Update the chain id, e.g. 

anvil: {
      url: process.env.L1_RPC,
      accounts: [process.env.PRIVATE_KEY_DEPLOYER || ethers.constants.HashZero],
      live: true,
      chainId: 1337
    },

go run cmd/main.go genesis l2 \
    --deploy-config ../packages/contracts-bedrock/deploy-config/anvil.json \
    --deployment-dir ../packages/contracts-bedrock/deployments/anvil/ \
    --outfile.l2 genesis.json \
    --outfile.rollup rollup.json \
    --l1-rpc http://localhost:9545



echo "47afecc02e839aed01864c196ad24615d449325c9ebdb1e0641e2105ceb30959" > datadir/block-signer-key


RUn op-geth

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
	--miner.etherbase=0x9ff3f67a61041829569e244579adf8d049ccd291 \
	--unlock=0x9ff3f67a61041829569e244579adf8d049ccd291



Run op-node

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
	--p2p.sequencer.key=47afecc02e839aed01864c196ad24615d449325c9ebdb1e0641e2105ceb30959 \
	--l1=http://localhost:9545 \
	--l1.rpckind=basic

    Error

    ROR[04-01|18:09:27.310] failed to fetch runtime config data      err="failed to fetch unsafe block signing address from system config: failed to fetch proof of storage slot 0x65a7ed542fb37fe237fdfbdd70b31598523fe5b32879e307bae27a0bd9581c08 at block 0xb55fbc00282419ddb5d90ab54363187b1f82d07db45dd05ceb53c067468fffc9: eth_getProof is not supported on a forked network. See https://github.com/trufflesuite/ganache/issues/3234 for details."


Ok - non forked?

ganache -p 9545 --db .

And
cast block 0 --rpc-url http://localhost:9545  | grep -E "(timestamp|hash|number)"
hash                 0x6606b6ef6152cdaed194834c71ec02716b867a623301a5d7f4835f787262c73d
number               0
timestamp            1680398087