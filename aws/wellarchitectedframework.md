Well architected labs - see https://www.wellarchitectedlabs.com/

Lab - Fault Isolation with Shuffle Sharding

https://www.wellarchitectedlabs.com/reliability/300_labs/300_fault_isolation_with_shuffle_shardin/

Step 1 - one target group writing to all instance, poison pill takes out all

Step 2 - shard implemented by multiple target groups, reduced spread based on url routing

Step 3 - shuffle shard - 8 target groups, workers can belong to multiple TGs

* 1, 2
* 3, 4
* 1, 8
* 7, 8
* 6, 7
* 6, 5
* 2, 3
* 4, 5