package com.student.compete.leetcode.dp.hard;

import java.util.ArrayList;
import java.util.List;

public class CherryPickup {

    // study more
    // https://leetcode.com/problems/cherry-pickup/description/

    public int cherryPickup(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int col = grid.length;
        int row = grid[0].length;
        int step = col + row - 1;
        int[][][] cherryDP = getCherryDP(grid, col, row);
        return Integer.max(0, cherryDP[step - 1][col - 1][col - 1]);
    }

    private int[][][] getCherryDP(int[][] grid, int col, int row) {

        // at each step either x increases or y increases
        // x + y = s = number of step
        // min : x == 0; y == 0; s == 0
        // max : x == col - 1; y == row - 1; s == col + row - 2;
        // length(x) = length : 0 to col - 1 = col
        // length(y) = length : 0 to row - 1 = row
        // length(s) = length : 0 to col + row - 2 = col + row - 1
        // cherryDP[s][x1][x2] =
        // maximum of number of cherry could be picked during
        // first s step of forward pickup and last s step of backward pickup, if
        // i pick cherry at (x1, s - x1) during forward pickup and
        // i pick cherry at (x2, s - x2) during backward pickup

        int[][][] cherryDP = new int[col + row - 1][col][col];

        cherryDP[0][0][0] = grid[0][0];

        for (int s = 1; s <= col + row - 2; s++) {
            for (int x1 = 0; x1 <= Integer.min(s, col - 1); x1++) {
                for (int x2 = 0; x2 <= Integer.min(s, col - 1); x2++) {
                    int y1 = s - x1;
                    int y2 = s - x2;
                    if (y1 >= 0 && y1 <= row - 1 && y2 >= 0 && y2 <= row - 1) {

                        int thornPickup = -1;
                        int currentStepPickup = thornPickup;
                        int f = grid[x1][y1];
                        int b = grid[x2][y2];
                        if (f != thornPickup && b != thornPickup) {
                            boolean sameForwardBackward = (x1 == x2 && y1 == y2);
                            currentStepPickup = sameForwardBackward ? f : f + b;
                        }

                        if (currentStepPickup == thornPickup) {
                            cherryDP[s][x1][x2] = thornPickup;
                        } else {
                            // at step s
                            // (x1, y1)
                            // (x2, y2)
                            // at step s - 1
                            // (x1, y1 - 1) or (x1 - 1, y1)
                            // (x2, y2 - 1) or (x2 - 1, y2)
                            List<Integer> previousStepPickupList = new ArrayList<>(4);
                            if (y1 - 1 >= 0 && y2 - 1 >= 0) {
                                previousStepPickupList.add(cherryDP[s - 1][x1][x2]);
                            }
                            if (y1 - 1 >= 0 && x2 - 1 >= 0) {
                                previousStepPickupList.add(cherryDP[s - 1][x1][x2 - 1]);
                            }
                            if (x1 - 1 >= 0 && y2 - 1 >= 0) {
                                previousStepPickupList.add(cherryDP[s - 1][x1 - 1][x2]);
                            }
                            if (x1 - 1 >= 0 && x2 - 1 >= 0) {
                                previousStepPickupList.add(cherryDP[s - 1][x1 - 1][x2 - 1]);
                            }

                            if (previousStepPickupList.isEmpty()) {
                                cherryDP[s][x1][x2] = currentStepPickup;
                            } else {
                                int maxPreviousStepPickup = previousStepPickupList.stream()
                                        .mapToInt(v -> v)
                                        .max()
                                        .orElse(thornPickup);
                                cherryDP[s][x1][x2] = (maxPreviousStepPickup == thornPickup) ? thornPickup : currentStepPickup + maxPreviousStepPickup;
                            }
                        }
                        //
                    }
                }
            }
        }

        return cherryDP;
    }

}
