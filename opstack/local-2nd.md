# OP Stack - Local L1

## Local L1


$ anvil --fork-url $GOERLI_URL --fork-block-number 871635 -p 9545 --block-time 10

Nope - swith to anvil -p 9545



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

export ACCT1PK=0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80 # Anvil default account 0
cast send --rpc-url http://localhost:9545 --private-key $ACCT1PK 0xc75d791ad01d7411acbb98e892448ce8c609092d --value 1ether
cast send --rpc-url http://localhost:9545 --private-key $ACCT1PK 0xd663cbb4ffbcbb8e25c81f22383d73c37c6bbb3c --value 1ether
cast send --rpc-url http://localhost:9545 --private-key $ACCT1PK 0x590f96f352415cab6f07cb6f2871e6aaf3ce2c51 --value 1ether

Network config

From before:

hash                 0xdf00adf5e973eec33c2493e1e1deab9dc541e4f5d96e7134cd0e20346607ed50
number               8716288
timestamp            1679758272

cast block finalized --rpc-url http://localhost:9545 | grep -E "(timestamp|hash|number)"
hash                 0xed60a5132adfd7188c0f7e17c5336567900a36b95743f721bc2a51a5da181b94
number               0
timestamp            1680292665


anvil.json

```
{
  "numDeployConfirmations": 1,

  "finalSystemOwner": "0xc75d791ad01d7411acbb98e892448ce8c609092d",
  "portalGuardian": "0xc75d791ad01d7411acbb98e892448ce8c609092d",
  "controller": "0xc75d791ad01d7411acbb98e892448ce8c609092d",

  "l1StartingBlockTag": "0xed60a5132adfd7188c0f7e17c5336567900a36b95743f721bc2a51a5da181b94",

  "l1ChainID": 5,
  "l2ChainID": 42069,
  "l2BlockTime": 2,

  "maxSequencerDrift": 600,
  "sequencerWindowSize": 3600,
  "channelTimeout": 300,

  "p2pSequencerAddress": "0x9ff3f67a61041829569e244579adf8d049ccd291",
  "batchInboxAddress": "0xff00000000000000000000000000000000042069",
  "batchSenderAddress": "0x590f96f352415cab6f07cb6f2871e6aaf3ce2c51",

  "l2OutputOracleSubmissionInterval": 120,
  "l2OutputOracleStartingBlockNumber": 0,
  "l2OutputOracleStartingTimestamp": 1680292665,

  "l2OutputOracleProposer": "0xd663cbb4ffbcbb8e25c81f22383d73c37c6bbb3c",
  "l2OutputOracleChallenger": "0xc75d791ad01d7411acbb98e892448ce8c609092d",

  "finalizationPeriodSeconds": 12,

  "proxyAdminOwner": "0xc75d791ad01d7411acbb98e892448ce8c609092d",
  "baseFeeVaultRecipient": "0xc75d791ad01d7411acbb98e892448ce8c609092d",
  "l1FeeVaultRecipient": "0xc75d791ad01d7411acbb98e892448ce8c609092d",
  "sequencerFeeVaultRecipient": "0xc75d791ad01d7411acbb98e892448ce8c609092d",

  "gasPriceOracleOverhead": 2100,
  "gasPriceOracleScalar": 1000000,

  "governanceTokenSymbol": "OP",
  "governanceTokenName": "Optimism",
  "governanceTokenOwner": "0xc75d791ad01d7411acbb98e892448ce8c609092d",

  "l2GenesisBlockGasLimit": "0x1c9c380",
  "l2GenesisBlockBaseFeePerGas": "0x3b9aca00",
  "l2GenesisRegolithTimeOffset": "0x0",

  "eip1559Denominator": 50,
  "eip1559Elasticity": 10
}
```


hardhat config

anvil: {
      url: process.env.L1_RPC,
      accounts: [process.env.PRIVATE_KEY_DEPLOYER || ethers.constants.HashZero],
      live: true,
    },



go run cmd/main.go genesis l2 \
    --deploy-config ../packages/contracts-bedrock/deploy-config/anvil.json \
    --deployment-dir ../packages/contracts-bedrock/deployments/anvil/ \
    --outfile.l2 genesis.json \
    --outfile.rollup rollup.json \
    --l1-rpc http://localhost:9545

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


Hmmmm.... ran into

ERROR[04-01|08:46:46.341] Error initializing the rollup node       err="incorrect L1 RPC chain id 5, expected 31337"
ERROR[04-01|08:46:46.343] Unable to create the rollup node         error="incorrect L1 RPC chain id 5, expected 31337"
CRIT [04-01|08:46:46.343] Application failed                       message="incorrect L1 RPC chain id 5, expected 31337"

What is we run anvil with --chain-id 5?

* restart
* fund the accounts again


* add chain id

anvil: {
      url: process.env.L1_RPC,
      accounts: [process.env.PRIVATE_KEY_DEPLOYER || ethers.constants.HashZero],
      live: true,
      chainId: 5
    },


* get hash and timestamp


$ cast block finalized --rpc-url http://localhost:9545 | grep -E "(timestamp|hash|number)"
hash                 0x29b67a46a093e18a2c0f7487148e991f939f22d76021078ff70e2008aa8259c2
number               0
timestamp            1680364561

Update the anvil config file

Redeploy L1 contracts

Re initialize geth and genesis block

Clean out datadir for op-geth

Back to the same error we see from before, e.g.

ERROR[04-01|13:21:03.509] failed to fetch runtime config data      err="failed to fetch unsafe block signing address from system config: failed to fetch proof of storage slot 0x65a7ed542fb37fe237fdfbdd70b31

Try with local geth?

