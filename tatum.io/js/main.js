const te = require('@tatumio/eth');
let apiKey = process.env.APIKEY;
ethSdk = te.TatumEthSDK({apiKey: apiKey});


console.log(JSON.stringify(ethSdk.offchain,null,2));


let check = async() => {
    //console.log(JSON.stringify(ethSdk.tatum, null, 2))
    await ethSdk.offchain.depositAddress.checkExists('LepMzqfXSgQommH2qu3fk7Gf5xgoHQsP1b');
}

let run = async() => {
    try {
        await check();
    } catch (e) {
        console.log("ruh roh scooby do")
        console.error(e);
    } finally {
        console.log('We do cleanup here');
    }
}
    
            

run();