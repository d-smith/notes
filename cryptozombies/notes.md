
contract

- basic building block Ethereum apps
- all variables and functions belong to a contract

version pragma

- declare the version of Solidity the contract is written in
- used to prevent issues with future compiler versions

```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {

    //start here

}
```
state variables

- stored permanently in contract storage
- written to the blockchain, conceptually similar to writing to a DB

uint - unsigned integer, can't be negative
int - signed integer, can be negative

```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {

    uint dnaDigits = 16;

}
````