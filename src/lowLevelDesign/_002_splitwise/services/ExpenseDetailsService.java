package lowLevelDesign._002_splitwise.services;

import lowLevelDesign._002_splitwise.models.Expenses;
import lowLevelDesign._002_splitwise.models.ExpenseDetails;
import lowLevelDesign._002_splitwise.models.Groups;
import lowLevelDesign._002_splitwise.models.Users;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDetailsService {
    List<ExpenseDetails> expenseDetailsList = new ArrayList<>();


    public List<ExpenseDetails> addExpensesDetails(Expenses expense, List<Users> splitWith){
        List<ExpenseDetails> thisExpenseDetailsList = new ArrayList<>();

        Double splitAmount = getSplitAmount(expense, splitWith);
        for (Users user: splitWith) {
            ExpenseDetails expenseDetails = new ExpenseDetails(expenseDetailsList.size()+1, expense, user, splitAmount);
            thisExpenseDetailsList.add(expenseDetails);

            expenseDetailsList.add(expenseDetails);
        }

        return thisExpenseDetailsList;
    }

    private Double getSplitAmount(Expenses expense, List<Users> splitWith) {
        //Assuming Paid User is part of SplitWith
        return expense.amount/splitWith.size();
    }

    public List<ExpenseDetails> settleExpensesDetailsByGroupAndUsers(Groups group, Users settledBy, Users settledTo){
        /* Find all the Expenses added by User - "settledTo" for the User - "settledBy"
         *  Because it Expenses will be opposite of settlement
         */

        List<ExpenseDetails> detailsList = getAllExpensesBetweenPaidByAndPaidToUsers(settledTo, settledBy);
        for (ExpenseDetails thisDetail: detailsList) {
            thisDetail.isSettled = true;
        }

        return detailsList;
    }

    private List<ExpenseDetails> getAllExpensesBetweenPaidByAndPaidToUsers(Users paidBy, Users paidTo) {
        List<ExpenseDetails> expenseDetails = new ArrayList<>();
        for (ExpenseDetails thisExpense : expenseDetailsList) {
            if(thisExpense.expense.paidBy.equals(paidBy) && thisExpense.splitWith.equals(paidTo))
                expenseDetails.add(thisExpense);
        }

        return expenseDetails;
    }

    public List<ExpenseDetails> getAllExpensesDetailsByUser(Users user){
        List<ExpenseDetails> thisUserDetails = new ArrayList<>();
        for (ExpenseDetails thisDetail : expenseDetailsList) {
            if(thisDetail.splitWith.equals(user))
                thisUserDetails.add(thisDetail);
        }
        return thisUserDetails;
    }

    public List<ExpenseDetails> getUnsettledExpenseDetailsByGroup(Groups group){
        List<ExpenseDetails> expenseDetails = new ArrayList<>();
        for (ExpenseDetails thisDetail : expenseDetailsList) {
            if(thisDetail.expense.group.equals(group) && !thisDetail.isSettled)
                expenseDetails.add(thisDetail);
        }
        return expenseDetails;
    }
}
