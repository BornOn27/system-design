package lowLevelDesign._002_splitwise;

import lowLevelDesign._002_splitwise.models.GroupMappings;
import lowLevelDesign._002_splitwise.models.Groups;
import lowLevelDesign._002_splitwise.models.Users;
import lowLevelDesign._002_splitwise.service.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Splitwise {
    GroupMappingsService groupMappingsService = new GroupMappingsService();
    UsersService usersService = new UsersService();
    ExpenseDetailsService expenseDetailsService = new ExpenseDetailsService();

    ExpensesService expensesService = new ExpensesService(expenseDetailsService);
    GroupsService groupsService = new GroupsService(groupMappingsService);
    ExpenseManagerService expenseManagerService = new ExpenseManagerService(expenseDetailsService, groupsService, groupMappingsService);


    public static void main(String[] args) {
        Splitwise splitwise = new Splitwise();

        splitwise.run();
    }

    void run(){
        Users ashish = usersService.createUser("Ashish", "a@z.com");
        Users manoj = usersService.createUser("Manoj", "m@l.com");
        Users rahul = usersService.createUser("Rahul", "r@n.com");
        Users ravi = usersService.createUser("Ravi", "r@s.com");

        //Users List
        List<Users> usersList = Arrays.asList(ashish, manoj, rahul, ravi);
        List<Users> usersList_AMRi = Arrays.asList(ashish, manoj, ravi);
        List<Users> usersList_AMRa = Arrays.asList(ashish, manoj, rahul);
        List<Users> usersList_ARlRi = Arrays.asList(ashish, rahul, ravi);
        List<Users> usersList_RlRi = Arrays.asList(rahul, ravi);

        //Group Trekking
        Groups trekkingGroup = groupsService.createGroup("Trekking", manoj, usersList);

        //Expenses
        expensesService.createExpenses("Breakfast", trekkingGroup, manoj, 500.0, usersList);

        expensesService.createExpenses("Lunch - M", trekkingGroup, manoj, 651.0, usersList_AMRi);
        expensesService.createExpenses("Lunch - A", trekkingGroup, ashish, 275.0, usersList_ARlRi);
        expensesService.createExpenses("Lunch - R", trekkingGroup, rahul, 351.0, usersList_AMRa);

        expensesService.createExpenses("Shoes", trekkingGroup, manoj, 500.0, Arrays.asList(ashish));

        expensesService.createExpenses("Snacks", trekkingGroup, ravi, 500.0, usersList_RlRi);

        //Reports
        expenseManagerService.getExpenses(manoj);

        expenseManagerService.getBalances(manoj);
    }
}
