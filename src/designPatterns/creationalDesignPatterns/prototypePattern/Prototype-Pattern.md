# Prototype   
When creating an object is memory-intensive or involves expansive networks calls etc, We can use **Prototype Pattern**

Prototype in programming terms is to create clone instead of actually creating object via original method





# Anti Pattern
In the class `AntiPattern.java` we can see that to create a clone object of original object there are following issues
- We need to call constructor or redo all the steps as done in an original step to create a clone object
- If we don't opt constructor way then we won't be able to set private fields as it won't be accessible
- **Client has to know the mandatory and non-mandatory fields to be udpated, even in an incremental update**
- Incremental Updates also becomes tougher and tighly coupled with client's code