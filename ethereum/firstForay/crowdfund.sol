contract CrowdFund {

    address public beneficiary;
    uint256 public goal;
    uint256 public deadline;

    struct Funder {
        address addr;
        uint256 contribution;
    }

    Funder[] funders;

    function CrowdFund(address _beficiary, uint256 _goal, uint256 _deadline) {
        beneficiary = _beficiary;
        goal = _goal;
        deadline = now + _deadline;
    }

    function contribute() payable {
        funders.push(Funder(msg.sender,msg.value));
    }

    function payout() {
        if(this.balance > goal && now > deadline) beneficiary.send(this.balance);
    }

    function refund() {
        if(msg.sender != beneficiary) throw;
        uint256 index = 0;
        while(index < funders.length) {
            funders[index].addr.send(funders[index].contribution);
            index++;
        }
    }
}
