## Spark Details

### Key-Value Pair RDDs

Very common to think of things or store things as key value pairs. Data structures
for storing key value pairs are associative arrays (Python dictionaries,
Scala maps, etc)

In Spark, key-value tuples are called pair RDDs.

Having the key along with the value enables sorting, aggregating, and joining data.

keyBy transformation

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