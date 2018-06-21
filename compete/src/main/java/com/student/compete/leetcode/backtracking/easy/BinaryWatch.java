package com.student.compete.leetcode.backtracking.easy;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryWatch {

    // study more
    // https://leetcode.com/problems/binary-watch/description/

    public List<String> readBinaryWatch(int glowingDigitCount) {
        if (glowingDigitCount == 0) {
            return Collections.singletonList("0:00");
        }
        int totalDigitCount1 = 4;
        int totalDigitCount2 = 6;
        return new ArrayList<>(getWatchSet(glowingDigitCount, totalDigitCount1, totalDigitCount2));
    }

    private Set<String> getWatchSet(int glowingDigitCount, int totalDigitCount1, int totalDigitCount2) {

        int maxGlowingDigitCount1 = Integer.min(glowingDigitCount, totalDigitCount1);
        int maxGlowingDigitCount2 = Integer.min(glowingDigitCount, totalDigitCount2);

        Set<int[]>[] dp1 = getPermutationSetDP(maxGlowingDigitCount1, totalDigitCount1);
        Set<int[]>[] dp2 = getPermutationSetDP(maxGlowingDigitCount2, totalDigitCount2);

        Set<String> watchSet = new HashSet<>();

        for (int count1 = 0; count1 <= maxGlowingDigitCount1; count1++) {
            for (int count2 = 0; count2 <= maxGlowingDigitCount2; count2++) {
                if (count1 + count2 == glowingDigitCount) {
                    for (int[] permutation1 : dp1[count1]) {
                        for (int[] permutation2 : dp2[count2]) {
                            int v1 = getIntegerValueOf(permutation1);
                            int v2 = getIntegerValueOf(permutation2);
                            if (0 <= v1 && v1 <= 11 && 0 <= v2 && v2 <= 59) {
                                String formatWatch = getFormatWatch(v1, v2);
                                watchSet.add(formatWatch);
                            }
                        }
                    }
                }
            }
        }

        return watchSet;
    }

    private String getFormatWatch(int v1, int v2) {
        String s1 = String.valueOf(v1);
        String s2 = v2 <= 9 ? "0" + String.valueOf(v2) : String.valueOf(v2);
        return s1 + ":" + s2;
    }

    private int getIntegerValueOf(int[] permutation) {
        String value = Arrays.stream(permutation)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining());
        return Integer.parseInt(value, 2);
    }

    private Set<int[]>[] getPermutationSetDP(int maxGlowingDigitCount, int totalDigitCount) {

        Set<int[]>[] permutationSetDP = new Set[totalDigitCount + 1];

        permutationSetDP[0] = Collections.singleton(new int[totalDigitCount]);

        for (int glowingDigitCount = 1; glowingDigitCount <= maxGlowingDigitCount; glowingDigitCount++) {
            Set<int[]> permutationSet = new HashSet<>();
            for (int[] permutation : permutationSetDP[glowingDigitCount - 1]) {
                permuteIncrementGlow(permutation, permutationSet, 0, 0, 1);
            }
            permutationSetDP[glowingDigitCount] = permutationSet;
        }

        return permutationSetDP;
    }

    private void permuteIncrementGlow(int[] permutation, Set<int[]> permutationSet, int glowIndex, int fade, int glow) {

        if (glowIndex > permutation.length - 1) {
            return;
        }

        int digit = permutation[glowIndex];
        if (digit == fade) {
            int[] permutationCopy = Arrays.copyOf(permutation, permutation.length);
            permutationCopy[glowIndex] = glow;
            permutationSet.add(permutationCopy);
            permuteIncrementGlow(permutation, permutationSet, glowIndex + 1, fade, glow);
        } else {
            permuteIncrementGlow(permutation, permutationSet, glowIndex + 1, fade, glow);
        }

    }

}
