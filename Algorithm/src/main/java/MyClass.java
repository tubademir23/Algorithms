import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MyClass {
	public static void main(String args[]) {
		MyClass mc = new MyClass();
		mc.knap();
		System.out.println(binomialCoefficientDP(6, 4));
		int[] array = {};

		sumCombination(array, 19, new HashSet<Integer>(), 0);
		List<String> dict = Arrays.asList("cat", "cats", "and", "sand", "dog");
		wordBreak("catsanddog", dict, new ArrayList<String>(), new boolean[dict.size()]);
		System.out.println("......");

		wordBreak("catsanddog", dict, new ArrayList<String>());
		int max = maximum(array, array.length - 1);
		System.out.println("Maximum = " + max);
		boolean isSeq = isSequence(array, array.length - 1);
		System.out.println("Seq = " + isSeq);
		int x = 800000;
		System.out.println("sum = " + sum(x));
		array = new int[] { 1, 2, 3, 4 };
		boolean used[] = new boolean[array.length];
		// System.out.println(" -Permütasyon- " );
		// permutation(array, new ArrayList<Integer>(), used);
		used = new boolean[3];
		anagram("god", new ArrayList<Character>(), used);
		System.out.println(" -Kombinasyon- ");
		// combination(array, 3, new HashSet<Integer>(), 0);
		// combinationAlternative(array, new HashSet<Integer>(), 0, 3);

		int boardSize = 8;
		int[][] board = new int[boardSize][boardSize];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				board[i][j] = 0;

		HashSet<Queen> set = new HashSet<Queen>();

		// mc.chessQueen(board, set, 0);
		int[] board2 = new int[boardSize];

		mc.helper(board2, 0);

		/*
		 * for (Queen q : set) { System.out.println(q.toString()); }
		 */
	}

	public class Thing {
		String name;
		int w, v;

		public Thing(String n, int w_, int v_) {
			name = n;
			w = w_;
			v = v_;
		}
	}

	public void knap() {
		Thing things[] = { new Thing("waterbox", 6, 5), new Thing("snicker", 10, 10), new Thing("camera", 7, 14),
				new Thing("book", 3, 4) };
		int W = 20;
		int ks = knapsack(things, W, things.length - 1);
		System.out.println("MK: " + ks);
		int ksm = knapsackMemo(things, W, things.length - 1, new int[W][things.length]);

		int ksDP = knapsackDP(things, W);
	}

	public static int knapsackDP(Thing[] things, int W) {
		int N = things.length;
		int[][] dp = new int[W + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= W; w++) {
				if (things[i - 1].w <= w) {
					dp[w][i] = Math.max(dp[w - things[i - 1].w][i - 1] + things[i - 1].v, dp[w][i - 1]);
				} else {
					dp[w][i] = dp[w][i - 1];
				}
			}
		}
		return dp[W][N];
	}

	public int knapsack(Thing[] things, int W, int i) {
		if (i == -1 || W == 0)
			return 0;
		if (things[i].w <= W) {
			int include = things[i].v + knapsack(things, W - things[i].w, i - 1);
			int exclude = knapsack(things, W, i - 1);
			return Math.max(include, exclude);
		} else
			return knapsack(things, W, i - 1);
	}

	public static int knapsackMemo(Thing[] things, int W, int i, int[][] dp) {
		if (i == -1 || W == 0) {
			return 0;
		}
		if (dp[W][i] != -1) {
			return dp[W][i];
		}
		if (things[i].w <= W) {
			int include = things[i].v + knapsackMemo(things, W - things[i].w, i - 1, dp);
			int exclude = knapsackMemo(things, W, i - 1, dp);
			dp[W][i] = Math.max(include, exclude);
			return dp[W][i];
		} else {
			return dp[W][i] = knapsackMemo(things, W, i - 1, dp);
		}
	}

	private static int totalSet(HashSet<Integer> set) {
		int total = 0;
		for (Integer i : set) {
			total += i;
		}
		return total;
	}

	public static void sumCombination(int[] array, int total, HashSet<Integer> set, int start) {
		if (totalSet(set) == total) {
			System.out.println("T:" + total + "\t" + Arrays.toString(set.toArray()));
			return;
		}
		if (start == array.length)
			return;

		for (int i = start; i < array.length; i++) {
			set.add(array[i]);
			sumCombination(array, total, set, i + 1);
			set.remove(array[i]);
		}
	}

	public boolean helper(int[] board, int i) {
		if (i == board.length) {
			for (int row : board) {
				for (int c = 0; c < board.length; c++) {
					if (c == row) {
						System.out.print(" O ");
					} else {
						System.out.print(" X ");
					}
				}
				System.out.println("");
			}
			return true;
		}

		for (int c = 0; c < board.length; c++) {
			boolean flag = false;
			for (int r = 0; r < i; r++) {
				if (board[r] == c || Math.abs(board[r] - c) == (i - r)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				continue;
			}
			board[i] = c;
			if (helper(board, i + 1)) {
				return true;
			}
		}
		return false;
	}

	public static int maximum(int[] nums, int i) {
		if (i <= 0)
			return -1;
		if (i == 0) {
			System.out.println("num[0] = " + nums[0]);
			return nums[0];
		}
		int max = Math.max(nums[i], maximum(nums, i - 1));
		// System.out.println("max = " + max);
		return max;
	}

	public static boolean isPalindrome(String input, int i, int j) {
		if (i >= j)
			return true;
		return input.charAt(i) == input.charAt(j) && isPalindrome(input, i + 1, j - 1);
	}

	public static boolean isSequence(int[] array, int i) {
		if (i <= 0)
			return true;
		if (i == 0) {
			return array[0] == array[1] - 1;
		}
		return isSequence(array, i - 1) && array[i] == array[i + 1] - 1;
	}

	public static int sum(int x) {
		if (x < 10)
			return x;
		return sum(x / 10) + x % 10;
	}

	public static int binomialCoefficient(int n, int k) {
		// (n,0)=(n,n)=1
		if (n == k || k == 0) {
			return 1;
		}
		return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
	}

	public static int binomialCoefficientDP(int n, int k) {
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
			if (i <= k)
				dp[i][i] = 1;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			}
		}
		return dp[n][k];
	}

	// buttom to up
	public static int fib(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	// top to buttom
	public static int fibMemo(int n, int[] cache) {
		if (n == 0 || n == 1)
			return n;
		if (cache[n] != 0)
			return cache[n];
		int res = fibMemo(n - 1, cache) + fibMemo(n - 2, cache);
		cache[n] = res;
		return res;

	}

	private static boolean equal(ArrayList<String> partial, String word) {
		StringBuilder sb = new StringBuilder();
		for (String s : partial)
			sb.append(s);
		// System.out.println("SB: " + sb + " W: " + word);
		return sb.toString().equals(word);
	}

	public static void wordBreak(String input, List<String> dict, ArrayList<String> partial) {
		if (input.length() == 0) {
			System.out.println(Arrays.toString(partial.toArray()));
			return;
		}
		for (int i = 0; i < input.length(); i++) {
			String word = input.substring(0, i + 1);
			if (dict.contains(word)) {
				partial.add(word);
				wordBreak(input.substring(i + 1), dict, partial);
				partial.remove(partial.size() - 1);
			}
		}
	}

	public static void wordBreak(String word, List<String> dictionary, ArrayList<String> partial, boolean[] used) {
		if (equal(partial, word)) {
			System.out.println("\n" + Arrays.toString(partial.toArray()) + "\n");
			return;
		}
		for (int i = 0; i < dictionary.size(); i++) {
			if (!used[i]) {

				used[i] = true;
				if (partial.size() == 0 && word.indexOf(dictionary.get(i)) != 0)
					break;
				if (partial == null) {
					partial = new ArrayList<String>();
				}
				partial.add(dictionary.get(i));
				wordBreak(word, dictionary, partial, used);
				used[i] = false;
				partial.remove(partial.size() - 1);
			}
		}
	}

	public static void anagram(String word, ArrayList<Character> partial, boolean[] used) {
		if (partial.size() == word.length()) {
			System.out.println(Arrays.toString(partial.toArray()));
			return;
		}
		for (int i = 0; i < word.length(); i++) {
			if (!used[i]) {
				used[i] = true;
				if (partial == null) {
					partial = new ArrayList<Character>();
				}
				partial.add(word.charAt(i));
				anagram(word, partial, used);
				used[i] = false;
				partial.remove(partial.size() - 1);
			}
		}
	}

	public static void permutation(int[] array, ArrayList<Integer> partial, boolean[] used) {
		if (partial.size() == array.length) {
			System.out.println(Arrays.toString(partial.toArray()));
			return;
		}
		for (int i = 0; i < array.length; i++) {
			if (!used[i]) {
				used[i] = true;
				if (partial == null) {
					partial = new ArrayList<Integer>();
				}
				partial.add(array[i]);
				permutation(array, partial, used);
				used[i] = false;
				partial.remove(partial.size() - 1);
			}
		}
	}

	public static void combination(int[] array, int k, HashSet<Integer> set, int start) {
		if (set.size() == k) {
			System.out.println(set);
			return;
		}
		if (start == array.length)
			return;
		for (int i = start; i < array.length; i++) {
			set.add(array[i]);
			combination(array, k, set, i + 1);
			set.remove(array[i]);

		}
	}

	public static void combinationAlternative(int[] input, HashSet<Integer> partial, int i, int k) {
		if (partial.size() == k) {
			System.out.println(Arrays.toString(partial.toArray()));
			return;
		}
		if (i == input.length) {
			return;
		}
		partial.add(input[i]);
		combinationAlternative(input, partial, i + 1, k);
		partial.remove(input[i]);
		combinationAlternative(input, partial, i + 1, k);
	}

	public class Queen {
		int row, col;

		public Queen(int r, int c) {
			this.row = r;
			this.col = c;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();

			sb.append("Row: " + row);
			for (int i = 0; i < col - 1; i++) {
				sb.append(" ");
			}
			sb.append("X" + "\n");
			return sb.toString();

		}
	}

	private static boolean controlBoard(HashSet<Queen> set, Queen q) {
		int i = 0;
		// Queen[] queens = set.toArray();
		for (Queen queen : set) {
			System.out.println("i: " + queen.row + ".." + q.row + " j : " + +queen.col + ".." + q.col);
			if (!(q.row == queen.row && q.col == queen.col)) {
				if (q.row == queen.row || q.col == queen.col) {
					System.out.println("i: " + queen.row + ".." + q.row + " j : " + +queen.col + ".." + q.col);
					return false;
				}
				if (Math.abs(q.col - queen.col) == Math.abs(q.row - queen.row))
					return false;
			}
		}
		return true;
	}

	public boolean chessQueen(int[][] board, HashSet<Queen> set, int i) {
		if (i == board.length) {
			if (set.size() != board.length) {
				return false;
			}
		}
		for (int j = 0; j < board.length; j++) {
			if (board[i][j] == 0) {
				Queen q = new Queen(i, j);
				System.out.println("i: " + i + " j : " + j);
				set.add(q);
				board[i][j] = 1;
				if (controlBoard(set, q)) {

					boolean result = chessQueen(board, set, i++);
					if (result)
						j = board.length;
					else {
						set.remove(q);
						board[i][j] = 0;
					}
				} else {
					set.remove(q);
					board[i][j] = 0;
				}
			}
		}
		int j;
		for (j = 0; j < board.length; j++)
			if (board[i][j] == 1)
				return true;
		return false;
	}

}