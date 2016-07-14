$ aws emr create-cluster --name sparky --ami-version 3.10.0 --applications Name=Spark --ec2-attributes KeyName=FidoKeyPair --instance-type m1.medium --instance-count 3 --use-default-roles
{
    "ClusterId": "j-3UA8770U5JAIU"
}


