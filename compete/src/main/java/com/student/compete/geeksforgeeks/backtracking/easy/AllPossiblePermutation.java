package com.student.compete.geeksforgeeks.backtracking.easy;

import java.util.*;
import java.util.stream.Collectors;

public class AllPossiblePermutation {

    // study more
    // https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/

    public List<String> getAllPossiblePermutation(String s) {
        String[] permutation = s.split("");
        Set<String[]> permutationSet = getAllPossiblePermutation(permutation, 0);
        return permutationSet.stream()
                .map(arr -> String.join("", arr))
                .collect(Collectors.toList());
    }

    private Set<String[]> getAllPossiblePermutation(String[] permutation, int index) {
        if (index >= permutation.length - 1) {
            return Collections.singleton(permutation);
        }
        Set<String[]> permutationSet = new HashSet<>();
        for (String[] permutationSwap : getPermutationSwapSet(permutation, index)) {
            permutationSet.addAll(getAllPossiblePermutation(permutationSwap, index + 1));
        }
        return permutationSet;
    }

    private Set<String[]> getPermutationSwapSet(String[] permutation, int index1) {
        Set<String[]> permutationSwapSet = new HashSet<>();
        for (int index2 = index1; index2 <= permutation.length - 1; index2++) {
            String[] permutationSwap = getPermutationSwap(permutation, index1, index2);
            permutationSwapSet.add(permutationSwap);
        }
        return permutationSwapSet;
    }

    private String[] getPermutationSwap(String[] permutation, int index1, int index2) {
        String[] permutationSwap = Arrays.copyOf(permutation, permutation.length);
        permutationSwap[index1] = permutation[index2];
        permutationSwap[index2] = permutation[index1];
        return permutationSwap;
    }

}
