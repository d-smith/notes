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

