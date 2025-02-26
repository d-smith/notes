# Zombit Attack!

## Addresses and Mappings

Addresses - Ethereum network is made up of accounts, which have an ether balance, can be used to send or receive transactions, and can store data. An account is controlled by a private key, and you can send ether or messages from an account by creating and signing a transaction (which you then broadcast to the network). An account can either be an **external account**, which is controlled by a private key, or a **contract account**, which is controlled by code.

Each account has an address which is a unique identifier that points to the account. Solidity has an address type used to store addresses.

In solidity, a mapping is a key-value store.

Mapping examples:

```solidity
mapping (address => uint) public accountBalance;
```

This creates a mapping with `address` keys and `uint` values. Each address has an associated `uint` (unsigned integer).

```solidity
mapping (address => mapping (address => uint)) public allowance;
```

This creates a mapping with `address` keys that map to another mapping with `address` keys and `uint` values. This can be used to store a table of `uint`s that are accessible by two addresses.

## Msg.Sender

msg.sender is a global variable in Solidity that contains the address of the person (or smart contract) who called the current function.

- Solidity function execution always needs to start with an external caller. A contract will never just run on its own.

Example:

```solidity
mapping (address => uint) favoriteNumber;

function setMyNumber(uint _myNumber) public {
  // Update our `favoriteNumber` mapping to store `_myNumber` under `msg.sender`
  favoriteNumber[msg.sender] = _myNumber;
  // ^ The syntax for storing data in a mapping is just like with arrays
}

function whatIsMyNumber() public view returns (uint) {
  // Retrieve the value stored in the sender's address
  // Will be `0` if the sender hasn't called `setMyNumber` yet
  return favoriteNumber[msg.sender];
}
``` 

## Require

In solidity you can use `require` to validate inputs and check conditions. If the input for require is false, the function will stop executing and revert any changes made.

```solidity
function sayHiToVitalik(string memory _name) public returns (string memory) {
  // Compares if _name equals "Vitalik". Throws an error and exits if not true.
  // (Side note: Solidity doesn't have native string comparison, so we
  // compare their keccak256 hashes to see if the strings are equal)
  require(keccak256(abi.encodePacked(_name)) == keccak256(abi.encodePacked("Vitalik")));
  // If it's true, proceed with the function:
  return "Hi!";
}
```

## Inheritance

In Solidity, you can inherit from other contracts. This is a way to make a contract inherit the behavior and storage of another contract.

Example:

```solidity
contract Doge {
  function catchphrase() public returns (string memory) {
    return
      "So Wow
      "Such Doge";
  }
}

// Inherit from Doge by specifying Doge in the contract declaration
contract BabyDoge is Doge {
  function anotherCatchphrase() public returns (string memory) {
    return
      "Much Moon
      "Very Mars";
  }
}
``` 

## Import

In Solidity, you can use `import` to include other source files. This is similar to how other programming languages use `include`, `require`, or `import`.

Example:

```solidity
import "./someothercontract.sol";

contract newContract is SomeOtherContract {

}  
```

## Storage and Memory

In solidity, there are two locations you can store variables — in storage and in memory.

Storage refers to variables stored permanently on the blockchain. Memory variables are temporary, and are erased between (and during) function calls. You can think of memory as your computer's hard disk and storage as a USB stick.

Most of the time you don't need to use these keywords because Solidity handles them by default. State variables (variables declared outside of a function) are by default storage and written permanently to the blockchain, while variables declared inside a function are memory and will disappear when the function call ends.

There are times when you need to use these keywords, for example when dealing with structs and arrays within functions.

Example from Cryotozombies:

```solidity
contract SandwichFactory {
  struct Sandwich {
    string name;
    string status;
  }

  Sandwich[] sandwiches;

  function eatSandwich(uint _index) public {
    // Sandwich mySandwich = sandwiches[_index];

    // ^ Seems pretty straightforward, but solidity will give you a warning
    // telling you that you should explicitly declare `storage` or `memory` here.

    // So instead, you should declare with the `storage` keyword, like:
    Sandwich storage mySandwich = sandwiches[_index];
    // ...in which case `mySandwich` is a pointer to `sandwiches[_index]`
    // in storage, and...
    mySandwich.status = "Eaten!";
    // ...this will permanently change `sandwiches[_index]` on the blockchain.

    // If you just want a copy, you can use `memory`:
    Sandwich memory anotherSandwich = sandwiches[_index + 1];
    // ...in which case `anotherSandwich` will simply be a copy of the
    // data in memory, and...
    anotherSandwich.status = "Eaten!";
    // ...will just modify the temporary variable and have no effect
    // on `sandwiches[_index + 1]`. But you can do this:
    sandwiches[_index + 1] = anotherSandwich;
    // ...if you want to copy the changes back into blockchain storage.
  }
}
```

Next: https://cryptozombies.io/en/lesson/2/chapter/9



