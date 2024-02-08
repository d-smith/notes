# Cosmos

Developer Portal - https://tutorials.cosmos.network/

Some history

* 2008 - Bitcoin
* 2013 - Ethereum - expansion of decentralized peer to peer networks to handle more uses cases via smart contract functionality

Challenges with public, general purposes blockchains:

 * Low flexibility for devs
 * Speed - transactions speed
 * Throughput
 * Scalability
 * State finality -  Finality describes whether and when committed blocks with transactions can no longer be reverted/revoked. It is important to differentiate between probabilistic and absolute finality.
 * Sovereignty

Interchain network - founded by Tendermint - https://tendermint.com/

Cosmos whitepaper - foundation of the interchain network - https://v1.cosmos.network/resources/whitepaper

The Interchain

> The interchain is a network of independent blockchains, which are:
>
> * All powered by consensus algorithms with Byzantine Fault-Tolerance (BFT).
> * All connected through the Inter-Blockchain Communication Protocol (IBC), which enables value transfers, token transfers, and other communication between chains, all without the need to involve exchanges or make compromises regarding the sovereignty of each chain.
>
> The interchain is also a blockchain ecosystem complete with protocols, SDK, tokens, wallets, applications, repositories, services, and tools.

Interchain ecosystem - https://cosmos.network/ecosystem/apps/?ref=cosmonautsworld

Cosmos SDK - used to create new application specific blockchains.

Cosmos SDK - object capability model - https://docs.cosmos.network/v0.45/core/ocap.html

Cosmos SDK presentation - https://youtu.be/1_ottIKPfI4

App-Specific Blockchain Architecture - https://tutorials.cosmos.network/academy/2-cosmos-concepts/1-architecture.html

IBC - inter-blockchain communication protocol - https://ibcprotocol.dev/

The interchain implements a modular architecture with two blockchain classes: hubs and zones.

> Zones are heterogeneous blockchains carrying out the authentication of accounts and transactions, the creation and distribution of tokens, and the execution of changes to the chain. Hubs are blockchains designed to connect the so-called zones. Once a zone connects to a hub through an IBC connection, it gets automatic access to the other zones connected to that hub. At this point, data and value can be sent and received between the zones without risk of, for example, double-spending tokens. This helps reduce the number of chain-to-chain connections that need to be established for interoperability.

* IBC can connect to non Tendermint-based chains
    * Fast finality consensus chains can connect via adapting the IBC interface
    * Probabilistic finality chains can connect via a peg zone, which is a fast finality proxy of the probabilistic finality chain

