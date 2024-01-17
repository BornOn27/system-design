package lowLevelDesign._002_splitwise;

import java.util.*;

public class MinimumNumberOfTransactions {
    //Question-Link :: https://www.codingninjas.com/studio/problems/settle-debt_1232658
    
    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(0, 1, 10));
        input.add(Arrays.asList(2, 0, 5));


        System.out.println("Minimum Number of Transaction :: "+settleDebt(input));
    }

    public static int settleDebt(List<List<Integer>> expenses) {
        // Write your code here
        Map<Integer, Integer> balances = new HashMap<>();
        for (List<Integer> thisExpense : expenses) {
            //Update lent
            balances.put(thisExpense.get(0), balances.getOrDefault(thisExpense.get(0), 0) + thisExpense.get(2));

            //Update receive
            balances.put(thisExpense.get(1), balances.getOrDefault(thisExpense.get(1), 0) - thisExpense.get(2));

        }

        System.out.println("Balances :: "+balances);

        List<Integer> allBalances = new ArrayList<>();
        for (Integer thisBalance : balances.values()) {
            if (thisBalance == 0)
                continue;
            allBalances.add(thisBalance);
        }

        return getMinimumTransactions(allBalances);
    }

    private static int getMinimumTransactions(List<Integer> allBalances) {
        int minimumTransactions = 0;

        PriorityQueue<Integer> giversMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> takersMinHeap = new PriorityQueue<>();

        for (int balance : allBalances) {
            if(balance < 0)
                takersMinHeap.add(balance);
            else giversMaxHeap.add(balance);

        }


        while (!giversMaxHeap.isEmpty() && !takersMinHeap.isEmpty()){
            System.out.println("Givers :: "+giversMaxHeap);
            System.out.println("Takers :: "+takersMinHeap);

            int settlement = giversMaxHeap.poll() + takersMinHeap.poll();
            minimumTransactions++;
            if (settlement == 0)
                continue;
            else if(settlement > 0)
                giversMaxHeap.add(settlement);
            else takersMinHeap.add(settlement);
            System.out.println();
        }


        return minimumTransactions;
    }
}
