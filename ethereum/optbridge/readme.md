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