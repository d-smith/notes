# Monitorama Day 3

## Grafana Loki: like Prometheus, but for logs

Tom Wilkie

[Loki](https://github.com/grafana/loki) - like prometheus, but for logs

WHy is loki interesting:

* simple and cost effective to operate
* integrated with existing observability tools
* cloud native and airplane friendly

Simple...

* Trad approach indexes logs, full inverted index
* Hard to scale - opt for writes complicates queries, opt for query inhibits write performance
* Minimal index - logs from same target into the same stream, like prometheus time series w/labels, reduce search space for brute force searches
* Build on top of [cortex](https://github.com/cortexproject/cortex) for distributed systems

Integrated 

* Standard workflow - alert, dashboard, adhoc query, log aggregation, distributed tracing, fix
* Uses prometheus data types, same type of integration with pods in kubernates for example, etc.
* Uses prometheus service discovery, applies same relabeling rules, etc - align prom monitoring with logs
* Same metadata for metrics and log streams: easy to go to the logs associated with the underlying stuff that produces a specific metric

Airplane Friendly

* Easy to dev and test on a laptop

Another data point in the long lived, large cluster camp

## A Story of OpenTracing and OpenCensus

Jaana B. Dogan

Application as seen by the users - tip of the iceberg
Application as seen by the devs - all the stuff under the surface

[Open tracing](https://opentracing.io/) vs [open census](https://opencensus.io/)

OT - one vertical, one layer (API), looser coupling (snall scope), logs of languages, broad adoption

OC - many verticals, several layers (impl, inf, API), tighter coupling (frameworky), many languages, broad adoption

OT - not opinionated on implementation, performance, propagation formats, export data

OC - the opposite, opinionated on all of these

Choice might not be ideal, no clear winner

* what should you use?

Merging the projects... new projects.

* OpenTelemetry - the next mahor version of OT and OC

Telemetry verticals - logs, metrics, tracing
