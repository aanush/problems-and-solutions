package com.student.compete.geeksforgeeks.dp.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobileNumericKeypad {

    // study more
    // https://www.geeksforgeeks.org/mobile-numeric-keypad-problem/

    public int getWaysCount(int num) {
        if (num <= 0) {
            return 0;
        }
        Map<Integer, List<Integer>> keypadMap = getKeypadMap();
        int[][] waysCountDP = getWaysCountDP(num, keypadMap);
        return Arrays.stream(waysCountDP[num - 1])
                .sum();
    }

    private Map<Integer, List<Integer>> getKeypadMap() {
        Map<Integer, List<Integer>> keypadMap = new HashMap<>();
        keypadMap.put(0, Arrays.asList(0, 8));
        keypadMap.put(1, Arrays.asList(1, 2, 4));
        keypadMap.put(2, Arrays.asList(2, 1, 3, 5));
        keypadMap.put(3, Arrays.asList(3, 2, 6));
        keypadMap.put(4, Arrays.asList(4, 1, 5, 7));
        keypadMap.put(5, Arrays.asList(5, 2, 4, 6, 8));
        keypadMap.put(6, Arrays.asList(6, 3, 5, 9));
        keypadMap.put(7, Arrays.asList(7, 4, 8));
        keypadMap.put(8, Arrays.asList(8, 0, 5, 7, 9));
        keypadMap.put(9, Arrays.asList(9, 6, 8));
        return keypadMap;
    }

    private int[][] getWaysCountDP(int num, Map<Integer, List<Integer>> keypadMap) {

        int[][] waysCountDP = new int[num][keypadMap.size()];

        Arrays.fill(waysCountDP[0], 1);

        for (int step = 1; step <= num - 1; step++) {
            for (int key = 0; key <= keypadMap.size() - 1; key++) {
                final int s = step;
                waysCountDP[step][key] = keypadMap.get(key).stream()
                        .mapToInt(k -> waysCountDP[s - 1][k])
                        .sum();
            }
        }

        return waysCountDP;
    }

}
