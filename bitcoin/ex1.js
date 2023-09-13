const bitcoin = require('bitcoinjs-lib')
const { alice, bob } = require('./wallets.json')
const network = bitcoin.networks.regtest

const keyPairAlice1 = bitcoin.ECPair.fromWIF(alice[1].wif, network)

const psbt = new bitcoin.Psbt({network})
.addInput({
hash: '6fc4a8fb90c903076548517194ae994a02c8e2efd9fb63aa781603a4e07c8065',
index: 0,
nonWitnessUtxo: Buffer.from('020000000150548cbbe73e8569194c6e2f4addb137bd525888794a6f793bfbf96835beb53b010000006a47304402205ab413129e0b4bc52eaf4e32bc6d788ec13e5ddec553341f0f13f8a3d16cd33d022035f14b1ff4dd03629ca5f2f8ae8c004c490de051dc260417affe04236d68dca80121022d1c3d88abf3d3de00eaa06f9aea4cfbd9b6b84d08fddf14ad53773a11218df6fdffffff0200e1f505000000001976a914fb8820f35effa054399540b8ca86040d8ddaa4d588ac0c8bf123010000001976a9142d75994204fd8d6eb39579414213de0b62e1316888accf000000', 'hex')
}) 
.addOutput({
address: bob[1].p2pkh,
value: 999e5,
}) 

psbt.signInput(0, keyPairAlice1)
psbt.validateSignaturesOfInput(0)

psbt.finalizeAllInputs()

console.log('Transaction hexadecimal:')
console.log(psbt.extractTransaction().toHex())

