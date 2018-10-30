import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF sites;
    private final int n;
    private final boolean[] siteStatus;
    private int openSiteCount;

    public Percolation(int num) {

        if (num <= 0)
            throw new IllegalArgumentException("size should be greater than 0");

        this.n = num;

        sites = new WeightedQuickUnionUF(num * num + 2);
        siteStatus = new boolean[num * num + 2];

        for (int i = 0; i < num * num; i++) {
            siteStatus[i] = false;
        }

        siteStatus[0] = true;
        siteStatus[num * num + 1] = true;
    }

    public void open(int row, int col) {

        checkIfValid(row, col);

        int id = rowCol2Id(row, col);

        if (!isOpen(row, col)) {
            // make site open
            siteStatus[id] = true;
            openSiteCount++;

            // if top site, make union to virtual top 0
            if (row == 1) {
                sites.union(0, id);
            } else if (row == n) {
                // if bottom site, make union to virtual bottom N*N + 1
                sites.union(id, (n * n) + 1);
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
        return id >= 0 && id <= siteStatus.length - 1 && siteStatus[id];
    }

    public boolean isFull(int row, int col) {
        checkIfValid(row, col);

        int id1 = rowCol2Id(row, col);

        // site is open and connected to virtual top
        return isOpen(row, col) && sites.connected(0, id1);
    }

    public int numberOfOpenSites() {

        return openSiteCount;
    }

    public boolean percolates() {
        // if top site and bottom sites are connected
        return sites.connected(0, n * n + 1);
    }

    private boolean isValidSite(int num) {
        return num >= 1 && num <= n;
    }

    private boolean isValid(int row, int col) {
        return isValidSite(row) && isValidSite(col);
    }

    private int rowCol2Id(int row, int col) {
        return (row - 1) * n + col;
    }

    private void checkIfValid(int row, int col) {
        if (!(isValid(row, col))) {
            throw new IllegalArgumentException(String.format("%d and %d are invalid row and column respectively", row, col));
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();

        Percolation percolation = new Percolation(n);

        while (StdIn.hasNextLine()) {
            int r = StdIn.readInt();
            int c = StdIn.readInt();

            percolation.open(r, c);

//				System.out.println(String.format("(%3d, %3d) Perculated? =%6b, NumberOfOpenSites = %5s ", r, c, percolation.percolates(),
//				        percolation.numberOfOpenSites()));

        }

    }
}
