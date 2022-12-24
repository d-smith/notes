# Speed Run ETH Notes

## Challenge 0 - Simple NFT Marketplace

https://speedrunethereum.com/challenge/simple-nft-example

* Be sure to use a supported version of node via nvm, e.g. ` nvm use v16.18.1`
* yarn generate - generate deployment account
* yarn account - view generated account
* surge deploy - https://straight-business.surge.sh/
* goerli contract - https://goerli.etherscan.io/address/0x877ea6Bed5bbfFBA5311304C98a8F46e5cbd9ca1
* opensea - https://testnets.opensea.io/collection/yourcollectible-ve2hb4ags8
* https://speedrunethereum.com/builders/0xA30Df2957194F42D5d684FC85D5885E38AFcE685

## Challenge 1 - Decentralized Staking App

https://speedrunethereum.com/challenge/decentralized-staking

* Deployed 0xc6A93866162e6AC0010dd5C93DD8BB4F3c3904EE
* subsequent-question.surge.sh
* https://subsequent-question.surge.sh/
* https://goerli.etherscan.io/address/0xccd78252e7C0cf6DFA085C466F0C92Ee85CE0615

The contract

```
// SPDX-License-Identifier: MIT
pragma solidity 0.8.4;  //Do not change the solidity version as it negativly impacts submission grading

import "hardhat/console.sol";
import "./ExampleExternalContract.sol";

contract Staker {

  ExampleExternalContract public exampleExternalContract;

  mapping ( address => uint256 ) public balances;
  uint256 public constant threshold = 1 ether;
  bool private executeCalled;

  event Stake(address, uint256);

  // local testing
  //uint256 public deadline = block.timestamp + 30 seconds;
  uint256 public deadline = block.timestamp + 72 hours;

  enum StakingState {
    Open,
    FundsSent,
    CanWithdraw
  }

  StakingState private stakingState;

  modifier expectedState(StakingState state) {
    string memory message;
    if(state == StakingState.Open) {
      message = "Staking time period has passed";
    } else {
      if(stakingState == StakingState.FundsSent) {
        message = "Cannot withdraw - funds have been sent";
      } else {
        message = "Funds not eligible to be withdrawn";
      }
    }

    require(stakingState == state, message);
    _;
  }

  constructor(address exampleExternalContractAddress) {
    stakingState = StakingState.Open;
    executeCalled = false;
    exampleExternalContract = ExampleExternalContract(exampleExternalContractAddress);
  }

  // Collect funds in a payable `stake()` function and track individual `balances` with a mapping:
  // ( Make sure to add a `Stake(address,uint256)` event and emit it for the frontend <List/> display )
  function stake() public payable expectedState(StakingState.Open) {
    uint currentBalance = balances[msg.sender];
    balances[msg.sender] = currentBalance + msg.value;
    emit Stake(msg.sender,msg.value);
  }

  // After some `deadline` allow anyone to call an `execute()` function
  // If the deadline has passed and the threshold is met, it should call `exampleExternalContract.complete{value: address(this).balance}()`
  function execute() public {
    require(executeCalled == false, "Execute has been called previously");
    require(deadline < block.timestamp, "Staking period still open");
    
    executeCalled = true;

    if(address(this).balance < threshold) {
      console.log("threshold not met");
      stakingState = StakingState.CanWithdraw;
      return;
    }

    console.log("send funds");
    stakingState = StakingState.FundsSent;
    exampleExternalContract.complete{value: address(this).balance}();
  }

  function withdraw() public payable expectedState(StakingState.CanWithdraw) {
    uint available = balances[msg.sender];
    require(available > 0, "No funds available to caller to withdraw");
    balances[msg.sender] = 0;
    (bool sent,) = msg.sender.call{value: available}("");
    require(sent, "Failed to send ether");
  }

  // Add a `timeLeft()` view function that returns the time left before the deadline for the frontend
  function timeLeft() public view returns(uint) {
    if(deadline > block.timestamp) {
        return deadline - block.timestamp;
    } 

    return 0;
  }

  // Add the `receive()` special function that receives eth and calls stake()
  receive() external payable {
    stake();
  }
}

```

## Challenge 2 - Token Vending Machine

https://speedrunethereum.com/challenge/token-vendor

The token contract

```
pragma solidity 0.8.4;  //Do not change the solidity version as it negativly impacts submission grading
// SPDX-License-Identifier: MIT

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
// learn more: https://docs.openzeppelin.com/contracts/4.x/erc20

contract YourToken is ERC20 {
    constructor() ERC20("Gold", "GLD") {
        _mint( msg.sender , 1000 * 10 ** 18);
    }
}
```

buyTokens:

```
 function buyTokens() public payable {
    require(msg.value > 0, "Must send ETH to buy tokens");
    uint numTokens = msg.value * tokensPerEth;
    uint vendorBalance = yourToken.balanceOf(address(this));

    require(vendorBalance >= numTokens, "Vendor balance is less than requested tokens");

    emit BuyTokens(msg.sender, msg.value , numTokens); 

    bool sent = yourToken.transfer(msg.sender, numTokens);
    require(sent, "Token transfer failed");
    
  }
  ```

  withdraw:

  ```
  function withdraw() public payable onlyOwner {
    uint ethBalance = address(this).balance;
    require(ethBalance > 0, "No ETH to withdraw");

    emit Withdraw(owner(), ethBalance);
    (bool sent,) = owner().call{value: ethBalance}("");
    require(sent, "Error withdrawing ETH");
  }
```