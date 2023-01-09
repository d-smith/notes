# MetricsDAO - Web 3 Analytics Notes

Notes from the MetricsDAO Web 3 Analytics Async Course

Course available [here](https://docs.metricsdao.xyz/analyst-resources/web3-analytics-101-async-course)

## Segment 0

[Segment 0](https://docs.metricsdao.xyz/analyst-resources/web3-analytics-101-async-course/segment-0)

## Segment 1

[Segment 1](https://docs.metricsdao.xyz/analyst-resources/web3-analytics-101-async-course/segment-1)

[Publishing your work](https://github.com/readme/guides/publishing-your-work)

Video [here](https://drive.google.com/file/d/1msewm8hfEWOHQCVC96EZqYMLu-eCXHMp/view)

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

fact tables - things that happened, raw
ez - cleaned up, tailored
dim - reference data, not a view over time

Flipside docs - have info about each table

