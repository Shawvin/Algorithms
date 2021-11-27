/* *****************************************************************************
 *  Name: Wang Xiaoyuan
 *  Date: 27 Nov 2021
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private int solverStep;
    private final Queue<Board> q = new Queue<>();
    private boolean solvable;

    // private class
    private class Node implements Comparable<Node> {
        private final Board board;
        private final Node parent;
        private final int step;
        // private int hPriority;
        private final int mPriority;

        private Node(Board board, int step, Node parent) {
            this.board = board;
            this.step = step;
            this.parent = parent;
            // hPriority = step + this.board.hamming();
            mPriority = this.step + this.board.manhattan();
        }

        public int compareTo(Node that) {
            if (this.mPriority != that.mPriority)
                return this.mPriority - that.mPriority;
                //   return this.hPriority - that.hPriority;
            else
                return that.step - this.step;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException();
        MinPQ<Node> minPQ = new MinPQ<>();
        MinPQ<Node> minPQ2 = new MinPQ<>();
        MinPQ<Node> curPQ = minPQ2;
        minPQ.insert(new Node(initial, 0, null));
        minPQ2.insert(new Node(initial.twin(), 0, null));
        while (!minPQ.isEmpty() && !minPQ2.isEmpty()) {
            curPQ = (curPQ == minPQ ? minPQ2 : minPQ);
            Node curNode = curPQ.delMin();
            Board curBoard = curNode.board;

            if (curBoard.isGoal()) {
                if (curPQ == minPQ2) {
                    solvable = false;
                    break;
                }
                solvable = true;
                solverStep = curNode.step;
                Node trace = curNode;
                Stack<Board> st = new Stack<>();
                while (trace != null) {
                    st.push(trace.board);
                    trace = trace.parent;
                }
                while (!st.isEmpty())
                    q.enqueue(st.pop());
                break;
            }
            for (Board neighbor : curBoard.neighbors()) {
                if (curNode.parent != null && neighbor.equals(curNode.parent.board)) {
                    continue;
                }
                curPQ.insert(new Node(neighbor, curNode.step + 1, curNode));
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable())
            return -1;
        else
            return solverStep;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;
        else
            return q;
    }

    // test client (see below)
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
            Solver solver = new Solver(initial);
            StdOut.println(filename + ": " + solver.moves());
        }
    }
}
