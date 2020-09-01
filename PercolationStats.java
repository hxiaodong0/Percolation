import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

//public class PercolationStats {
//
//    private int experimentsCount;
//    private Percolation pr;
//    private double[] fractions;
//
//    /**
//     * Performs T independent computational experiments on an N-by-N grid.
//     */
//    public PercolationStats(int N, int T) {
//        if (N <= 0 || T <= 0) {
//            throw new IllegalArgumentException("Given N <= 0 || T <= 0");
//        }
//        experimentsCount = T;
//        fractions = new double[experimentsCount];
//        for (int expNum = 0; expNum < experimentsCount; expNum++) {
//            pr = new Percolation(N);
//            int openedSites = 0;
//            while (!pr.percolates()) {
//                int i = StdRandom.uniform(1, N + 1);
//                int j = StdRandom.uniform(1, N + 1);
//                if (!pr.isOpen(i, j)) {
//                    pr.open(i, j);
//                    openedSites++;
//                }
//            }
//            double fraction = (double) openedSites / (N * N);
//            fractions[expNum] = fraction;
//        }
//    }
//
//    /**
//     * Sample mean of percolation threshold.
//     */
//    public double mean() {
//        return StdStats.mean(fractions);
//    }
//
//    /**
//     * Sample standard deviation of percolation threshold.
//     */
//    public double stddev() {
//        return StdStats.stddev(fractions);
//    }
//
//    /**
//     * Returns lower bound of the 95% confidence interval.
//     */
//    public double confidenceLo() {
//        return mean() - ((1.96 * stddev()) / Math.sqrt(experimentsCount));
//    }
//
//    /**
//     * Returns upper bound of the 95% confidence interval.
//     */
//    public double confidenceHi() {
//        return mean() + ((1.96 * stddev()) / Math.sqrt(experimentsCount));
//    }
//
//    public static void main(String[] args) {
//        int N = Integer.parseInt(args[0]);
//        int T = Integer.parseInt(args[1]);
//        PercolationStats ps = new PercolationStats(N, T);
//
//        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
//        StdOut.println("mean                    = " + ps.mean());
//        StdOut.println("stddev                  = " + ps.stddev());
//        StdOut.println("95% confidence interval = " + confidence);
//    }
//}


public class PercolationStats {

    private Percolation item;
    // perform independent trials on an n-by-n grid
    private int cnt;
    private double[] fractions;
    public PercolationStats(int n, int trials){
        if (n<=0 || trials <= 0){
            throw new IllegalArgumentException("n<=0 or trials <= 0");

        }
        cnt = trials;
        fractions = new double[cnt];
        for (int i = 0; i < cnt; i++) {
            item = new Percolation(n);
            int open = 0;
            while(!item.percolates()){
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1,n+1);
                if (! item.isOpen(row, col)){
                item.open(row, col);
                open +=1;
                }
            }
        double frac = (double) open/(n *n);
        fractions[i] = frac;

        }
    }
//
    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(fractions);
    }
//
    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    }
//
    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - ((1.96 * stddev()) / Math.sqrt((double)cnt));
    }
//
    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() - ((1.96 * stddev()) / Math.sqrt((double)cnt));
    }

//
    // test client (see below)
    public static void main(String[] args){
        PercolationStats ps = new PercolationStats(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1])
        );
        System.out.println("Mean                    = " + ps.mean());
        System.out.println("StdDev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());

    }

}