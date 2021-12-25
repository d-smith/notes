contract owned {
  address public owner;

  function owned() {
    owner = msg.sender;
  }

  modifier onlyOwner() {
    if(msg.sender == owner) {
      _;
    }
  }
}

contract helloworld is owned {
  string public message;

  function setMessage(string _message) onlyOwner() {
    message = _message;
  }
}
