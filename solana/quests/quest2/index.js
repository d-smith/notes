const web3 = require("@solana/web3.js");
const connection=new web3.Connection(web3.clusterApiUrl("devnet"),"confirmed");
//For checking whether the connection is successfully made
console.log(connection._rpcEndpoint);

//Create a key pair

const userWallet=web3.Keypair.generate();
console.log(userWallet);