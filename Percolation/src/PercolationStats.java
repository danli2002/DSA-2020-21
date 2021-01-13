/**
 * In a famous scientific problem, researchers are interested in the following question: 
 * if sites are independently set to be open with probability p (and therefore blocked with 
 * probability 1 − p), what is the probability that the system percolates? When p equals 0, 
 * the system does not percolate; when p equals 1, the system percolates. The plots below 
 * show the site vacancy probability p versus the percolation probability for 20-by-20 random 
 * grid (left) and 100-by-100 random grid (right).
 * 
 * When N is sufficiently large, there is a threshold value p* such that when p < p* a random 
 * N-by-N grid almost never percolates, and when p > p*, a random N-by-N grid almost always 
 * percolates. No mathematical solution for determining the percolation threshold p* has yet 
 * been derived. Your task is to write a computer program to estimate p*.
 * 
 * @author Daniel Li
 * @version Java 1.8.0
 */

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private Percolation percolation;
    private int size;
    private int len;
    private int iterations;
    public double[] thresholds;

    public PercolationStats(int n, int t) {
        // throw error if negative numbers are supplied
        if (n < 1 || t < 1) {
            throw new IllegalArgumentException("Give n or t a value that is greater than 0");
        }
        iterations = t;
        len = n;
        size = n * n;
        // create array of the thresholds that we experimentally collect
        thresholds = new double[iterations];
        for (int i = 0; i < iterations; i++) {
            // initialize new percolation object every experiment
            percolation = new Percolation(n);
            // keep opening unopened sites randomly until it percolates
            while (!percolation.percolates()) {
                // uses StdRandom to pick random numbers uniformly from 0 to n
                int j = StdRandom.uniform(0, n);
                int k = StdRandom.uniform(0, n);
                // open the site
                if (!percolation.isOpen(j, k)) {
                    percolation.open(j, k);
                }
            }
            // add the threshold to the list
            thresholds[i] = percolation.numberOfOpenSites() / (double) size;
        }
    }

    // return mean of the threshold list
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // return stddev of threshold list
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // return the lower bound of the 95% confidence interval
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(iterations));
    }

    // return the upper bound of the 95% confidence interval
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(iterations));
    }

    // tester method
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(20, 5000);
        System.out.println(String.format("Showing experimental stats for %d trials for a %dx%d grid:\n",
                stats.iterations, stats.len, stats.len));
        System.out.println("mean: " + stats.mean());
        System.out.println("stddev: " + stats.stddev());
        System.out.println("95% confidence low: " + stats.confidenceLow());
        System.out.println("95% confidence high: " + stats.confidenceHigh());
    }

    /** Output
     * 
     *  Showing experimental stats for 5000 trials for a 20x20 grid:

        mean: 0.5926330000000009
        stddev: 0.04876095094506902
        95% confidence low: 0.5912814153964426
        95% confidence high: 0.5939845846035591
     * 
     */
}