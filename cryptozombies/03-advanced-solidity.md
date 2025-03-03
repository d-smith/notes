## Immutability of Contracts

Once deployed, a contract is immutable. You can't change the code of a contract once it's on the blockchain. This means that you can't fix bugs or add new features to a contract once it's been deployed. If you want to make any of these changes, you'll have to deploy a new contract.

This is why it's extremely important to test your contracts thoroughly before deploying them to the blockchain. Once they're out there, they can't be changed.

It can make sense to have functions that allow you to update key portions of the dap. For example having a function to set the address of external contracts that your contract interacts with. This way you can update the external contract address without having to deploy a new contract.

```solidity
contract ZombieFeeding is ZombieFactory {

  KittyInterface kittyContract;

  function setKittyContractAddress(address _address) external {
    kittyContract = KittyInterface(_address);
  }

  etc...
}
```

