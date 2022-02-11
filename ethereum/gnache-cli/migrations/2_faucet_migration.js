const faucet = artifacts.require("faucet");

module.exports = function(deployer) {
  // Command Truffle to deploy the Smart Contract
  deployer.deploy(faucet);
};