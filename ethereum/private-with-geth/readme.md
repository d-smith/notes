# Private Blockchain Network

Based on [this](https://medium.com/scb-digital/running-a-private-ethereum-blockchain-using-docker-589c8e6a4fe8)

```
curl --location --request POST 'localhost:8545' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jsonrpc": "2.0",
    "id": 3,
    "method": "eth_accounts",
    "params": []
}'
{"jsonrpc":"2.0","id":3,"result":["0x10cba52b34930586aa166e1c8aca0eea9442f01e"]}

curl --location --request POST 'localhost:8545' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jsonrpc": "2.0",
    "id": 4,
    "method": "eth_getBalance",
    "params": [
        "0x10cba52b34930586aa166e1c8aca0eea9442f01e",
        "latest"
    ]
}'
{"jsonrpc":"2.0","id":4,"result":"0x30927f74c9de00000"}
```

## Using this With Truffle

Add an entry to truffle-config.js for the network, e.g. dev2:

```
development: {
    host: "127.0.0.1",     // Localhost (default: none)
    port: 7545,            // Standard Ethereum port (default: none)
    network_id: "*",       // Any network (default: none)
},
dev2: {
    host: "127.0.0.1",     // Localhost (default: none)
    port: 8545,            // Standard Ethereum port (default: none)
    network_id: "*",       // Any network (default: none)
},
```



Create a second account:

```
curl --location --request POST 'http://localhost:8545' \
--header 'Content-type: application/json' \
--data-raw '{
    "jsonrpc": "2.0",
    "id": 5,
    "method": "personal_newAccount",
    "params": [
        "5uper53cr3t"
    ]
}'

{"jsonrpc":"2.0","id":5,"result":"0xb5f47784f55ed77c040db48b4771997ceebdb549"}
```

What is the root account?

```
curl --location --request POST 'localhost:8545' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jsonrpc": "2.0",
    "id": 3,
    "method": "eth_accounts",
    "params": []
}'

{"jsonrpc":"2.0","id":3,"result":["0x10cba52b34930586aa166e1c8aca0eea9442f01e","0xb5f47784f55ed77c040db48b4771997ceebdb549"]}
```


Unlock the root account...

```
geth attach http://localhost:8545
personal.unlockAccount("0x10cba52b34930586aa166e1c8aca0eea9442f01e")

Deploy the smart contract:

truffle deploy --network dev2 