import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     March 30, 2021
 **************************************************************************** */
public class PercolationStats {
    private final int myN;
    private final double[] myTrials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Use Positive Values for n and trials");
        }
        myN = n;
        myTrials = new double[trials];
        percolator();
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(myTrials);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(myTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + absConfidence();
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - absConfidence();
    }

    private double absConfidence() {
        double Z_95 = 1.960;
        return Z_95 * stddev() / Math.sqrt(myTrials.length);
    }

    private void percolator() {
        for (int i = 0; i < myTrials.length; i++) {
            Percolation testBoard = new Percolation(myN);
            double percentageOpen;
            while (!testBoard.percolates()) {

                int randomRow = StdRandom.uniform(1, myN + 1);
                int randomCol = StdRandom.uniform(1, myN + 1);

                testBoard.open(randomRow, randomCol);
            }
            percentageOpen = testBoard.numberOfOpenSites() / Math.pow(myN, 2);
            myTrials[i] = percentageOpen;
        }
    }


    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println(String.format("%-25s= %s", "mean", stats.mean()));
        System.out.println(String.format("%-25s= %s", "stddev", stats.stddev()));
        System.out.println(String.format("%-25s= %s", "95% confidence interval", "[" + stats.confidenceLo() + "," + stats.confidenceHi() + "]"));
    }

}
