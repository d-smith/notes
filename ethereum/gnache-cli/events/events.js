let Web3 = require('web3')
let web3 = new Web3(new Web3.providers.WebsocketProvider('ws://localhost:8545'))

/*
web3.eth.subscribe('newBlockHeaders', function (error, blockHeader) {
if (error) console.log(error)
console.log(blockHeader)
})
.on('data', function (blockHeader) {
    // alternatively we can log it here
    console.log(blockHeader)
})
*/
const fs = require('fs');
const contract = JSON.parse(fs.readFileSync('../build/contracts/faucet.json'));

//let faucet = require('../build/contracts/faucet.json');

const faucetContract = new web3.eth.Contract(
    contract.abi,
    contract.networks["1644613203137"].address
)

faucetContract.events.allEvents({
    fromBlock: 0
  }, function (error, event) {
    if (error) console.log(error)
    console.log(event)
  })