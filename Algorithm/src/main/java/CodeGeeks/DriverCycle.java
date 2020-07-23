package CodeGeeks;

import java.util.ArrayList;
import java.util.Scanner;

/*
3
2 2
0 1 0 0
4 3
0 1 1 2 2 3
4 3
0 1 2 3 3 2
*/
public class DriverCycle {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			ArrayList<ArrayList<Integer>> list = new ArrayList();
			int nov = sc.nextInt();
			int edg = sc.nextInt();
			for (int i = 0; i < nov; i++) {
				list.add(i, new ArrayList<Integer>());
			}
			System.out.println("nov: " + nov + " edg: " + edg + " s:" + list.size());
			for (int i = 0; i < edg; i++) {

				int u = sc.nextInt();
				int v = sc.nextInt();

				list.get(u).add(v);
				// this line code is necessary when undirect detection
				list.get(v).add(u);

				System.out.println(
						"u: " + u + " V: " + v + " u size: " + list.get(u).size() + " v size: " + list.get(v).size());
			}
			UndirectDetectedCycle dc = new UndirectDetectedCycle();

			if (dc.isCyclic(list, nov)) {
				System.out.println("1");
			} else
				System.out.println("0");
		}
	}

}
