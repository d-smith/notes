const web3 = require("@solana/web3.js");
const PublicKey = web3.PublicKey;

(async () => {
    console.log('do it');

     //Connect
     const connection=new web3.Connection("http://localhost:8899","confirmed");
     const version = await connection.getVersion();
     console.log(version);

     const programId = 'EnaY2Ux2wGWBwZtGejrSi2hDXy9hyBJHQbbw2xB8G9wd';

    const publicKey = new PublicKey(programId);
    const programInfo = await connection.getAccountInfo(publicKey);
    console.log(programInfo);
})();