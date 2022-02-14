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


