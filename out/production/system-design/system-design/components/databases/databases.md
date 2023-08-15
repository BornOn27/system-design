# Databases  

- Db have the ledger to keep record of write happened to Dbs, which is helpful in cases master went down.
It is used to keep track of data updated, to be updated everything.

  
## Scenarios defines the design
- Read-Intensive and Write-Once
- Read-Once and Write-Intensive
- Read-Intensive and Write-Intensive 
  
## Types  
- In-memory : Cache  
- Disk-based :   
- Server embedded :   
- Embedded : This is embedded within the server, not a self-hosted  
- Row-based : Structured around rows  
- Columnar : Structured around column  
- Graph Db : Preferred when there is tree-like structure  
- Time-Series DB : Data-point to be stored per time-unit  
- Relational : SQL  
- Non-Relational : No-Sql  
- Blob Storage : Binary Long Object  
- Flat file storage : Blob storage with visibility over structure to be queried  
- Storage for text-based search : Elastic-Search  
  
## Modeling Approach  
- We should always start with SQL way which helps us in understanding the relationships, what needs to be stored etc.  
- Then we can separate it out based on optimizations and best-practices

## Normalization
- 1NF
- 2NF
- 3NF
## Hard Delete vs  Soft Delete

### Soft Delete Advantages
- If we hard-delete data everytime individually which will be separated and scattered over different
pages, B+ tree has to re-balance the entire tree and, a lot of CPU processing will be wasted.
- Instead, when we will be deleting hard-delete in bulk or single query all the adjustment will happen once  

## Pitfalls
 
 #### Why we shouldn't store really large field in a row with frequent read?
 Provided the DB is storing data in row-based format. Since we will be indexing every field, specially the large-text. In the scenario where we need to select certain field 
 - When Disk Driver loads data the unit is called Disk Block. Based on size of Disk Block, it will have to read all those blocks then only can go next block. For ex - 5MB data and block size is 4KB
 - Disk IO will be performed over that long-text field for every row, provided even if we want to select only single column, it has to load in memory. During this a lot of Disk IO will be wasted
 - Since we know the size of each row, but if we are selecting any specific column, then all the Disk IO, it went in vain.
 - Sequential search is the worst thing in this case, as has to load entire data and compare. So here indexing would help but not as much since data will be anyways loaded to the memory.
 - Indexes makes lookup faster, but still entire rows needs to loaded in memory.
 - Also here the data-types play an important role, as long-text will be stored at a different location and that location will be stored in row. It is equivalent to Pointer to Object.
 - The data-type conscious decision has to be made. String comparison are slower as compared to Integer. CPU are designed to do mathematical computations. Also it would take a lot of storage space 

#### While storing Date-time what should be the data-type? Date-time object vs epoch?
- When we store as a data-time object, ultimately DB engine stores it in integer form only. So when we choose to store it as Object, for every operation it will marshal,de-marshal which is a performance hit at some level. We could easily handle this at app-level
- Also performing the range queries will become lighting-fast as it will be direct integer comparison as compared to object-comparison
- For readability and performance we can choose some format `yyyymmdd` and store it into integer format.
- To handle time-zones always store in UTC and convert according to user time-zone. It will cover the case where user is changing its time-zone. It should be handled at front-end, nothing to worry at back-end.
##### MMT did this modification over DB and it leads to performance improvement by 20%.  



### DB Scaling Strategies
- Vertical Scaling : Making instances Bulkier
- Horizontal Scaling 
  - Read Replicas : With Modification of adding Read-Replicas with read:write ratio as 99:1
  - Sharded/Distributed DB : Adding multiple nodes for handling rule-based writes
 
#### Scaling Pitfalls
- Do not over-optimise
- Do not over-provision

#### Master Replicas
- Active-Active Configuration would be a pitfall as it will be difficult to manage write-conflicts operation over multiple instances.
- Sharded DBs are the feasible solutions for Active-Active Master Configuration

#### Actions Items
- Do Sequentially
- Start by vertical Scaling
- Then add Read Replicas
- Then Shard the data


## In Details

Relational Databases has the important selling point is ACID Properties.

## ACID
- Atomicity
- Consistency
- Durability
- Isolation

### Atomicity
- Each transaction is a single unit of execution
- Either all queries executed or none.
- It means if there are 5 queries to be executed in a transaction, then all of them would be executed or none.
Let's suppose DB crashes after executing 3rd queries then, all the changes would be removed since all was not executed.
  

### Consistency
- DB moves from one consistent state to another consistent state.
- It ensures the constraint applied will always be applied. For ex - If there is constraint that Balance > 0, then
DB would never accept Balance < 0.

### Durability
- Post commit data is recoverable even after failure of system i.e, Once the data stored, even if db-engine is stopped
or crashed, the data would remain persisted. When it will be up, it will have all the data.
  
