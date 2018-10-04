package edu.princeton.cs.algs4.sachin;

public interface UFI {

	/**
	 * Add connection between p & q
	 * 
	 * @param p
	 * @param q
	 */
	void union(int p, int q);

	/**
	 * Component identifier for p (0 to N-1)
	 * 
	 * @param p
	 * @return
	 */
	int find(int p);

	/**
	 * return true if p and q are in same component
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	boolean connected(int p, int q);

	/**
	 * number of components
	 * 
	 * @return
	 */
	int count();

}