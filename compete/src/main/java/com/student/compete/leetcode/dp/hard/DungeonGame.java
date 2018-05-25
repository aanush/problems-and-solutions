package com.student.compete.leetcode.dp.hard;

public class DungeonGame {

    // study more
    // https://leetcode.com/problems/dungeon-game/description/

    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        int col = dungeon.length;
        int row = dungeon[0].length;
        int[][] healthDP = getHealthDP(dungeon, col, row);
        return healthDP[0][0];
    }

    private int[][] getHealthDP(int[][] dungeon, int col, int row) {

        // healthDP[x][y] = minimum health required to reach dungeon[col - 1][row - 1] from dungeon[x][y]
        int[][] healthDP = new int[col][row];

        healthDP[col - 1][row - 1] = Integer.max(1, 1 - dungeon[col - 1][row - 1]);

        for (int x = col - 2, y = row - 1; x >= 0; x--) {
            healthDP[x][y] = Integer.max(1, healthDP[x + 1][y] - dungeon[x][y]);
        }

        for (int y = row - 2, x = col - 1; y >= 0; y--) {
            healthDP[x][y] = Integer.max(1, healthDP[x][y + 1] - dungeon[x][y]);
        }

        for (int x = col - 2; x >= 0; x--) {
            for (int y = row - 2; y >= 0; y--) {
                int hx = Integer.max(1, healthDP[x + 1][y] - dungeon[x][y]);
                int hy = Integer.max(1, healthDP[x][y + 1] - dungeon[x][y]);
                healthDP[x][y] = Integer.min(hx, hy);
            }
        }

        return healthDP;
    }

}
