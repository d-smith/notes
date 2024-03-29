# Cosmwasm Accademy Notes

## Installing the Toolset

* Smart contracts are written in Rust
* Rust book: https://doc.rust-lang.org/book/
* Tools - need the rust compiler, wasm, and cosmwasm-check

Rust install page - [here](https://www.rust-lang.org/tools/install)

Need the wasm part of the toolset:

```bash
rustup target add wasm32-unknown-unknown
```

Check contract tool:

```bash
cargo install cosmwasm-check
```

Use cosmwasm-check in the examples instead of the original tools

To check the tools:

```bash
clone https://github.com/CosmWasm/cw-plus
cd cw-plus/contracts/cw1-whitelist
cargo wasm
cd ../../
find ./target/ -name "*.wasm"
cosmwasm-check ./target/wasm32-unknown-unknown/release/cw1_whitelist.wasm
```

## Preparing a Project

Materials [here](https://academy.cosmwasm.com/learn/smart-contracts/prepare-a-project)


cargo new --lib ./counting_contract

Smart contracts are wasn dynamic libraries, so we use the --lib flag

cd counting_contract/
cargo build

Edit the Cargo.toml file to add a library section

Now build with wasm target


For convenience create some aliases

$ cat .cargo/config
[alias]
wasm = "build --release --target wasm32-unknown-unknown"
wasm-debug = "build --target wasm32-unknown-unknown"

At this point if we check the contract:

$ cosmwasm-check target/wasm32-unknown-unknown/release/counting_contract.wasm
Available capabilities: {"cosmwasm_1_1", "cosmwasm_1_2", "cosmwasm_1_4", "staking", "stargate", "cosmwasm_1_3", "iterator"}

target/wasm32-unknown-unknown/release/counting_contract.wasm: failure
Error during static Wasm validation: Wasm contract missing a required marker export: interface_version_*

Passes: 0, failures: 1

This fails because the contract does not have an entry point.

Add a dependency

cargo add cosmwasm-std

Now edit src/lib.rs - add an instantiate function

```rust
use cosmwasm_std::{DepsMut, Env, MessageInfo, Empty, StdResult, Response, entry_point};

#[entry_point]
pub fn instantiate(deps: DepsMut, _env: Env, _info: MessageInfo, _msg: Empty) -> StdResult<Response> {
    Ok(Response::default())
}
```

Instantiate called when the contract is first created

The repo for the lession is [here](https://github.com/CosmWasm/cw-academy-course/commit/7d007d4833530c3f7464f1e304749715e5c4d2f3)



Entry point documention - https://docs.rs/cosmwasm-std/latest/cosmwasm_std/attr.entry_point.html

## Creating a Message

Need to include the serde crate for serializing and deserializing messages

Create a message model file src/msg.rs

Note the structuring for serialization including snake_case, values attributes, etc.

Note use of modules for both the message and the query functionality

cargo build, cargo wask, cosmwasm-check target/wasm32-unknown-unknown/release/counting_contract.wasm

## Contract Testing

Additional dependency

`

We test the contract code directly as opposed to first deploying it to the blockchain.

Note we need a smart contract wrapper for this, in lib.rs


App - our simulated blockchain interface

Build and test

cargo test
cargo wasm
cosmwasm-check target/wasm32-unknown-unknown/release/counting_contract.wasm

## State

Create for working with state:

cargo add cw-storage-plus

State can be initialized on contract instantiation via an instantiation entrypoint.

## Execution Entry Point

Define a message, e.g. Poke in msg.rs

Define a message handler, e.g. poke in contract.rs

Add code to dispatch the message in lib.rs

Add events too


> Every execution (and other action, like instantiation) emits events. Events are logs reporting what was perfromed by an action. Event contains a type and the set of key-value pairs (both key and value being strings) named attributes.
>
> Events are emitted from execution using the Response::add_event function, passing the constructed Event type.
>
> Every execution emits at least one default event, with the type of wasm. In most cases, it is good enough to emit only that one. To add attributes to the wasm event, we can use a Response::add_attribute function. 

## Receiving Funds

Token - arbitrary abstraction that an account can possess, with the ability to know how much of the token an account possesses.

Two types of tokens: native and cw20

Native tokens - managed directly by the blockchain

* Two ways to send them to other addresses: sending a blank message to the blockchain just to transfer them, and pass tokens as funds with a message.

Note you can't control receiving funds, but you can react to them.


## Sending Funds

[Lesson here](https://academy.cosmwasm.com/learn/smart-contracts/sending-funds)

Example - add a withdraw function that the contract creator can use to withdraw funds from the contract.

* Need to keep track of the owner when the contract is instantiated
* Add a WithDraw message and message handler

## Schema

Add `use schemars::JsonSchema;`

Extend the traits... `#[derive(Serialize, Deserialize, Clone, Debug, PartialEq, Eq, JsonSchema)]`

That's unwieldy... we can use the ` #[cw_serde]` macro.
