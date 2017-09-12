// This entire file is part of my masterpiece.
// Jacob Warner

package back_end.model;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import application.gui.interfaces.Observer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Model implements Subject {

	/**
	 * This class holds all turtle objects and model properties (color, size, etc),
	 * as well as variable data.  This is the top of the model hierarchy, so everything
	 * another class might needed to observe and read is in here.  Essentially, this class
	 * is my external API, which serves as the reason for all of its contents.  This class
	 * also implements an essential design pattern, the observer design pattern, and serves
	 * as a subject for observers to view and read from.  I wrote both the Observer and design
	 * interfaces, and you can see how they work together at the back-end level of our project
	 * starting at line
	 *
	 * @author Jacob Warner
	 */
	private List<Observer> observers;
	private List<Turtle> turtles;
	private double width;
	private double height;
	private boolean clear;
	private DoubleProperty stampCount;
	private ObservableList<Integer> backgroundColor;
	private ObservableMap<String, Double> variables;
	private ObservableMap<String, List<String>> commands;
	private ObservableMap<Double, int[]> palette;

	public Model(double width, double height) {
		this.width = width;
		this.height = height;
		stampCount = new SimpleDoubleProperty(0);
		observers = new ArrayList<Observer>();
		turtles = new ArrayList<Turtle>();
		backgroundColor = FXCollections.observableArrayList();
		variables = FXCollections.observableHashMap();
		commands = FXCollections.observableHashMap();
		palette = FXCollections.observableHashMap();
	}
	/**
	 * adds an active turtle to the model and assigns it an ID
	 */
	public void addTurtle() {
		Turtle t = new Turtle(this);
		t.setID(turtles.size());
		turtles.add(t);
		t.getState().setActive(true);
	}
	/**
	 * adds an active turtle with a given ID
	 */
	public void addTurtle(int id) {
		boolean newID = true;
		for(Turtle t : turtles) {
			if(t.getID() == id) {
				newID = false;
			}
		}
		if(newID) {
			addTurtle();
		}
	}
	/**
	 * gets rid of the turtles
	 */
	public void clear() {
		turtles.clear();
	}
	/**
	 * activates a specific turtle
	 * @param ID
	 * 		ID of turtle to be activated
	 */
	public void activateTurtle(int ID) {
		for(Turtle t: turtles) {
			if(t.getID() == ID) t.getState().setActive(true);
		}
	}
	/**
	 * deactivates a specific turtle
	 * @param ID
	 * 		ID of turtle to be deactivated
	 */
	public void deactivateTurtle(int ID) {
		for(Turtle t: turtles) {
			if(t.getID() == ID) t.getState().setActive(false);
		}
	}
	/**
	 * getter for the list of turtles
	 */
	public List<Turtle> getTurtles() {
		return turtles;
	}
	/**
	 * updates a variable to a new value
	 */
	public void updateVariable(String name, double value) {
		variables.put(name, value);
	}
	/**
	 * retrieves a varible's value by key
	 */
	public double getVariableValue(String name) {
		if(!variables.containsKey(name)) {
			throw new NoSuchElementException();
		}
		return variables.get(name);
	}
	/**
	 * returns a bind-able map of the variables
	 */
	public ObservableMap<String, Double> getVariables() {
		return variables;
	}
	/**
	 * updates a command to a new value (String list of commands)
	 */
	public void updateCommand(String name, List<String> list) {
		commands.put(name, list);
	}
	/**
	 * gets a commands value by key
	 */
	public List<String> getCommandValue(String name) {
		return commands.get(name);
	}
	/**
	 * gets a bind-able map of the commands
	 */
	public ObservableMap<String, List<String>> getCommands() {
		return commands;
	}
	/**
	 * sets a pallete's new rgb value
	 */
	public void setPalette(double index, int r, int g, int b) {
		int[] rgb = {r,g,b};
		palette.put(index, rgb);
	}
	/**
	 * sets the background to a new color by index
	 */
	public void setBackgroundColor(double index) {
		checkPalette(index);
		backgroundColor.clear();
		ArrayList<Integer> backgroundColorArray = new ArrayList<Integer>();
		for(int i : palette.get(index)) {
			backgroundColorArray.add(i);

		}
		backgroundColor.addAll(backgroundColorArray);
	}
	/**
	 * returns an bind-able background color
	 */
	public ObservableList<Integer> getBackgroundColor() {
		return backgroundColor;
	}
	void checkPalette(double index) {
		if(!palette.containsKey(index)) {
			int[] rgb = toRGB(index);
			if(rgb == null) throw new NoSuchElementException();
			palette.put(index, rgb);
		}
	}
	private int[] toRGB(double index) {
		if(index <= 255255255 && index >= 0) {
			String rgb = String.format("%09d", (int)index);
			int[] rgbInts = {Integer.parseInt(rgb.substring(0,3)),
					Integer.parseInt(rgb.substring(3, 6)),
					Integer.parseInt(rgb.substring(6))};
			return rgbInts;
		}
		else return null;
	}
	/**
	 * returns whether or not the model is clear
	 */
	public boolean isClear() {
		return clear;
	}
	/**
	 * clears the model
	 */
	public void setClear(boolean clear) {
		this.clear = clear;
	}
	/**
	 * gets the height of the model
	 */
	double getWidth() {
		return width;
	}
	/**
	 * gets the width of the model
	 */
	double getHeight() {
		return height;
	}
	/**
	 * gets a bind-able palette of the colors
	 */
	public ObservableMap<Double, int[]> getPalette() {
		return palette;
	}

	/**
	 * adds a stamp to the model, to be listened to by
	 * the frontend
	 */
	public double addStamp() {
		stampCount.setValue(stampCount.getValue() + 1);
		return stampCount.getValue();
	}
	/**
	 * clears the stamps from the model, to be listened
	 * to by the frontend
	 */
	public boolean clearStamps() {
		if(stampCount.getValue() > 0) {
			stampCount.setValue(0);
			return true;
		}
		return false;
	}
	/**
	 * registers an observer to view the model
	 */
	@Override
	public void register(Observer obj) {
		observers.add(obj);
	}
	/**
	 * unregisters an observer to view the model
	 */
	@Override
	public void unregister(Observer obj) {
		observers.remove(obj);
	}
	/**
	 * notifies all observers to update according to the new model
	 */
	@Override
	public void notifyObservers() {
		for(Observer obs : observers) obs.update();
		setClear(false);
	}
	/**
	 * returns an updated copy of the model, insuring that observers can definitely
	 * access anything they might need
	 */
	@Override
	public Model getUpdate(Observer obj) {
		return this;
	}
}