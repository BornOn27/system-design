
# Splitwise

### Functional Requirement
-   Service should be able to record Expenses
-   Service should be able to simplify the Settlements
-   User Profile with capability to create groups
- What is Daily Active User (DAU)?
- What is Read:Write Ratio or Write RPS?
- What is maximum group-size allowed?
- Currency
- Settlement 

#### DB Design
This will require the DB Modeling then Capacity Planning can be carried out.

**Tables**
- **User Profile**
	- U-ID
	- Name
	- Email
- **Groups Details**
	- G-ID
	- Name
	- CreatedBy
	- CreatedOn
- **Group Mapping**
	- GM-ID
	- G-ID
	- Member-ID
- **Transactions**
	- T-ID
	- G-ID
	- PaidBy
	- Amount
	- Date
- **Transaction Details**
	- TD-ID
	- T-ID
	- Split-With
	- Amount