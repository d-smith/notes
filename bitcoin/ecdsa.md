# Some Rough Notes

Motivation - math behind signing and verifying bitcoin and Ethereum signatures

Basic outline

* Why are we taking about this?
* What is a signature?
* Finite fields
* Elliptic curves
* secp256k1
    * Params
    * private key - random number 
    * public key - pub key = random number * generator point
    * compression - symmetry of the equation
* Signature basics and the discrete log problem
* Signing and verifying

Useful link - https://cryptobook.nakov.com/asymmetric-key-ciphers/elliptic-curve-cryptography-ecc

Also: https://graui.de/code/elliptic2/

F107  for elliptic curve

https://graui.de/code/ffplot/

y = 15*x + 3
97

https://andrea.corbellini.name/ecc/interactive/reals-add.html


Generator point 15,13 on F17 A = 0, b = 7 - illustrate how multiplying the generator point up to r 