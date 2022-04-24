# Notes from Building Smart Contracts with Solana and Rust Lang

https://www.youtube.com/watch?v=gA7hFdq2h9Q

Make sure your cli version is the same version as your cluster

Run local network

```
solana-test-validator -r 
```

```
(base) ds@lappy contract-first % solana cluster-version
1.9.6
(base) ds@lappy contract-first % solana --version
solana-cli 1.9.6 (src:781609b2; feat:2191737503)
```

Solana uses clusters as the terms for dev net, test net, main net, localnet

export RUST_LOG=solana_runtime::system_instruction_processor=trace,solana_runtime::message_processor=debug,solana_bpf_loader=debug,solana_rbpf=debug

Use --log for local

Use hello world

https://github.com/solana-labs/example-helloworld

Base concept - account

* Accounts have storage for data, and hold lamports (money units)
* Every program live in an account
* Every account has to be owned by a program
* Account holder - has the private keys associated with an account
* Owner property in an account - has to be a program
* Only way the lamports in the account can be removed is if the program that owns the account is the one doing those actions.
* In Solana accounts are charged rent to stay on the blockchain

