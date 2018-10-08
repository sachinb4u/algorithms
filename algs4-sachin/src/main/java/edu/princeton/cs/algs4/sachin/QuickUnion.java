package edu.princeton.cs.algs4.sachin;

import java.util.Arrays;

public class QuickUnion implements UFI {

	private int[] id;
	private int count;

	public QuickUnion(int N) {
		id = new int[N];

		// initialize to N connected components
		count = N;

		for (int i = 0; i < N; i++)
			id[i] = i;

	}

	public void union(int p, int q) {

		int pRoot = find(p);
		int qRoot = find(q);

		// if connected then return
		if (pRoot == qRoot)
			return;

		id[pRoot] = qRoot;
		count--;
	}

	public int find(int p) {

		while (id[p] != p)
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
