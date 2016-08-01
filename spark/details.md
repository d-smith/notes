## Spark Details

### Key-Value Pair RDDs

Very common to think of things or store things as key value pairs. Data structures
for storing key value pairs are associative arrays (Python dictionaries,
Scala maps, etc)

In Spark, key-value tuples are called pair RDDs.

Having the key along with the value enables sorting, aggregating, and joining data.

keyBy transformation

