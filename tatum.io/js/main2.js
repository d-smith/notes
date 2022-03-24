const api = require('@tatumio/api-client');
let apiKey = process.env.APIKEY;
ta = api.TatumApi({apiKey: apiKey})
console.log(JSON.stringify(ta.blockchain.eth,null,2))