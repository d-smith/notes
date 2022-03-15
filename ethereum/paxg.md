## Notes

Deploy to ganache - update port in truffle-config.js
Removed the gas restriction
All tests not running yet, either against ganache-cli or ui

start ganache, add contract context via pointing to truffle-config.js, restart
truffle migrate
truffle console

let instance = await PAXGImplementation.deployed()