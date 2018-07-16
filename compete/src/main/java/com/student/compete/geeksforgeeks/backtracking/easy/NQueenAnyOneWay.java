package com.student.compete.geeksforgeeks.backtracking.easy;

import java.util.Arrays;

public class NQueenAnyOneWay {

    // study more
    // https://www.geeksforgeeks.org/backtracking-set-3-n-queen-problem/

    public void getPrintNQueen(int n) {
        // place[x] == garbage
        // means there is no queen in column x
        // place[x] == y
        // means there is a queen in column x and row y
        int garbage = -1;
        int[] place = placeNQueen(n, garbage);
        Arrays.stream(place).forEach(System.out::println);
    }

    private int[] placeNQueen(int n, int garbage) {
        int[] place = new int[n];
        Arrays.fill(place, garbage);
        for (int y1 = 0; y1 <= place.length - 1; y1++) {
            boolean success = placeAndBacktrack(0, y1, place, garbage);
            if (success) {
                break;
            }
        }
        return place;
    }

    private boolean placeAndBacktrack(int x, int y, int[] place, int garbage) {
        for (int x1 = 0; x1 <= place.length - 1; x1++) {
            int y1 = place[x1];
            if (y1 != garbage && attack(x1, y1, x, y)) {
                return false;
            }
        }
        place[x] = y;
        if (x == place.length - 1) {
            return true;
        }
        for (int y1 = 0; y1 <= place.length - 1; y1++) {
            boolean success = placeAndBacktrack(x + 1, y1, place, garbage);
            if (success) {
                return true;
            }
        }
        place[x] = garbage;
        return false;
    }

    private boolean attack(int x1, int y1, int x2, int y2) {
        return (x1 == x2) || (y1 == y2) || (x1 + y1 == x2 + y2) || (x1 - y1 == x2 - y2);
    }

}
