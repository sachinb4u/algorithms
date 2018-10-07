package edu.princeton.cs.algs4.sachin;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

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

	public String showUnions() {
		StringBuilder builder = new StringBuilder();
		builder.append("     ");
		for (int i = 0; i < id.length; i++) {
			builder.append(i);
			builder.append(", ");
		}
		builder.replace(builder.length() - 2, builder.length(), " ");
		builder.append("\n    ");
		builder.append(Arrays.toString(id));
		builder.append("\n");
		return builder.toString();
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();

		QuickUnion quickUnion = new QuickUnion(N);

		for (int i = 0; i < N; i++) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();

			if (quickUnion.connected(p, q))
				continue;

			quickUnion.union(p, q);
			StdOut.println(p + " " + q);
			StdOut.println(quickUnion.showUnions());
		}
		StdOut.println(quickUnion.count + " components");
	}
}
