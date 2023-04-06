# Geth Notes

Notes from [geth docs](https://geth.ethereum.org/docs)

## Intro

Account overview [here](https://ethereum.org/en/developers/docs/accounts/)

* Does not cover account abstraction

clef - tool to manage account key pairs

* create accounts
* clef uses the private keys stored in the keystore to sign transactions
    * Can start it as a process for signing transactions
* can use geth as a terminal via `geth attach`
    * List accounts via eth.accounts
    * Get balance - e.g. `web3.fromWei(eth.getBalance('0xca57F3b40B42FCce3c37B8D18aDBca5260ca72EC'), 'ether');`
    * Send eth - e.g.
    
    ```
    eth.sendTransaction({
    from: '0xca57f3b40b42fcce3c37b8d18adbca5260ca72ec',
    to: '0xce8dba5e4157c2b284d8853afeeea259344c1653',
    value: web3.toWei(0.1, 'ether')
    });
    ```
    
    * Get details on transactions via `eth.getTransaction`

## Install

Verify the download on ubuntu via md5sum

Ubuntu [install](https://geth.ethereum.org/docs/getting-started/installing-geth#ubuntu-via-ppas)