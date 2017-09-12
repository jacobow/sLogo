package back_end.model;
import java.util.Queue;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Turtle {

	/**
	 * Holds the most basic properties of a turtle:
	 * ID, position, orientation, state, and trail.
	 *
	 * @author Jacob Warner
	 */

	private int ID;
    private DoubleProperty xPosition;
    private DoubleProperty yPosition;
    private DoubleProperty thetaAngle;
    private TurtleState state;
    private TurtleTrail trail;

	public Turtle(Model model) {
		xPosition = new SimpleDoubleProperty(0);
		yPosition = new SimpleDoubleProperty(0);
		thetaAngle = new SimpleDoubleProperty(0);
		state = new TurtleState(model);
		trail = new TurtleTrail(model);
	}
	/**
	 * sets the x position
	 */
    public void setXPosition(double value){
    	xPosition.set(value);
    }
    /**
     * gets a bind-able x position
     */
    public DoubleProperty xPositionProperty() {
    	return xPosition;
    }
    /**
     * sets the y position
     */
    public void setYPosition(double value){
    	yPosition.set(value);
    }
    /**
     * gets a bind-able y position
     */
    public DoubleProperty yPositionProperty() {
    	return yPosition;
    }
    /**
     * sets the angular orientation
     */
    public void setThetaAngle(double value){
    	thetaAngle.set(Math.toRadians(value));
    }
    /**
     * gets a bind-able angular orientation
     */
    public DoubleProperty thetaAngleProperty() {
    	return thetaAngle;
    }
    /**
     * sets the turtle's ID number
     */
	public void setID(int ID) {
		this.ID = ID;
	}
	/**
	 * gets the turtle's ID number
	 */
	public int getID() {
		return ID;
	}
	/**
	 * moves the turtle forward
	 */
	public void moveForward(double delta){
		double newY = getY()+delta*Math.sin(thetaAngle.get());
		double newX = getX()+delta*Math.cos(thetaAngle.get());
		double[] pos = makeTrail(getX(), getY(), newX, newY, delta, thetaAngle.get());
		setXPosition(pos[0]);
		setYPosition(pos[1]);
	}
	/**
	 * moves the turtle backward
	 */
	public void moveBack(double delta){
		double newY = getY()-delta*Math.sin(thetaAngle.get());
		double newX = getX()-delta*Math.cos(thetaAngle.get());
		double[] pos = makeTrail(getX(), getY(), newX, newY, delta, thetaAngle.get() + Math.PI);
		setXPosition(pos[0]);
		setYPosition(pos[1]);
	}
	/**
	 * sets the x and y position
	 */
	public void setXY(double x, double y){
		trail.clear();
		if(Math.hypot(getX(), getY()) != 0) {
			trail.add(new LineSegment(getX(), getY(), x, y));
		}
		setXPosition(x);
		setYPosition(y);
	}
	/**
	 * clears the turtles trail queue and returns the distance to
	 * the origin
	 */
	public double clear() {
		double distance = Math.hypot(getX(), getY());
		trail.clear();
		setXY(0,0);
		return distance;
	}
	/**
	 * moves the turtle to the origin
	 */
	public void home(){
		trail.clear();
		setXY(0,0);
	}

	private double[] makeTrail(double x, double y, double newX, double newY, double delta, double theta) {
		return trail.makePaths(x, y, newX, newY, delta, theta);
	}
	/**
	 * sets the angular orientation
	 */
	public void setHeading(double theta) {
		setThetaAngle(theta);
	}
	/**
	 * faces the turtle toward the coordinate given
	 */
	public void faceToward(double x, double y) {
		setThetaAngle(Math.acos((getX()-x)/Math.sqrt((getX()-x)*(getX()-x)+(getY()-y)*(getY()-y))));
	}
	/**
	 * turns the turtle right
	 */
	public void turnRight(double theta){
		setThetaAngle(getOrientation() + theta);
	}
	/**
	 * turns the turtle left
	 */
	public void turnLeft(double theta) {
		setThetaAngle(getOrientation() - theta);
	}
	/**
	 * gets the turtle's position
	 */
	public double[] getPosition() {
		double[] pos = {getX(), getY()};
		return pos;
	}
	/**
	 * gets the turtle's x position
	 */
	public double getX() {
		return xPosition.get();
	}
	/**
	 * gets the turtle's y position
	 */
	public double getY() {
		return yPosition.get();
	}
	/**
	 * gets the turtle's orientation
	 */
	public double getOrientation() {
		return Math.toDegrees(thetaAngle.get());
	}
	/**
	 * gets the turtles path queue
	 */
	public Queue<LineSegment> getPaths() {
		return trail.getPaths();
	}
	/**
	 * gets the turtle's state
	 */
	public TurtleState getState() {
		return state;
	}
}