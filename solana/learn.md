
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