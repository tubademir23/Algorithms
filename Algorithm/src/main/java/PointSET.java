
import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Stream;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
	private final TreeSet<Point2D> points;

	// construct an empty set of points
	public PointSET() {
		points = new TreeSet<Point2D>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// number of points in the set
	public int size() {
		return points == null ? 0 : points.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		if (p.x() < 0 || p.y() < 0)
			throw new IllegalArgumentException("x or y value of point can not be negative");
		if (!contains(p))
			points.add(p);
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		if (isEmpty())
			return false;
		return points.contains(p);
	}

	// draw all points to standard draw
	public void draw() {
		if (points == null)
			throw new NullPointerException("while drawing points can not be null");
		for (Point2D point2d : points) {
			StdDraw.point(point2d.x(), point2d.y());
		}
	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {

		Iterable<Point2D> iterable = new Iterable<Point2D>() {
			public Iterator<Point2D> iterator() {
				// TODO Auto-generated method stub
				if (rect == null)
					throw new IllegalArgumentException();
				if (isEmpty())
					throw new NullPointerException();
				Stream<Point2D> stream = points.stream().filter(rect::contains);
				return stream.iterator();
			}
		};
		return iterable;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		if (points == null || size() == 0)
			return null;
		Point2D nearestPoint = null;
		for (Point2D point2d : points) {
			if (nearestPoint == null || point2d.distanceSquaredTo(p) < nearestPoint.distanceSquaredTo(p))
				nearestPoint = point2d;
		}
		return nearestPoint;
	}

	public static void main(String[] args) {
		String filename = args[0];
		In in = new In(filename);
		PointSET brute = new PointSET();
		while (!in.isEmpty()) {
			double x = in.readDouble();
			double y = in.readDouble();
			Point2D p = new Point2D(x, y);
			brute.insert(p);
		}
		StdDraw.enableDoubleBuffering();

		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		brute.draw();
	}
}
