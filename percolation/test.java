/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

public class test {
    // test client (optional)
    public static void main(String[] args) {
        In in = new In(args[0]);

        int n = in.readInt();
        Percolation p = new Percolation(n);
        while (!in.isEmpty()) {
            int row = in.readInt();
            int col = in.readInt();
            p.open(row, col);
        }

        System.out.println(p.percolates());
    }
}
