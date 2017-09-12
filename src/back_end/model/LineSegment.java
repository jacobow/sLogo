package back_end.model;

public class LineSegment {

	/**
	 * A line segment with starting points and ending points.
	 *
	 * @author Jacob Warner
	 */

	private double x0;
	private double y0;
	private double x1;
	private double y1;

	public LineSegment(double x0, double y0, double x1, double y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}

	/**
	 * gets the coordinates of the start point and the end point
	 */
	public double[][] getCoordinates() {
		double[][] coordinates = {{x0, y0}, {x1, y1}};
		return coordinates;
	}

	/**
	 * gets the length of the line segment
	 */
	public double getLength(){
		double length = Math.sqrt((Math.pow((y1-y0),2) + Math.pow((x1-x0),2)));
		return length;
	}

}
