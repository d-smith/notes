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

* Can reuse deployment accounts via copying ./packages/hardhat/mnemonic.txt across projects
* Use `yarn account` to verify
* 0xD6604D041A5cBBB96DFd128000b64a2b44ccd957
* responsible-jeans.surge.sh

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

Sell tokens

```
function sellTokens(uint256 amount) public {
    // Amount to sell must be greater than zero
    require(amount > 0, "Amount to sell must be greater than zero");

    // Seller must have the given amount of tokens
    uint256 tokenBal = yourToken.balanceOf(msg.sender);
    require(tokenBal >= amount, "Seller does not have enough tokens to sell specified amount");

    // Buyer must have enough eth
    uint256 requiredEth = amount / tokensPerEth;
    require(address(this).balance >= requiredEth, "Vendor does not have enough eth to purchase given amount of tokens");

    // Emit the event
    emit SellTokens(msg.sender, requiredEth, amount);


    // Transfer tokens to buyer
    bool transferred = yourToken.transferFrom(msg.sender, address(this), amount);
    require(transferred, "Error transferring tokens");
    
    // Send eth to seller
    (bool sent,) = msg.sender.call{value: requiredEth}("");
    require(sent, "Error sending eth to seller");

  }
  ```

  ## Challenge 3 - Dice Game

  https://speedrunethereum.com/challenge/dice-game

  The rigged dice game:

  ```
  pragma solidity >=0.8.0 <0.9.0;  //Do not change the solidity version as it negativly impacts submission grading
//SPDX-License-Identifier: MIT

import "hardhat/console.sol";
import "./DiceGame.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract RiggedRoll is Ownable {

    DiceGame public diceGame;

    constructor(address payable diceGameAddress) {
        diceGame = DiceGame(diceGameAddress);
    }

    function withdraw(address _addr, uint256 _amount) public payable onlyOwner {
        require(address(this).balance > 0, "Nothing to withdraw");
        require(_amount > 0 && _amount <= address(this).balance, "Amount to withdraw must by greater than zero and less than or equal to amount of funds");

        (bool sent,) = _addr.call{value: _amount}("");
        require(sent, "Error withdrawing funds");
    }

    function riggedRoll() public payable {
        require(address(this).balance >= .002 ether, "Rigged roll contract balance must be at least 0.002 eth");

        uint256 nonce = diceGame.nonce();
        bytes32 prevHash = blockhash(block.number - 1);
        bytes32 hash = keccak256(abi.encodePacked(prevHash, address(diceGame), nonce));
        uint256 roll = uint256(hash) % 16;

        console.log(" rigged role: ", roll);
        require(roll <= 2, "Losing roll, revert txn");

        // Roll will be a winner
        console.log("will win - call role the dice");
        diceGame.rollTheDice{value: 0.002 ether}();
    }


    //Add receive() function so contract can receive Eth
    receive() external payable {  }
}
```

## Challenge 4 - DEX

Original notes [here](https://medium.com/@austin_48503/%EF%B8%8F-minimum-viable-exchange-d84f30bd0c90)
Also see [Formulas of a Uniswap](https://hackernoon.com/formulas-of-uniswap-a-deep-dive)
And... [Price Pool Calculation](https://youtu.be/IL7cRj5vzEU)
And... [A Brief HIstory of Uniswap and It's Math](https://pandichef.medium.com/a-brief-history-of-uniswap-and-its-math-90443241c9b7)
* Local address for front end - 0xAFCa58B131b95084A8Ae5c0B22AEbA2313D9C5A1


Price

DEX invariant:

$xy = k$ where x is the reserve of token a, y is the reserve of token b

If I put in $\Delta x$ (e.g. sell token a), what $\Delta y$ do I get out?

$(x+\Delta x)(y-\Delta y) = xy$

$y(x+\Delta x) - \Delta y(x+\Delta x) = xy$

$\Delta y(x+\Delta x)-y(x+\Delta x)-xy$

$\Delta y = \dfrac{y(x+\Delta x) - xy}{(x+\Delta x)}$

$\Delta y = y \dfrac{(x+\Delta x) -x}{(x+\Delta x) }$

$\Delta y = \dfrac{y \Delta x}{x + \Delta x}$

What if I want to buy token b? How much $\Delta x$ token a do I need to 
put in to get out a desired amount of token b ($\Delta y$)?

Again, we use the invariant, and express the calculation
of the invariant in terms of token a in and token b out.

$x(y-\Delta y) + \Delta x (y-\Delta y) = xy$

$\Delta x = \dfrac{xy - x(y-\Delta y)}{(y-\Delta y)}$

$\Delta x = x \dfrac{y - (y-\Delta y)}{(y-\Delta y)}$

$\Delta x = \dfrac{x \Delta y}{(y-\Delta y)}$

Anyway, the price function looks like:

```
 function price (
        uint256 xInput,
        uint256 xReserves,
        uint256 yReserves
    ) public view returns (uint256 yOutput) {
        uint256 xInputWithFee = xInput.mul(997);
        uint256 numerator = xInputWithFee.mul(yReserves);
        uint256 denominator = (xReserves.mul(1000)).add(xInputWithFee);
        return (numerator / denominator);
    }
```

This uses the above price formula, applying a 0.3% fee using Solidity's integer math.

Trading functions

```
/**
     * @notice sends Ether to DEX in exchange for $BAL
     */
    function ethToToken() public payable returns (uint256 tokenOutput) {
        require(msg.value > 0, "Cannot call with zero ETH value");

        uint256 ethReserve = address(this).balance.sub(msg.value);
        uint256 tokenReserve = token.balanceOf(address(this));
        tokenOutput = price(msg.value,ethReserve,tokenReserve);

        emit EthToTokenSwap(msg.sender,"Eth to balloons", msg.value, tokenOutput);
        bool xferStatus = token.transfer(msg.sender, tokenOutput);
        require(xferStatus, "error transferring tokens");
    }

    /**
     * @notice sends $BAL tokens to DEX in exchange for Ether
     */
    function tokenToEth(uint256 tokenInput) public returns (uint256 ethOutput) {
        // Checks
        require(tokenInput > 0, "Must specify number of tokens greater than zero");

        // Effects
        uint256 tokenReserve = token.balanceOf(address(this));
        uint256 ethReserve = address(this).balance;

        ethOutput = price(tokenInput,tokenReserve,ethReserve);

        emit TokenToEthSwap(msg.sender, "Balloons to Eth", ethOutput, tokenInput);

        // Interactions
        require(token.transferFrom(msg.sender,address(this),tokenInput), "Error transferring token to smart contract");
        (bool sent,) = msg.sender.call{value: ethOutput}("");
        require(sent, "Error sending ETH to caller");

    }
    ```

