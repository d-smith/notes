## Spark Basics

RDD - reliable distributed data set

An RDD is

* immutable
* resiliant (fault tolerant)
* distributed (spread out across multiple nodes)

Spark fault tolerance - spark can heal an RDD in case of node failure.
This is done by logging the transformations used to build the data set.

RDD lineage  - the transformations and their order needed to produce the RDD

Two types of RDD operations:

* Transformations - operations that produce a new RDD by performing some
data manipulation on another RDD
* Actions - trigger a computation in order to a return a result to a calling
program or to perform some actions against an RDD's elements

Trasformations are evaluated lazily - they are not triggered until needed for some
action