# No-SQL Database

Non-SQL not No-SQL

Incorrect Understanding - **No-SQL is schema-less**
It's incorrect that No-SQL is schema-less, they have underlying schema which Documents follows, which allows searching, storing the data. The main usage point is **They don't have relation, constraints between attributes**, the meaning of schema-less is we don't have to follow a fixed constraints of schema, but ultimately the data is stored in schema-based manner

Sharding comes from Day-0 as it is it's requirement to start the servers, which SQL doesn't.

> **No-SQL** is **relation and constraint less** database with **Sharding** as its default offering

It gives eventual-consistency which means, even after writing the data, it takes some time to reflect the data across all shards, but it will gives high availability as the data by default is sharded and present on different machines

## Types of No-SQL Databases
- Document : Data stored in document i.e, JSON
- Key-Value pair : Stores KV pairs and no complex queries
- Wide-Column : Stores data in tables, rows, dynamic columns. Dynamic column means there is no fixed rule which columns will follow. In some row 1,2,3 can be column and in some 4,5,6.
- Graph : Nodes and Edges, Good for Identifying patterns. Generates recommendations

### Document DB
- Mostly JSON based stores, but there are different options like [Apache Parquet](https://www.databricks.com/glossary/what-is-parquet)
- Support complex queries
- Every document gets a unique keys
- Secondary indexes can be created on any nested fields
- Partial updates to a document is possible.

Since it stores the data in Key-Value pair, where they don't provide options to search, it provides the option to search and also partial update it's data. Partial updates mean in KV based DB, we override the data for a key, it is not partially updated, which happens in case of No-SQL

Examples - MongoDB, DocumentDB

### Key Value Stores
- Stores keys and values in highly partitioned manner
- No partial updates on values, but new some started supporting
- No complex queries on values

Examples - Redis, Aerospike, DynamoDB

### Columnar DB
- Analytics queries might have to scan through all.
- Schema less where certain row can have a column or choose not to have
- Since the data is homogeneous i.e, same data-type across the same column, **Compression of data works extremely well**


> Time to scan ⇒(Directly Proportional) Amount of Data read from disk (not utilized or shown)

#### Usage use-case as compared to Row-based DB
There is use-case where we need particular columns data very frequently, for ex- to plot a graph we need to scan all column of a particular DB. For Row-based storage, we need to **load entire Row to get a single column value and multiplied by Frequency and Count** where we will be wasting a lot of resource by loading extra-data
Whereas Columnar can be useful here as the data stored is in column-sequential manner, fetching a row would give me same column values for multiple records.
- Column representation reduces SCAN time
- Stores each column separately
  This way we reduce bytes read from the disk.

#### How different column information is linked? I want to search column of certain row
Since the data is homogeneous we know the size of each column, so **Offset is stored for each row (column stored-rows)** . Since we are saying particular column can be present for some data and absent for some, Offset is not typically calculated, it would be been modified using some algorithm.

#### What is it's read:write scenario? Is it suitable for real-time analytics?
- Because of structure of data, it is suitable for **write-once-read-heavy** system. Also due to storage technique, real-time analytics in not possible due to writes.
- It could easily be achieved using SQL because it provides support to write-heavy system and for faster retrieval of columns we can create indexes over it
- With the growing data, Indexing size would also becomes huge. So to control this we can have archival mechanism to maintain only the selected time frame data.

### Graph DB
- Stores data in form of Nodes & Edges
- Good for modeling Social Behaviors and identifying relations and patterns
- Works best for recommendation engines

> The scenario which suits to use Graph DB is when SQL, No-SQL are not providing those such features. We can't use this just to store and find the relationship between two separate entities. It can be easily achieved via SQL and KV based DBs. It can't be only storage, if we want to implement Graph Algorithms then only it must be used.

> Recommendation System where Graph algorithm is used to determine the classification of customers based on there common buying behavior. And it can be used to recommend the products to other person from its classified groups.

Example - Neo4j, Amazon Nephine, D Graph

## Why No-SQL scales?
- It utilizes partitioning and sharding
- Data is de-normalized and schema-less
- Data is split into multiple nodes

#### There are two ways of sharding
- Physical Sharding
- Logical Sharding

> Any system is infinitely scalable only with **Horizontal Scaling** because Vertical Scaling has a limit because of system resource constraints

We will partition the data, based on some partition-key. While querying we have to know **Partition Key**. Because we have to make sure data around partition-key resides on a single node so it becomes extremely important to choose the **Partition Key**. Else to execute the query, node would need to visit entire list of shards to find the data which is not efficient.
We just to need to store the mapping of **"Partition-key:Database Instance"**

### Use cases of Sharded DB
Here the requirement is data should be sharded and can be provisioned individually based on traffic and use-cases like
- One company don't want to share the data with other client
- Financial Data centers should be within country

#### Verified Users in Social Media
Here when a Highly followed account tweets, the strategy of sharing and publishing would be different as compared to normal users, based traffic and traction created by data we don't want to disturb entire DB engines, instead if the data is sharded we can provision those DB engines as high-end system.

#### Stock Trading
When we see high stock-trading traffic on a particular stock, when we will handle it with other stocks on same DB it would effect there traffic also. Instead when we shard the DB engine separately we can provision a high-end DB for specific stocks

### Joins in No-SQL
Database design is different for No-SQL, since it doesn't supports Joins.
So we can handle this on Application-side which is basically

> One Join ⇒ Multiple Select Statements

Considering time for GET is low, this could be acceptable BUT **Benchmark** for use-case.

This would also get affected based on read-write use-cases.
- If the use-case is write-intensive then there would be a lot of data we need to update for every single update, which will take a performance hit.

> Based on this factor we need to choose whether to Normalize the data or De-Normalize the data

In order to avoid fetching data to multiple collections, we can de-normalize the data in a single collection, so that **Shot-Gun effect** doesn't comes into picture. So here document size would become heavy but eventually saves a lot of DB I/O.

So basically we can decide this based on document-size and frequency of **read:write**


### De-Normalization of Data
Instead of storing Foreign Key, store the value directly and this avoiding lookup ⇒ But works well when **#read >>>>> #writes**

#### N+1 Problem
When 1 query does N dependent queries, performance takes a hit.


### Sharding
 Sharding is partitioning of data based on certain parameters.
There are 2 types of partitioning - 
- Horizontal Partitioning: 
  - Which in other terms is Range based partitioning where certain data exists on certain systems
  - In other terms, we are partitioning the data based on certain criteria
- Vertical Partitioning: 
  - Where we keep tables in different database, it is not about specific data. 
  - It is like a grouping on table or resource level.
  
#### Partitioning Schemes
Different types of way to partitioning the data and store the information so that we can refer where 
to look for data for specific partition-key

##### Hash-based Partitioning
- Result = Hash-Function of Partition-Key
- Result % N

Problems
- What if N changes? We would have to re-generate entire partitioning mapping data
- Or we have entirely move the data, based on the new-partitioning strategy.
- Generally, this is avoided in range-based partitioning

##### List Partitioning
More like static assigned partition per value, which really works in Multi-Tenancy

##### Round Robin
Tuple to i % N partition. This generally works in load-balancers, but not with 
actual data scenario, else how would we know where to look for data.

#### Issues with Sharding
- Join-Queries are not available
- De-normalization of data
- Referential Integrity
- Re-balancing of data if new shard added


# When to use SQL vs NoSQL
There is no text-book definition because eventually we can do all operation in both by some means, but we can use following factors for decision.

- When we don't know what kind of queries is going to come to system, we should prefer SQL's. Because it provides a lot of features, like sorting, sorting on multiple-fields, read-lookups etc. Which doesn't comes with NoSQL by default.
- For Analytics, SQL works extremely well

**Most systems use BOTH**

### Use SQL when
- Need string consistency & integrity
- Data is structured & structure is not changing

### Use NoSQL when
- When we need custom data structure
- If we could far-sight the sharding of data
- When we don't need **Acidity**


