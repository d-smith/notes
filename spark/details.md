## Spark Details

### Key-Value Pair RDDs

Very common to think of things or store things as key value pairs. Data structures
for storing key value pairs are associative arrays (Python dictionaries,
Scala maps, etc)

In Spark, key-value tuples are called pair RDDs.

Having the key along with the value enables sorting, aggregating, and joining data.

keyBy transformation - accepts a function f for generating keys from an ordinary
RDD and maps each element into a tuple (f(element), element)

<pre>
val tranFile = sc.textFile("first-edition/ch04/ch04_data_transactions.txt")
val tranData = tranFile.map(_.split("#"))
var transByCust = tranData.map(tran => (tran(2).toInt, tran))
transByCust.keys.distinct().count()

transByCust.first
transByCust.countByKey()
transByCust.countByKey().values.sum
</pre>

You can use standard Scala things like map, sum, etc.

<pre>
val (cid, purch) = transByCust.countByKey().toSeq.sortBy(_._2).last
</pre>

Lookup values for a single key using lookup (must have memory to hold items)

<pre>
transByCust.lookup(53)
</pre>

mapValues - changes the values in the pair RDD without changing the values.

<pre>
transByCust = transByCust.mapValues(tran => {
  if(tran(3).toInt == 25 && tran(4).toDouble > 1)
    tran(5) = (tran(5).toDouble * 0.95).toString
  tran })
</pre>

flatMapValues - transformation to add values to keys

Add item 70 to customers who bought 5 or more of item 81 with
price 0.00 and quantity 1

<pre>
transByCust = transByCust.flatMapValues(tran => {
  if(tran(3).toInt == 81 && tran(4).toDouble >= 5) {
    val cloned = tran.clone()
    cloned(5) = "0.00"; cloned(3) = "70"; cloned(4) = "1";
    List(tran, cloned)
  } else
    List(tran)
  })
</pre>

reduceByKey - merge all the values of a key into a single value of the same type

foldByKey - very similar in functionality

foldByKey requires an additional parameter - a zero value, applied on the
first value of a key, and the result applied on the second value of the key

Note zero value might be applied several times (unlike scala fold) due to parallel nature
of RDDs

Who spent the most? Map values to contain only the prices, use foldByKey, then
sort the result and take the largest element

<pre>
val amounts = transByCust.mapValues(t => t(5).toDouble)
val totals = amounts.foldByKey(0)((p1, p2) => p1 + p2).collect()
totals.toSeq.sortBy(_._2).last
</pre>

Using aggregateByKey to group all values of a key

* aggregateByKey is similar to foldByKey and reduceByKey in that it takes a zero value
and merges values, but it also transforms the value to another type
* takes two args: a transform function to transform from V to U, and a merge
function to merging the transformed values into themselves


<pre>
val prods = transByCust.aggregateByKey(List[String]())((prods,tran) => prods ::: List(tran(3)),(prods1,prods2) => prods1 ::: prods2)
prods.collect()
</pre>

### Data Partitioning and Reducing Shuffling

Data partitioning is Spark's mechanism for dividing data between multiple nodes in a cluster.

* Think of a cluster as a interconnected nodes being used in parallel.
* Each piece of an RDD is called a partition. When you load a text file, the data is 
split into partitions distributed across the cluster nodes.
* Each RDD maintain a list of the partitions and an optional list of preferred locations
for computing the partitions.
* Recommendation: use 3 - 4 more partitions than there are cores in the cluster.
* Paritioning of RDDs is performed by Partitioner objects that assign a partition index 
index to each RDD element. Two implementations provided by spark - HashPartitioner and
RangePartitioner.

HashPartitioner

* Partition index based on element's Java hashcode (or key's hashcode for pair RDDs)
* Index is hashcode % number of partitions

Range Partitioner

* Partitions data of sorted RDDs into roughly equal ranges
* Data is sampled to determine the range boundaries

Customer Pair Partitioners

* Pair RDDs can be paritioned using custom Partitioners
* They are passed to pair transformations as an extra arg to overloaded
methods
* Pass the desired number of partitions, or via passing a custom Partitioner type

Shuffling

* Physical movement of data between nodes is called Shuffling
* Occurs when data from multiple partitions needs to be combined in order to
build partitions for a new RDD.

aggregateByKey

* Two functions - one to transform and merge two values into the target type, and one
to merge the transformed values
* The first function merges values within a partition, the second merges between partitions

Tasks that immediately precede and follow the shuffle are called map and reduce tasks, repsectively.
The results of map tasks are written to intermediate files, and read by reduce tasks. Besides
being written to disk, the data is sent over the network, so it is important to minimize shuffles.

Conditions that require shuffling:

* Occurs when explicitly changing paritioners - custom pair partitioners, providing new hash partitioners,
providing new number of paritions.
* Shuffle caused by removing partitioner - map and flatMap remove the RDD's 
partitioner, which doesn't cause a shuffle per se. If the following RDD is transformed,
however, a shuffle occurs (even with the default partitioner)

Transformations that cause shuffling after map or flatMap

* Pair RDD transformations that can change the RDD's partitioner:
  * aggregateByKey
  * foldByKey
  * reduceByKey
  * groupByKey
  * join
  * leftOuterJoin
  * rightOuterJoin
  * fullOuterJoin
  * subtractByKey
* RDD transformations 
  * subtract
  * intersection
  * groupWith
* sortByKey transformation
* partitionBy or coalesce with shuffle=true

External Shuffle Service

* An external shuffle service to optimize shuffling can be enabled via the 
spark.shuffle.service.enabled parameter (set to true)

### Repartitioning RDDs

Spark API allows reparitioning at runtime. Why would you want to do that?

* Sometimes the RDD needs to be repartitioned to spread the work out more evenly
across the cluster

Repartitioning of RDDs can be accomplished using partitionBy, coalesce, repartition, 
and repartitionAndSortWithPartition transformations.

### Mapping Data Within Partitions

Spark offers a way to apply functions not to the RDD as a whole, but
to each of its partitions separately. This can be an important tool in
optimizing transformations.

* mapParititions, mapPartitionsWithIndex, glom

glom gathers elements of each partition into an array and returns a new RDD
with those arrays as elements. The number of elements in the new RDD is equal
to the number of partitions.

<pre>
val list = List.fill(500)(scala.util.Random.nextInt(100))
val rdd = sc.parallelize(list, 30).glom()
rdd.collect()
rdd.count()
</pre>

### Joining, Sorting, and Grouping Data

Products with total sold... first key the transactions by product id

<pre>
val transByProd = tranData.map(tran => (tran(3).toInt,tran))
</pre>

Totals per product using the reduce by key transformation:

<pre>
val totalsByProd = transByProd.mapValues(t => t(5).toDouble).reduceByKey{case(tot1,tot2) => tot1 + tot2}
</pre>

Load product data

<pre>
val products = sc.textFile("first-edition/ch04/ch04_data_products.txt").map(
    line => line.split("#")
  ).map(
    p => (p(0).toInt,p)
  )
</pre>

The four classic join transformations. 