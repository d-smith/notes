One time step:

$ aws emr create-default-roles 


$ aws emr create-cluster --name sparky --ami-version 3.10.0 --applications Name=Spark --ec2-attributes KeyName=FidoKeyPair --instance-type m1.medium --instance-count 3 --use-default-roles
{
    "ClusterId": "j-2VGKO25FTPE15"
}
$ aws emr list-clusters
{
    "Clusters": [
        {
            "Status": {
                "Timeline": {
                    "CreationDateTime": 1468506436.972
                }, 
                "State": "STARTING", 
                "StateChangeReason": {
                    "Message": "Provisioning Amazon EC2 capacity"
                }
            }, 
            "NormalizedInstanceHours": 0, 
            "Id": "j-2VGKO25FTPE15", 
            "Name": "sparky"
        }, 
        {
            "Status": {
                "Timeline": {
                    "EndDateTime": 1468505958.058, 
                    "CreationDateTime": 1468505865.5
                }, 
                "State": "TERMINATED_WITH_ERRORS", 
                "StateChangeReason": {
                    "Message": "EMR service role arn:aws:iam::930295567417:role/EMR_DefaultRole is invalid", 
                    "Code": "VALIDATION_ERROR"
                }
            }, 
            "NormalizedInstanceHours": 0, 
            "Id": "j-3UA8770U5JAIU", 
            "Name": "sparky"
        }
    ]
}
$ aws emr list-instances --cluster-id j-2VGKO25FTPE15
{
    "Instances": [
        {
            "Status": {
                "Timeline": {
                    "CreationDateTime": 1468506448.256
                }, 
                "State": "AWAITING_FULFILLMENT", 
                "StateChangeReason": {}
            }, 
            "InstanceGroupId": "ig-1COV0ZJDEGDQ5", 
            "Id": "ci-1ZH4IKWV23VRB", 
            "EbsVolumes": []
        }, 
        {
            "Status": {
                "Timeline": {
                    "CreationDateTime": 1468506448.256
                }, 
                "State": "AWAITING_FULFILLMENT", 
                "StateChangeReason": {}
            }, 
            "InstanceGroupId": "ig-1COV0ZJDEGDQ5", 
            "Id": "ci-M9RVX2GVZW8F", 
            "EbsVolumes": []
        }
    ]
}

...

 $ aws emr list-instances --cluster-id j-2VGKO25FTPE15
{
    "Instances": [
        {
            "Status": {
                "Timeline": {
                    "ReadyDateTime": 1468507106.473, 
                    "CreationDateTime": 1468506448.256
                }, 
                "State": "RUNNING", 
                "StateChangeReason": {}
            }, 
            "Ec2InstanceId": "i-c9f1bf4f", 
            "EbsVolumes": [], 
            "PublicDnsName": "ec2-54-90-154-139.compute-1.amazonaws.com", 
            "PrivateDnsName": "ip-10-71-138-109.ec2.internal", 
            "PublicIpAddress": "54.90.154.139", 
            "InstanceGroupId": "ig-1COV0ZJDEGDQ5", 
            "Id": "ci-1ZH4IKWV23VRB", 
            "PrivateIpAddress": "10.71.138.109"
        }, 
        {
            "Status": {
                "Timeline": {
                    "ReadyDateTime": 1468507106.472, 
                    "CreationDateTime": 1468506448.256
                }, 
                "State": "RUNNING", 
                "StateChangeReason": {}
            }, 
            "Ec2InstanceId": "i-c8f1bf4e", 
            "EbsVolumes": [], 
            "PublicDnsName": "ec2-23-22-166-167.compute-1.amazonaws.com", 
            "PrivateDnsName": "ip-10-239-36-230.ec2.internal", 
            "PublicIpAddress": "23.22.166.167", 
            "InstanceGroupId": "ig-1COV0ZJDEGDQ5", 
            "Id": "ci-M9RVX2GVZW8F", 
            "PrivateIpAddress": "10.239.36.230"
        }, 
        {
            "Status": {
                "Timeline": {
                    "ReadyDateTime": 1468507044.27, 
                    "CreationDateTime": 1468506466.161
                }, 
                "State": "RUNNING", 
                "StateChangeReason": {}
            }, 
            "Ec2InstanceId": "i-03094085", 
            "EbsVolumes": [], 
            "PublicDnsName": "ec2-23-22-184-123.compute-1.amazonaws.com", 
            "PrivateDnsName": "ip-10-238-236-58.ec2.internal", 
            "PublicIpAddress": "23.22.184.123", 
            "InstanceGroupId": "ig-24G3JT3544OX2", 
            "Id": "ci-TYFCKDA05X6K", 
            "PrivateIpAddress": "10.238.236.58"
        }
    ]
}

