# MetricsDAO - Web 3 Analytics Notes

Notes from the MetricsDAO Web 3 Analytics Async Course

Course available [here](https://docs.metricsdao.xyz/analyst-resources/web3-analytics-101-async-course)

## Segment 0

[Segment 0](https://docs.metricsdao.xyz/analyst-resources/web3-analytics-101-async-course/segment-0)

## Segment 1

[Segment 1](https://docs.metricsdao.xyz/analyst-resources/web3-analytics-101-async-course/segment-1)

[Publishing your work](https://github.com/readme/guides/publishing-your-work)

Video [here](https://drive.google.com/file/d/1msewm8hfEWOHQCVC96EZqYMLu-eCXHMp/view)

* Metrics dao poaps - see them via app.poap.xy
* Gnosis network

Tools - Flipside, Dune, Foorprint, DappLooker

docs.metricsdao.xyz

```
select count(*) FROM ethereum.core.fact_transactions
  where block_timestamp::date BETWEEN '2022-06-01'
  and '2022-06-30'
```

```
select count(DISTINCT from_address)  FROM ethereum.core.fact_transactions
  where block_timestamp::date BETWEEN '2022-06-01'
  and '2022-06-30'
```

```
select count(*) FROM  ethereum.core.ez_dex_swaps
  where 
  platform = 'uniswap-v2' and
  event_name = 'Swap' and
  block_timestamp::date BETWEEN '2022-06-01'
  and '2022-06-30'
```

```
select count(*) FROM
ethereum.core.ez_nft_sales
  where platform_name='opensea'
AND
block_timestamp::date BETWEEN '2022-06-01'
  and '2022-06-30'
```

fact tables - records of things that happened, raw
ez - cleaned up, tailored
dim - reference data, not a view over time

Flipside docs - have info about each table

Decentralized Networks

* Immutable
* Tamper proof
* Secure

Some standards

* ERC 20 - fungable tokens
* ERC 721 - non-fungable tokens
* ERC 777 - defines functions to send tokens on behalf of another address, contract, or regular account
* ERC 1155 - stanard for contracts that manage multiple token types

Flipside

* Prepared data that can be easily queries using SQL

Metrics DAO

* Bounties
* Take a look at the dashboards on metrics dao, dune to see the queries behind the charts, etc.


EZ Tables

* Example - dex swaps
* Break out json from transactions into easy to query tables, tailored for use cases
* Standard items for analysis - in and out in terms of tokens and dollar amounts, addresses,
contract address
* NFTs - providers, fees, price, buyers and sellers
* NFT tables will have different data than swaps tables

A16Z - https://debank.com/profile/0x66b870ddf78c975af5cd8edc6de25eca81791de1


Flipside - runs on snowflake
Dune - runes on postgres

Dune

* Focus on protocol-specific tables

## Segment 2

[Video](https://drive.google.com/file/d/1L60fdvgRLtxCZgltVfbkrOByc4ljDn-c/view)


