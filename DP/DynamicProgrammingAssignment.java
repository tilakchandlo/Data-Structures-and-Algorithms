/**
 * Assignment to teach dynamic programming using 3 simple example problems:
 * 1. Fibonacci numbers
 * 2. Longest common subsequence
 * 3. Edit distance
 * 
 * @author Julia Ting (julia.ting@gatech.edu)
 * @author Tilak Patel
 */
public class DynamicProgrammingAssignment {
	public static int num_calls = 0; //DO NOT TOUCH

	/**
	 * Calculates the nth fibonacci number: fib(n) = fib(n-1) + fib(n-2).
	 * Remember that fib(0) = 0 and fib(1) = 1.
	 * 
	 * This should NOT be done recursively - instead, use a 1 dimensional
	 * array so that intermediate fibonacci values are not re-calculated.
	 * 
	 * The running time of this function should be O(n).
	 * 
	 * @param n A number 
	 * @return The nth fibonacci number
	 */
	public static int fib(int n) {
		num_calls++; //DO NOT TOUCH
		if (n <= 1) {
			return n;
		} else {
			int arr[] = new int[n + 1];
			arr[1] = 1;
			for (int i = 2; i <= n; i++) {
				arr[i] = arr[i-1] + arr[i-2];
			}
			return arr[n];
		}
	}
	
	/**
	 * Calculates the length of the longest common subsequence between a and b.
	 * 
	 * @param a
	 * @param b
	 * @return The length of the longest common subsequence between a and b
	 */
	public static int lcs(String a, String b) {
		num_calls++; //DO NOT TOUCH
		int n = a.length();
		int m = b.length();
		int[][] lcs = new int[n+1][m+1];
		for (int i = 0; i <= n; i++) {
			lcs[i][0] = 0;
		}
		for (int j = 0; j <= m; j++) {
			lcs[0][j] = 0;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
			}
		}
		return lcs[n][m];
		
	}

	/**
	 * Calculates the edit distance between two strings.
	 * 
	 * @param a A string
	 * @param b A string
	 * @return The edit distance between a and b
	 */
	public static int edit(String a, String b) {
		num_calls++; //DO NOT TOUCH
		int n = a.length();
		int m = b.length();
	 
		int[][] edit = new int[n + 1][m + 1];
	 
		for (int i = 0; i <= n; i++) {
			edit[i][0] = i;
		}
	 
		for (int j = 0; j <= m; j++) {
			edit[0][j] = j;
		}
	 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (a.charAt(i) == b.charAt(j)) {
					edit[i + 1][j + 1] = edit[i][j];
				} else {
					int replace = edit[i][j] + 1;
					int insert = edit[i][j + 1] + 1;
					int delete = edit[i + 1][j] + 1;
					int min;
					if (replace > insert) {
						min = insert;
					} else {
						min = replace;
					}
					if (delete > min) {
					} else {
						min = delete;
					}
					edit[i + 1][j + 1] = min;
				}
			}
		}
	 
		return edit[n][m];
	}
}

