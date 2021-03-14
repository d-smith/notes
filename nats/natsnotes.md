# NATS

NATS core - at most once messaging: client not listening, messages will not be received (all in memory, no writes to disk)

* Use streaming for higher level abstractions, or built reliabilty using reference designs such as acks and sequence numbers

Subjects - scope messages into streams or topics

* In nats subjects are names publishers and subscribers can use to find each other
* The '.' character is used to create a subject hierarchy

Wildcards

* Use \* to match a single component in a subject hierarchy
* Use \> to match the remaining parts (use only at the end)

Patterns

* Pub/sub - aka fan out
* Also request/reply - subscribers listen on a publisher, reply to a reply mailbox

Run on cloud9

* docker run -p 4222:4222 -ti nats:latest
* Install go sources
    * GO111MODULE=on go get github.com/nats-io/nats-server/v2
    * Go sample - git clone git@github.com:nats-io/nats.go.git