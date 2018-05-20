package com.student.compete.leetcode.dp.hard;

import java.util.*;

public class ArithmeticSlices2Subsequence {

    // study more
    // https://leetcode.com/problems/arithmetic-slices-ii-subsequence/description/

    // solution 1 : Time Limit Exceeded
    public int numberOfArithmeticSlices(int[] a) {
        if (a.length <= 2) {
            return 0;
        }
        Long[] arr = Arrays.stream(a)
                .mapToLong(value -> value)
                .boxed()
                .toArray(Long[]::new);

        Long[] difference = getAllDifference(arr);
        Map<Long, Integer> differenceMap = getDifferenceIndexMap(difference);
        int[][] sliceCountDP = getSliceCountDP(arr, differenceMap);

        return Arrays.stream(sliceCountDP)
                .map(col -> Arrays.stream(col).sum())
                .mapToInt(value -> value)
                .sum();
    }

    private int[][] getSliceCountDP(Long[] arr, Map<Long, Integer> differenceMap) {

        int[][] pairsCountDP = new int[arr.length][differenceMap.size()];

        for (int x = 1; x <= arr.length - 1; x++) {
            for (int w = 0; w <= x - 1; w++) {
                Long diff = arr[x] - arr[w];
                int diffIndex = differenceMap.get(diff);
                pairsCountDP[x][diffIndex] = pairsCountDP[x][diffIndex] + 1;
            }
        }

        int[][] sliceCountDP = new int[arr.length][differenceMap.size()];

        if (arr[2] - arr[1] == arr[1] - arr[0]) {
            Long diff = arr[2] - arr[1];
            int diffIndex = differenceMap.get(diff);
            sliceCountDP[2][diffIndex] = 1;
        }

        for (int x = 3; x <= arr.length - 1; x++) {
            for (int w = 0; w <= x - 1; w++) {
                Long diff = arr[x] - arr[w];
                int diffIndex = differenceMap.get(diff);
                sliceCountDP[x][diffIndex] = sliceCountDP[x][diffIndex] + sliceCountDP[w][diffIndex] + pairsCountDP[w][diffIndex];
            }
        }

        return sliceCountDP;
    }

    private Long[] getAllDifference(Long[] arr) {
        List<Long> allDifference = new ArrayList<>();
        for (int x = 1; x <= arr.length - 1; x++) {
            for (int w = 0; w <= x - 1; w++) {
                allDifference.add(arr[x] - arr[w]);
            }
        }
        return allDifference
                .stream()
                .sorted()
                .distinct()
                .toArray(Long[]::new);
    }

    private Map<Long, Integer> getDifferenceIndexMap(Long[] difference) {
        Map<Long, Integer> differenceMap = new HashMap<>();
        for (int i = 0; i <= difference.length - 1; i++) {
            differenceMap.put(difference[i], i);
        }
        return differenceMap;
    }

}
