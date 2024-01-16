package lowLevelDesign._002_splitwise.service;

import lowLevelDesign._002_splitwise.models.ExpenseDetails;
import lowLevelDesign._002_splitwise.models.Groups;
import lowLevelDesign._002_splitwise.models.Users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManagerService {
    ExpenseDetailsService expenseDetailsService;
    GroupsService groupsService;
    GroupMappingsService groupMappingsService;

    public ExpenseManagerService(ExpenseDetailsService expenseDetailsService, GroupsService groupsService, GroupMappingsService groupMappingsService) {
        this.expenseDetailsService = expenseDetailsService;
        this.groupsService = groupsService;
        this.groupMappingsService = groupMappingsService;
    }

    public void getBalances(Users user){
        List<ExpenseDetails> userExpenses = expenseDetailsService.getAllExpensesDetailsByUser(user);

        /* Find all Balances for each User, for each Group
         *  1. Find all lent amount by Group where splitBy != this user
         *  2. Find all received amount  by Group
         *
         *  Answer = 1 - 2
         */

        List<Groups> userGroups = groupMappingsService.getGroupListByUser(user);

        for (Groups thisGroup : userGroups) {
            //Find Balances for each Group
            List<ExpenseDetails> thisGroupExpenseDetails = expenseDetailsService.getUnsettledExpenseDetailsByGroup(thisGroup);
            Map<Users, Double> lentMapping = new HashMap<>();
            Map<Users, Double> receivedMapping = new HashMap<>();

            for (ExpenseDetails thisExpenseDetail : thisGroupExpenseDetails) {
                if(thisExpenseDetail.expense.paidBy.equals(user)){
                    //Update lentMapping, User have given Money
                    lentMapping.put(thisExpenseDetail.splitWith, lentMapping.getOrDefault(thisExpenseDetail.splitWith, 0.0) + thisExpenseDetail.splitAmount);
                } else if(!thisExpenseDetail.expense.paidBy.equals(user) && thisExpenseDetail.splitWith.equals(user)) {
                    receivedMapping.put(thisExpenseDetail.expense.paidBy, receivedMapping.getOrDefault(thisExpenseDetail.expense.paidBy, 0.0) + thisExpenseDetail.splitAmount);
                }
            }

            //Find the difference Amount
            for (Map.Entry<Users, Double> thisEntry: lentMapping.entrySet()) {
                if(thisEntry.getKey().equals(user))
                    continue;

                double difference = thisEntry.getValue() - receivedMapping.getOrDefault(thisEntry.getKey(), 0.0);

                System.out.println(user + (difference < 0? "pays to " : "gets by") + thisEntry.getKey() + " :: " + difference);
            }
        }

    }

    public void settleExpenses(Groups group, Users settledBy, Users settledTo){
        List<ExpenseDetails> settledExpenses = expenseDetailsService.settleExpensesDetailsByGroupAndUsers(group, settledBy, settledTo);
        System.out.println("Settled Expenses between Users - ["+settledBy+"::"+settledTo+"]");
        System.out.println(settledExpenses);
    }

    public void getExpenses(Users user){
        List<ExpenseDetails> details = expenseDetailsService.getAllExpensesDetailsByUser(user);
        Map<Groups, Double> userExpensesDetails = new HashMap<>();

        System.out.println("GroupWise Expenses for User-"+user);
        for (ExpenseDetails thisDetail: details) {
            Groups thisGroup = thisDetail.expense.group;
            userExpensesDetails.put(thisGroup, userExpensesDetails.getOrDefault(thisGroup, 0.0) + thisDetail.splitAmount);

            System.out.println("Group-"+thisGroup.name+" :: "+thisDetail.expense.name+" :: Expense "+thisDetail.splitAmount);
        }
    }
}
