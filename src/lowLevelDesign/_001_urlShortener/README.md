
  
# Url-Shortener - What are the use cases of the system?    
- What is the amount of Daily Active Users (DAU) for writes?    
- How many years should we persist the short URL by default?    
- What is the anticipated read: write ratio of the system?    
- What is the usage pattern of the shortened URL?    
- Who will use the URL shortener service?    
- What is the reasonable length of a short URL?    
    
    
#### Read:Write Ratio = 50:1    
 #### Total Write/Day = 3600 * 24 = 86400    
  #### Total Write/Year = 86400  * 365 = 31,536,000    
#### Total in 5 Years This will be total unique urls to be generated, which determines the length of Url    
      
#### DB-Design #### Total Memory Required = 63 Gb/year

#### Length of Shortened Url = 9 #### Total no of Chars = 62   

#### Wrong Assumption 
##### Total Number of Unique Url = power(9, 62)    
 Since we are using numbers now to generate the link, total unique Urls will be equal to total unique number of 7 digit = power(10, 9) = 1,000,000,000    
      
      
## Important Question   
#### How is **Randomness** and **Uniqueness** guaranteed for generated Urls?    
    
 **Uniqueness** - We are using a Zooker like service to maintain multiple servers which will hold the counters. Suppose we have 1000 Slots of 1M numbers, we will spawn 10 servers with fixed slots.    
  When any server goes down we will drop the entire slot, that’s why we need extra slots.    
      
  **Randomness** - Since there are 10 servers, we will be using Round-Robin or Random behavior to select the next Unique Number    
      
## Draw.io Diagram  
- Snaps