# Getting Started with EKS

Docs [here](https://docs.aws.amazon.com/eks/latest/userguide/getting-started-eksctl.html)

* install kubekutl - https://docs.aws.amazon.com/eks/latest/userguide/install-kubectl.html

```
curl -o kubectl https://amazon-eks.s3.us-west-2.amazonaws.com/1.21.2/2021-07-05/bin/darwin/amd64/kubectl
chmod +x ./kubectl
mkdir -p $HOME/bin && cp ./kubectl $HOME/bin/kubectl && export PATH=$HOME/bin:$PATH
echo 'export PATH=$PATH:$HOME/bin' >> ~/.bash_profile
kubectl version --short --client
```

* install eksctl

```
brew tap weaveworks/tap
brew install weaveworks/tap/eksctl
eksctl version
```

## Fargate Linux

Create the cluster

```
eksctl create cluster \    
--name my-cluster \
--region us-west-2 \
--fargate
```

View the nodes

```
kubectl get nodes -o wide
```

View the workloads

```
kubectl get pods --all-namespaces -o wide
```

Clean up

```
eksctl delete cluster --name my-cluster --region us-west-2
```

