Doccos - https://docs.aws.amazon.com/msk/latest/developerguide/what-is-msk.html

Cluster spec:

```
{
  "BrokerNodeGroupInfo": {
    "BrokerAZDistribution": "DEFAULT",
    "InstanceType": "kafka.t3.small",
    "ClientSubnets": [
      "subnet-0f84c3d238f543e2f",
      "subnet-0746d4caf4d3a9c4a",
      "subnet-029fa4efe5631162d"
    ],
    "SecurityGroups": [
      "sg-02fb953e4f34b12d4"
    ]
  },
  "ClusterName": "msk1",
  "EncryptionInfo": {
    "EncryptionInTransit": {
      "InCluster": true,
      "ClientBroker": "TLS"
    }
  },
  "LoggingInfo": {
      "BrokerLogs": {
        "CloudWatchLogs": {
        "Enabled": true,
        "LogGroup": "mksloggruppo"
        }
      }
  },
  "KafkaVersion": "2.2.1",
  "NumberOfBrokerNodes": 3
}
```
Create the cluster

```
aws logs create-log-group --log-group-name mksloggruppo
aws kafka create-cluster --cli-input-json file://msk1.json
```

Get cluster status

```
aws kafka describe-cluster --cluster-arn arn:aws:kafka:us-east-1:nnnnnnnnnnnn:cluster/msk1/b4a211ec-fb6a-4038-a6e4-436f6288bf92-13
```

Get bootstrap brokers

```
aws kafka get-bootstrap-brokers --cluster-arn xxxx
```

Delete cluster

```
aws kafka delete-cluster --cluster-arn arn:aws:kafka:us-east-1:nnnnnnnnnnnn:cluster/msk1/b4a211ec-fb6a-4038-a6e4-436f6288bf92-13
```

Kafka Client Set Up - see [here](https://docs.aws.amazon.com/msk/latest/developerguide/create-topic.html)


Shell set Up

```
export ZK=`aws kafka describe-cluster --cluster-arn "arn:aws:kafka:us-east-1:427848627088:cluster/msk1/6dd948de-285d-4234-9712-2bd656d98f8b-13"|jq '.ClusterInfo.ZookeeperConnectString'`
```

## Kinesis Analytics

Guide here = https://docs.aws.amazon.com/kinesisanalytics/latest/java/example-msk.html

Clone this - https://github.com/aws-samples/amazon-kinesis-data-analytics-java-examples

Package it - mvn package -Dflink.version=1.11.1

Make a bucket to host it, e.g. 
aws s3api create-bucket --bucket mantis-shrimp

create a topic

bin/kafka-topics.sh --create --zookeeper ZookeeperConnectString --replication-factor 3 --partitions 1 --topic AWSKafkaTutorialTopic

Also create AWSKafkaTutorialTopicDestination

producer / consumer set up here - https://docs.aws.amazon.com/msk/latest/developerguide/produce-consume.html



