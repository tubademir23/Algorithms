
import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

	private final int x; // x-coordinate of this point
	private final int y; // y-coordinate of this point

	// constructs the point (x, y)
	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}

	/**
	 * Draws this point to standard draw.
	 */
	public void draw() {
		StdDraw.point(x, y);
	}

	/**
	 * Draws the line segment between this point and the specified point to standard
	 * draw.
	 *
	 * @param that the other point
	 */
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	/**
	 * the slope between this point and that point
	 * 
	 * @param that the other point
	 * @return the slope between this point and the specified point
	 */
	public double slopeTo(Point that) {
		if (this.x == that.x) {
			return this.y == that.y ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
		}
		// avoid "-0.0".
		if (this.y == that.y) {
			return 0.0;
		}
		return (this.y - that.y) * 1.0 / (this.x - that.x);
	}

	/**
	 * compare two points by y-coordinates, breaking ties by x-coordinates
	 *
	 * @param that the other point
	 * @return the value <tt>0</tt> if this point is equal to the argument point (x0
	 *         = x1 and y0 = y1); a negative integer if this point is less than the
	 *         argument point; and a positive integer if this point is greater than
	 *         the argument point
	 */
	public int compareTo(Point that) {
		/* YOUR CODE HERE */
		return this.y == that.y ? this.x - that.x : this.y - that.y;
	}

	/**
	 * compare two points by slopes they make with this point
	 * 
	 * @return the Comparator that defines this ordering on points
	 */
	public Comparator<Point> slopeOrder() {
		/* YOUR CODE HERE */
		return new SlopeComparator(this);
	}

	private class SlopeComparator implements Comparator<Point> {

		private final Point point;

		SlopeComparator(Point point) {
			this.point = point;
		}

		public int compare(Point p1, Point p2) {
			double slope1 = p1.slopeTo(point);
			double slope2 = p2.slopeTo(point);
			return slope1 == slope2 ? 0 : (slope1 > slope2 ? 1 : -1);
		}
	}

	/**
	 * Returns a string representation of this point. This method is provide for
	 * debugging; your program should not rely on the format of the string
	 * representation.
	 *
	 * @return a string representation of this point
	 */

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}