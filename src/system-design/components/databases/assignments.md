# Assignments
- Implement/Simulate Transaction Isolation
- Implement Transaction Durability
    - Handle System Failure
- Build a trigger framework on top of database
    - Define triggers upon data & conditions. Ex - age == 18 do eligible_vote = true
    - Max 50 triggers upon each condition
    - Specify your own specification language
- Write/Build a super-simple in-memory key-value store
  acting as a database
- Build Locking Scenarios and Understand how Transaction handled it
- Try Read & Write Locks with normal queries
- Model Graph Database on top of SQL Engine
- Read and understand the foundational paper introducing DynamoDB

## Reads
- Secondary Index
- Composite Index
- Partial Index
- Syncing/Replication Mechanism between Master-Replica Database
- B+ Tree
- Isolation Levels
- Columnar Compression - Run length Encodings
- Homogeneous Data Compression techniques
- C-Store DB with research paper, which explains the use case of it 
  'C-Store : A column oriented DBMS'
- N + 1 Problem  


# Simulate & Understand
- Transactional Deadlocks
- Effect of SKIP Locked and Nowait
- What happens to a row after acquiring a shared lock and then value getting modified.
- Find 3 practical situations where exclusive-locks are super useful and 5 situations where shared
ones do a good job.
  - Consider using nowait and skip locked related use cases