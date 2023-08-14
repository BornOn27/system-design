
# System Design : SQL Backed Key-Value Store

### Key Decisions
- Communication
- Constraints
- DB Modeling
- Features
- Concurrency
- Scaling

### Extension Feature
- Index the data on different key
- How about making range queries? With sharded db
- How about TTLs?

### Routes
- GET   /key
- POST  /key
- DEL   /key

### Schema Design
Table name : `store` Columns -
- key
- value : BLOB (max length 10 KB) -- Why BLOB?
- ttl : expiration time
- created_at : Timestamp
- is_deleted


## Discussions

### Why BLOB used for value?
- We would never be querying on value
- We don't want to load the value while performing any operation on row.
- We don't want to store the data in contiguous memory location to row, which will make row as memory-heavy
- Blob will be stored as pointer to an address and `value` column will have the address.

### How TTL would be stored?
We are having two columns `ttl (expiration time) & created_at (Timestamp)`
- First of all both columns must be in `integer`

Now we have a Cleanup-Scheduler which deletes the expired data, How would query look like?  
`select * from store where sum(created_at, created_at) < now()`

#### Probable Solutions
- Store only offset, not entire expiration timestamp
- Expiration time = `created_at + ttl`

Now every time scheduler runs it would scan all the rows to find out which ones to be deleted. Now since we know every time we would be using same query, we can create some indexes to save the entire table lookup.

#### Solution
- If we only store offset then we don't know when it was created and then it would be difficult to find out when to delete this. So we need `created_at` information
- So if we store the final epoch i.e, `created_at + ttl` into a single column, then we can create index out of it.
- Creating index would help us faster lookup w/o loading entire row. We can use a range query to find out the targeted rows to be deleted
- If we want to keep data forever, keep `ttl` as null not a big value.
- We can remove `is_deleted` entirely, by adding some external processing agent like SQS, Event

#### Approach - 1
Run a CRON job every T minutes that does soft delete
- until T minutes, expired key is accessible

#### Approach - 2

Check expiration when GET happens on the key at application level

#### Approach - 1 + Approach - 2 is better

### How to scale DB?
When reads are high, we can add **Read Replicas**.

If we want to scale writes
- We can scale up vertically
- We can **shard the data**, since it is key-value store and it is possible to shard the database
    - Based on prefix of the key.
    - Data is mutually-exclusive
    - Each shard will have **Read Replicas**

Image Description - [Link](https://github.com/BornOn27/system-design/blob/main/realtime-app-components/snaps/Key-Value-Pair-Scaling-DB.png)


### When Load Balancer added
When there will be huge request, we would add Load-Balancers for scaling the application-server. But it will introduce issues in the system.

#### Issue
When multiple app-servers will be added, at the application start-up all the servers will create a connection-pool for all the DB-Shards. So **Number of connection for each shard will get exhausted**

#### Solution
- Instead of letting Load-Balancer blindly divide the load among server with no control over prefix of keys, which would result in each api-server has to have connection to each Shards.
- **We can add a custom load-balancer where we can route the calls based on prefix of the key**
- Each Load-Balancer with its api-server will be connected to single or configured Shards, eventually we solved the problem of **Cross-Connection**
- With we can manage the resource based on number of requests coming based on prefix. If lets say **[A-M]** is getting lower number of requests, we can keep the connection-pool, DB Size & Replicas on lower side as compared to **[N-Z]**

##### This is Architecture of Dynamo-DB internally, to keep cost minimum and high availability
##### Also, to improve more on performance, we can introduce caching at any level
##### Always try to reduce DB-connections as low as possible
##### Load Balancers are getting developed at DB level : proxy-sql

Image Description - [Link](https://github.com/BornOn27/system-design/blob/main/realtime-app-components/snaps/Key-Value-Pair-Custom-Load-Balancer.png)

### Implementing Updates
All updates to key are full replacement hence while updating locks should be taken
- Locks should be at ROW-Level

Should this is Exclusive or Shared?
- Since updates are rare, Exclusive Locks not recommended

### Implement /list

#### To fetch the list of all key for K*
First load balancer know where to forward the request considering pattern has starting character.

#### To fetch the list of all key *
Now we want the result from all the Shards from at any single API-Server which won't have connection to different shard.
- The front facing Load Balancer has to know when iteration on one shard ends and then start querying other.
##### The front facing Load Balancer requires application logic
The front facing load-balancer know which child Load-Balancer is working for what prefix, so it has to know the last iterated key and when it receives the response from child LB, it will again hit the other LB for the response.
- Limit Offset based Pagination wont work here. We have to implement Last-Record served infinite pagination.
- First `NULL` will be passed, so front-LB will forward it to child-LB-1. It will return till J
- When user asks for after J, front-LB will forward it again to child-LB-1. It will return till M, let's suppose it is empty.
- When front-LB get the empty response, it can't send it to user, so it will again query child-LB-2 for keys after M i.e, starting from N

