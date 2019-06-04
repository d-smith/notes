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


## What can network teach you about your service?
Sergey Fedorov

## Schema On Read and the New Logging Way
Dave Josephsen


## Observability: Superpowers for Developers

Christine Yen

write test commit release observe

shift observe left

write - test
   |<----|
         |
      observe

* Speak the language of production
* Sees through prod to code
* Experiments + Checks Hypothesis

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