# Scaling
Number of requests system can handle simultaneously.
More Resource for More Requests

## Scaling Strategy
- Vertical Scaling : The Hulk
  - Making Infra bulky : Scaling Up
  - Adding more disk, CPU, RAM
- Horizontal Scaling : Minions
  - Adding more Machine : Scaling Out


## Scale Up or Scale Out?
|Characteristics| Scale Out | Scale Up|
|--|--|--|
| Performance | Combines power of multiple machine. Not limited by capacity of one | Limited to the capacity of one unit|
| Flexibility | Can use cheaper h/w | Reaches max limit cost goes high - H/W limitation|
| Deployment | % Traffic affected is less during down | % Traffic affected is 100% during down |
| Redundancy | No Single Point of Failure | Single Point of Failure|
| Cost| Usually Cheaper | Usually Costlier|
| Infinite Scale| Yes| No|
| Geo-Latency Optimization| Possible| Not Possible|


###  Advantages
|Horizontal| Vertical |
|--|--|
| Easy Scaling | Low Licensing Fees  |
| Linear Amplification | Easy Implementation |
| Easy forecasting | Just one system to manage |
| Fault Tolerant |  |
| Easy Deployment |  |
| Cheaper |  |
| Resilient |  |
| Infinite Scale |  |

###  Disadvantages
|Horizontal| Vertical |
|--|--|
| Complex Architecture  | Limited Scaling |
| High Licensing Fee  | Risk of Complete Downtime |
| Bigger footprint | Outage due to h/w failure |
| Network and Partitioning |  |

#### Licensing Fee
Licensed Software charges based on different servers it is deployed. Ex - DataDog, NewRelic charges based on Number of Server it is monitoring

#### Optimal : Vertical at First, Horizontal Later

#### During Horizontal Scaling, DB-Connection can be a big Bottle-Neck
As a solution, Horizontal and Vertical Scaling can be applied at DB level to tackle this.



