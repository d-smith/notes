# BitcoinJS Guide

Following along with [this](https://bitcoinjs-guide.bitcoin-studio.com/bitcoinjs-guide/v5/)

Clone repo, checkout v5 branch

Wallet set up

bash-3.2$ history
    1  bitcoin-cli
    2  alias bitcoin-cli='docker exec -it bitcoin bitcoin-cli'
    3  count=0
    4  wallets=(alice_1 alice_2 alice_3 bob_1 bob_2 bob_3 carol_1 carol_2 carol_3 dave_1 dave_2 dave_3 eve_1 eve_2 eve_3 mallory_1 mallory_2 mallory_3)
    5  cat wallets.json | jq -r '.[][] | (.wif // empty)' | while read -r wif; do bitcoin-cli importprivkey ${wif} ${wallets[count]}; ((count ++)); done
    6  ls
    7  generatetoaddress 101 bcrt1qnqud2pjfpkqrnfzxy4kp5g98r8v886wgvs9e7r
    8  bitcoin-cli generatetoaddress 101 bcrt1qnqud2pjfpkqrnfzxy4kp5g98r8v886wgvs9e7r
    9  bitcoin-cli scantxoutset start '["addr(bcrt1qnqud2pjfpkqrnfzxy4kp5g98r8v886wgvs9e7r)"]'
   10  history
