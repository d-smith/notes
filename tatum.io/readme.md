# Tatum

https://tatum.io/

https://dashboard.tatum.io/

## Create a custodial wallet

https://docs.tatum.io/tutorials/how-to-build-custodial-wallet

Notes:

1. Generate bitcoin wallet

https://docs.tatum.io/rest/blockchain/b3A6NDE2NTc3NDc-generate-bitcoin-wallet

curl --request GET \
  --url https://api-eu1.tatum.io/v3/bitcoin/wallet \
  --header 'Content-Type: application/json' \
  --header "x-api-key: $APIKEY"

2. Create a ledger account

https://docs.tatum.io/rest/virtual-accounts/b3A6MzA2MjE3MTM-create-new-account

curl --request POST \
  --url https://api-eu1.tatum.io/v3/ledger/account \
  --header 'Content-Type: application/json' \
  --header "x-api-key: $APIKEY" \
  --data '{
  "currency": "SOL",
  "xpub": "xpub...",
  "customer": {
    "accountingCurrency": "USD",
    "customerCountry": "US",
    "externalId": "000001",
    "providerCountry": "US"
  },
  "compliant": false,
  "accountCode": "AC_1011_B",
  "accountingCurrency": "USD",
  "accountNumber": "000001"
}'

Can create multiple accounts...


3. Create deposit address for an account.

https://docs.tatum.io/rest/virtual-accounts/b3A6MzA2MjE3MzA-create-new-deposit-address

curl --request POST \
  --url https://api-eu1.tatum.io/v3/offchain/account/id/address \
  --header 'Content-Type: application/json' \
  --header "x-api-key: $APIKEY"

4. List accounts for the customer

curl --request GET \
  --url https://api-eu1.tatum.io/v3/ledger/account/customer/id \
  --header 'Content-Type: application/json' \
  --header "x-api-key: $APIKEY"


  Get the deposit addresses for the account

  curl --request GET \
  --url https://api-eu1.tatum.io/v3/offchain/account/id/address \
  --header 'Content-Type: application/json' \
  --header "x-api-key: $APIKEY"

  Internal transfer

  curl --location --request 
POST 'https://api-eu1.tatum.io/v3/ledger/transaction' \
--header 'x-api-key: YOUR_API_KEY ' \ 
--header 'Content-Type: application/json' \
--data-raw '{
  "senderAccountId": "5fad2aa1cac7f2e8aeac0e6b",
  "recipientAccountId": "5fbaca3001421166273b3779",    
  "amount": "0.000001"
}'

## JavaScript

Latest dev will not install, e.g.

```
npm install @tatumio/eth    
npm ERR! code ETARGET
npm ERR! notarget No matching version found for @tatumio/api-client@v2.0.1-alpha.202.
npm ERR! notarget In most cases you or one of your dependencies are requesting
npm ERR! notarget a package version that doesn't exist.

npm ERR! A complete log of this run can be found in:
npm ERR!     /Users/ds/.npm/_logs/2022-03-18T22_28_33_345Z-debug-0.log
```

Previous version looks sus, e.g.

```
npm i --save @tatumio/tatum
...
19 vulnerabilities (9 moderate, 10 high)
```

What about earlier v2 versions?

```
npm install @tatumio/eth@2.0.1-alpha.245
...
found 0 vulnerabilities
```