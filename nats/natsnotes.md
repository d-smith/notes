# NATS

## NATS Core

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
* Request/reply - subscribers listen on a publisher, reply to a reply mailbox
* Queue groups - subscribers register a queue name, all subscribers with the same queue name form a queue group. Messages are distributed across the queue group.
* acks - turn fire and forget into fire and know by doing an empty reply as an ack
* sequence numbers - append to messages, subscribe to all using wildcard, then sort, etc.

## NAT Streaming

Extends NATS via...

* Enhanded message protocols - e.g. protobufs
* Message/event persistence
* At-least-once delivery
* Publisher rate limiting
* Rate matching/limiting per subscriber
* Historical message replay
* Durable subscriptions

Channels are subjects client send data to and read from. No wildcarding like with nats subjects

Subscription types

* Regular - removed when unsubscribed or closed, or client connection is closed (by client or by server via timeout). They survive a server failure (if using a persistent store)
* Durable - resume processing from where the client previously stopped
* Queue groups
* Redelivery

## Run on cloud9

NATS

* docker run -p 4222:4222 -ti nats:latest
* Install go sources
    * GO111MODULE=on go get github.com/nats-io/nats-server/v2
    * Go sample - git clone git@github.com:nats-io/nats.go.git

NATS Streaming

* docker pull nats-streaming
* docker run -p 4222:4222 -ti nats-streaming:latest

Subscriber:

```
$ telnet demo.nats.io 4222
Trying 107.170.221.32...
Connected to demo.nats.io.
Escape character is '^]'.
INFO {"server_id":"NBIU6Z3ALWOXBKJG6JRBBGDCIEKEHPRQGI3H7ENAMGAH7BJPPH3KIIJT","server_name":"NBIU6Z3ALWOXBKJG6JRBBGDCIEKEHPRQGI3H7ENAMGAH7BJPPH3KIIJT","version":"2.2.0","proto":1,"go":"go1.16.2","host":"0.0.0.0","port":4222,"headers":true,"max_payload":1048576,"client_id":7303,"client_ip":"3.92.60.59"} 
sub foo.* 90
+OK
PING
PING
MSG foo.bar 90 5
hello
```

Publisher

```
$ telnet demo.nats.io 4222
Trying 107.170.221.32...
Connected to demo.nats.io.
Escape character is '^]'.
INFO {"server_id":"NBIU6Z3ALWOXBKJG6JRBBGDCIEKEHPRQGI3H7ENAMGAH7BJPPH3KIIJT","server_name":"NBIU6Z3ALWOXBKJG6JRBBGDCIEKEHPRQGI3H7ENAMGAH7BJPPH3KIIJT","version":"2.2.0","proto":1,"go":"go1.16.2","host":"0.0.0.0","port":4222,"headers":true,"max_payload":1048576,"client_id":7533,"client_ip":"3.92.60.59"} 
pub foo.bar 5
hello
+OK
```