truffle compile
truffle deploy
truffle console

 Faucet2.deployed().then(i => {Faucet2Deployed = i})
 Faucet2Deployed.send(web3.utils.toWei("1","ether")).then(res => {console.log(res.logs[0].event, res.logs[0].args) })

let res = await Faucet2Deployed.withdraw(web3.utils.toWei("0.1","ether"));

Faucet2Deployed.getPastEvents("allEvents", {fromBlock:"earliest"})