
# System Design Approach  
In order to design a system, we need to decide the boundaries for it. Whenever we want to design a system, we need to have quantitative analysis around the traffic, budget i.e, we need to understand the scope of entire-system.  
  
## Scopes  
  
| Functional Scope | Non-Functional Scope  |  
|--|--|  
| Feature for the user | How the system should be implemented |  
|Input, Behavior, Outputs|Redundancy, Availability, Security etc|  
| Something that system must have | Something that defines and determines system operational capability|  
| Role of Product Manager | Role of Architect|   
  
  
### Action Items  
- Questions every point till it defines **granular details**  
- We need to align the technical challenges with functional requirements  
- Critical questions around **Present and Future Scope** of the product  
- **Address ambiguity** around choosing different systems over one-another like SQL-vs-nosql, stream-vs-broker  
- **Identify the bottle-neck and mitigations around it**. Assume it is going to happen, then plan accordingly  
- **Subjective Pitfalls** must be addressed. (*Subjective is generally terms with different quantitative value from p2p*)  
   - Fast Experience : How much fast? 1sec for ML vs 1sec Page Load  
   - No scope of Interpretation, numbers has to be there for each subjective requirement  
  
  
  
### Approaches  
- Bottom-Up : Detailed plan where we know what would be the scale and traffic from day-1  
- Incremental : Build a MVP, then based on response go on to improvement. While doing this we need to make sure, we can handle scale for next 2x growth.

## Functional Action Items
To start with designing, there are 2 ways we can start.
- Defining the Database Modeling
- Defining the API contracts  
