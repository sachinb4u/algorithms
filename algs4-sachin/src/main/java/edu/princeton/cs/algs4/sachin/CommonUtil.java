package edu.princeton.cs.algs4.sachin;

import java.util.Arrays;

public class CommonUtil {
	
	public static String showUFArray(int[] id) {
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
}
