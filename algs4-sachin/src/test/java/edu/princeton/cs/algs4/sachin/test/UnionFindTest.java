package edu.princeton.cs.algs4.sachin.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class UnionFindTest {

	@Test
	public void run_quickfind_tiny() throws FileNotFoundException {
		File tinyFile = new File("src/main/resources/tinyUF.txt");
		Scanner scanner = new Scanner(tinyFile);
		
		int N = scanner.nextInt();
		System.out.println("Total Records : " + N);
		
		scanner.close();
	}
}
