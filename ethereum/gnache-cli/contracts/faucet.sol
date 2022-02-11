pragma solidity 0.8.10;

contract faucet {
    event Withdrawal(address indexed to, uint amount);
    event Deposit(address indexed from, uint amount);

    address owner;

    constructor() {
        owner = msg.sender;
    }

    modifier onlyOwner {
        require(msg.sender == owner, "only the contract owner may call this function");
        _;
    }

    function destroy() public onlyOwner {
        selfdestruct(payable(owner));
    }

    function withdraw(uint withdraw_amount) public {
        require(withdraw_amount <= 0.1 ether);

        require(
            address(this).balance >= withdraw_amount,
            "Insufficient balance in faucet for withdrawal request"
        );

        payable(msg.sender).transfer(withdraw_amount);

        emit Withdrawal(msg.sender, withdraw_amount);
    }

    receive() external payable {
         emit Deposit(msg.sender, msg.value);
    }
}