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

List subnets for a VPC:

<pre>
aws ec2 describe-subnets --filter "Name=vpc-id,Values=vpc-c5ed77a0"
</pre>

List a specific subnet for a vpc and extract the subnet id:

<pre>
aws ec2 describe-subnets --filter "Name=vpc-id,Values=vpc-c5ed77a0" --filter "Name=tag:Name,Values=public-subnet" | jq '.Subnets[0].SubnetId'
</pre>

List a specific security group (here we assume it's a unique name, might have
to account for the same name scoped to different VPCs)

<pre>
aws ec2 describe-security-groups \
--filters Name=group-name,Values=basic-security-group
</pre>

Want the security group id of the basic-security-group?

<pre>
aws ec2 describe-security-groups --filters Name=group-name,Values=basic-security-group |jq '.SecurityGroups[0].GroupId'
</pre>


Launch an ubuntu 14.04 instance in US East 1 - ami-2d39803a. To run this I
need the security group id and the subnet id associated with the 
VPC I want to run the instance in.



aws ec2 run-instances \
--image-id ami-2d39803a \
--key-name FidoKeyPair \
--instance-type t2.micro \
--network-interfaces '[ { "DeviceIndex": 0, "Groups": ["sg-c2728ca6"], "SubnetId": "subnet-fcbd7fd7", "DeleteOnTermination": true, "AssociatePublicIpAddress": true } ]'

Grab the public IP address for the instance you launched:

<pre>
ws ec2 describe-instances --instance-ids i-08f67219e73b99f3d | jq '.Reservations[0].Instances[0].PublicDnsName'
</pre>

Or, tag the instance:

<pre>
aws ec2 create-tags --resources \
`aws ec2 run-instances \
--image-id ami-2d39803a \
--key-name FidoKeyPair \
--instance-type t2.micro \
--network-interfaces '[ { "DeviceIndex": 0, "Groups": ["sg-c2728ca6"], "SubnetId": "subnet-fcbd7fd7", "DeleteOnTermination": true, "AssociatePublicIpAddress": true } ]' \
| jq -r ".Instances[0].InstanceId"` --tags "Key=Name,Value=my-shintinz"
</pre>

And grab the address via the tag

<pre>
aws ec2 describe-instances --filter "Name=tag:Name,Values=my-shintinz" | jq '.Reservations[0].Instances[0].PublicDnsName'
</pre>

