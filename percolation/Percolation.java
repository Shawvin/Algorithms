/* *****************************************************************************
 *  Name:              Wang Xiaoyuan
 *  Coursera User ID:  123456
 *  Last modified:     10/11/2021
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int count;
    private final int size;
    private final int top;
    private final int bottom;
    private boolean[][] grid;
    private final WeightedQuickUnionUF wqu;
    private final WeightedQuickUnionUF backWqu;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        count = 0;
        size = n;
        int gridSize = n * n + 2;
        top = n * n;
        bottom = n * n + 1;
        grid = new boolean[n][n];
        wqu = new WeightedQuickUnionUF(gridSize);
        backWqu = new WeightedQuickUnionUF(gridSize - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row = row - 1;
        col = col - 1;
        if (row < 0 || row >= size || col < 0 || col >= size)
            throw new IllegalArgumentException();
        if (!grid[row][col]) {
            grid[row][col] = true;
            count += 1;
            int curPos = row * size + col;
            if (row == 0) {
                wqu.union(curPos, top);
                backWqu.union(curPos, top);
            }
            if (row == size - 1) {
                wqu.union(curPos, bottom);
            }
            if (row - 1 >= 0 && grid[row - 1][col]) {
                int upPos = (row - 1) * size + col;
                wqu.union(curPos, upPos);
                backWqu.union(curPos, upPos);
            }
            if (col - 1 >= 0 && grid[row][col - 1]) {
                int leftPos = row * size + col - 1;
                wqu.union(curPos, leftPos);
                backWqu.union(curPos, leftPos);
            }
            if (col + 1 <= size - 1 && grid[row][col + 1]) {
                int rightPos = row * size + col + 1;
                wqu.union(curPos, rightPos);
                backWqu.union(curPos, rightPos);
            }
            if (row + 1 <= size - 1 && grid[row + 1][col]) {
                int downPos = (row + 1) * size + col;
                wqu.union(curPos, downPos);
                backWqu.union(curPos, downPos);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row -= 1;
        col -= 1;
        if (row < 0 || row >= size || col < 0 || col >= size)
            throw new IllegalArgumentException();
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        row -= 1;
        col -= 1;
        if (row < 0 || row >= size || col < 0 || col >= size)
            throw new IllegalArgumentException();
        int curPos = row * size + col;
        return backWqu.find(curPos) == backWqu.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqu.find(bottom) == wqu.find(top);
    }

}
