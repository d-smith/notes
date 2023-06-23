# Signing

Context - signing an MPC transaction is different as we don't have a private key, so we need
to take care of the details.

In normal txn where we have the key...

```
signedTx, err := types.SignTx(tx, types.NewEIP155Signer(chainID), privateKey)
if err != nil {
    log.Fatal(err)
}

fmt.Println("send tx")
err = client.SendTransaction(context.Background(), signedTx)
if err != nil {
    log.Fatal(err)
}
```

What does SignTx do?

```
func SignTx(tx *Transaction, s Signer, prv *ecdsa.PrivateKey) (*Transaction, error) {
	h := s.Hash(tx)
	sig, err := crypto.Sign(h[:], prv)
	if err != nil {
		return nil, err
	}
	return tx.WithSignature(s, sig)
}
```

Ok - so we can supply the transaction, and hash it directly. What does Sign do?

```
func Sign(digestHash []byte, prv *ecdsa.PrivateKey) (sig []byte, err error) {
	if len(digestHash) != DigestLength {
		return nil, fmt.Errorf("hash is required to be exactly %d bytes (%d)", DigestLength, len(digestHash))
	}
	seckey := math.PaddedBigBytes(prv.D, prv.Params().BitSize/8)
	defer zeroBytes(seckey)
	return secp256k1.Sign(digestHash, seckey)
}
```

Ok - looks like we should be able to have the MPC sign the hash, the use Transaction withSignature to get the signed transaction representation for broadcast to the blockchain.