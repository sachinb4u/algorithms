import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final int n;
    private final int trials;
    private final double[] thresholds;

    public PercolationStats(int num, int trials) {
        if (num <= 0 || trials <= 0)
            throw new IllegalArgumentException(String.format("Illegal n = %d and/or trials = %d", num, trials));

        this.n = num;
        this.trials = trials;
        thresholds = new double[trials];

    }

    private void execute() {

        for (int t = 0; t < trials; t++) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {

                int num1 = StdRandom.uniform(1, n * n);

                int c1 = num1 % n == 0 ? n : num1 % n;
                int r1 = num1 % n == 0 ? num1 / n : num1 / n + 1;

                perc.open(r1, c1);
                /*
                 * System.out.println(String.
                 * format("random = %d, (%3d, %3d) Perculated? =%6b, NumberOfOpenSites = %5s ", num1, r1, c1,
                 * perc.percolates(), perc.numberOfOpenSites()));
                 */

            }
            thresholds[t] = (double) perc.numberOfOpenSites() / (n * n);

        }

        System.out.println("mean = " + mean());
        System.out.println("stddev = " + stddev());
        System.out.println("95 % confidence interval = [" + confidenceLo() + "," + confidenceHi() + "]");

    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            int n = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);

            PercolationStats stats = new PercolationStats(n, trials);
            stats.execute();
        }

    }
}
