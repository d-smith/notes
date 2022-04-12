
const web3 = require("@solana/web3.js");

(async () => {
    console.log('do it');
    
    //Connect
    const connection=new web3.Connection("http://localhost:8899","confirmed");
    const version = await connection.getVersion();
    console.log(version);

    //Generate a keypair
    const from = web3.Keypair.generate();
    console.log(from.publicKey.toString());

    //Fund it
    const hash = await connection.requestAirdrop(from.publicKey, web3.LAMPORTS_PER_SOL);
    await connection.confirmTransaction(hash);


    //Get the balance
    const balance = await connection.getBalance(from.publicKey, 'confirmed');
    if (balance === 0 || balance === undefined) {
      console.log("account is unfunded");
    } else {
        console.log(`balance is ${balance} lamports`);
    }

    //Transfer to some rando...
    let randoKey = web3.Keypair.generate();
    console.log(`transfer to j random address ${randoKey.publicKey}`);


    const instructions = web3.SystemProgram.transfer({
        fromPubkey: from.publicKey,
        toPubkey:randoKey.publicKey,
        lamports:1000000
      });
      
      const signers = [
        from
      ];
  
      const transaction = new web3.Transaction().add(instructions);
  
      let sig = await web3.sendAndConfirmTransaction(connection, transaction, signers);
      console.log('SIGNATURE', sig);
})();