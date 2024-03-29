# gnache-cli

Docs [here](https://github.com/trufflesuite/ganache#command-line-use)

Run ganache like...

```
ganache -d
```

This will deterministically set the accounts and wallet mnemonic

```
Available Accounts
==================
(0) 0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1 (1000 ETH)
(1) 0xFFcf8FDEE72ac11b5c542428B35EEF5769C409f0 (1000 ETH)
(2) 0x22d491Bde2303f2f43325b2108D26f1eAbA1e32b (1000 ETH)
(3) 0xE11BA2b4D45Eaed5996Cd0823791E0C93114882d (1000 ETH)
(4) 0xd03ea8624C8C5987235048901fB614fDcA89b117 (1000 ETH)
(5) 0x95cED938F7991cd0dFcb48F0a06a40FA1aF46EBC (1000 ETH)
(6) 0x3E5e9111Ae8eB78Fe1CC3bb8915d5D461F3Ef9A9 (1000 ETH)
(7) 0x28a8746e75304c0780E011BEd21C72cD78cd535E (1000 ETH)
(8) 0xACa94ef8bD5ffEE41947b4585a84BdA5a3d3DA6E (1000 ETH)
(9) 0x1dF62f291b2E969fB0849d99D9Ce41e2F137006e (1000 ETH)

Private Keys
==================
(0) 0x4f3edf983ac636a65a842ce7c78d9aa706d3b113bce9c46f30d7d21715b23b1d
(1) 0x6cbed15c793ce57650b9877cf6fa156fbef513c4e6134f022a85b1ffdd59b2a1
(2) 0x6370fd033278c143179d81c5526140625662b8daa446c22ee2d73db3707e620c
(3) 0x646f1ce2fdad0e6deeeb5c7e8e5543bdde65e86029e2fd9fc169899c440a7913
(4) 0xadd53f9a7e588d003326d1cbf9e4a43c061aadd9bc938c843a79e7b4fd2ad743
(5) 0x395df67f0c2d2d9fe1ad08d1bc8b6627011959b79c53d7dd6a3536a33ab8a4fd
(6) 0xe485d098507f54e7733a205420dfddbe58db035fa577fc294ebd14db90767a52
(7) 0xa453611d9419d0e56f499079478fd72c37b251a94bfde4d19872c44cf65386e3
(8) 0x829e924fdf021ba3dbbc4225edfece9aca04b929d6e75613329ca6f1d31c0bb4
(9) 0xb0057716d5917badaf911b193b12b910811c1497b5bada8d7711f758981c3773

HD Wallet
==================
Mnemonic:      myth like bonus scare over problem client lizard pioneer submit female collect
```

```
truffle init
truffle create contract faucet
edit the truffle config
truffle migrate
truffle console
```

Once deployed you can fire up the event processor via `nooe events.js`

Then in the truffle console deposit some ether in the contract, then withdraw some...

```
let instance = await faucet.deployed()
await instance.send(web3.utils.toWei("1","ether"))
instance.withdraw("100000000000000000")
```

You should observe events printed out from the console

```
{
  address: '0xCfEB869F69431e42cdB54A4F4f105C19C080A601',
  blockHash: '0xe8b1e4d94245d20b9d37511154476bbce170775cd839be3050044d64772f4f58',
  blockNumber: 5,
  logIndex: 0,
  removed: false,
  transactionHash: '0x98bee93a24b0bf7a7c79dd7e1dd8a5f09f7a8f74b6bd9432e3be2d5d60ab3f10',
  transactionIndex: 0,
  id: 'log_b76d6442',
  returnValues: Result {
    '0': '0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1',
    '1': '1000000000000000000',
    from: '0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1',
    amount: '1000000000000000000'
  },
  event: 'Deposit',
  signature: '0xe1fffcc4923d04b559f4d29a8bfc6cda04eb5b0d3c460751c2402c5c5cc9109c',
  raw: {
    data: '0x0000000000000000000000000000000000000000000000000de0b6b3a7640000',
    topics: [
      '0xe1fffcc4923d04b559f4d29a8bfc6cda04eb5b0d3c460751c2402c5c5cc9109c',
      '0x00000000000000000000000090f8bf6a479f320ead074411a4b0e7944ea8c9c1'
    ]
  }
}
{
  address: '0xCfEB869F69431e42cdB54A4F4f105C19C080A601',
  blockHash: '0xbacdb8306890601df9ad0edc805005ad970b16ad27538da8f772bcb52b728eac',
  blockNumber: 6,
  logIndex: 0,
  removed: false,
  transactionHash: '0x433b87706809f46b33a3cf72e30f2531157c3f4e9b096b11c7701c49c64bc9ba',
  transactionIndex: 0,
  id: 'log_0ceca7af',
  returnValues: Result {
    '0': '0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1',
    '1': '100000000000000000',
    to: '0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1',
    amount: '100000000000000000'
  },
  event: 'Withdrawal',
  signature: '0x7fcf532c15f0a6db0bd6d0e038bea71d30d808c7d98cb3bf7268a95bf5081b65',
  raw: {
    data: '0x000000000000000000000000000000000000000000000000016345785d8a0000',
    topics: [
      '0x7fcf532c15f0a6db0bd6d0e038bea71d30d808c7d98cb3bf7268a95bf5081b65',
      '0x00000000000000000000000090f8bf6a479f320ead074411a4b0e7944ea8c9c1'
    ]
  }
}
```
