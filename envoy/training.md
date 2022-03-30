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
