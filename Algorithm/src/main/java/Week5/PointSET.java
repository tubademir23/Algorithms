package Week5;

import static java.util.stream.Collectors.toList;

import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
	private TreeSet<Point2D> points;

	// construct an empty set of points
	public PointSET() {
		points = new TreeSet<Point2D>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return points == null || points.size() == 0;
	}

	// number of points in the set
	public int size() {
		return points == null ? 0 : points.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null)
			throw new NullPointerException("insert null point");
		if (p.x() < 0 || p.y() < 0)
			throw new IllegalArgumentException("x or y value of point can not be negative");
		if (!contains(p))
			points.add(p);
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null)
			throw new NullPointerException("contains null point");
		if (points == null)
			throw new NullPointerException("points can not be null");
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
		if (rect == null)
			throw new NullPointerException("rect can not be null");
		if (points == null)
			throw new NullPointerException("at range func. points can not be null");
		return points.stream().filter(rect::contains).collect(toList());
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null)
			throw new NullPointerException("p can not be null");
		if (points == null || size() == 0)
			return null;
		Point2D nearestPoint = null;
		for (Point2D point2d : points) {
			if (nearestPoint == null || point2d.distanceTo(p) < nearestPoint.distanceTo(p))
				nearestPoint = point2d;
		}
		return nearestPoint;
	}

	public static void main(String[] args) {

	}
}
