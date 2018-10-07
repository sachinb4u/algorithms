package edu.princeton.cs.algs4.sachin;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Dynamic Connectivity Problem :-
 * 
 * The input is a sequence of pairs of integers, where each integer represents an object of some
 * type and we are to interpret the pair p q as meaning “p is connected to q.
 *
 * Our goal is to write a program to filter out extraneous pairs (pairs where both objects are in
 * the same equivalence class) from the sequence
 * 
 * In other words, when the program reads a pair p q from the input, it should write the pair to the
 * output only if the pairs it has seen to that point do not imply that p is connected to q
 * 
 * If the previous pairs do imply that p is connected to q, then the program should ignore the pair
 * p q and proceed to read in the next pair
 * 
 * Page 216
 * 
 * @author Sachin
 *
 */
public class QuickFind implements UFI {

	private final int[] id;
	// number of connected component
	private int count;

	public QuickFind(int N) {
		// initialize connected components to N
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public void union(int p, int q) {
		// Put p and q into same component
		int pID = id[p];
		int qID = id[q];

		// Nothing to do if p and q are in same component
		if (pID == qID)
			return;

		// rename p's component to q's name
		for (int i = 0; i < id.length; i++)
			if (id[i] == pID)
				id[i] = qID;

		// we go through the array, changing all the entries with values equal to id[p] to the value id[q].
		// We could have decided to change all the entries equal to id[q] to the value id[p] — the choice
		// between these two alternatives is arbitrary.

		count--;
	}

	public int find(int p) {
		// return connected component number. as we maintain one number for all the nodes in the component,
		// it would be single number
		return id[p];
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public int count() {
		return count;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		QuickFind quickFind = new QuickFind(N);

		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();

			if (quickFind.connected(p, q))
				continue;

			quickFind.union(p, q);
			StdOut.println(p + " " + q);
			StdOut.println(quickFind.showUnions());	
		}
		StdOut.println(quickFind.count + " components");
		
	}
	
	public String showUnions() {
		StringBuilder builder = new StringBuilder();
		builder.append("     ");
		for(int i=0; i <id.length;i++) {
			builder.append(i );
			builder.append(", ");
		}
		builder.replace(builder.length()-2, builder.length(), " ");
	    builder.append("\n    ");
		builder.append(Arrays.toString(id));
		builder.append("\n");
		return builder.toString();
	}
}

/**
 * Networks.
 * 
 * The integers might represent computers in a large network, and the pairs might represent
 * connections in the network. Then, our program determines whether we need to establish a new
 * direct connection for p and q to be able to communicate or whether we can use existing
 * connections to set up a communications path.
 * 
 * Or, the integers might represent contact sites in an electrical circuit, and the pairs might
 * represent wires connecting the sites.
 * 
 * Or, the integers might represent people in a social network, and the pairs might represent
 * friendships. In such applications, we might need to process millions of objects and billions of
 * connections.
 */
