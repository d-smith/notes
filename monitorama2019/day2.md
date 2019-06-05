## Monitorama

[Day 2 stream](https://youtu.be/65-6aDlR_WE)

## Software Supply Chain Observability with Grafeas and Kritis

Aysylu Greenberg

[Grafeas](https://github.com/grafeas/grafeas) - from the Greek word for Scribe 

Grafeas backed storage used for metadata knowledge based for software supply chain

[Kritis](https://github.com/grafeas/kritis) - from Greek work for Judge

* Kubernetes admission controller
* Queries Grafeas for metadata attributes to check against admission policies
* Write attestations into grafeas - used in scale up and restart scenarios to allow system to continue to function if policies and/or scan info changed since initial admission.

Observability with Kritis - when did the image get deployed, when did it pass the scan, when did the image stop satisfying policy, etc

Kind specific schemas

## Logs, Metrics, and the Evolution of Observability at Coinbase
Luke Demi

Learn

Log 
everything
arbitrarily, but
rely on
new relic, primarily


Wanted to know when and why services were breaking

Facebook [Scuba](https://research.fb.com/publications/scuba-diving-into-data-at-facebook/)

First take away: use structured log events

Conventional wisdom: 3 pillars of observability

* Being sold three different products - metrics, logging, tracing

Metrics don't give you context needed for investigation
Predefined aggregates limit the questions you can ask in the future

MELT - metrics, events, logging, tracing

Author - recommends doubling down on events and tracing

## What can network teach you about your service?

Sergey Fedorov

How to improve things: experiment (A|B), scale

* Faster iterations - better results

Network - not a black box

DNS | TCP | TLS | First byte | Download

You can pick you DNS provider, you can adjust your tcp settings, etc.

How to experiment... synthetic monitoring: full control, flexibiliy, but does not represent all clients.

Agent/probe based - run the experiments from the user device. Download recipe, test and measure, visualize

[Probnik](https://github.com/netflix/probnik)

## Schema On Read and the New Logging Way

Dave Josephsen


## Observability: Superpowers for Developers

Christine Yen

Incidents - change is the most common trigger

* Developers often the cause of chaos in production systems

DevOps

* First wave - getting ops folks to code
* Second wave - teaching devs to own their code in prod

* [Blog post](https://m.subbu.org/incidents-trends-from-the-trenches-e2f8497d52ed)

Dev process

* write -> test -> commit -> release -> observe

shift observe left - better feedback in dev lifecycle

* Observability shares much with testing

```console
write - test
   ^     |
   |<----|
   |     |
   |<---observe
```

Devs good with functionality - expected vs actual.

* Good instincts in place - state assumptions, be able to verify them
* Extend these principles to production behavior.
    * Think about how things might fail, not how they failed
    * Build up an ops sensitivity

What does it mean for developers to embrace observability as a super power?

* Speak the language of production
* See through production to how it maps to their code
* Run experiments and check hypotheses

Extend the dev environment to make production constructs and tools familiar.

Feature flags plus observability - see how the new feature behaves

Building a shared brain

Ops - think about how to share prod knowledge, share the power, enable observability

Dev - embrace observabiity, build things based on facts, not intuition

## Statistical Aspects of Distributed Tracing

Joe Ross

Traces

Span - service that did the work, start time, stop time, trace it belongs to

Data relevance problem - what traces snould you leep (coherent sampling)?

* Incoherent - samples spans from different traces

Head based sampling - keep a given trace with some probability p, e.g. keep 1 in 100 traces

* Make the decision at the head of the trace (keep all the spans with the given trace id)
* Uniform distribution makes the stats simple

Tail based - decide while you're running, find interesting/anomolous traces

Interesting stuff - latency, error rate, frequency, some structure aspects (for this path what's the latency)

Keep the simplicity of the head and the statistical properties, with some of the tail advantages.

divvy up the trace budget to weight the samples based on endpoint frequency (prefer infrequent), divvy up endpoint  budget based on latency buckets

* forecasting

## How Capital One used a Data Driven approach and Governance to improve their observability in the Cloud

Amit Sheth and Prasad Konduri

Capital One Transformation

* All in on cloud - zero data center mandate 2020
* Fully embrace open and inner sourcing
* Build all services based on microservices, API enabled, etc
* Build data platform in cloud (analytics, stream)
* Machine learning (fraud detection, credit card approvals, etc.)
* Agile, DevOps, push button deployments

How do you know each app has the right monitoring in place?

* Monitoring governance

Monitoring governance

* Establish standards
* Capture set ups, alerts, config - capture and compare to standards
* Engage the right people