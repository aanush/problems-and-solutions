package com.student.compete.leetcode.dp;

public class InversePairsArray {

    // study more
    // https://leetcode.com/problems/k-inverse-pairs-array/description/

    public int kInversePairs(int n, int k) {
        int[][] dp = getInversePairsCountDP(n, k);
        return dp[n][k];
    }

    private int[][] getInversePairsCountDP(int itemCount, int pairCount) {

        int[][] countDP = new int[itemCount + 1][pairCount + 1];

        if (itemCount >= 1) {
            countDP[1][0] = 1;
        }
        if (itemCount >= 2) {
            countDP[2][0] = 1;
        }
        if (itemCount >= 2 && pairCount >= 1) {
            countDP[2][1] = 1;
        }

        for (int n = 3; n <= itemCount; n++) {
            countDP[n][0] = 1;
            for (int x = 1; x <= pairCount; x++) {
                if (x >= n) {
                    countDP[n][x] = countDP[n - 1][x] + countDP[n][x - 1] - countDP[n - 1][x - n];
                } else {
                    countDP[n][x] = countDP[n - 1][x] + countDP[n][x - 1];
                }
            }
        }

        return countDP;
    }

}
