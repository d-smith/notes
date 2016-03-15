Starting a cluster using the dasmith/consul image

docker run -d --name node1 -h node1 dasmith/consul agent --data-dir /home/vagrant/consul/n1data -server -bootstrap-expect 3

JOIN_IP="$(docker inspect -f '{{.NetworkSettings.IPAddress}}' node1)"
docker run -d --name node2 -h node2 dasmith/consul agent --data-dir /home/vagrant/consul/n2data -server -join $JOIN_IP

docker run -d --name node3 -h node3 dasmith/consul agent --data-dir /home/vagrant/consul/n3data -server -join $JOIN_IP
