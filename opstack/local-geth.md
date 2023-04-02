geth -miner.gaslimit 12000000 \
--http \
--http.port 9545 \
--http.api personal,eth,net,web3,debug \
--allow-insecure-unlock \
--rpc.allow-unprotected-txs \
--http.vhosts '*,localhost,host.docker.internal' \
--http.corsdomain '*' \
--http.addr "0.0.0.0" \
--dev \
--dev.period 13 \
--nodiscover --maxpeers 0 --mine \
--miner.threads 1 \
--ignore-legacy-receipts \
--datadir ./datadir \
--authrpc.port 9551

 geth attach ./datadir/geth.ipc

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



 eth.sendTransaction({from: eth.accounts[0], to: '0xc75d791ad01d7411acbb98e892448ce8c609092d', value: web3.toWei(5, "ether")})
eth.sendTransaction({from: eth.accounts[0], to: '0xd663cbb4ffbcbb8e25c81f22383d73c37c6bbb3c', value: web3.toWei(5, "ether")})
eth.sendTransaction({from: eth.accounts[0], to: '0x9ff3f67a61041829569e244579adf8d049ccd291', value: web3.toWei(5, "ether")})
eth.sendTransaction({from: eth.accounts[0], to: '0x590f96f352415cab6f07cb6f2871e6aaf3ce2c51', value: web3.toWei(5, "ether")})



cast block 3 --rpc-url http://localhost:9545 | grep -E "(timestamp|hash|number)"
hash                 0x3bb1a1955de57d673ee6853f4a95fe890c5999218bbc07cce92282d3d9402630
number               3
timestamp            1680450880

What is I shutdown and restart? Same result... excellent.

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


local batcher

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
    --l1-eth-rpc=http://localhost:9545 \
    --private-key=f5c7a01c600701d000ef4d847029d41ebb5ed91fdb685b8888ba7d87d1f087f2

Works

How to shutdown...

Stop the batcher

curl -d '{"id":0,"jsonrpc":"2.0","method":"admin_stopBatcher","params":[]}' \
    -H "Content-Type: application/json" http://localhost:8548 | jq

Then stop the batcher process, op-node, op-geth

Restart in the same order