[Ignite CLI](https://docs.ignite.com/)

* Tool used to build, test, and launch a chain



[Ethermint](https://github.com/tharsis/ethermint) - extend the Cosmos SDK to use the EVM as a Cosmos SDK module. Ethermint docs [here](https://github.com/cosmos/ethermint)

## Native Token, Staking

https://tutorials.cosmos.network/academy/1-what-is-cosmos/4-atom-staking.html

[ATOM](https://www.coingecko.com/en/coins/cosmos-hub) - native token of the Cosmos Hub


Wallet used in tutorial - [Keplr](https://www.keplr.app/)

## Cosmos Concepts

### A Blockchain App Architecture

https://tutorials.cosmos.network/academy/2-cosmos-concepts/


Tendermint

* Accelerate blockchain dev with prebuilt networking and consensus layers
    *  peer discovery, block propagation, consensus, and transaction finalization

[CometBFT](https://docs.cometbft.com/v0.37/) - BFT state machine replication (SMR)

https://arxiv.org/abs/1807.04938

CometBFT packages the networking and consensus layers of a blockchain and presents an interface to the application layer, the Application Blockchain Interface (ABCI).

CometBFT provides security guarantees, including the following:

* Forks are never created, provided that at least half the validators are honest.
* Strict accountability for fork creation allows determination of liability.
* 

Transactions are finalized as soon as a block is created.

There are at least two broad approaches to application-level concerns using blockchains:

1. Create an application-specific blockchain where everything that can be done is defined in the protocol.
2. Create a programmable state machine and push application concerns to a higher level, such as bytecode created by compilers interpreting higher-level languages.


EVM is an example of approach 2
Cosmos supports approach 1

Cosmos SDK - build the application blockchain on top of CometBFT

* Also the Cosmos SDK provides a rich set of modules that address common concerns such as governance, tokens, other standards, and interactions with other blockchains through the Inter-Blockchain Communication Protocol (IBC).

S -> apply(t) -> S'

* S - state
* t - transaction
* S' - new state

## Accounts

Cosmos uses BIP39 for key reconstruction and BIP44 for derivation paths.

Cosmos SDK - keyring

Key schemes

* secp256k1 as implemented in the SDK's crypto/keys/secp256k1 package: is the most common and the same one used for Bitcoin.
* secp256r as implemented in the SDK's crypto/keys/secp256r1 package.
* tm-ed25519 as implemented in the SDK's crypto/keys/ed25519 package: is supported only for consensus validation.

## Dev Environment Setup

https://tutorials.cosmos.network/tutorials/2-setup/

## Run a Node, API, and CLI

[SimApp](https://github.com/cosmos/cosmos-sdk/tree/main/simapp) -  an application built using the Cosmos SDK for testing and educational purposes.

> The Cosmos SDK repository contains a folder called simapp (opens new window). In this folder you can find the code to run a simulated version of the Cosmos SDK, so you can test commands without actually interacting with your chain. The binary is called simd and you will be using it to interact with your node.

Interchain Academy Playlist - [here](https://www.youtube.com/playlist?list=PLE4J1RDdNh6sTSDLehUpp7vqvm2WuFWNU  )


Booting up a local chain

1. Clean out the private directory
2. Initialize a new chain genesis block
3. Prepare an account
4. Make genesis account a validator
5. Start the chain

### Clean Out Private Directory

Note - after installing and building the Cosmos SDK we can work with simd and a local chain in the private subdirectory. This subdirectory is gitignored.

Prior to working there we can clean it out, e.g.

 ```
 rm -rf ./private/.simapp
 ```

### Initialize a New Chain Genesis Block

```
./build/simd init demo \
    --home ./private/.simapp \
    --chain-id learning-chain-1
```

You can inspect the genesis file with:

```
cat ./private/.simapp/config/genesis.json
```


```
{
   "app_message":{
      "auth":{
         "accounts":[
            
         ],
         "params":{
            "max_memo_characters":"256",
            "sig_verify_cost_ed25519":"590",
            "sig_verify_cost_secp256k1":"1000",
            "tx_sig_limit":"7",
            "tx_size_cost_per_byte":"10"
         }
      },
      "authz":{
         "authorization":[
            
         ]
      },
      "bank":{
         "balances":[
            
         ],
         "denom_metadata":[
            
         ],
         "params":{
            "default_send_enabled":true,
            "send_enabled":[
               
            ]
         },
         "supply":[
            
         ]
      },
      "capability":{
         "index":"1",
         "owners":[
            
         ]
      },
      "crisis":{
         "constant_fee":{
            "amount":"1000",
            "denom":"stake"
         }
      },
      "distribution":{
         "delegator_starting_infos":[
            
         ],
         "delegator_withdraw_infos":[
            
         ],
         "fee_pool":{
            "community_pool":[
               
            ]
         },
         "outstanding_rewards":[
            
         ],
         "params":{
            "base_proposer_reward":"0.010000000000000000",
            "bonus_proposer_reward":"0.040000000000000000",
            "community_tax":"0.020000000000000000",
            "withdraw_addr_enabled":true
         },
         "previous_proposer":"",
         "validator_accumulated_commissions":[
            
         ],
         "validator_current_rewards":[
            
         ],
         "validator_historical_rewards":[
            
         ],
         "validator_slash_events":[
            
         ]
      },
      "evidence":{
         "evidence":[
            
         ]
      },
      "feegrant":{
         "allowances":[
            
         ]
      },
      "genutil":{
         "gen_txs":[
            
         ]
      },
      "gov":{
         "deposit_params":{
            "max_deposit_period":"172800s",
            "min_deposit":[
               {
                  "amount":"10000000",
                  "denom":"stake"
               }
            ]
         },
         "deposits":[
            
         ],
         "proposals":[
            
         ],
         "starting_proposal_id":"1",
         "tally_params":{
            "quorum":"0.334000000000000000",
            "threshold":"0.500000000000000000",
            "veto_threshold":"0.334000000000000000"
         },
         "votes":[
            
         ],
         "voting_params":{
            "voting_period":"172800s"
         }
      },
      "mint":{
         "minter":{
            "annual_provisions":"0.000000000000000000",
            "inflation":"0.130000000000000000"
         },
         "params":{
            "blocks_per_year":"6311520",
            "goal_bonded":"0.670000000000000000",
            "inflation_max":"0.200000000000000000",
            "inflation_min":"0.070000000000000000",
            "inflation_rate_change":"0.130000000000000000",
            "mint_denom":"stake"
         }
      },
      "params":null,
      "slashing":{
         "missed_blocks":[
            
         ],
         "params":{
            "downtime_jail_duration":"600s",
            "min_signed_per_window":"0.500000000000000000",
            "signed_blocks_window":"100",
            "slash_fraction_double_sign":"0.050000000000000000",
            "slash_fraction_downtime":"0.010000000000000000"
         },
         "signing_infos":[
            
         ]
      },
      "staking":{
         "delegations":[
            
         ],
         "exported":false,
         "last_total_power":"0",
         "last_validator_powers":[
            
         ],
         "params":{
            "bond_denom":"stake",
            "historical_entries":10000,
            "max_entries":7,
            "max_validators":100,
            "unbonding_time":"1814400s"
         },
         "redelegations":[
            
         ],
         "unbonding_delegations":[
            
         ],
         "validators":[
            
         ]
      },
      "upgrade":{
         
      },
      "vesting":{
         
      }
   },
   "chain_id":"learning-chain-1",
   "gentxs_dir":"",
   "moniker":"demo",
   "node_id":"7784967d06ce74ad61bc266bebb7aa99034a8423"
}
```

### Prepare an Account


Inspect keys - initial keyring is of course empty:

```
./build/simd keys list \
    --home ./private/.simapp \
    --keyring-backend test
```


Create an account, e.g. alice:

```
    ./build/simd keys add alice \
        --home ./private/.simapp \
        --keyring-backend test
```

Staking denomination

```
grep bond_denom ./private/.simapp/config/genesis.json
```

Fund alice with some tokens.

```
./build/simd add-genesis-account alice 100000000stake \
    --home ./private/.simapp \
    --keyring-backend test
```

Check the balance in the genesis file:

```
grep -A 10 balances ./private/.simapp/config/genesis.json
```
### Make Genesis Account a Validator


Stake some of Alice's tokens in a genesis transaction.

```
./build/simd gentx alice 70000000stake \
    --home ./private/.simapp \
    --keyring-backend test \
    --chain-id learning-chain-1
```

Collect the genesis transactions:

```
./build/simd collect-gentxs \
    --home ./private/.simapp
```

### Start the Chain

```
./build/simd start \
    --home ./private/.simapp
```

Once the chain is started we can try it out by sending a transaction.

```
export alice=$(./build/simd keys show alice --address \
    --home ./private/.simapp \
    --keyring-backend test)

./build/simd query bank balances $alice


export bob=cosmos1ytt4z085fwxwnj0xdckk43ek4c9znuy00cghtq

./build/simd query bank balances $bob

./build/simd tx bank send $alice $bob 10stake \
    --home ./private/.simapp \
    --keyring-backend test \
    --chain-id learning-chain-1


export txhash=<hash from above>

./build/simd query tx $txhash

./build/simd query bank balances $bob
```

Cosmwasm 

* Write smart contracts to target an existing chain - ignite is for creating new chains.
* Cosmwasm Acadegy - see [here](https://academy.cosmwasm.com/learn/smart-contracts)
