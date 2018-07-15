package com.student.compete.geeksforgeeks.backtracking.easy;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RatInAMaze {

    // study more
    // https://www.geeksforgeeks.org/backttracking-set-2-rat-in-a-maze/

    public void getPrintTour(boolean[][] maze, int m, int n) {
        Block[][] board = new Block[m][n];
        for (int x = 0; x <= m - 1; x++) {
            for (int y = 0; y <= n - 1; y++) {
                Block block = new Block(x, y);
                board[x][y] = block;
            }
        }
        Block currentBlock = board[0][0];
        List<Block> visited = new LinkedList<>();
        visitAndBacktrack(currentBlock, visited, board, maze, m, n);
        for (Block block : visited) {
            System.out.println(block.getX() + "," + block.getY());
        }
    }

    private boolean visitAndBacktrack(Block currentBlock, List<Block> visited, Block[][] board, boolean[][] maze, int m, int n) {
        visited.add(currentBlock);
        if (currentBlock.equals(board[m - 1][n - 1])) {
            return true;
        }
        List<Block> nextBlockList = getNextBlockList(currentBlock, board, m, n);
        List<Block> nextNonVisitedBlockList = getNextAllowedNonVisitedBlockList(nextBlockList, visited, maze);
        for (Block nextNonVisitedBlock : nextNonVisitedBlockList) {
            boolean success = visitAndBacktrack(nextNonVisitedBlock, visited, board, maze, m, n);
            if (success) {
                return true;
            }
        }
        visited.remove(currentBlock);
        return false;
    }

    private List<Block> getNextAllowedNonVisitedBlockList(List<Block> nextBlockList, List<Block> visited, boolean[][] maze) {
        return nextBlockList.stream()
                .filter(block -> maze[block.getX()][block.getY()])
                .filter(block -> !visited.contains(block))
                .collect(Collectors.toList());
    }

    private List<Block> getNextBlockList(Block currentBlock, Block[][] board, int m, int n) {
        List<Block> nextBlockList = new LinkedList<>();
        addNextBlock(currentBlock.getX() + 1, currentBlock.getY(), nextBlockList, board, m, n);
        addNextBlock(currentBlock.getX(), currentBlock.getY() + 1, nextBlockList, board, m, n);
        return nextBlockList;
    }

    private void addNextBlock(int x, int y, List<Block> nextBlockList, Block[][] board, int m, int n) {
        if (0 <= x && x <= m - 1 && 0 <= y && y <= n - 1) {
            nextBlockList.add(board[x][y]);
        }
    }

    private class Block {

        private final int x;
        private final int y;

        private Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Block that = (Block) obj;
            return getX() == that.getX() && getY() == that.getY();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getX(), getY());
        }

    }

}
