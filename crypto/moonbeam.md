# Moonbeam

Run a node locally

```
 docker pull purestake/moonbeam:v0.29.0

 docker run --rm --name moonbeam_development -p 9944:9944 -p 9933:9933 \
purestake/moonbeam:v0.29.0 \
--dev --ws-external --rpc-external
```

Connect a block explorer - see explore section in https://docs.moonbeam.network/builders/get-started/networks/moonbeam-dev/


Hardhat - see https://docs.moonbeam.network/builders/build/eth-api/dev-env/hardhat/