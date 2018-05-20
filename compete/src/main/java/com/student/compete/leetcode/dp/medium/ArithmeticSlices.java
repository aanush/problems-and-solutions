package com.student.compete.leetcode.dp.medium;

import java.util.Arrays;

public class ArithmeticSlices {

    // study more
    // https://leetcode.com/problems/arithmetic-slices/description/

    public int numberOfArithmeticSlices(int[] arr) {
        if (arr.length <= 2) {
            return 0;
        }

        int[] sliceDP = getSliceCountDP(arr);
        return Arrays.stream(sliceDP)
                .sum();
    }

    private int[] getSliceCountDP(int[] arr) {

        int[] sliceCountDP = new int[arr.length];

        //sliceCountDP[0] = 0;
        //sliceCountDP[1] = 0;
        //sliceCountDP[2] = 0;
        if (arr[2] - arr[1] == arr[1] - arr[0]) {
            sliceCountDP[2] = 1;
        }

        for (int x = 3; x <= arr.length - 1; x++) {
            //sliceCountDP[x] = 0;
            if (arr[x] - arr[x - 1] == arr[x - 1] - arr[x - 2]) {
                sliceCountDP[x] = 1 + sliceCountDP[x - 1];
            }
        }

        return sliceCountDP;
    }

}
