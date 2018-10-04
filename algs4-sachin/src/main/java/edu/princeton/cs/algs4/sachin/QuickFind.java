package edu.princeton.cs.algs4.sachin;

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
public class UnionFind implements UFI {

	public void union(int p, int q) {
		// TODO Auto-generated method stub

	}

	public int find(int p) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean connected(int p, int q) {
		// TODO Auto-generated method stub
		return false;
	}

	public int count() {
		// TODO Auto-generated method stub
		return 0;
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
