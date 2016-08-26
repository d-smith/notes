# Some Useful AWS CLI Examples

For sure use the jq tool for dealing with the responses: https://stedolan.github.io/jq/

Describe VPCs

<pre>
aws ec2 describe-vpcs
</pre>

Look as a specific VPC using its tag name:

<pre>
aws ec2 describe-vpcs --filter "Name=tag:Name,Values=vpc-single-public-subnet"
</pre>

Pull out the security group id:

<pre>
aws ec2 describe-vpcs --filter "Name=tag:Name,Values=vpc-single-public-subnet"|jq '.Vpcs[0].VpcId'
</pre>

aws ec2 describe-subnets


aws ec2 describe-security-groups \
--filters Name=group-name,Values=basic-security-group

Want the security group id of the basic-security-group?

aws ec2 describe-security-groups --filters Name=group-name,Values=basic-security-group |jq '.SecurityGroups[0].GroupId'



Launch an ubuntu 14.04 instance in US East 1 - ami-2d39803a

aws ec2 run-instances \
--dry-run \
--image-id ami-2d39803a \
--key-name FidoKey \
--instance-type t2.micro \
--network-interfaces '[ { "DeviceIndex": 0, "Groups": ["sg-123456"], "SubnetId": "subnet-123456", "DeleteOnTermination": true, "AssociatePublicIpAddress": true } ]'



