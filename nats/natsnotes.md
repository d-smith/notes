# NATS

NATS core - at most once messaging: client not listening, messages will not be received (all in memory, no writes to disk)

* Use streaming for higher level abstractions, or built reliabilty using reference designs such as acks and sequence numbers

Subjects - scope messages into streams or topics

* In nats subjects are names publishers and subscribers can use to find each other
* The '.' character is used to create a subject hierarchy

Wildcards

* Use \* to match a single component in a subject hierarchy
* Use \> to match the remaining parts (use only at the end)
