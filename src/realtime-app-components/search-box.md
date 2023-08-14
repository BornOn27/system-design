# Search Box
Searches are fuzzy-search i.e, all similar data needs to get loaded and can have some discrepancies.

- To design an efficient search box, we need to have low-latency high throughput over the content. 
- Since number of user doing write, might be less but the frequency of reads would be high, and due to locks and all SQL would be a bad choice.
- Also String-matching over SQL is not optimal
- For searches over string Elastic-Search is best for it.

## Use-Cases
- Payments, where we want SQL to guarantee ACID, but also we want to implement search feature over it.

### Why we can't use elastic-search as main db?
Elastic-Search doesn't guarantees ACID properties

### What about data-synchronization between SQL and ES?
- All the write can go to SQL
- Data can be pushed over a stream to update in ES

#### Issues
Because of ES out of sync, search might not be optimal
- As data might be updated in SQL, but ES still references to it.
- Due to these reason Search queries becomes less efficient.

##### We can't directly store the data on ES as we would have to de-normalize and modify it according to rules of ES.
