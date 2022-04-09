Deploying paxos gold on skale

Metamask - create a wallet on the skale network, fund it via faucet link

For hackathon had the following resource available: https://ethportland.skale.network/

Some commands - note had to provide gas to unpause

```
let instance = await PAXGImplementation.deployed()

let accounts = await web3.eth.getAccounts()

balance = web3.eth.getBalance(accounts[0])

instance.unpause({gas: web3.utils.toWei('100000','wei')})
instance.paused()
instance.increaseSupply(10000)
instance.totalSupply()
```

Truffle config:

Environment set up

```
var HDWalletProvider = require("@truffle/hdwallet-provider");
const privateKeyOrMnemonic = process.env.PRIVATE_KEY || process.env.MNEMONIC;
const skale = process.env.SKALE_CHAIN;
```

Network setup

```
skale: {
      provider: () => new HDWalletProvider(privateKeyOrMnemonic, skale),
      gasPrice: 100000,
      network_id: "*",
      skipDryRun: true
    }
```