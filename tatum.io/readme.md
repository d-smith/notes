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