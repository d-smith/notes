pragma solidity 0.8.10;

contract Faucet2 {

    address owner;

    constructor() {
        owner = msg.sender;
    }

    modifier onlyOwner {
        require(msg.sender == owner);
        _;
    }

    function destroy() public onlyOwner {
        selfdestruct(payable(owner));
    }
}