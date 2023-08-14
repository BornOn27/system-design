# Content-Delivery Network
Get content from closest available servers instead of hitting the main server
It serves a kind of Geographical Caching Server where we can cache mostly static content like -
- Static Images
- Static API Responses
- Provides the Geo-Localized feature for low-latency API

## Characteristics of CDN
- Better speed and scale
- Reduces Load on Main Server
- Improved Uptime
- Increased Security
    - Delegates DDoS Attack Handling
    - API Rate Limiting
- Improve speed (response time) by keeping the content close to users

### Which content wpuld be kept on CDN?
- The data which doesn't change often - image, video, text, API response

## Pitfalls
- Dirty content server to users - time for invalidation
- Caching Policies and Configuration
## Action Items
- For huge data, same API will work faster based on location of Servers, it can be solved using CDNs
