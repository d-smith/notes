---
title: "API Call Data"
output: html_document
---

```{r}
library(RPostgreSQL)
library(dplyr)
knitr::read_chunk('r-datasource.R')
```

This report is based on API call data scraped from Sumologic logs. 


```{r cache=FALSE}
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
last25 <- cr <- tbl(rsds,sql("select name, aud from callrecord order by logtime desc limit 25"))
as.data.frame(last25 %>%
filter(aud != ''))
```
