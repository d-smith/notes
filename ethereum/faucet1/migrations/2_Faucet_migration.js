const Faucet = artifacts.require("Faucet");

module.exports = function(deployer) {
  // Command Truffle to deploy the Smart Contract
  deployer.deploy(Faucet);
};