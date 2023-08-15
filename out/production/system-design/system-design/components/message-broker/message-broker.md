# Message Broker


## Implementations
- Producer-Consumer : Message Queues
- Publisher-Subscriber : Data Steams

### Homogeneous Consumers : Message Queues
Common queue but consumers are exactly same with same configurations.
It expects that all consumers are of same type and would deliver it to anyone
- SQS
- RabbitMq
- Redis : can be used as Queues

### Heterogeneous Consumers : Data Steams
Common queue but consumers are different with different set of properties and functionality.
Since consumers can be different, they are responsible to read and process it. 
- Kafka 
- Kinesis

Example - On publishing a blog, for one single event
- Count supposed to be updated in count-service
- It is updated at Search-Service at ElasticSearch

For Message queues, we need to publish it to different queues in order to achieve it.

## Features
- Expiration
- Delay Handling
- At-most once
- At-least once
- Exactly once
- Waits until message Retrieved

