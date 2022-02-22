# solana

Install the tools - see [here](https://docs.solana.com/cli/install-solana-cli-tools)

```
sh -c "$(curl -sSfL https://release.solana.com/v1.9.6/install)"
```

Add this to .zshrc


```
export PATH="$HOME/.local/share/solana/install/active_release/bin:$PATH"
```

* create a wallet

```
solana-keygen new
```

keypair is written to $HOME/.config/solana/id.json

Now... how to send some SOL. Coinbase minimum is 1.12 SOL as of today, way more then a small test amount.

Moon pay will allow a minimum of 0.33 but is fussy about billing address...
FTX US? https://ftx.us

* validate the public key

solana-keygen verify public-key-val

* Connect a phantom wallet

https://mattmazur.com/2021/11/18/using-a-phantom-wallet-address-with-the-solana-cli/

Specifically to use my phantom wallet via the cli:

solana-keygen recover 'prompt:?key=0/0' --outfile ~/.config/solana/id.json

 % solana balance
0.15 SOL

https://spl.solana.com/token

Rust install 

curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh

cargo install spl-token-cli 

Create a fungible token:

spl-token create-token

spl-token create-account <token>

spl-token mint <token> 100000

To send some tokens, use spl-token transfer, may want the --fund-recipient and --allow-unfunded-recipient options

https://solscan.io/token/<token>


Shovels icon attribution

<a href="https://www.flaticon.com/free-icons/shovels" title="shovels icons">Shovels icons created by Eucalyp - Flaticon</a>

To add token...

Fork https://github.com/solana-labs/token-list

Under assets add a folder with the token address as the name

6HzndwRRT1Eamf8pFZxikuFPHcRkp6457fUcxFACZK7H

