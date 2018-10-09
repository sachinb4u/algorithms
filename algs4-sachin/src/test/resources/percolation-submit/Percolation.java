import edu.princeton.cs.algs4.QuickUnionUF;

public class Percolation {

	QuickUnionUF sites;
	int TOP_SITE;
	int BOTTOM_SITE;
	int N;
	int[] siteStatus;

	public Percolation(int N) {
		this.N = N;

		// we will use actual number as site number
		TOP_SITE = 0; // first site will be virtual top
		BOTTOM_SITE = N + 1; // N+1 site will be virtual bottom

		sites = new QuickUnionUF(N * N + 2);
		
		for(int i = 0;i < N*N ; i++) {
			siteStatus[i] = 0;
		}
	}

	public void open(int row, int col) {

		int id = rowColToSiteId(row, col);
		
		
		if(!isOpen(row, col)) {
			sites.union(id, id);
			// make site open
			siteStatus[id] = 1;
		}

		// if top is opened, union it
		if (isOpen(id - N, id - N))
			sites.union(id - N, id);

		// if bottom is opened, union it
		if (isOpen(id + N, id + N))
			sites.union(id + N, id);

		// if left is opened, union it
		if (isOpen(id - 1, id - 1))
			sites.union(id, id - 1);

		// if right is opened, union it
		if (isOpen(id + 1, id + 1))
			sites.union(id, id + 1);
	}

	public boolean isOpen(int row, int col) {
		int id = rowColToSiteId(row, col);
		return siteStatus[id] == 1;
	}

	public boolean isFull(int row, int col) {
		return !isOpen(row, col);
	}

	public int numberOfOpenSites() {

		return 0;
	}

	public boolean percolates() {

		return sites.count() == 1;
	}

	private boolean isTopSite(int num) {
		return num % N == 0;
	}

	private boolean isBottomSite(int num) {
		return num % N == N - 1;
	}

	private boolean isValidSite(int num) {
		return num < 1 || num > (N * N);
	}

	private int rowColToSiteId(int row, int col) {
		return (row - 1) * N + col;
	}
}
