const Faucet2 = artifacts.require("Faucet2");

module.exports = function(deployer) {
  // Command Truffle to deploy the Smart Contract
  deployer.deploy(Faucet2);
};