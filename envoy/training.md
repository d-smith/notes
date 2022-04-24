# Envoy Fundamentals

https://academy.tetrate.io/courses/take/envoy-fundamentals/lessons/28296413-1-0-module-overview

## Intro

Sidecar pattern
Edge pattern (api gateway)

Envoy handles the network concerns

App - send to virtual address

Rate limiting, forwarding, etc at L7
Can do L3/L4 filtering - http, tcp, udp

HTTP routing

Static config, or dynamic config xDS dynamic config services, etc

Health checking
Automatic retries, circuit breaking, etc.

Building blocks - https://academy.tetrate.io/courses/take/envoy-fundamentals/lessons/28296471-1-2-envoy-building-blocks

Listeners
    Network Filters
        Listener
        Network
        HTTP 
            last filter in http chain is the routing filter
Routes
Clusters
Endpoints

### Listeners

It all starts with the listeners. Envoy exposes listeners that are named network locations, either an IP address and a port or a Unix Domain Socket path. Envoy receives connections and requests through listeners. Consider the following Envoy configuration:

static_resources:
  listeners:
  - name: listener_0
    address:
      socket_address:
        address: 0.0.0.0
        port_value: 10000
    filter_chains: [{}]

With the Envoy config above, we’re declaring a listener called listener_0 on address 0.0.0.0 and port 10000. That means Envoy is listening on 0.0.0.0:10000 for incoming requests.

### Routes

To move to the next building block (routes), we need to create one or more network filter chains (filter_chains) with at least one filter.

3 types

* listener filter - works on packet headers
* 

We can also write a configuration that selects a different filter chain based on the incoming request or connection properties.


route_config:
  name: my_route_config
  virtual_hosts:
  - name: tetrate_host
    domains: ["tetrate.io"]
    routes:
    ...
  - name: test_hosts
    domains: ["test.tetrate.io", "qa.tetrate.io"]
    routes:
    ...

### Clusters

Clusters are a group of similar upstream hosts that accept the traffic. This could be a list of hosts or IP addresses on which your services are listening.

