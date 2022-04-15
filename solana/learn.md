
Figment - https://learn.figment.io/protocols/solana

Create data hub account - datahub.figment.io

Running a test validator

```
solana-test-validator
```


Before messing around with local...

Config File: /Users/ds/.config/solana/cli/config.yml
RPC URL: https://api.mainnet-beta.solana.com 
WebSocket URL: wss://api.mainnet-beta.solana.com/ (computed)
Keypair Path: /Users/ds/.config/solana/id.json 
Commitment: confirmed 

http://localhost:8899

On the local validator you may need to transfer a min amount of lamports, e.g. 1000000 worked for me, 1 or 100 did not


{
  "message": "failed to send transaction: Transaction simulation failed: Transaction leaves an account with data with a lower balance than rent-exempt minimum",
  "file": "/api/solana/transfer",
  "args": {
    "address": "H4b8jAvS7kqWjdGhCK2EXY1R73qGNZCbv2upZfmgJBUM",
    "secret": "[159,112,220,72,241,96,46,212,35,255,58,75,24,37,224,175,44,22,122,248,232,193,166,240,161,83,160,9,87,213,34,190,238,166,83,235,93,121,15,189,229,231,254,35,246,52,71,8,28,249,208,118,132,104,255,178,131,90,152,211,150,38,123,178]",
    "network": "localnet",
    "lamports": 1,
    "recipient": "GvZefdVjQoZa9YYNoUpmFayCLEurbDo2G6KoyJbgxsDa"
  }
}

https://explorer.solana.com/?cluster=custom

## Deploying a Program

https://docs.solana.com/developing/on-chain-programs/overview

Cargo, creates, and basis project structure - https://learning-rust.github.io/docs/a4.cargo,crates_and_basic_project_structure.html

Binary Object Representation Serializer for Hashing - borsh

Derive macros - https://doc.rust-lang.org/reference/procedural-macros.html#derive-macros

Set up

```
solana config set --url <local url>

mkdir solana-wallet
solana-keygen new --outfile solana-wallet/keypair.json

solana airdrop 1 $(solana-keygen pubkey solana-wallet/keypair.json)

yarn run solana:build:program
```

Command looks like...

```
solana deploy -v --keypair solana-wallet/keypair.json dist/solana/program/helloworld.so
```

After the program is deployed, create an account used by the program for storage. The account is derived from program id, e.g.

```
const greetedPubkey = await PublicKey.createWithSeed(
      payer.publicKey,
      GREETING_SEED,
      programId,
    );

    // This function calculates the fees we have to pay to keep the newly
    // created account alive on the blockchain. We're naming it lamports because
    // that is the denomination of the amount being returned by the function.
    const lamports = await connection.getMinimumBalanceForRentExemption(
      GREETING_SIZE,
    );

    // Find which instructions are expected and complete SystemProgram with
    // the required arguments.
    const transaction = new Transaction().add(
      SystemProgram.createAccountWithSeed({
        fromPubkey: payer.publicKey,
        basePubkey: payer.publicKey,
        seed: GREETING_SEED,
        newAccountPubkey: greetedPubkey,
        lamports,
        space: GREETING_SIZE,
        programId,
      }),
    );

    // Complete this function call with the expected arguments.
    const hash = await sendAndConfirmTransaction(connection, transaction, [payer]);
    ```

    Then... get data from the program (i.e. the counter) and send data to the program (the greetingm3rg3nt
    )