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


