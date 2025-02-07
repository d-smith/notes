## Zombie Factory

contract

- basic building block Ethereum apps
- all variables and functions belong to a contract

version pragma

- declare the version of Solidity the contract is written in
- used to prevent issues with future compiler versions

```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {

}
```
state variables

- stored permanently in contract storage
- written to the blockchain, conceptually similar to writing to a DB

uint - unsigned integer, can't be negative
int - signed integer, can be negative
string - arbitrary length UTF-8 data

```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {
    uint dnaDigits = 16;
}
```

math operations

- addition (+)
- subtraction (-)
- multiplication (*)
- division (/)
- modulus  (%)
- exponentiation (**)

```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {
    uint dnaDigits = 16;
    uint dnaModulus = 10 ** dnaDigits;
}
```

structs
- custom data structures to store data with multiple properties

```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {

    uint dnaDigits = 16;
    uint dnaModulus = 10 ** dnaDigits;

    struct Zombie {
        string name;
        uint dna;
    }
}
```