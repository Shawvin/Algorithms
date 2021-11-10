/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static double confLevel95;
    private final double[] trialRecord;
    private final int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        trialRecord = new double[trials];
        this.trials = trials;
        confLevel95 = 1.96;
        double size = (double) (n * n);
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }
            trialRecord[i] = p.numberOfOpenSites() / size;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialRecord);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialRecord);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double mean = this.mean();
        double s = this.stddev();
        return mean - confLevel95 * s / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double mean = this.mean();
        double s = this.stddev();
        return mean + confLevel95 * s / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats pStat = new PercolationStats(n, trials);

        System.out.println("mean\t\t\t\t\t= " + pStat.mean());
        System.out.println("stddev\t\t\t\t\t= " + pStat.stddev());
        System.out.println(
                "95% confidence interval = [" + pStat.confidenceLo() + "," + pStat.confidenceHi()
                        + "]");
    }
}
