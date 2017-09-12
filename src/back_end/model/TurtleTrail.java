package back_end.model;

import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.Queue;

public class TurtleTrail {

	/**
	 * Holds the turtle's paths in a queue.  The paths
	 * are given toroidal bounds and generated as such.
	 */

	private Queue<LineSegment> paths;
	private double xBound;
	private double yBound;

	public TurtleTrail(Model model) {
		paths = new LinkedList<LineSegment>();
		xBound = model.getWidth()/2;
		yBound = model.getHeight()/2;
	}
	/**
	 * adds a LineSegment to the paths queue
	 */
	public void add(LineSegment line) {
		paths.add(line);
	}
	/**
	 * clears the paths queue
	 */
	public void clear() {
		paths.clear();
	}
	/**
	 * gets the paths queue
	 */
	public Queue<LineSegment> getPaths() {
		return paths;
	}
	/**
	 * generates paths and adds them to the queue
	 * @param x
	 * 			the current x position
	 * @param y
	 * 			the current y position
	 * @param newX
	 * 			the new x position (can be outside of the bounds)
	 * @param newY
	 * 			the new y position (can be outside of the bounds)
	 * @param delta
	 * 			the distance to be traveled
	 * @param theta
	 * 			the current angular orientation
	 * @return
	 * 			the final LineSegment endpoint
	 */
	public double[] makePaths(double x, double y, double newX, double newY, double delta, double theta) {
		paths.clear();
		return addLineSegments(x, y, newX, newY, delta, theta);
	}

	private double[] addLineSegments(double x, double y, double newX, double newY, double delta, double theta) {
		boolean recurse = false;
		double segX = newX;
		double segY = newY;
		double hypot = 0;
		double x2 = 0;
		double y2 = 0;
		Line2D up = new Line2D.Double(-xBound, yBound, xBound, yBound);
		Line2D down = new Line2D.Double(-xBound, -yBound, xBound, -yBound);
		Line2D left = new Line2D.Double(-xBound, -yBound, -xBound, yBound);
		Line2D right = new Line2D.Double(xBound, -yBound, xBound, yBound);
		Line2D seg = new Line2D.Double(x, y, newX, newY);
		double m = (newY - y) / (newX - x);
		if(newX == x && newY == y) {
			double[] ret = {segX, segY};
			return ret;
		}
		if(Math.abs(newX) > xBound || Math.abs(newY) > yBound) {
			recurse = true;
			if(seg.intersectsLine(left)) {
				segX = -xBound;
				segY = m * (-xBound - x) + y;
				x2 = xBound - 1;
				y2 = segY;
			}
			else if(seg.intersectsLine(right)) {
				segX = xBound;
				segY = m * (xBound - x) + y;
				x2 = -xBound + 1;
				y2 = segY;
			}
			else if(seg.intersectsLine(up)) {
				segY = yBound;
				segX = (yBound - y) / m + x;
				x2 = segX;
				y2 = -yBound + 1;
			}
			else if(seg.intersectsLine(down)) {
				segY = -yBound;
				segX = (-yBound - y) / m + x;
				x2 = segX;
				y2 = yBound - 1;
			}
			hypot = Math.hypot(x - segX, y - segY);
		}
		paths.add(new LineSegment(x, y, segX, segY));
		if(recurse) {
			newY = y2 + (delta-hypot)*Math.sin(theta);
			newX = x2 + (delta-hypot)*Math.cos(theta);
			return addLineSegments(x2, y2, newX, newY, delta - hypot, theta);
		}
		else {
			double[] ret = {segX, segY};
			return ret;
		}
	}
}
