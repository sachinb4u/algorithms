package edu.princeton.cs.algs4.sachin.test;

import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.sachin.QuickFind;
import edu.princeton.cs.algs4.sachin.QuickUnion;
import edu.princeton.cs.algs4.sachin.UFI;
import edu.princeton.cs.algs4.sachin.WeightedQuickUnion;

public class UnionFindTest {

	private static final String TINY_UF = "src/main/resources/mediumUF.txt";

	@BeforeEach
	private void beforeClass() throws Exception {
		Scanner scanner = new Scanner(Paths.get(TINY_UF));
		StdIn.setScanner(scanner);
	}
	
	/**
	 * 
	 * @param ufImplClass
	 * @throws Exception
	 */
	private void unionFindClient(Class<? extends UFI> ufImplClass) throws Exception{
		
		StdOut.println("Running "+ ufImplClass.getSimpleName() +" implementation");
		
		int N = StdIn.readInt();
		
		UFI ufImpl = (UFI) ufImplClass.getConstructors()[0].newInstance(N);
		
		for (int i = 0; i < N; i++) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();

			if (ufImpl.connected(p, q))
				continue;

			ufImpl.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(ufImpl.count() + " components \n");
	}

	
	@Test
	public void run_quickfind_tiny() throws Exception {

		unionFindClient(QuickFind.class);
	}

	
	@Test
	public void run_quickunion_tiny() throws Exception {
		
		unionFindClient(QuickUnion.class);
	}
	
	@Test
	public void run_weighted_quick_union_tiny() throws Exception {
		
		unionFindClient(WeightedQuickUnion.class);
	}
}
