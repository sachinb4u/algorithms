import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class PercolationStats {

	int N;
	int trials;
	int[] numsites;

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException(String.format("Illegal n = %d and/or trials = %d", n, trials));

		this.N = n;
		this.trials = trials;
		numsites = new int[trials];

		execute();
	}

	private void execute() {

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		List<Future<Integer>> fts = new ArrayList<Future<Integer>>();
		for (int i = 0; i < trials; i++) {
			fts.add(forkJoinPool.submit(new PercolationTask(N)));
		}
		forkJoinPool.shutdown();
		int i = 0;
		for (Future<Integer> ft : fts) {
			try {
				numsites[i++] = ft.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		double mean = 0, sum = 0, std = 0;
		for (int j = 0; j < numsites.length; j++) {
			sum += numsites[j];
		}

		mean = sum / numsites.length;

		for (int j = 0; j < numsites.length; j++) {
			std += Math.pow(numsites[j] - mean, 2);
		}
		std = Math.sqrt(std / numsites.length);

		System.out.println("mean = " + mean);
		System.out.println("std = " + std);
	}

	class PercolationTask implements Callable<Integer> {
		int n;

		public PercolationTask(int n) {
			this.n = n;
		}

		public Integer call() throws Exception {
			Random random = SecureRandom.getInstanceStrong();
			Percolation perc = new Percolation(n);

			while (!perc.percolates()) {
				int num1 = random.nextInt(n);
				int num2 = random.nextInt(n);
//				System.out.println(String.format("num1 = %d, num2 = %d", num1, num2));
				if ((num1 > 0 && num1 <= n) && (num2 > 0 && num2 <= n)) {
					System.out.println(String.format("n1 = %d, n2 = %d", num1, num2));
					perc.open(num1, num2);
					
				}
			}
			return perc.numberOfOpenSites();
		}

	}

	public double mean() {
		return 0;
	}

	public double stddev() {
		return 0;
	}

	public double confidenceLo() {
		return 0;
	}

	public double confidenceHi() {
		return 0;
	}

	public static void main(String[] args) {
		args = new String[] { "5", "1" };
		if (args.length >= 2) {
			int n = Integer.parseInt(args[0]);
			int trials = Integer.parseInt(args[1]);

			PercolationStats stats = new PercolationStats(n, trials);
		}

	}
}
