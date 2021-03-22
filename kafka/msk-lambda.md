# MSK - Lambda Integration

Details from [here](https://docs.aws.amazon.com/lambda/latest/dg/msk-permissions.html)

Role - new role, add policy AWSLambdaMSKExecutionRole

Called the role MSK lambda

Used kafka vpc context, kafka sg

Create a topic, e.g. $ bin/kafka-topics.sh --create --zookeeper $ZK --replication-factor 3 --partitions 1 --topic LambdaTopic

./kafka-console-producer.sh --broker-list $BSS -producer.config client.properties --topic LambdaTopic


add msk trigger

* via console

The result...

Batch size: 100
Last processing result: PROBLEM: Lambda internal error. Please contact Lambda customer support.
Starting position: TRIM_HORIZON
Topic name: LambdaTopic

Normal consumer

Messages are accessible from the stream as per  ./kafka-console-consumer.sh --bootstrap-server $BSS -consumer.config client.properties --topic LambdaTopic --from-beginning


