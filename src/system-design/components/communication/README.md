# Communications

## Types
- Short Polling
- Long Polling
- Web Sockets

### Short Polling

### Long Polling

### Web Sockets
Client and server opens bi-directional channel, it is kept open. Since it is kept open
Server can actively write on it, and vice-versa.

Connection persisted once the 3-way Handshakes happens as compared to TCP where it happens
everytime connection established.

#### Use Cases
- Real time data Transfer
- Low Communication Overhead
- When data changes very frequently


## Reads
- Max number of TCP connections
- Async IO