package Stack;

public class InterviewSQ1 {
	static Stack<Integer> s1 = new Stack<Integer>();
	static Stack<Integer> s2 = new Stack<Integer>();

	private void enQueue(int x) {
		// carry out s1 elements to s2
		while (s1.getFirst() != null) {
			s2.push(s1.pop());
		}
		s1.push(x);
		// enempty the s1 stack
		while (s2.getFirst() != null) {
			s1.push(s2.pop());
		}
	}

	private int deQueue() {
		// if s1 is null error
		if (s1.getFirst() == null) {
			throw new NullPointerException("Empty Queue");
		}
		int x = s1.peek();
		s1.pop();
		return x;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
