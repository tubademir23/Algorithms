
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	private Node root;

	public boolean isEmpty() {
		return size() == 0;

	}

	public int size() {
		return root == null ? 0 : root.size;
	}

	private int size(Node node) {
		return node == null ? 0 : node.size;
	}

	public void insert(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();

		root = put(root, p, 0, new RectHV(0, 0, 1, 1));
	}

	private Node put(Node node, Point2D p, final int level, final RectHV rect) {
		if (p == null)
			throw new NullPointerException("p can not be null");
		if (node == null) {
			return new Node(level, p, rect);
		}

		RectHV rectLeft = null;
		RectHV rectRight = null;
		double cmp = node.compare(p);

		if (cmp < 0 && node.left == null) {
			if (level % 2 == 0) {
				rectLeft = new RectHV(node.rect.xmin(), node.rect.ymin(), node.point.x(), node.rect.ymax());
			} else {
				rectLeft = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.point.y());
			}
		} else if (cmp >= 0 && node.right == null) {
			if (level % 2 == 0) {
				rectRight = new RectHV(node.point.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
			} else {
				rectRight = new RectHV(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.rect.ymax());
			}
		}

		if (cmp < 0)
			node.left = put(node.left, p, level + 1, rectLeft);
		else if (cmp > 0)
			node.right = put(node.right, p, level + 1, rectRight);
		else if (!p.equals(node.point))
			node.right = put(node.right, p, level + 1, rectRight);

		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}

	public boolean contains(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		return get(root, p, 0) != null;
	}

	private Point2D get(Node node, Point2D p, final int level) {
		if (node == null)
			return null;

		double cmp = node.compare(p);
		if (cmp < 0)
			return get(node.left, p, level + 1);
		else if (cmp > 0)
			return get(node.right, p, level + 1);
		else if (!p.equals(node.point))
			return get(node.right, p, level + 1);
		else
			return node.point;
	}

	public void draw() {
		draw(root);
	}

	private void draw(Node node) {
		if (node == null)
			return;
		StdDraw.point(node.point.x(), node.point.y());
		draw(node.left);
		draw(node.right);
	}

	public Iterable<Point2D> range(RectHV rectHV) {
		if (rectHV == null)
			throw new IllegalArgumentException();
		return range(rectHV, root);
	}

	private List<Point2D> range(RectHV rectHV, Node node) {
		if (node == null)
			return Collections.emptyList();

		if (node.doesSpittingLineIntersect(rectHV)) {
			List<Point2D> points = new ArrayList<>();
			if (rectHV.contains(node.point)) {
				points.add(node.point);
			}
			points.addAll(range(rectHV, node.left));
			points.addAll(range(rectHV, node.right));
			return points;
		} else {
			if (node.isRightOf(rectHV))
				return range(rectHV, node.left);
			else
				return range(rectHV, node.right);
		}
	}

	public Point2D nearest(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();

		return nearest(p, root, root.point, p.distanceSquaredTo(root.point));
	}

	// recursive function for finding nearest points of point p
	private Point2D nearest(Point2D p, final Node node, final Point2D curClosestPoint,
			final double currentlyClosestDistance) {
		if (node == null)
			return null;
		Point2D closestPoint = curClosestPoint;
		double closestDistance = currentlyClosestDistance;

		Point2D currentPoint = node.point;
		double currentDistance = p.distanceSquaredTo(currentPoint);
		if (currentDistance < closestDistance) {
			closestDistance = currentDistance;
			closestPoint = currentPoint;
		}

		double cmp = node.compare(p);
		if (cmp < 0) {
			currentPoint = nearest(p, node.left, closestPoint, closestDistance);
		} else {
			currentPoint = nearest(p, node.right, closestPoint, closestDistance);
		}

		if (currentPoint != null) {
			if (currentPoint != closestPoint) {
				closestDistance = currentPoint.distanceSquaredTo(p);
				closestPoint = currentPoint;
			}
		}

		double nodeRectDistance = -1;
		if (cmp < 0 && node.right != null) {
			nodeRectDistance = node.right.rect.distanceSquaredTo(p);
		} else if (cmp >= 0 && node.left != null) {
			nodeRectDistance = node.left.rect.distanceSquaredTo(p);
		}

		if (nodeRectDistance != -1 && nodeRectDistance < closestDistance) {
			if (cmp < 0) {
				currentPoint = nearest(p, node.right, closestPoint, closestDistance);
			} else {
				currentPoint = nearest(p, node.left, closestPoint, closestDistance);
			}
		}

		if (currentPoint != null) {
			closestPoint = currentPoint;
		}

		return closestPoint;
	}

	private static class Node {

		private final Point2D point;
		private final RectHV rect;
		private final int level;
		// left and right subtrees
		private Node left, right;
		// number of nodes in subtree
		private int size;

		Node(final int level, final Point2D point, final RectHV rect) {
			this.level = level;
			this.point = point;
			this.rect = rect;
			this.size = 1;
		}

		int size() {
			return size;
		}

		double compare(final Point2D pointToCompare) {
			if (level % 2 == 0) {
				return pointToCompare.x() - point.x();
			} else {
				return pointToCompare.y() - point.y();
			}
		}

		boolean doesSpittingLineIntersect(final RectHV rectToCheck) {
			if (level % 2 == 0) {
				return rectToCheck.xmin() <= point.x() && point.x() <= rectToCheck.xmax();
			} else {
				return rectToCheck.ymin() <= point.y() && point.y() <= rectToCheck.ymax();
			}
		}

		boolean isRightOf(final RectHV rectToCheck) {
			if (level % 2 == 0) {
				return rectToCheck.xmin() < point.x() && rectToCheck.xmax() < point.x();
			} else {
				return rectToCheck.ymin() < point.y() && rectToCheck.ymax() < point.y();
			}
		}

	}
}
