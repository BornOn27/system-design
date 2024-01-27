# BookMyShow


## v1 Design Issues
- Booking is having Shows & Shows has CinemaHall which has the seat details. 
How does the system find out the booked & leftover seats?
- Since Shows has the CinemaHall, and CinemaHall has SeatDetails, 
to book shows for each seat of CinemaHall, that much entry will be created for Shows. 
Ideally Shows should be single for each CinemaHall. But there has to be mapping of that show to seat mapping.
- CinemaHall has SeatDetails, now to store the details of all seats, 
it has to have the list of all seats which is incorrect. Ideally it has to be the other way around.
- Models shouldn't have data which it isn't supposed to have. Follow Single-Responsibility-Principle.


### Solution
- Maintain a Seat-Auditorium Mapping
- In the booking table, maintain the seat to show mapping. In this approach, we
can get the empty and booked seat using above data.


## Database Isolation Level
In order to achieve the consistent state, we have to use Isolation level in DB.

## Isolation Level

### What is DB Locking?
DB Locking, helps us to make sure that no other transaction update the locked rows

|Lock Type| Another Shared Lock | Another Exclusive Lock |
|--|--|--|
| Have Shared Lock | Yes | No |
| Have Exclusive Lock| No | No |

- Shared Lock - Read Lock
- Exclusive Lock - Write Lock


### Why it is required?
If the application is single-server, then on a single process, with multi-threading we can achieve this using synchronised block. But due to **Distributed System**, we need **Distributed Concurrency Control**.

### Problems
If there is no any Isolation Level defined then there can consistency problem with the data. These problems can be of following types -
#### Dirty Read
Dirty read is **a read of uncommitted data**. If a particular row is modified by another running application and not yet committed, we also run an application to read the same row with the same uncommitted data. This is the state we say it as a dirty read

#### Non Repeatable Read
A non-repeatable read occurs, when during the course of a transaction, a row is retrieved twice and the values within the row differ between reads.

#### Phantom Read
A phantom read occurs when, in the course of a transaction, two identical queries are executed, and the collection of rows returned by the second query is different from the first.


#### DR vs NRR vs PR 
[](https://stackoverflow.com/posts/23138849/timeline)

A simple way I like to think about it is:

Both non-repeatable and phantom reads have to do with data modification operations from a different transaction, which were committed after your transaction began, and then read by your transaction.

Non-repeatable reads are when your transaction reads committed **UPDATES** from another transaction. The same row now has different values than it did when your transaction began.

Phantom reads are similar but when reading from committed **INSERTS** and/or **DELETES** from another transaction. There are new rows or rows that have disappeared since you began the transaction.

Dirty reads are _similar_ to non-repeatable and phantom reads, but relate to reading UNCOMMITTED data, and occur when an UPDATE, INSERT, or DELETE from another transaction is read, and the other transaction has NOT yet committed the data. It is reading "in progress" data, which may not be complete, and may never actually be committed.


### Isolation Types
#### Read Uncommitted
 - Read - No Lock Acquired
 - Write - No Lock Acquired

#### Read Committed
- Read - Shared Lock is Acquired & **Released as soon as Read is DONE**
- Write - Exclusive Lock is Acquired & Kept till Transaction Ends

#### Repeatable Read
- Read - Shared Lock is Acquired & **Released only at the End of Transaction** 
- Write -  Exclusive Lock is Acquired & Kept till Transaction Ends

#### Serializable
Same as Repeatable Read Locking Strategy **+** **Range Lock is applied** till Transaction Ends


### Isolation Level Mapping

|Isolation Level| Dirty Read | Non-Repeatable Read | Phantom Read | Concurrency Level |
|--|--|--|--|--|
| **Read Uncommitted** | Yes | Yes | Yes | **HIGH**
| **Read Committed** | No|Yes|Yes | 
| **Repeatable Read** | No|No| Yes|
| **Serializable**| No|No |No | **LOW**

### To be Read
- Optimistic Concurrency Control
- Pessimistic Concurrency Control

## Reference
- https://www.youtube.com/watch?v=D3XhDu--uoI&list=PL6W8uoQQ2c63W58rpNFDwdrBnq5G3EfT7&index=23