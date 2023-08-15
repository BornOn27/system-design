# Concurrency

## Concurrency vs Parallelism

Image Description - [Link](https://github.com/BornOn27/system-design/blob/main/system-design/concepts/snaps/Concurrency-vs-Parallelism.png)

### Concurrency
Concurrency is executing multiple things at the same time but in a One-at-a-time fashion.
There is a lot of context switch happens, between executing different tasks. It gives false 
sense of Parallelism, since only one thread/process is running at a time.
- Python, Node

Python uses uswgi, which achieves multi-processing, but making multiple copies of same application 
on different child threads
Multi-processing systems can communicate easily via Disk IOs

### Parallelism
Parallelism is executing multiple things at the same time, but it actually executes more-than-one-at-a-time fashion.
It is only possible at Multi-core CPUs, where multiple things are executing on different cores.
- Java, C, Go-lang

## Issues 
- Communication b/w sub-components - Complex to handle
- Number of possible execution path - very large
- Indeterminate outcomes - Depending on the execution path
- Concurrent use of shared resource
  - High DB Connection
  - Same memory blocks w/o locks
- Complex co-ordination & data exchange

### How to handle concurrency?
- Make things thread-safe
    - Locks
    - Lock free data-structures


### Reads
- Sync-Async Programming Model
- Sync/Async process pool, Sync/Async event pool
- Deadlocks and Starvation
- Parallel Algorithm
- Lock Free data Structure
- Persistent Data Structures