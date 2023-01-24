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


```
select * from ethereum.core.dim_labels
where address like '0x8484ef%'
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


* group by
* ehterscan.io for transaction details when investigating transactions, e.g. GALA currency

```
select currency_symbol,
sum(platform_fee) as total_platform_fee,
sum(platform_fee_usd) as total_platform_fee_usd
FROM
ethereum.core.ez_nft_sales
where platform_name='opensea'
AND
block_timestamp::date BETWEEN '2022-06-01'
  and '2022-06-30'
group by 1
order by 1 desc
```

```
select currency_symbol,
sum(platform_fee) as total_platform_fee,
sum(platform_fee_usd) as total_platform_fee_usd
FROM
ethereum.core.ez_nft_sales
where platform_name='opensea'
AND
block_timestamp::date BETWEEN '2022-06-01'
  and '2022-06-30'
group by 1
  having total_platform_fee_usd > 1000
order by 1 desc
```


```
select
  date_trunc('day', block_timestamp) as _date,
  case
    when currency_symbol = 'ETH' then 'ETH'
    when currency_symbol = 'WETH' then 'WETH'
    when currency_symbol = 'USDC' then 'USDC'
    else 'Other'
  end as currency_symbol,
  sum(platform_fee) as total_platform_fee,
  sum(platform_fee_usd) as total_platform_fee_usd
FROM
  ethereum.core.ez_nft_sales
where
  platform_name = 'opensea'
  AND block_timestamp::date BETWEEN '2022-06-01' and '2022-06-30'
group by
  1,
  2
  --  having total_platform_fee_usd > 1000
order by
  1 asc

```


CTE - common table expression

```

with opensea_sales as (
    select * FROM  ethereum.core.ez_nft_sales
    where block_timestamp::date BETWEEN '2022-01-01' and '2022-06-30'
    and platform_name = 'opensea'
)


select
  date_trunc('day', block_timestamp) as _date,
  case
    when currency_symbol = 'ETH' then 'ETH'
    when currency_symbol = 'WETH' then 'WETH'
    when currency_symbol = 'USDC' then 'USDC'
    else 'Other'
  end as currency_symbol,
  sum(platform_fee) as total_platform_fee,
  sum(platform_fee_usd) as total_platform_fee_usd
FROM
  opensea_sales

group by
  1,
  2
order by
    1, 4 desc
```

Learning strategies

* Take a look at completed bounties, think about how those were built in an
iterative fashion

Trust Wallet - https://trustwallet.com/

Metrics Dao - course has moved to https://docs.metricsdao.xyz/analyst-resources/web3-analytics-101-async-course


```
select platform_name, count(*) as count FROM
ethereum.core.ez_nft_sales
where event_type = 'sale'
  and block_timestamp::date between '2022-07-01'
  and '2022-07-31'
group by platform_name
order by count desc
```

```
select pool_name, count(*) as count
FROM
ethereum.core.ez_dex_swaps
where platform = 'sushiswap'
  and block_timestamp::date between '2022-07-01'
  and '2022-07-31'
group by pool_name
order by count desc
```


```
with top_10 as (
  select nft_to_address, count(*) as count
from 
ethereum.core.ez_nft_mints
where block_timestamp::date between '2022-07-01'
  and '2022-07-31'
group by nft_to_address
having count >= 20000
)
select sum(count)
from top_10
```
