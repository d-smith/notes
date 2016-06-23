## Getting Started
See [http://kubernetes.io/docs/hellonode/](http://kubernetes.io/docs/hellonode/)


To install on AWS, default config plus a couple minor tweaks:

<pre>
export AWS_S3_REGION=us-west-1
export KUBE_AWS_ZONE=us-west-1a
cluster/kube-up.sh 
</pre>

Note the installation cannot be done while attached to the corp LAN as
there are some commands done via ssh that need to 
(but can't) go through the socks proxy.

For the server.js/node hello world example, if using docker for
mac while connected via VPN, create a new network profile
for VPN use via the Mac network control panel settings, add
the proxy server details, and make sure the docker socket is
not routed through the proxy by adding /var/run/docker.sock
to the proxy exclusions.

For the example, create server.js and the docker file, build
the image locally then push it to docker hub so the AWS cluster
can pull it.

Note when using kubectl behind a proxy you need to set the 
http_proxy environment variable.

<pre>
docker build -t dasmith/hellonode .
docker push dasmith/hellonode
kubectl.sh run hello-node --image=dasmith/hellonode --port=8080
kubectl.sh get deployments
kubectl.sh get pods
kubectl.sh get logs hello-node-50758845-shwnz
kubectl.sh logs hello-node-50758845-shwnz
kubectl.sh cluster-info
kubectl.sh get events
kubectl expose deployment hello-node --type="LoadBalancer"
</pre>

Note that on AWS you'll never see a public IP address appear when
doing a `kubectl.sh get services hello-node`. To get the ELB address
you can do a `kubectl.sh describe service hello-node`


