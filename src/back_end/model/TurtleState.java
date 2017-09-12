package back_end.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TurtleState {

	/**
	 * holds visible properties of the turtle
	 */
	private BooleanProperty penDown;
	private BooleanProperty visible;
	private BooleanProperty active;
	private ObservableList<Integer> penColor;
    private double penColorIndex;
    private DoubleProperty turtleShape;
    private DoubleProperty penSize;
    private Model myModel;

    public TurtleState(Model model) {
    	myModel = model;
    	penDown = new SimpleBooleanProperty(false);
		visible = new SimpleBooleanProperty(true);
		active = new SimpleBooleanProperty(false);
    	turtleShape = new SimpleDoubleProperty();
        penColor = FXCollections.observableArrayList();
        penColorIndex = -1;
        penSize = new SimpleDoubleProperty();
    }

    /**
     * sets the pen color of the turtle to an indexed color
     */
    public void setPenColor(double index) {
    	myModel.checkPalette(index);
    	penColor.clear();
    	penColorIndex = index;
    	for(int i : myModel.getPalette().get(index)) penColor.add(i);
    }
    /**
     * makes the turtle visible
     */
    public void showTurtle() {
		visible.setValue(true);
	}
    /**
     * makes the turtle invisible
     */
	public void hideTurtle() {
		visible.setValue(false);
	}
	/**
	 * drops the pen
	 */
	public void dropPen() {
		penDown.setValue(true);
	}
	/**
	 * raises the pen
	 */
	public void raisePen() {
		penDown.setValue(false);
	}
	/**
	 * returns true if the pen is up
	 */
	public boolean isPenUp() {
		return !penDown.getValue();
	}
	/**
	 * returns bind-able boolean if the turtle is visible
	 */
	public BooleanProperty isVisible() {
		return visible;
	}
	/**
	 * sets the turtle to be active
	 */
	public void setActive(boolean active) {
		this.active.setValue(active);
	}
	/**
	 * returns true if the turtle is active
	 */
	public Boolean isActive() {
		return active.getValue();
	}
	/**
	 * returns bind-able boolean if the turtle is active
	 */
	public BooleanProperty getActiveProperty(){
		return active;
	}
	/**
	 * returns bind-able rgb color of turtle
	 */
    public ObservableList<Integer> getPenColor() {
    	return penColor;
    }
    /**
     * gets the pen color
     */
    public double getPenColorIndex() {
    	return penColorIndex;
    }
    /**
     * set the turtle's shape
     */
    public void setTurtleShape(double index) {
    	turtleShape.setValue(index);
    }
    /**
     * returns a bind-able index of the turtle shape
     */
    public DoubleProperty getTurtleShape() {
    	return turtleShape;
    }
    /**
     * sets the turtle's pen size
     */
    public void setPenSize(double index) {
    	penSize.setValue((int)index);
    }
    /**
     * gets a bind-able double of the pen size
     */
    public DoubleProperty getPenSize() {
    	return penSize;
    }
}
