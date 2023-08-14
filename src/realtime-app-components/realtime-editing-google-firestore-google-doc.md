# Realtime database :: Google Firestore
This is behind Google's Backed-as-Service, where an entire application or micro-service is 
provided as a service.

Cloud hosted No-Sql database for (Document Database and Serverless) storage and synchronization of data.

## The LIT Feature
What does it means is - Let's say there are 50 people working on same database. 
Any update get pushed and updated to db as well as they are synchronized to all other users devices. 
In other words it updates the db and broadcasts the change to all devices, and updates their local copy.

## Key features
- Indexed Queries
- ACID
- Offline Synchronization


## Other features
- Managed Performance
- Managed Scalability
- Managed Reliability
- Peak developer Productivity



# System Design


> **Problem Statement -** Design a System which takes the update from any connected client, and broadcasts the update to every connected client


## Approaches


> **NEW -** There will be a queue, whenever anyone make update, first it writes into DB, then published to a queue. Using Message-Broker it will be broadcasted to every connected user and they will update there content.

Problem with the above appraoch is -
- How to identify the sequence of edits performed, so that we can identify what to update?
- Also since there are queues involved, there can be delay in communication with some nodes, so we will not be able to synchronise the operations, also it will not be real-time.

> We reads the updates from client, updates in DB and it has to be communicated to all the client in PUSH-Based approach to the client. Now the question is How?

In Push based approach -
- How do we maintain the sequence?
- What clients will read, and how they update there system?

> In other words it is "Group-Chat"

Still the problem is of **FAN-OUT**. How do we publish the udpate to all in real-time?

> **NEW -** every update will have ID, which can works as sequence number will be stored in DB. Now all the client, will/can fetch the information and can update there system according to there local update sequence. In Socket-IO there is concept of ROOM, Table-Id. We can get connected clients and can broadcast the update to them via Room

Above solution works really well when we assum that all the client are connected to single API-Server. But that is not the case, Google-Docs and Youtube-Chat have millions of users and thousands of API-Server.

# Screen-Shot-1

### This actually biols down to - How to we scale Web-Sockets?

> We will maintain a Central-Server which will have the list of all API-Server, whenever there is an update, we will get the list of other API-Server and will broadcast the message to them. Upon reaching API-Server, they will broadcast it to connected clients. For this to happen we need **API-Server to API-Server** communication.

# Solution

### Redis as PubSub Broker
Why/How use Redis for broadcasting?
- If we have to write it naively, we have to implement all above feature like mainting port for all API-Servers, broadcasting and everything.

Commands provided from Redis
- Subscribe
- Publish
- Unsubscribe

Now all the API-Server will Subscribe to event/table/key from Redis, so that Redis will maintain an Active TCP connection with all its Subscribers. Even if it is lost, Redis will re-connect and will make sure all API-Servers are subscribed to it.

> Redis will do all the Heavy-Lifting of managing netork-io, communication between API-Servers

# Screenshot-2

### Solution Flow
- Client-A on S1-server made an update.
- Update is stored in DB and then it broadcast the change to Redis, S1 publishes to all its local clients
- All the API-Servers S2, S3, S4 subscribed to Redis will receive the update event
- Upon filtering, they will broadcast it to it's connected clients

## How to handle Geo-Spacial Communication of Redis?

Here the problem is API-Servers are scattered across globe, Redis DB will be Geo Spacial, now nodes of Redis will take a lot of time to update in Central-Server. Becasue it is TCP connection, which will be slow and will often breaks

> Introduce Message Broker to handle communication between Redis Geo Spacially then with same location Redis TCP can handle it.


## Biggest Challenge : Multi-Directional Conflicts

This is scanario of SQL Multi-Master Model, where every one is master and they have to update each-others data. Conflicts is when they udpate same row at same time.

### Conflict Unresolution
- Old vs New one Wins based on use-case
- Show merge conflicts like GIT to let users/client decide the merge strategy.

### Other Issue : Ordering Issue