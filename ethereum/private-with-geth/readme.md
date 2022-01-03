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