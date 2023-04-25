# Geth Notes

Notes from [geth docs](https://geth.ethereum.org/docs)

## Intro

Account overview [here](https://ethereum.org/en/developers/docs/accounts/)

* Does not cover account abstraction

clef - tool to manage account key pairs

* create accounts
* clef uses the private keys stored in the keystore to sign transactions
    * Can start it as a process for signing transactions
* can use geth as a terminal via `geth attach`
    * List accounts via eth.accounts
    * Get balance - e.g. `web3.fromWei(eth.getBalance('0xca57F3b40B42FCce3c37B8D18aDBca5260ca72EC'), 'ether');`
    * Send eth - e.g.
    
    ```
    eth.sendTransaction({
    from: '0xca57f3b40b42fcce3c37b8d18adbca5260ca72ec',
    to: '0xce8dba5e4157c2b284d8853afeeea259344c1653',
    value: web3.toWei(0.1, 'ether')
    });
    ```
    
    * Get details on transactions via `eth.getTransaction`

## Install

Verify the download on ubuntu via md5sum

Ubuntu [install](https://geth.ethereum.org/docs/getting-started/installing-geth#ubuntu-via-ppas)

## Tutorial

THe tutorial in the current docs are dated - they will not work as now a consensus client needs to run alongside the execution client.

The [prysm docs](https://docs.prylabs.network/docs/install/install-with-script/#step-5-run-a-beacon-node-using-prysm) outline how to do this.

What I did:

1. Generate the jwt using `./prysm.sh beacon-chain generate-auth-secret`
2. Downloaded the genesis file as [raw from github](https://github.com/eth-clients/merge-testnets/blob/main/sepolia/genesis.ssz?raw=true) - had to fix the name.
3. In the dir with the secret, ran geth.
```
geth --sepolia --http --http.api eth,net,engine,admin --authrpc.jwtsecret ./jwt.hex
```
4. Ran prysm
```
 ./prysm.sh beacon-chain --execution-endpoint=http://localhost:8551 --sepolia --suggested-fee-recipient=0x01234567722E6b0000012BFEBf6177F1D2e9758D9 --jwt-secret=jwt.hex --genesis-state=genesis.ssz
 ```

 ## Fundamentals

 ### Node Architecture

 A node consists of two clients

 * Execution client - transaction handling, transaction gossip, state management, and EVM support
 * Consensus client - block building, block gossiping, handling consensus logic
    * Can add a validator to the consensus client to handle attestations and block proposals
    * Running a validator makes the node eligible to propose new blocks

### Ports

* 8545 - allow traffic from trusted machines, block others
* 30303 - udp and tcp, for peer to peer discovery and communications.

### Sync Modes

* Snap - start from relatively recent block, sync from there to the head of the chain, as opposed to rebuilding state starting from the genesis block.
* Full - block by blok starting from genesis

Archive nodes - keep all data, never pruned, good for lookups of historical data, e.g. etherscan
Light nodes - process only headers, not block data, keeps small amount of state. Rely heavily on data served by altruistic full nodes. Not currently working on PoS.

Note that consensus logic and block propogation is handled by the consensus client - syncing is a process shared between execution and consensus client. Header to start syncing from is obtained by the consensus client.

Two ways for consensus client to find header to sync to:

* [Optimistic sync](https://github.com/ethereum/consensus-specs/blob/dev/sync/optimistic.md) - download blocks before the execution client has validated them.
* Checkpoint sync - start from checkpoint provided by a trusted source

### Databases

* Data storage split between recent blocks and state data kept in quick-access storage, older blocks and receipts kept in a freezer database.

Recent blocks - kept in LevelDB, migrate to freezer db over time as they age out.

Note the state database can be rebuilt using data from the freezer - passing --datadir and --removedb can start the process (remove only the state db not freezer or LES database)




