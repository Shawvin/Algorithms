/* *****************************************************************************
 *  Name: Wang Xiaoyuan
 *  Date: 27 Nov 2021
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class Board {
    private final int[][] tiles;
    private final int dimension;
    private final int hammingD;
    private final int manhaD;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        dimension = tiles.length;
        // this.tiles = tiles.clone();
        this.tiles = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }

        int distance = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != tileVal(i, j))
                    distance += 1;
            }
        }
        hammingD = distance;

        int distance2 = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int tileValue = tiles[i][j];
                if (tileValue != 0) {
                    int correctRow = (tileValue - 1) / dimension;
                    int correctCol = (tileValue - 1) % dimension;
                    distance2 = distance2 + Math.abs(i - correctRow) + Math.abs(j - correctCol);
                }
            }
        }
        manhaD = distance2;

    }

    // string representation of this board
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(dimension + "\n");
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                str.append(tiles[row][col] + "\t");
            }
            if (row != dimension - 1) str.append("\n");
        }
        return str.toString();
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // private helper value
    private int tileVal(int i, int j) {
        if (i == dimension - 1 && j == dimension - 1)
            return 0;
        return i * dimension + j + 1;
    }

    // number of tiles out of place
    public int hamming() {
        return hammingD;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhaD;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0 && manhattan() == 0;
    }

    // get the value at index i, j
    private int getValue(int i, int j) {
        return this.tiles[i][j];
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass())
            return false;
        Board that = (Board) y;
        if (dimension != that.dimension())
            return false;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.tiles[i][j] != that.getValue(i, j))
                    return false;
            }
        }
        return true;
    }

    // private helper function
    private void swap(int i1, int j1, int i2, int j2) {
        int temp = tiles[i1][j1];
        tiles[i1][j1] = tiles[i2][j2];
        tiles[i2][j2] = temp;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> q = new Queue<>();
        int zeroIndRow = 0;
        int zeroIndCol = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tiles[i][j] == 0) {
                    zeroIndRow = i;
                    zeroIndCol = j;
                }
            }
        }

        if (zeroIndCol >= 1) {
            swap(zeroIndRow, zeroIndCol, zeroIndRow, zeroIndCol - 1);
            q.enqueue(new Board(tiles));
            swap(zeroIndRow, zeroIndCol, zeroIndRow, zeroIndCol - 1);
        }
        if (zeroIndCol < dimension - 1) {
            swap(zeroIndRow, zeroIndCol, zeroIndRow, zeroIndCol + 1);
            q.enqueue(new Board(tiles));
            swap(zeroIndRow, zeroIndCol, zeroIndRow, zeroIndCol + 1);
        }

        if (zeroIndRow >= 1) {
            swap(zeroIndRow, zeroIndCol, zeroIndRow - 1, zeroIndCol);
            q.enqueue(new Board(tiles));
            swap(zeroIndRow, zeroIndCol, zeroIndRow - 1, zeroIndCol);
        }
        if (zeroIndRow < dimension - 1) {
            swap(zeroIndRow, zeroIndCol, zeroIndRow + 1, zeroIndCol);
            q.enqueue(new Board(tiles));
            swap(zeroIndRow, zeroIndCol, zeroIndRow + 1, zeroIndCol);
        }
        return q;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        Board twinBoard;
        if (tiles[0][0] != 0 && tiles[0][1] != 0) {
            swap(0, 0, 0, 1);
            twinBoard = new Board(tiles);
            swap(0, 0, 0, 1);
        }
        else {
            swap(1, 0, 1, 1);
            twinBoard = new Board(tiles);
            swap(1, 0, 1, 1);
        }
        return twinBoard;
    }

    // unit testing
    public static void main(String[] args) {
        // for each command-line argument
        for (String filename : args) {

            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            // Solver solver = new Solver(initial);
            // StdOut.println(filename + ": " + solver.moves());
            System.out.println(initial.twin());
            for (Board b : initial.neighbors()) {
                System.out.println(b);
                System.out.println(b.isGoal());
                System.out.println(b.manhattan());
            }
        }
    }
}
