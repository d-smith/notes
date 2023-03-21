Notes - Truffle Suite Optimism Bridge

https://trufflesuite.com/guides/optimism-bridge-widget/

The docker compose project with an L1/L2 set up appears to have a sequencer

Web 3 Unleashed - [Episode 8](https://www.youtube.com/live/i_yhkHgl4tA?feature=share)


L2 - Ethereum scaling solution

* Rollups are the long term scaling solution
* Move execution off L1 chain into a sequencer - centralized in block production, centralized in ethereum security
* Think of L2 as an app on L1 - contract on L1 that verifies execution on L2 follows the rules.

Rollup Types

* Optimistic roll ups - post all transactions optimistically on L1
    * Verifiers run to check the computation
    * 7 day delay before withdrawals
    * Flexible - no constraints on what can be rolled up
* zkRollups - only posted when txn prover true
    * Instant finality
    * Provers can take a while due to computational constraints
    * Limits today on what can be proven - less flexible

Fraud Proof

* Fault proofs
* Prove each computational step executed correctly

Bridging

* How to get funds from L1 to L2
* Common pattern - bridge holds assets from originating chain, uses a wrapped asset on the target chain

Sequencer 

* Main block producer in the rollup
* Optimism sequencer - fork of geth
* Executes the transactions to get the rollup state
* The 'super node' of the rollup.

Design Principles

* Deliver most realistic scaling
* Plasma problem - doesn't support general computation
* Modularity - can plug in other proof systems in the future

Bedrock

* Changes Optimism to geth with some rollup enhancements, modular/open architecture
* Cannon prover - based on geth
* Foundation of OP stack

Truffle boxes

* Boilerplate/scaffol code to start projects


Bridging

* L1, L2 cross domain - L1CrossDomainMessenger.sol and L2CrossDomainMessenger.sol
* Deployments - see https://github.com/ethereum-optimism/optimism/tree/develop/packages/contracts/deployments
* sendMessage - call an L1 contract method from an L2 contract and vice versa
    * target - other chain address
    * message - encoded version of the function and its input
    * gas limit - how much gas you are willing to pay

xDomainMessageSender

* accessing msg.sender will return the calling contract, which is the messenger contract
    * xDomainMessageSender allows us to identify the actual contract who is calling the function, e.g. the optimism contract that is using the messenger contract to call the L1 function

Transfer ETH and ERC20s

* THere are two main contracts - L1StandardBridge.sol and L2StandardBridge.sol
* Details [here](https://ethereum.org/en/developers/tutorials/optimism-std-bridge-annotated-code/)
* You need to update the optimism token list - not every ERC20 is supported. See [here](https://github.com/ethereum-optimism/optimism-tutorial/tree/main/standard-bridge-standard-token)
* Note - current tutorial for bedrock does not reference a token list - link to the pre-bedrock to to see the list referenced in the tutoril.

Bridge box - uses pre-deployed contracts frm optimism

* Need infura, metamash, goerli eth, optimism goerli eth, goerli dai

goerli_bridge_value.js is the interesting bit

Unbox

```
truffle unbox optimism-bridge optimism-bridge
cd optimism-bridge
```

For the local optimism container, see [here]https://community.optimism.io/docs/developers/build/dev-node/#accessing-logs) for addresses.

* Optimism contracts as advertised for mainnet
* For local L1:

```
$ curl http://localhost:8080/addresses.json
{
"Lib_AddressManager": "0x5FbDB2315678afecb367f032d93F642f64180aa3",
"ChainStorageContainer-CTC-batches": "0xe7f1725E7734CE288F8367e1Bb143E90bb3F0512",
"ChugSplashDictator": "0x959922bE3CAee4b8Cd9a407cc3ac1C251C2007B1",
"Proxy__OVM_L1CrossDomainMessenger": "0x8A791620dd6260079BF849Dc5567aDC3F2FdC318",
"ChainStorageContainer-SCC-batches": "0x9fE46736679d2D9a65F0992F2272dE9f3c7fa6e0",
"CanonicalTransactionChain": "0xCf7Ed3AccA5a467e9e704C703E8D87F634fB0Fc9",
"Proxy__OVM_L1StandardBridge": "0x610178dA211FEF7D417bC0e6FeD39F05609AD788",
"L1StandardBridge_for_verification_only": "0x3Aa5ebB10DC797CAC828524e59A333d0A371443c",
"OVM_L1CrossDomainMessenger": "0x0165878A594ca255338adfa4d48449f69242Eb8F",
"AddressDictator": "0xB7f8BC63BbcaD18155201308C8f3540b07f84F5e",
"BondManager": "0x5FC8d32690cc91D4c39d9d3abcBD16989F875707",
"StateCommitmentChain": "0xDc64a140Aa3E981100a9becA4E685f962f0cF6C9",
"AddressManager": "0x5FbDB2315678afecb367f032d93F642f64180aa3"
}
```

Optimish tutorials - see [here](https://github.com/ethereum-optimism/optimism-tutorial)

[Bridged ETH](https://github.com/ethereum-optimism/optimism-tutorial/tree/main/cross-dom-bridge-eth)



