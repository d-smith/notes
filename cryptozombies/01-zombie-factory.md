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

arrays

- two types: fixed and dynamic
- fixed: length is specified
- dynamic: length can change
- arrays can be declared as public, which generates a getter method

```solidity
// Array with a fixed length of 2 elements:
uint[2] fixedArray;
// another fixed Array, can contain 5 strings:
string[5] stringArray;
// a dynamic Array - has no fixed size, can keep growing:
uint[] dynamicArray;
```

```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {

    uint dnaDigits = 16;
    uint dnaModulus = 10 ** dnaDigits;

    struct Zombie {
        string name;
        uint dna;
    }

    Zombie[] public zombies;

}
```

function declarations

Look like this:

```solidity
function eatHamburgers(string memory _name, uint _amount) public {

}
```

In the declaration above:
- function visibilty has been set to public
- we provided an instruction about where to store the reference type, e.g. memory. Note this is required for all reference types such as arrays, structs, mappings, and strings.
- by convention, function arguments are prefixed with an underscore (_) to differentiate them from global variables

```solidity


There are two ways to pass an argument to a solidity fucnction:
- by value, where solidity makes a copy of the argument
- by reference, where solidity uses a reference to the original argument, so if the reference is modified, the original argument is modified

```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {

    uint dnaDigits = 16;
    uint dnaModulus = 10 ** dnaDigits;

    struct Zombie {
        string name;
        uint dna;
    }

    Zombie[] public zombies;

    function createZombie (string memory _name, uint _dna) public {
    }

}
```

creating structs and adding them to an array

```solidity
struct Person {
  uint age;
  string name;
}

Person[] public people;

Person satoshi = Person(172, "Satoshi");

// Add that person to the Array:
people.push(satoshi);
```

```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {

    uint dnaDigits = 16;
    uint dnaModulus = 10 ** dnaDigits;

    struct Zombie {
        string name;
        uint dna;
    }

    Zombie[] public zombies;

    function createZombie(string memory _name, uint _dna) public {
        zombies.push(Zombie(_name, _dna));
    }

}
```

private / public functions

- In solidity, functions are public by default
- Good practice is to make functions private by default, and then only make public the functions you want to expose to the world
- By convention, functions that are private are prefixed with an underscore (_)

```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {

    uint dnaDigits = 16;
    uint dnaModulus = 10 ** dnaDigits;

    struct Zombie {
        string name;
        uint dna;
    }

    Zombie[] public zombies;

    function _createZombie(string memory _name, uint _dna) private {
        zombies.push(Zombie(_name, _dna));
    }

    // start here

}
```

return values

- function declaration is modified to indicate return values

```solidity
function sayHello() public returns (string memory) {
  return "Hello";
}
```

function modifiers

- view functions don't change any state variables
- pure functions don't read or modify the state

```solidity
function _generateRandomDna(string memory _str) private view returns (uint) {
}
```

Keccak256 and typecasting

- keccak256 is a version of sha3
- keccak256 expects a single parameter of type bytes, which can be produced via abi.encodePacked
- typecasting is done by enclosing the type in parentheses

```solidity
// Typecast example
uint8 a = 5;
uint b = 6;
// throws an error because a * b returns a uint, not uint8:
uint8 c = a * b;
// we have to typecast b as a uint8 to make it work:
uint8 c = a * uint8(b);
```


```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {

    // declare our event here

    uint dnaDigits = 16;
    uint dnaModulus = 10 ** dnaDigits;

    struct Zombie {
        string name;
        uint dna;
    }

    Zombie[] public zombies;

    function _createZombie(string memory _name, uint _dna) private {
        zombies.push(Zombie(_name, _dna));
        // and fire it here
    }

    function _generateRandomDna(string memory _str) private view returns (uint) {
        uint rand = uint(keccak256(abi.encodePacked(_str)));
        return rand % dnaModulus;
    }

    function createRandomZombie(string memory _name) public {
        uint randDna = _generateRandomDna(_name);
        _createZombie(_name, randDna);
    }

}
```

events

- events are a way for your contract to communicate that something happened on the blockchain to your app front-end, which can be 'listening' for certain events and take action when they happen

```solidity
// declare the event
event IntegersAdded(uint x, uint y, uint result);

function add(uint _x, uint _y) public returns (uint) {
  uint result = _x + _y;
  // fire an event to let the app know the function was called:
  emit IntegersAdded(_x, _y, result);
  return result;
}
```

```solidity
pragma solidity >=0.5.0 <0.6.0;

contract ZombieFactory {

    event NewZombie(uint zombieId, string name, uint dna);

    uint dnaDigits = 16;
    uint dnaModulus = 10 ** dnaDigits;

    struct Zombie {
        string name;
        uint dna;
    }

    Zombie[] public zombies;

    function _createZombie(string memory _name, uint _dna) private {
        uint id = zombies.push(Zombie(_name, _dna)) - 1;
        emit NewZombie(id, _name, _dna);
    }

    function _generateRandomDna(string memory _str) private view returns (uint) {
        uint rand = uint(keccak256(abi.encodePacked(_str)));
        return rand % dnaModulus;
    }

    function createRandomZombie(string memory _name) public {
        uint randDna = _generateRandomDna(_name);
        _createZombie(_name, randDna);
    }

}
```



