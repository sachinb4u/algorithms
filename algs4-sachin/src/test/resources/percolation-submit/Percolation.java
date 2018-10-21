import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdIn;

public class Percolation {

	QuickUnionUF sites;
	int TOP_SITE;
	int BOTTOM_SITE;
	int N;
	int[] siteStatus;
	int openSiteCount;

	public Percolation(int n) {

		if (n <= 0)
			throw new IllegalArgumentException("size should be greater than 0");

		this.N = n;

		// we will use actual number as site number
		TOP_SITE = 0; // first site will be virtual top
		BOTTOM_SITE = n + 1; // N+1 site will be virtual bottom

		sites = new QuickUnionUF(n * n + 2);
		siteStatus = new int[n * n + 2];

		for (int i = 0; i < n * n; i++) {
			siteStatus[i] = 0;
		}

		siteStatus[0] = 1;
		siteStatus[n * n + 1] = 1;
	}

	public void open(int row, int col) {

		checkIfValid(row, col);

		int id = rowCol2Id(row, col);

		if (!isOpen(row, col)) {
			// make site open
			siteStatus[id] = 1;
			openSiteCount++;

			// if top site, make union to virtual top 0
			if (row == 1) {
				sites.union(0, id);
			} else if (row == N) {
				// if bottom site, make union to virtual bottom N*N + 1
				sites.union(id, (N * N) + 1);
			}

		}

		// if top is opened, union it
		if (isValid(row - 1, col) && isOpen(row - 1, col))
			sites.union(id, rowCol2Id(row - 1, col));

		// if bottom is opened, union it
		if (isValid(row + 1, col) && isOpen(row + 1, col))
			sites.union(id, rowCol2Id(row + 1, col));

		// if left is opened, union it
		if (isValid(row, col - 1) && isOpen(row, col - 1))
			sites.union(id, rowCol2Id(row, col - 1));

		// if right is opened, union it
		if (isValid(row, col + 1) && isOpen(row, col + 1))
			sites.union(id, rowCol2Id(row, col + 1));
	}

	public boolean isOpen(int row, int col) {
		checkIfValid(row, col);

		int id = rowCol2Id(row, col);
		return id >= 0 && id <= siteStatus.length - 1 && siteStatus[id] == 1;
	}

	public boolean isFull(int row, int col) {
		checkIfValid(row, col);

		return !isOpen(row, col);
	}

	public int numberOfOpenSites() {

		return openSiteCount;
	}

	public boolean percolates() {
		// if top site and bottom sites are connected
		return sites.connected(0, N * N + 1);
	}

	private boolean isValidSite(int num) {
		return num >= 1 || num <= N;
	}

	private boolean isValid(int row, int col) {
		return isValidSite(row) && isValidSite(col);
	}

	private int rowCol2Id(int row, int col) {
		return (row - 1) * N + col;
	}

	private void checkIfValid(int row, int col) {
		if (!(isValid(row, col))) {
			throw new IllegalArgumentException(String.format("%1d and % %2d are invalid row and column respectively", row, col));
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(Paths.get("src/test/resources/percolation-input/wayne98.txt"));
		StdIn.setScanner(scanner);

		int n = StdIn.readInt();

		Percolation percolation = new Percolation(n);

		while (StdIn.hasNextLine()) {
			try {
				int r = StdIn.readInt();
				int c = StdIn.readInt();

				percolation.open(r, c);

				System.out.println(String.format("(%3d, %3d) Perculated? =%6b, NumberOfOpenSites = %5s ", r, c, percolation.percolates(),
				        percolation.numberOfOpenSites()));
			} catch (NoSuchElementException nse) {
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
