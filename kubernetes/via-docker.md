Docker desktop docs [here](https://docs.docker.com/docker-for-mac/kubernetes/) and [here](https://docs.docker.com/docker-for-mac/#kubernetes)

Need to explicitly enable Kubernetes via the docker preferences.

With K8s enabled (and the docker daemon restarted) you can run hello world:

```console
lappy:kubernetes ds$ kubectl get nodes
NAME                 STATUS    ROLES     AGE       VERSION
docker-for-desktop   Ready     master    42m       v1.10.11
lappy:kubernetes ds$ kubectl config get-contexts
CURRENT   NAME                 CLUSTER                      AUTHINFO             NAMESPACE
*         docker-for-desktop   docker-for-desktop-cluster   docker-for-desktop   
lappy:kubernetes ds$ kubectl create deployment hello-node --image=gcr.io/hello-minikube-zero-install/hello-node
deployment.extensions "hello-node" created
lappy:kubernetes ds$ kubectl get nodes
NAME                 STATUS    ROLES     AGE       VERSION
docker-for-desktop   Ready     master    43m       v1.10.11
lappy:kubernetes ds$ kubectl get deployments
NAME         DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
hello-node   1         1         1            1           33s
lappy:kubernetes ds$ kubectl get pods
NAME                          READY     STATUS    RESTARTS   AGE
hello-node-7f5b6bd6b8-hskmk   1/1       Running   0          49s
lappy:kubernetes ds$ kubectl get events
LAST SEEN   FIRST SEEN   COUNT     NAME                                           KIND         SUBOBJECT                     TYPE      REASON                    SOURCE                              MESSAGE
45m         45m          6         docker-for-desktop.159fd04a37d2bd73            Node                                       Normal    NodeHasSufficientDisk     kubelet, docker-for-desktop         Node docker-for-desktop status is now: NodeHasSufficientDisk
45m         45m          6         docker-for-desktop.159fd04a37d2d58a            Node                                       Normal    NodeHasSufficientMemory   kubelet, docker-for-desktop         Node docker-for-desktop status is now: NodeHasSufficientMemory
45m         45m          6         docker-for-desktop.159fd04a37d2e3e5            Node                                       Normal    NodeHasNoDiskPressure     kubelet, docker-for-desktop         Node docker-for-desktop status is now: NodeHasNoDiskPressure
45m         45m          5         docker-for-desktop.159fd04a37d2eec0            Node                                       Normal    NodeHasSufficientPID      kubelet, docker-for-desktop         Node docker-for-desktop status is now: NodeHasSufficientPID
45m         45m          1         docker-for-desktop.159fd04ab3bf3915            Node                                       Normal    NodeAllocatableEnforced   kubelet, docker-for-desktop         Updated Node Allocatable limit across pods
16m         16m          1         hello-node-7f5b6bd6b8-4s7hq.159fd1db088e7c82   Pod                                        Normal    Scheduled                 default-scheduler                   Successfully assigned hello-node-7f5b6bd6b8-4s7hq to docker-for-desktop
16m         16m          1         hello-node-7f5b6bd6b8-4s7hq.159fd1db19c5c2b6   Pod                                        Normal    SuccessfulMountVolume     kubelet, docker-for-desktop         MountVolume.SetUp succeeded for volume "default-token-r767r" 
16m         16m          1         hello-node-7f5b6bd6b8-4s7hq.159fd1db48df6a92   Pod          spec.containers{hello-node}   Normal    Pulling                   kubelet, docker-for-desktop         pulling image "gcr.io/hello-minikube-zero-install/hello-node"
15m         15m          1         hello-node-7f5b6bd6b8-4s7hq.159fd1f06d36336d   Pod          spec.containers{hello-node}   Normal    Pulled                    kubelet, docker-for-desktop         Successfully pulled image "gcr.io/hello-minikube-zero-install/hello-node"
15m         15m          1         hello-node-7f5b6bd6b8-4s7hq.159fd1f0776f5134   Pod          spec.containers{hello-node}   Normal    Created                   kubelet, docker-for-desktop         Created container
15m         15m          1         hello-node-7f5b6bd6b8-4s7hq.159fd1f08cd17757   Pod          spec.containers{hello-node}   Normal    Started                   kubelet, docker-for-desktop         Started container
4m          4m           1         hello-node-7f5b6bd6b8-4s7hq.159fd285e628f63a   Pod          spec.containers{hello-node}   Normal    Killing                   kubelet, docker-for-desktop         Killing container with id docker://hello-node:Need to kill Pod
1m          1m           1         hello-node-7f5b6bd6b8-hskmk.159fd2b44047dc10   Pod                                        Normal    Scheduled                 default-scheduler                   Successfully assigned hello-node-7f5b6bd6b8-hskmk to docker-for-desktop
1m          1m           1         hello-node-7f5b6bd6b8-hskmk.159fd2b4491fb693   Pod                                        Normal    SuccessfulMountVolume     kubelet, docker-for-desktop         MountVolume.SetUp succeeded for volume "default-token-r767r" 
1m          1m           1         hello-node-7f5b6bd6b8-hskmk.159fd2b484019476   Pod          spec.containers{hello-node}   Normal    Pulling                   kubelet, docker-for-desktop         pulling image "gcr.io/hello-minikube-zero-install/hello-node"
1m          1m           1         hello-node-7f5b6bd6b8-hskmk.159fd2b4d70b24ac   Pod          spec.containers{hello-node}   Normal    Pulled                    kubelet, docker-for-desktop         Successfully pulled image "gcr.io/hello-minikube-zero-install/hello-node"
1m          1m           1         hello-node-7f5b6bd6b8-hskmk.159fd2b4dedce95f   Pod          spec.containers{hello-node}   Normal    Created                   kubelet, docker-for-desktop         Created container
1m          1m           1         hello-node-7f5b6bd6b8-hskmk.159fd2b4f4029d5c   Pod          spec.containers{hello-node}   Normal    Started                   kubelet, docker-for-desktop         Started container
16m         16m          1         hello-node-7f5b6bd6b8.159fd1db073c8ec3         ReplicaSet                                 Normal    SuccessfulCreate          replicaset-controller               Created pod: hello-node-7f5b6bd6b8-4s7hq
4m          4m           1         hello-node-7f5b6bd6b8.159fd27ede9350f2         ReplicaSet                                 Normal    SuccessfulDelete          replicaset-controller               Deleted pod: hello-node-7f5b6bd6b8-4s7hq
1m          1m           1         hello-node-7f5b6bd6b8.159fd2b43f2adcc5         ReplicaSet                                 Normal    SuccessfulCreate          replicaset-controller               Created pod: hello-node-7f5b6bd6b8-hskmk
16m         16m          1         hello-node.159fd1db05124e0b                    Deployment                                 Normal    ScalingReplicaSet         deployment-controller               Scaled up replica set hello-node-7f5b6bd6b8 to 1
4m          4m           1         hello-node.159fd27edcf9624a                    Deployment                                 Normal    ScalingReplicaSet         deployment-controller               Scaled down replica set hello-node-7f5b6bd6b8 to 0
1m          1m           1         hello-node.159fd2b43c87bb7c                    Deployment                                 Normal    ScalingReplicaSet         deployment-controller               Scaled up replica set hello-node-7f5b6bd6b8 to 1
43m         43m          1         linuxkit-025000000001.159fd05db00782b0         Node                                       Normal    Starting                  kube-proxy, linuxkit-025000000001   Starting kube-proxy.
lappy:kubernetes ds$ kubectl config view
apiVersion: v1
clusters:
- cluster:
    insecure-skip-tls-verify: true
    server: https://localhost:6443
  name: docker-for-desktop-cluster
contexts:
- context:
    cluster: docker-for-desktop-cluster
    user: docker-for-desktop
  name: docker-for-desktop
current-context: docker-for-desktop
kind: Config
preferences: {}
users:
- name: docker-for-desktop
  user:
    client-certificate-data: REDACTED
    client-key-data: REDACTED
lappy:kubernetes ds$ kubectl expose deployment hello-node --type=LoadBalancer --port=8080
service "hello-node" exposed
lappy:kubernetes ds$ kubectl get services
NAME         TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
hello-node   LoadBalancer   10.107.75.219   localhost     8080:30510/TCP   7s
kubernetes   ClusterIP      10.96.0.1       <none>        443/TCP          45m
lappy:kubernetes ds$ curl localhost:8080
Hello World!lappy:kubernetes ds$ kubectl delete service hello-node
service "hello-node" deleted
lappy:kubernetes ds$ kubectl delete deployment hello-node
deployment.extensions "hello-node" deleted
lappy:kubernetes ds$ curl localhost:8080
curl: (7) Failed to connect to localhost port 8080: Connection refused
lappy:kubernetes ds$
```

