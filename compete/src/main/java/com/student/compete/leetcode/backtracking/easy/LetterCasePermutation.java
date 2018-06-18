package com.student.compete.leetcode.backtracking.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LetterCasePermutation {

    // study more
    // https://leetcode.com/problems/letter-case-permutation/description/

    public List<String> letterCasePermutation(String s) {
        String[] split = s.split("");
        List<String[]> list = new ArrayList<>();
        permute(split, 0, list);
        return list
                .stream()
                .map(str -> String.join("", str))
                .distinct()
                .collect(Collectors.toList());
    }

    private void permute(String[] split, int index, List<String[]> list) {
        if (index > split.length - 1) {
            return;
        }
        String c1 = split[index].toLowerCase();
        String c2 = split[index].toUpperCase();
        if (c1.equals(c2)) {
            list.add(split);
            permute(split, index + 1, list);
        } else {
            String[] copy1 = Arrays.copyOf(split, split.length);
            String[] copy2 = Arrays.copyOf(split, split.length);
            copy1[index] = c1;
            copy2[index] = c2;
            list.add(copy1);
            list.add(copy2);
            permute(copy1, index + 1, list);
            permute(copy2, index + 1, list);
        }
    }

}
