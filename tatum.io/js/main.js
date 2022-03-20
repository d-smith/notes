const te = require('@tatumio/eth');
let apiKey = process.env.APIKEY;
ethSdk = te.TatumEthSDK({apiKey: apiKey});


let check = async() => {
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