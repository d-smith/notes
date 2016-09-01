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


