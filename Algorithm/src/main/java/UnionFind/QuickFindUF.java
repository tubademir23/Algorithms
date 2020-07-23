package UnionFind;

public class QuickFindUF {

	private int N;
	private int[] id;

	public QuickFindUF(int N_) {
		this.N = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < N; i++) {
			if (id[i] == pid)
				id[i] = qid;
		}

	}

	boolean connected(int p, int q) {
		return id[p] == id[q];
	}

	static int getSuccessor(int x, int[] id) {
		int smlst = x;
		int ndx = 0;
		for (ndx = 0; ndx < id.length - 1; ndx++) {
			if (id[ndx] == x)
				break;
		}

		for (; ndx < id.length - 1; ndx++) {
			id[ndx] = id[ndx + 1];
			if (id[ndx] > x && (id[ndx] < smlst || smlst == x))
				smlst = id[ndx];
		}
		return smlst;
	}

	public static void main(String[] args) {
		int[] id = { 1, 3, 4, 5, 2, 7, 3, 8 };
		int c = getSuccessor(2, id);
		System.out.println(c);
	}
}
