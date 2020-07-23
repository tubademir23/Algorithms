
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private final LineSegment[] lineSegments;

	/**
	 * Finds all line segments containing 4 points.
	 */
	public BruteCollinearPoints(Point[] points) {
		checkDuplicate(points);
		ArrayList<LineSegment> segments = new ArrayList<LineSegment>();

		Point[] pointsCopy = Arrays.copyOf(points, points.length);
		Arrays.sort(pointsCopy);

		for (int p = 0; p < pointsCopy.length - 3; p++) {
			for (int q = p + 1; q < pointsCopy.length - 2; q++) {
				for (int r = q + 1; r < pointsCopy.length - 1; r++) {
					for (int s = r + 1; s < pointsCopy.length; s++) {
						if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r])
								&& pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) {
							segments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
						}
					}
				}
			}
		}

		lineSegments = segments.toArray(new LineSegment[segments.size()]);
	}

	private void checkNull(Point[] points) {
		if (points == null) {
			throw new NullPointerException("The array \"Points\" is null.");
		}
		for (Point p : points) {
			if (p == null) {
				throw new NullPointerException("The array \"Points\" contains null element.");
			}
		}
	}

	private void checkDuplicate(Point[] points) {
		for (int i = 0; i < points.length - 1; i++) {
			if (points[i].compareTo(points[i + 1]) == 0) {
				throw new IllegalArgumentException("Duplicate(s) found.");
			}
		}
	}

	/**
	 * The number of line segments.
	 */
	public int numberOfSegments() {
		return lineSegments.length;
	}

	/**
	 * The line segments.
	 */
	public LineSegment[] segments() {
		return lineSegments.clone();
	}

	/**
	 * Simple client provided by Princeton University.
	 */
	public static void main(String[] args) {

		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
