Go [here](https://aws.amazon.com/cli/) for aws cli docs.

* Install ok? Try `aws ec2 describe-instances`
* Behind a proxy? Set both http_proxy and https_proxy (https_proxy is definitely
required, not sure about http_proxy).

List running instance ids: `aws ec2 describe-instances --query 'Reservations[*].Instances[*].[InstanceId]' --filters Name=instance-state-name,Values=running`
