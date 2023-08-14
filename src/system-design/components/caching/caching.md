# Caches
Locality of Reference. Recent Data most likely to be  accessed.

- Reduces Disk IO and Network IO
#### Caches exist at all levels of Architecture, but they are extremely costly

## Points of Caching
- Disk Caching
- CPU Caching
- GPU Caching
- Browser Caching 

## Types of Cache
- Application Server Cache
- Central Cache : Redis, Mem-cached
- Central and Distributed Cache : Data stored at central system in sharded-way

## Types of Caching System
- Write Through Cache
- Write Around Cache
- Write Back Cache

### Write Through Cache
- Write to DB and write to cache
- Both write Pass then it is complete
- Data present at both palces

Pros
- Fast Retrievals, data consistency and fault tolerant

Cons
- Higher Write Latency

### Write Around Cache
- Write to DB but no write to cache
- Good for applications that do not read immediately after write i.e letting the
first cache-miss and then pull data for it.
- We can use another service to update the data in cache

Pros
- Not flooding cache with unused writes. i.e, We are not putting everything to cache, 
only when it is read we will cache it.
- We are not populating a cache when we are expecting a read, but when it will happen.

Cons
- Recently written items will be cache missed, higher read latency (first read)
- We need to wait until async-job updates the data or we let the first cache-miss

### Write-back Cache
- Write to Cache and IO done
- Data written to DB in background
- For backup, we can add it in data-stream immediately


Pros
- **Low Latency, High Throughput, Write-Intensive**

Cons
- Data Availability Risk 

## Action Points
- E-Tags : It is kind of a hash, which stores the hash of data. It help to
reduce the API calls making cheap call to reduce the actual API calls. 

## Pitfalls
- Cache-hit (Increase) vs Cache-miss (Decrease)
- Cache Updation in case of resource deletion

## Cache Eviction Policy
- FIFO : First In First Out
- LIFO : Last In First Out
- LRU : Least Recently Used (Important)
- MRU : Most Recently Used
- LFU : Least Frequency Used (Important)
- RR : Random Replacement
#### Learn Implementation 

### Characteristics of Cache
- Build High Throughput System
- Volatility - Data Loss Possible
- Not for Transactional Data
- Quick Reads and writes
- Super Expensive
- Meant for Performance
- Reduces Disk IO on DB
- Reduces Network IO on network calls 

### Caching at various levels
- DNS, CDN
- API-Server : In-Memory, On-Disk
- Client side : Browser/App based
- Pre Computed DB : Materialized Table views, where we already do joins and keep the data
- Load Balancer : ngnix
- Centralized Cache
- Transparent Cache in front of DB, which saves query result to cache to reduce DB hits. (eg - Cacheops)
- API-Gateway

