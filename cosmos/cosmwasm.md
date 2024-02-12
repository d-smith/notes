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

