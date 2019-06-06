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

* OpenTelemetry - the next major version of OT and OC

Telemetry verticals - logs, metrics, tracing

## Tracing at Dropbox: A Story of Incremental Improvements

Ross Delinger

Span
Trace
Integrations

Time series database - good for aggregates, trends, longer term analytics, hard to dig into a problem

Tracing - want to drill down into anamolous behavior

Dropbox

tens of thousands of spans per second, sampling rate of 0.3%, head sampling

Initially used zipkin... never reached critical mass, didn't evangelize it, maintain, no consistent ux, etc.

Next... fixed 3 years of rot, stabilized the ingestion pipline, stabilization of the interface.

Then... integrations

* Feature partity for go and python, support for the desired transports
* Devs could use the tools in all parts of the stack

Then... moved off Zipkin

* Picked Jaeger + in memory to start, switched to cassandra as the backend storage system
* Moved from Kafka to Kaiju

How to transition

* Leverage the interface to haul out stuff behind the interface over time
* Feature flags to move traffic gradually

## Fight, Flight, or Freeze — Releasing Organizational Trauma

Matty Stratton

Rest and Digest - parasympatheic nervous system
Flight or Fight - sympatheic nervous system
Freeze - overloaded nervous system

Hyperarousal 

* flight or fight, constantly worried about outages
* focus on production support teams

Hypoarousal

* freeze, avoid change

Post mortems - did your organization become unregulated?

* Resiliant organizations - routine threats to business are things we used and can handle (we're regulated).

What can we do?

* Game days - keep them low pressure and safe
* Need guidance

Cognitive Bias

* Over Generalization
* Fortune telling - if we know enough, we can predict the future
* Control fallacies - we have no control... or we have complete control


Resilient stringth is the opposite of helplessness

* Culture of blame will create feelings of helplessness

https://speaking.mattstrattib.com

## How to get the 30,000 ft view, 1 ft view and everything in between without breaking the bank!

Martin Mao

[M3](https://github.com/m3db/m3) - open source metrics system from uber

* Compare series by tags
* 11x storage compression over cassandra rep
* down sample - store high res metrics for a set amount of time, then store at lower res later

## Practical Anomaly Detection using Prometheus

Andrew Newdigate

Humans - very good at visual anomoly dectection

Refer to the talk for the details on how to query prometheus for anamolies.