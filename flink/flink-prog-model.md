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

## Stream Processing

* Data is received as a stream
* Continuously processed - process individual messages, patterns of messages

Basic Architecture

* Source data streamed into message transport
* Stream processing reads from message transport

Message Transport

* Buffer for event data 
* Performant and persistent
* Decouple multiple sources from processing of the streaming data

Examples: Kafka, MapR stream

Stream Processing

* high throughput, low lateny
* fault toleerance with low overhead
* managed out of order events
* easy to use, maintainable
* replay streams

data sources -> transformations -> sink

Directed acylic graph

## Apache Flink

* Open source, unified stream-processing framework for batch and streaming apps
* Unbounded streams, not micro-batches
* Stream dataflows in flink are transformed by user-defined operators
* Nodes in the graph represent operators

Flink applications

* User defined operators
* Streaming data flow
* Flink programs are inherently parallel and distributed 
* Streams are composed of stream partitions
* Operators are composed of operator subtasks

![graph broken down into subtasks](./subtasks.png)

* Number of subtasks refer to the parallelism of the operator
* Edges in graph - streams
* Forwarding (one to one) Pattern
* Redistributing Pattern - each operator subtask sends data to different target subtasks

## Environment Set Up

1.11.1 - Flink version used for the course

* Java and mvn prerequisites
* Download flink 1.11.x for scala
* Project dir - ApacheFlink/code/datasets ApacheFlink/code/output - put flink download in ApacheFlink and untar there
* Add a FLINK_HOME env var to .bash_profile

## Stream Transformations

* Stateless - transformations that are applied on a single stream entity
    * map, flatMap, filter
* Stateful - transformations which acumulate across multiple stream entities
    * assumulate data across a longer time interval - entire stream, window, per key, per operator