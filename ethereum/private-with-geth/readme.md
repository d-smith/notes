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

After booting we can see the root account

curl --location --request POST 'localhost:8545' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jsonrpc": "2.0",
    "id": 3,
    "method": "eth_accounts",
    "params": []
}'
{"jsonrpc":"2.0","id":3,"result":["0x10cba52b34930586aa166e1c8aca0eea9442f01e"]}


Unlock the root account...

```
geth attach http://localhost:8545
> personal.unlockAccount("0x10cba52b34930586aa166e1c8aca0eea9442f01e", "d0ntt3ll",0)
```

Deploy the smart contract:

truffle deploy --network dev2 

In the truffle console, fund the faucet

truffle console --network dev2

Faucet2.deployed().then(i => {Faucet2Deployed = i})
Faucet2Deployed.send(web3.utils.toWei("1","ether")).then(res => {console.log(res.logs[0].event, res.logs[0].args) })

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
{"jsonrpc":"2.0","id":5,"result":"0x2a265e23e2ded2c8a7f37f7094f637f62ca472b9"}
```

Transfer some ether to the second account so it can run the smart contract

curl --location --request POST 'localhost:8545' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jsonrpc": "2.0",
    "id": 7,
    "method": "eth_sendTransaction",
    "params": [
        {
            "from":"0x10cba52b34930586aa166e1c8aca0eea9442f01e",
            "to":"0x2a265e23e2ded2c8a7f37f7094f637f62ca472b9",
            "value": "0xf4240"
        }
    ]
}'


Fund the second account from the faucet

let res = await Faucet2Deployed.withdraw(web3.utils.toWei("0.1","ether"));

What are the accounts?

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




Deploy the smart contract:

truffle deploy --network dev2 

Get balance

curl --location --request POST 'localhost:8545' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jsonrpc": "2.0",
    "id": 4,
    "method": "eth_getBalance",
    "params": ["0xb5f47784f55ed77c040db48b4771997ceebdb549",
        "latest"
    ]
}'

