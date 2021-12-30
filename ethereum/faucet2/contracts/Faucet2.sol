pragma solidity 0.8.10;

contract Faucet2 {

    address owner;

    constructor() {
        owner = msg.sender;
    }

    function destroy() public {
        require(msg.sender == owner);
        selfdestruct(payable(owner));
    }
}