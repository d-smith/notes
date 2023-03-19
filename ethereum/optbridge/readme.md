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
