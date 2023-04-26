# EVM Notes

[Ethereum Virtual Machine](https://ethereum.org/en/developers/docs/evm/?ref=zaryabs.com)

Overview

* From a smart contracts perspective, the EVM looks like a distributed state machine
* State is a large data structure - accounts, balances, and machine state which can change
from block to block according to predefined rules.

Start transition function:

$Y(S,T) = S'$

EVM is a stack machine

* Operands are pushed on the stack, results go on the stack.
* Depth of the stack is 1024 items
* Each item is a 256-bit word
* Opcodes are in the [yellow paper](https://ethereum.github.io/yellowpaper/paper.pdf)