### Isolation
- Concurrent execution
- Sequential Execution
- It means multiple transactions will not affect each other. 

#### It doesn't ensure this by-default, it provides mechanism to achieve this i.e, DB-Locks

## Database Indexes
Primary Job is to improve lookups
- Improves lookup performance
- Decreases write performance

Indexes is typically a small table having two columns 
primary/candidate key -> Disk where k-w stored

#### Data Structure used : B+ Trees

#### Understand Execution Plan of Query
We can tell engine to use different index to improve the execution.

### Types of indexes
- Primary Index : Key -> Address the data (One-hope access)
- Clustered Index : Index + Data Residing Together ordered by the key
    - Huge boost in performance Re-ordering & Updates Expensive


## Database Locking

It is just try to protect the critical section.
- Read Locks / Shared Lock
- Write Lock / Exclusive Lock

### Read Locks / Shared Lock
- Reserved for read by current session
- Other session can read the locked data
- Other session cannot write/update locked data

### Write Locks / Exclusive Lock
- Reserved for write by current session
- Other sessions cannot read/write locked data

### Lock Granularity
- Table
- Row
- Column
- Page level locking

If engine detects deadlock then it kills the transaction running which the deadlock detected.

#### Why do we need DB locks when we can handle it at application side?

## Locking Reads
if we query the data and then INSERT/UPDATE within same transaction, regular SELECT statement
does not give enough protection `[autocommit = OFF]`

`SELECT ... FOR SHARE`
- Shared mode locks on any rows that are read. Other session can read but not modify.
If rows modified, other transaction waits until the first one ends and then uses the latest values
  
`SELECT ... FOR UPDATE`
- Locks the rows and any associated index entries. Other transactions blocked from updating these rows
or even reading then (in certain isolation levels).
- Useful when dealing with tree-structured data in same or split tables.

#### What if it is okay to skip to locked rows?
If a row locked by a transaction, other transactions requesting the same rows must wait until the lock released.
It is used in Ticketing Apps

- NOWAIT : Locking read never waits to acquire a new row lock. Query executes immediately, failing if row locked.
    - `select * from t where i = 2 for update nowait`
- SKIP LOCKED : Removes/SKIP locked rows from the result set.
    - `select * from t where i = 2 for SKIP LOCKED`

#### These commands are super important while designing lock intensive system.
#### We can completely design Ticketing system, Message Broker, Kafka only by SQL with locks


## Scaling Databases

Single Database is 
- Single Point of failure
- Not scalable beyond a limit

### Read Replicas
- Read strategies og handling disaster recovery in case Master crashes
- Scaling Parameters i.e, Condition when new Read replica will be added

### Data Sharding
This is splitting data horizontally. This is like partitioning the data based mutual-exclusive condition.
For ex - For stock exchanges we can derive an algo that `[A-M]` will go Shard-A, `[N-Z]` will go to Shard-B
and continues. Where individual shards have their own Read-Replicas and Master recovery rules.

- In case data is sharded, and we need the data, so instead of doing joins and all. We can go and bring the data.
For ex - Europe data has to be in Europe, and we need it in India, so instead of doing cross-join go bring the data.

#### Limitations
- Data should be totally mutually-exclusive.
- We can't afford cross-shard joins.
- Anything which have to do with multiple-shards are not possible.
- If we have a new shard, entire shards needs to be done

### Multi-Master
Tough to implement, but it is used in most cases for high-availability, scaling writes.

- Conflicts must be handled at application side

#### Limitations
- Super complicated
- Data Conflicts
- Sync Issues


### Pitfalls
- If data lost because Master went down, we don't really need to worry about the data loss
happened during the down-time. It is not a good strategy to add Data w/o knowledge to user.
Anyways when it was down app would give 500 error.


## Why RDBMS system do not scale?
- Because it guarantees ACID
- SQL database has to have all data in single node, else cross node joins would be terribly slow.
  - Also, Foreign key checks across node would be hard to guarantee the data-consistency.
  - So sharding makes sense if data is mutually-exclusive
- In Read-Replica setup what about write-propagation?
  - Sync : Slower write, costly when more nodes added
  - Async : Replicas are not in sync with master and master got completely destroyed. We will lose data.
- In multi master setup, what happens to consistencies?
  - Consistency guarantees lost with multiple nodes.
  - If data is sharded then it is No-SQL way of implementation, Because consistency guaranteed in single shard
- Traditional Scaling : Vertical Scaling - Finite Limit
- Normalized data don't help
  - Joins across DB are super expensive - multi-region, multi-master
  - At some point we have to have the redundancy to help db scaling
  - Instead of having a lot of scattered foreign-keys, merge it and this is moving towards No-Sql
  - Constraint check on networked DB not feasible
- Data model not built to scale horizontally

### But RDBMS managed to scale
- By adopting No-SQL paradigms like sharding and isolation
- Cross DB joins are possible but expensive
- MySQL Vitesse


