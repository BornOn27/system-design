package lowLevelDesign._002_splitwise.models;

public class ExpenseDetails {
    public Integer edId;
    public Expenses expense;
    public Users splitWith;
    public Double splitAmount;
    public boolean isSettled;

    public ExpenseDetails(Integer edId, Expenses expense, Users splitWith, Double splitAmount) {
        this.edId = edId;
        this.expense = expense;
        this.splitWith = splitWith;
        this.splitAmount = splitAmount;
    }
}
