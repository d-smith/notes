const bitcoin = require('bitcoinjs-lib')
const { alice, bob } = require('./wallets.json')
const network = bitcoin.networks.regtest

const keyPairAlice1 = bitcoin.ECPair.fromWIF(alice[1].wif, network)

const p2wpkhAlice1 = bitcoin.payments.p2wpkh({ pubkey: keyPairAlice1.publicKey, network })
console.log('Previous output script:')
console.log(p2wpkhAlice1.output.toString('hex'))


const psbt = new bitcoin.Psbt({ network })
    .addInput({
        hash: 'e62eaa0ae8693ece0241438426c47ff03515247b2645aeb14814502299af481c',
        index: 1,
        witnessUtxo: {
            script: Buffer.from('0014' + alice[1].pubKeyHash, 'hex'),
            value: 1e8,
        },
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