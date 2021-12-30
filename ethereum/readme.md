# Ethereum Notes

[White paper](https://ethereum.org/en/whitepaper/)

Test net ether - [see here](https://www.2key.network/blog-posts/what-is-ropsten-eth-and-how-can-i-get-some)



Book repo - [here](https://github.com/ethereumbook/ethereumbook)

Working faucet - https://faucet.ropsten.be/

Using Ganache with Metamask - [this](https://medium.com/@kacharlabhargav21/using-ganache-with-remix-and-metamask-446fe5748ccf)

Network id - [this](https://ethereum.stackexchange.com/questions/91072/setup-ganache-with-metamask-what-and-where-is-a-chain-id)

* Options > Server > Network Id is 5777

## Events and Logs

https://medium.com/linum-labs/everything-you-ever-wanted-to-know-about-events-and-logs-on-ethereum-fec84ea7d0a5



## First solidity example

From [here](https://github.com/ethereumbook/ethereumbook/blob/develop/code/Solidity/Faucet.sol)

* Create and deploy in remix
* Copy the address
* Go to https://ropsten.etherscan.io/ and type the address into the search bar

### Using Truffle

truffle init
edit truffle-config.js - change the port
truffle migrate
create Faucet.sol in the contracts folder
create 2_Faucet_migration.js in migrations
truffle migrate --reset
execute from remix - on the deploy and run page, enter the contract in the "at address field", select the contract, the use the interface to run

Note - can't fund from metamask

```
truffle(development)> let instance = await Faucet.deployed()
truffle(development)> await instance.send(web3.utils.toWei("1","ether"))
truffle(development)> instance.withdraw("100000000000000000")
```

### Get the ABI

const fs = require('fs');
const contract = JSON.parse(fs.readFileSync('./build/contracts/Faucet.json', 'utf8'));
console.log(JSON.stringify(contract.abi));
