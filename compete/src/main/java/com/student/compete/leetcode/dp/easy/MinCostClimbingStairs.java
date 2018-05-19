package com.student.compete.leetcode.dp.easy;

public class MinCostClimbingStairs {

    // study more
    // https://leetcode.com/problems/min-cost-climbing-stairs/description/

    public int minCostClimbingStairs(int[] cost) {
        if (cost.length <= 2) {
            return 0;
        }
        int[] costDP = getCostDP(cost);
        return costDP[cost.length - 1];
    }

    private int[] getCostDP(int[] cost) {

        int[] costDP = new int[cost.length];

        for (int x = 2; x <= cost.length - 1; x++) {
            costDP[x] = Integer.min(cost[x - 1] + costDP[x - 1], cost[x - 2] + costDP[x - 2]);
        }

        return costDP;
    }

}
