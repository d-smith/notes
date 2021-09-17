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

## Flink Architecture

* Flink application - any program that spawns a flink job
* Execution of a flink job can be
    * local jvm
    * remote cluster
        * job manager
        * task managers

Job Manager

* Coordinates the distributed execution of flink applications
* schedules tasks, manages failures
* Coordinate checkpoints and recovery
* Recieves JobGraph (DAG of operators and intermediate results), transforms to execution graph
* Execution graph is parallelized

Job Manager Components

* ResourceManager
    * Allocation, deallocation, and provisioning of cluster
    * Manages task slots
* Dispatcher
    * REST interface to submit applications
    * Runs flink webui
* JobMaster
    * Managed execution of a single job

TaskManagers 

* One of more TaskManagers (workers) execute the tasks of the dataflow
* Buffer and exchange data streams
* Use units of scheduling called task slots

Task Slots

* Represent a fixed subset of resources of a task manager
* Smallest unit of resource scheduling
* Number of task slots indicate concurrent processing tasks
* Multiple operators can work within the same slot

Task

* Basic unit of execution
* Where each parallel instance of an operator is execute

Executing and Monitoring Streaming Queries

* Start the cluster - `*$FLINK_HOME/bin/start-cluster.sh`
* Go localhost:8081
* Submit a sample

```
$FLINK_HOME/bin/start-cluster.sh

$FLINK_HOME/bin/flink run $FLINK_HOME/examples/streaming/WordCount.jar \
--input /Volumes/seagate/ApacheFlink/code/datasets/words.txt \
--output /Volumes/seagate/ApacheFlink/code/datasets/output/wordcount.txt

$FLINK_HOME/bin/stop-cluster.sh
```

## Configuration Settings 

See flink.yml in conf/

```
$FLINK_HOME/bin/flink run $FLINK_HOME/examples/streaming/TopSpeedWindowing.jar

tail -f $FLINK_HOME/log/flink-*-taskexecutor-*.out

$FLINK_HOME/bin/flink list -m 127.0.0.1:8081
```

Example - change number of task slots from 1 to 4, parallelism default from 1 to 2, set rest.port frpm 8081 to 8088

```
$FLINK_HOME/bin/flink run -p 3 $FLINK_HOME/examples/streaming/TopSpeedWindowing.jar 
```


## Flink Project Set Up

mvn archetype:generate                               \
  -DarchetypeGroupId=org.apache.flink              \
  -DarchetypeArtifactId=flink-quickstart-java      \
  -DarchetypeVersion=1.11.1 \
  -DgroupId=my-flink-project \
  -DartifactId=my-flink-project \
  -Dversion=1.0-SNAPSHOT \
  -Dpackage=ds.org.helloflink \
  -DinteractiveMode=false

In IntelliJ, to run our program, we add the contents of the $FLINK_HOME/lib as module dependencies to the project via File > ProjectSTructure > Modules > Dependencies > Plus

To run HighSpeedDetection, start nc -l 9000, then the app. In nc submit a line at a time with car name and speed, e.g. Toyota, 67

netstat -ant -p TCP|grep LISTEN