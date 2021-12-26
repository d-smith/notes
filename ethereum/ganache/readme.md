# Ganache Article

Some notes based on following along with [this](https://blog.logrocket.com/develop-test-deploy-smart-contracts-ganache/)

Article repo is [here](https://github.com/KumarAbhirup/truffle-tutorial)

Install truffle:

```
npm i truffle -g
```

Ganache download [here](http://trufflesuite.com/ganache/)

* Start ganache, quick start a block chain, determine the RPC port
* Update truffle-config.js


```
  networks: {
    development: {
      host: "127.0.0.1",     // Localhost (default: none)
      port: 7545,            // Standard Ethereum port (default: none)
      network_id: "*"        // Any network (default: none)
     }
  },
  ```

  Then...

  * truffle migrate

  For visual studio code, the Solidity plugin looks useful.

  Tutorial steps

  * create the smart contract in the contracts directory
  * create the migration file 2_TruffleTutorial_migration.js
  * deploy via truffle migration --reset
  * smart contract interaction via truffle console
  * test in /test directory, execute via `truffle test`
    * note - Chai needed

```    
npm install chai
npm install chai-as-promised