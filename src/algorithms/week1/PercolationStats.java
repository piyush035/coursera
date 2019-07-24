import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
    private static final double DEFAULT_MULTIPLIER = 1.96;
    private final double[] results;
    private final int T;
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T should be greater than zero!");
        }
        this.T = T;
        results = new double[T];
        int square = N * N;
        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
            double numberOfOpenedCells = 0;
            while (!percolation.percolates()) {
                int x = StdRandom.uniform(1, N + 1);
                int y = StdRandom.uniform(1, N + 1);
                if (!percolation.isOpen(y, x)) {
                    percolation.open(y, x);
                    numberOfOpenedCells++;
                }
            }
            results[i] = numberOfOpenedCells / square;
        }
    }

    public static void main(String[] args) {
        new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1])).info();
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }


    public double confidenceLo() {
        return mean() - DEFAULT_MULTIPLIER * stddev() / Math.sqrt(T);
    }

    public double confidenceHi() {
        return mean() + DEFAULT_MULTIPLIER * stddev() / Math.sqrt(T);
    }

    private void info() {
        StdOut.printf("mean\t\t\t\t\t= %f\nstddev\t\t\t\t\t= %f\n95%% confidence interval\t= %f, %f\n",
                mean(), stddev(), confidenceLo(), confidenceHi());
    }

}
