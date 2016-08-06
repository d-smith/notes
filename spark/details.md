## Spark Details

### Key-Value Pair RDDs

Very common to think of things or store things as key value pairs. Data structures
for storing key value pairs are associative arrays (Python dictionaries,
Scala maps, etc)

In Spark, key-value tuples are called pair RDDs.

Having the key along with the value enables sorting, aggregating, and joining data.

keyBy transformation

  1  val tranFile = sc.textFile("first-edition/ch04/ch04_data_transactions.txt")
  2  val tranData = tranFile.map(_.split("#"))
  3  var transByCust = tranData.map(tran => (tran(2).toInt, tran))
  4  transByCust.keys.distinct().count()
transByCust.first
transByCust.countByKey()
transByCust.countByKey().values.sum
