# Performance

### Actions Items for Performance
- What does not need to be done in Realtime, Should not be done in realtime. 
    - Example : Like on Instagram. Realtime means doing action in request context
- For Aggregated results/reports, always keeps it Pre-Calculated, else it would make
the system slow and heavy
  - Example : Everytime we count the blogs/user, it would be intensive. 
    Better store it in a field, and update it.
  - To handle delays in Streaming/Broker Services, extra provisions can be taken accordingly.  

