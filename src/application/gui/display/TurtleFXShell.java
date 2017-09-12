package application.gui.display;

import java.util.Formatter;
import java.util.Queue;

import back_end.model.LineSegment;
import back_end.model.Turtle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

/**
 * "Shell" for turtle object in the gui to simplify interfacing with the display.
 * TurtleFXShell extends ImageView for simplicity-the whole object itself is a new node
 * @author michaelseaberg
 *
 */
public class TurtleFXShell extends ImageView{
	public final String[] SUPPORTED_TURTLES={"bc", "clemson","duke","fsu","gt","---","miami",
			"unc","ncs","---","---","---","virginia","vt","wakeforest" };
	private Turtle myTurtle;
	private TurtleChangeListener myTurtleListener;
	private BooleanProperty mySelectionStatus;
	private String myPenColor = "000000000";
	private int currentImageInt = 2;

	public TurtleFXShell(Turtle turtle){
		super();
		myTurtle = turtle;
		setTurtleImage(2); //Default Duke Image
		myTurtleListener = new TurtleChangeListener(this);
		mySelectionStatus = new SimpleBooleanProperty(true);
		bindShellToTurtle();
	}
	
	TurtleChangeListener getListener(){
		return myTurtleListener;
	}
	
	private void bindShellToTurtle() {
		//myTurtle.xPositionProperty().addListener(myTurtleListener.getXListener());
		//myTurtle.yPositionProperty().addListener(myTurtleListener.getYListener());
		//myTurtle.thetaAngleProperty().addListener(myTurtleListener.getThetaListener());
		myTurtle.getState().getTurtleShape().addListener(myTurtleListener.getImageListener());
		myTurtle.getState().isVisible().addListener(myTurtleListener.getVisibilityListener());
		myTurtle.getState().getActiveProperty().addListener(myTurtleListener.getSelectionStatusListener());
	}

	public Node draw(){
		Group newTurtlePath = new Group();
		//Whenever draw is called, it should unwaveringly draw whatever is in the queue, even if there is some error. Therefore it is not bounded.
		Queue<LineSegment> pathsToDraw = myTurtle.getPaths();
		while(!pathsToDraw.isEmpty()){
			LineSegment currentPath = pathsToDraw.poll();
			if(!myTurtle.getState().isPenUp()){
				double[][] lineCoordinates = currentPath.getCoordinates();
				Line newTurtleLine = new Line(lineCoordinates[0][0],lineCoordinates[0][1],lineCoordinates[1][0],lineCoordinates[1][1]);
				newTurtlePath.getChildren().add(newTurtleLine);
			}
		}
		return newTurtlePath;
	}
	
	public void setTurtleImage(double imageIndex){
		currentImageInt = (int) imageIndex;
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("resources/"+SUPPORTED_TURTLES[(int) imageIndex] + "turtle.png"));
		this.setImage(image);
		this.setFitHeight(60);
		this.setFitWidth(60);
	}

	public Node getTurtleSprite(){
		return this;
	}

	public Turtle getTurtleObject(){
		return myTurtle;
	}

	public boolean getMySelectionStatus() {
		return mySelectionStatus.getValue();
	}

	public void setMySelectionStatus(boolean mySelectionStatus) {
		this.mySelectionStatus.set(mySelectionStatus);
	}
	
	void setPenColor(int r, int g, int b){
		Formatter f = new Formatter();
		f.format("%03d%03d%03d", r,g,b);
		myPenColor = f.toString();
	}
	
	String getPenColor(){
		return myPenColor;
	}
	
}
