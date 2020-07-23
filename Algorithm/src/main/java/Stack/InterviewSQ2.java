package Stack;

import edu.princeton.cs.algs4.StdOut;

public class InterviewSQ2 {
	static class StackMax {
		Stack<Double> main_ = new Stack<Double>();
		Stack<Double> max_ = new Stack<Double>();

		void push(Double x) {
			main_.push(x);
			if (main_.getSize() == 1) {
				max_.push(x);
				return;
			}

			if (x > max_.peek())
				max_.push(x);
			else
				max_.push(max_.peek());
		}

		Double getMax() {
			return max_.peek();
		}

		void pop() {
			main_.pop();
			max_.pop();
		}
	};

	public static void main(String[] args) {
		StackMax s = new StackMax();
		Double[] inputs = { (double) 10.01, (double) 10.05, (double) 10.02, (double) 10.03, (double) 10.04 };
		for (Double double1 : inputs) {
			s.push(double1);
			StdOut.println(s.getMax());
		}

	}
}
