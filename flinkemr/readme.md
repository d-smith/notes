# Flinkemr - Notes related to running Flink on EMR

AWS docs - see [here](https://docs.aws.amazon.com/emr/latest/ReleaseGuide/emr-flink.html)

## Console Set Up

As per the AWS guide...

* Create cluster, advanced options
    * EMR 5.33.0
    * Add Flink 1.12.1
    * Next
    * One master on deman, two cores spot
    * Next
    * Cluster name c1
    * Next
    * Pick key pair
    * Create cluster

Wait for the cluster to boot... wait... wait... wait...

Go back into the console, steps - add el flinky jar

```
Exception in thread "main" java.lang.UnsupportedClassVersionError: com/amazonaws/services/kinesisanalytics/BasicStreamingJob has been compiled by a more recent version of the Java
```

This is for a java 11 compiler - is java on EMR that old?


