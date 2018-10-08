package edu.princeton.cs.algs4.sachin;

public class WeightedQuickUnion implements UFI {

	private int[] id; // parent link (site indexed)
	private int[] size; // size of component for roots (site indexed)

	private int count; // number of connected components

	public WeightedQuickUnion(int N) {

		id = new int[N];
		size = new int[N];

		count = N;
		for (int i = 0; i < N; i++) {
			id[i] = i;
			size[i] = 1; // initialize size to 1
		}

	}

	public void union(int p, int q) {

		int pRoot = find(p);
		int qRoot = find(q);

		if (pRoot == qRoot)
			return;

		if (size[pRoot] < size[qRoot]) {
			id[pRoot] = qRoot;
			size[qRoot] += size[pRoot];
		} else {
			id[qRoot] = pRoot;
			size[pRoot] += size[qRoot];
		}

		count--;
	}

	public int find(int p) {
		// follow links to find a root
		while (p != id[p])
			p = id[p];

		return p;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public int count() {
		return count;
	}

	public int[] getId() {
		return id;
	}

}
