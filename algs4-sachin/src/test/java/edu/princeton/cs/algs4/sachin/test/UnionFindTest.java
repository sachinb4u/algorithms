package edu.princeton.cs.algs4.sachin.test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.sachin.CommonUtil;
import edu.princeton.cs.algs4.sachin.QuickFind;
import edu.princeton.cs.algs4.sachin.QuickUnion;
import edu.princeton.cs.algs4.sachin.WeightedQuickUnion;

public class UnionFindTest {

	private static final String TINY_UF = "src/main/resources/tinyUF.txt";
//	private static final String MEDIUM_UF = "src/main/resources/mediumUF.txt";
//	private static final String LARGE_UF = "src/main/resources/largeUF.txt";

	@Test
	public void run_quickfind_tiny() throws IOException {
		Scanner scanner = new Scanner(Paths.get(TINY_UF));
		StdIn.setScanner(scanner);
		
		int N = StdIn.readInt();
		
		QuickFind quickFind = new QuickFind(N);
		
		for (int i = 0; i < N; i++) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();

			if (quickFind.connected(p, q))
				continue;

			quickFind.union(p, q);
			StdOut.println(p + " " + q);
			StdOut.println(quickFind.showUnions());
		}
		StdOut.println(quickFind.count() + " components");
	}
	
	@Test
	public void run_quickunion_tiny() throws IOException {
		Scanner scanner = new Scanner(Paths.get(TINY_UF));
		StdIn.setScanner(scanner);
		
		int N = StdIn.readInt();

		QuickUnion quickUnion = new QuickUnion(N);

		for (int i = 0; i < N; i++) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();

			if (quickUnion.connected(p, q))
				continue;

			quickUnion.union(p, q);
			StdOut.println(p + " " + q);
			StdOut.println(CommonUtil.showUFArray(quickUnion.getId()));
		}
		StdOut.println(quickUnion.count() + " components");
	}
	
	@Test
	public void run_weighted_quick_union_tiny() throws IOException {
		Scanner scanner = new Scanner(Paths.get(TINY_UF));
		StdIn.setScanner(scanner);
		
		int N = StdIn.readInt();

		WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(N);

		for (int i = 0; i < N; i++) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();

			if (weightedQuickUnion.connected(p, q))
				continue;

			weightedQuickUnion.union(p, q);
			StdOut.println(p + " " + q);
			StdOut.println(CommonUtil.showUFArray(weightedQuickUnion.getId()));
		}
		StdOut.println(weightedQuickUnion.count() + " components");
	}
}
