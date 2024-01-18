package lowLevelDesign._002_splitwise.services;

import lowLevelDesign._002_splitwise.models.Groups;
import lowLevelDesign._002_splitwise.models.Expenses;
import lowLevelDesign._002_splitwise.models.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpensesService {
    List<Expenses> expensesList = new ArrayList<>();
    ExpenseDetailsService expenseDetailsService;

    public ExpensesService(ExpenseDetailsService expenseDetailsService) {
        this.expenseDetailsService = expenseDetailsService;
    }

    public Expenses createExpenses(String name, Groups group, Users paidBy, Double amount, List<Users> splitWith){
        Expenses thisExpense = new Expenses(expensesList.size() + 1, name, group, paidBy, amount, new Date());
        expensesList.add(thisExpense);

        //Update Expenses Details
        expenseDetailsService.addExpensesDetails(thisExpense, splitWith);

        return thisExpense;
    }
}
