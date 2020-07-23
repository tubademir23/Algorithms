package Stack;

import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {

	private final static List operators = Arrays.asList("+", "-", "*", "/");

	public static void main(String[] args) {
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();

		while (true) {
			String s = StdIn.readString();
			if (s.equals("C"))
				break;

			if (s.equals("("))
				;
			else if (operators.contains(s))
				ops.push(s);
			else if (s.equals(")")) {
				String op = ops.pop();
				switch (op.charAt(0)) {
				case '+':
					vals.push(vals.pop() + vals.pop());
					break;
				case '-':
					vals.push(vals.pop() - vals.pop());
					break;
				case '*':
					vals.push(vals.pop() * vals.pop());
					break;
				case '/':
					vals.push(vals.pop() / vals.pop());
					break;
				default:
					break;
				}
			} else
				vals.push(Double.valueOf(s));

		}
		StdOut.println(vals.pop());

	}
}
