---
title: "API Call Data"
output: html_document
---

```{r, echo=FALSE}
library(RPostgreSQL)
library(dplyr)
knitr::read_chunk('r-datasource.R')
```

This report is based on API call data scraped from Sumologic logs. 


```{r, echo=FALSE, cache=FALSE}
<<redshiftDataSource>>
cr <- tbl(rsds,sql("select * from callrecord where txnid <> ''"))
```

Total calls, all environments:

```{r, echo=FALSE}
count(cr)
```

API call breakdown - all environments:

```{r, echo=FALSE}
as.data.frame(cr %>%
filter(name != 'xtracApi' && name != 'unspecified timer' && name != '') %>%
group_by(name) %>%
summarize(count=n()) %>%
arrange(desc(count)))
```

Last 25 API Calls

```{r, echo=FALSE}
last25  <- tbl(rsds,sql("select name, aud from callrecord order by logtime desc limit 25"))
as.data.frame(last25 %>%
filter(aud != ''))
```

## HackOrg2 Activity

```{r, echo=FALSE}
activity <- tbl(rsds,sql("select name, date_trunc('day', callrecord.logtime), count(*) from callrecord where aud = '6db4c856-6663-4cf9-a218-3a9ed9a23dab' and logtime >= '2016-10-01' group by 2,1 order by 2,1"))
as.data.frame(activity %>%
                filter(name != ''))
```


## HackOrg3 Activity

```{r, echo=FALSE}
activity <- tbl(rsds,sql("select name, date_trunc('day', callrecord.logtime), count(*) from callrecord where aud = 'c3cf1378-1aeb-494b-b3a9-86eabe5248bc' and logtime >= '2016-10-01' group by 2,1 order by 2,1"))
as.data.frame(activity %>%
                filter(name != ''))
```

## HackOrg4 Activity

```{r, echo=FALSE}
activity <- tbl(rsds,sql("select name, date_trunc('day', callrecord.logtime), count(*) from callrecord where aud = '2a4534f0-82f0-4f49-a076-a57fbefaa667' and logtime >= '2016-10-01' group by 2,1 order by 2,1"))
as.data.frame(activity %>%
                filter(name != ''))
```

## HackOrg5 Activity

```{r, echo=FALSE}
activity <- tbl(rsds,sql("select name, date_trunc('day', callrecord.logtime), count(*) from callrecord where aud = '65e2a8f0-cb38-415b-a117-830f55d68158' and logtime >= '2016-10-01' group by 2,1 order by 2,1"))
as.data.frame(activity %>%
                filter(name != ''))
```

## HackOrg6 Activity

```{r, echo=FALSE}
activity <- tbl(rsds,sql("select name, date_trunc('day', callrecord.logtime), count(*) from callrecord where aud = '2b1736c4-4dbe-4a9c-858c-45dbbe4251e1' and logtime >= '2016-10-01' group by 2,1 order by 2,1"))
as.data.frame(activity %>%
                filter(name != ''))
```