aws emr describe-cluster --cluster-id j-2VGKO25FTPE15
{
    "Cluster": {
        "Status": {
            "Timeline": {
                "ReadyDateTime": 1468507111.216, 
                "CreationDateTime": 1468506436.972
            }, 
            "State": "WAITING", 
            "StateChangeReason": {
                "Message": "Cluster ready after last step completed."
            }
        }, 
        "Ec2InstanceAttributes": {
            "EmrManagedMasterSecurityGroup": "sg-eaca39fc", 
            "IamInstanceProfile": "EMR_EC2_DefaultRole", 
            "Ec2KeyName": "FidoKeyPair", 
            "Ec2AvailabilityZone": "us-east-1d", 
            "EmrManagedSlaveSecurityGroup": "sg-1cce3d0a"
        }, 
        "Name": "sparky", 
        "ServiceRole": "EMR_DefaultRole", 
        "Tags": [], 
        "TerminationProtected": false, 
        "RunningAmiVersion": "3.10.0", 
        "NormalizedInstanceHours": 6, 
        "InstanceGroups": [
            {
                "RequestedInstanceCount": 1, 
                "Status": {
                    "Timeline": {
                        "ReadyDateTime": 1468507044.27, 
                        "CreationDateTime": 1468506436.973
                    }, 
                    "State": "RUNNING", 
                    "StateChangeReason": {
                        "Message": ""
                    }
                }, 
                "Name": "MASTER", 
                "InstanceGroupType": "MASTER", 
                "EbsBlockDevices": [], 
                "ShrinkPolicy": {}, 
                "Id": "ig-24G3JT3544OX2", 
                "Configurations": [], 
                "InstanceType": "m1.medium", 
                "Market": "ON_DEMAND", 
                "RunningInstanceCount": 1
            }, 
            {
                "RequestedInstanceCount": 2, 
                "Status": {
                    "Timeline": {
                        "ReadyDateTime": 1468507111.297, 
                        "CreationDateTime": 1468506436.973
                    }, 
                    "State": "RUNNING", 
                    "StateChangeReason": {
                        "Message": ""
                    }
                }, 
                "Name": "CORE", 
                "InstanceGroupType": "CORE", 
                "EbsBlockDevices": [], 
                "ShrinkPolicy": {}, 
                "Id": "ig-1COV0ZJDEGDQ5", 
                "Configurations": [], 
                "InstanceType": "m1.medium", 
                "Market": "ON_DEMAND", 
                "RunningInstanceCount": 2
            }
        ], 
        "Applications": [
            {
                "Version": "2.4.0", 
                "Name": "hadoop"
            }, 
            {
                "Name": "Spark"
            }
        ], 
        "MasterPublicDnsName": "ec2-23-22-184-123.compute-1.amazonaws.com", 
        "VisibleToAllUsers": true, 
        "BootstrapActions": [
            {
                "Args": [], 
                "Name": "Install Spark", 
                "ScriptPath": "file:///usr/share/aws/emr/install-spark/install-spark"
            }
        ], 
        "RequestedAmiVersion": "3.10.0", 
        "AutoTerminate": false, 
        "Id": "j-2VGKO25FTPE15", 
        "Configurations": []
    }
}

NOTE: you will need to add ssh to the security group for the cluster before you can connect and run
the spark shell