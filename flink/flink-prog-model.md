# Notes from Conceptualizing the Processing Model for Apache Flink

## Batch and Stream Processing

* Flink can handle both - bounded vs unbounded.
* Collect, analyze, extract - batch
* Monitor, process, extract - streaming

Batch

* Bounded data
* Processes in batches
* High latency ok
* Periodic updates as jobs complete
* Data order unimportant
* single global state of the world
* processing code "knows" all the data

Streaming

* unbounded data
* processed continuously, as data is receieved
* low latencies
* continuous updates
* Ordering important, out of order arrival tracked
* No global state, only history of events received
* Processing code does not know what lies ahead
