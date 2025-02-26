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

