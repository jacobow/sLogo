package application.gui.display;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import back_end.model.LineSegment;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.util.Duration;

public class TurtleFXAnimator extends Canvas{
	private final int DEFAULT_ANIMATION_SPEED = 1000;
	private LinkedList<Animation> myAnimationQueue;
	private GraphicsContext myGraphicsContext;
	private List<TurtleFXShell> myCurrentTurtles;
	private int myAnimationSpeed;

	public TurtleFXAnimator(double width, double height) {
		super(width,height);
		myAnimationQueue = new LinkedList<Animation>();
		myGraphicsContext = this.getGraphicsContext2D();
		myCurrentTurtles = new ArrayList<TurtleFXShell>();
		myAnimationSpeed = DEFAULT_ANIMATION_SPEED;
	}
	
	/**
	 * Return the currently executing animation.
	 * @return
	 */
	public Animation getCurrentAnimation(){
		if(myAnimationQueue.isEmpty()){
			return null;
		}
		return myAnimationQueue.getFirst();
	}
	
	/**
	 * clearScreen() method used to clear the canvas which the addAnimationToQueue method uses to draw lines on. Does not delete the canvas
	 * but instead clears the graphics context which was linked to the canvas.
	 */
	public void clearScreen(){
		myGraphicsContext.clearRect(0, 0, this.getWidth(), this.getHeight());
		setAnimationSpeed();
		
	}

	/**
	 * Create a PathTransition that encapsulates the currentShell moving along some given path taken from the LineSegment Object.
	 * @param currentSegment
	 * @param currentShell
	 * @return
	 */
	private PathTransition getLineAnimation(LineSegment currentSegment, TurtleFXShell currentShell) {
		PathElement initialMove = new MoveTo(currentSegment.getCoordinates()[0][0]+30,currentSegment.getCoordinates()[0][1]+30);
		PathElement lineTo = new LineTo(currentSegment.getCoordinates()[1][0]+30,currentSegment.getCoordinates()[1][1]+30);
		PathTransition currentPath = new PathTransition(Duration.millis(myAnimationSpeed*(currentSegment.getLength()/200)), new Path(initialMove,lineTo), currentShell);
		return currentPath;
	}
	
	/**
	 * Create a RotationTransition that encapsulates the currentShell rotating a certain input angle.
	 * @param angle
	 * @param currentShell
	 * @return
	 */
	private RotateTransition getRotationAnimation(double angle, TurtleFXShell currentShell) {
		RotateTransition rotation = new RotateTransition(Duration.millis(0.4*myAnimationSpeed*Math.abs((Math.toDegrees(angle)/360))),currentShell);
		rotation.setByAngle(Math.toDegrees(angle));
		return rotation;
	}

	/**
	 * Reset animation speed to the method default
	 */
	public void setAnimationSpeed(){
		myAnimationSpeed = DEFAULT_ANIMATION_SPEED;
	}
	
	/**
	 * Get the value of the myAnimationSpeed parameter
	 * @param newSpeed
	 */
	public int getMyAnimationSpeed() {
		return myAnimationSpeed;
	}

	/**
	 * Set the animation speed to the input parameter newSpeed
	 * @param newSpeed
	 */
	public void setAnimationSpeed(int newSpeed){
		myAnimationSpeed = newSpeed;
	}
	
	/**
	 * Method to animate a TurtleFXShell given a queue of line segments to draw. Also links the turtleShells rotation property
	 * to the turtle models angle on the first time a particular shell is used in this method.
	 * @param currentShell
	 */
	public void createAnimation(TurtleFXShell currentShell) {
		/**
		 * Implements line animation by taking an updated queue of LineSegements (path queue) and adding an animation to
		 * the animationQueue for every path in the given path queue.
		 */
		Queue<LineSegment> linesToDraw = currentShell.getTurtleObject().getPaths();
		while(!linesToDraw.isEmpty()){
			addAnimationToQueue(getLineAnimation(linesToDraw.poll(),currentShell),currentShell);
		}
		
		/**
		 * Implement rotation animation by constructing a listener to add a rotation animation to the animation queue
		 * every time the turtle model's angle changes
		 */
		if(!myCurrentTurtles.contains(currentShell)){
			currentShell.getTurtleObject().thetaAngleProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					addAnimationToQueue(getRotationAnimation((double)newValue-(double)oldValue, currentShell),currentShell);
				}

			});
			myCurrentTurtles.add(currentShell);
		}
	}

	/**
	 * Takes a given Transition and adds the transition to the queue. Adds a listener to each animation which listens to the respective
	 * animations time property and draws a line on the screen if the turtles pen is down. 
	 * @param animation
	 * @param currentShell
	 */
	private void addAnimationToQueue(Animation animation, TurtleFXShell currentShell) {
		animation.currentTimeProperty().addListener(new ChangeListener<Duration>() {
			Location oldLocation = null;
			@Override
			public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
				// skip starting at 0/0
				if( oldValue == Duration.ZERO)
					return;

				// get current location
				double x = (currentShell.getTranslateX() + currentShell.getLayoutX() + 30);
				double y = (currentShell.getTranslateY() + currentShell.getLayoutY() + 30);

				// initialize the location
				if( oldLocation == null) {
					oldLocation = new Location();
					oldLocation.x = x;
					oldLocation.y = y;
					return;
				}

				// draw line
				if(!currentShell.getTurtleObject().getState().isPenUp()){
					List<Integer> penColor = currentShell.getTurtleObject().getState().getPenColor();
					//currentShell.getTurtleObject().getState().setPenColor(255);
					if(currentShell.getTurtleObject().getState().getPenColorIndex() > -1){
						currentShell.setPenColor(penColor.get(0),penColor.get(1),penColor.get(2));
						myGraphicsContext.setStroke(Color.rgb(penColor.get(0), penColor.get(1), penColor.get(2)));
						//System.out.println("Gets to here");
					}
					else{
						myGraphicsContext.setStroke(Color.BLACK);
					}
					myGraphicsContext.setLineWidth(currentShell.getTurtleObject().getState().getPenSize().doubleValue());
					myGraphicsContext.strokeLine(oldLocation.x, oldLocation.y, x, y);
				}
			

				// update old location with current one
				oldLocation.x = x;
				oldLocation.y = y;
			}
				
		});
		animation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myAnimationQueue.poll();

			}
		});
		//if animation Queue has elements in it, get the last element and upon finishing, remove itself from the queue and play the
		//next animation in the queue (the one in this method) which is then added to the queue.

		//this method i dont think is removing the queue correctly
		if(!myAnimationQueue.isEmpty()){
			myAnimationQueue.getLast().setOnFinished(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					myAnimationQueue.poll();
					animation.play();

				}
			});
			myAnimationQueue.add(animation);
		}
		//if animation Queue is empty, make sure the animation generated in this method removes itself upon completion. Then add it to the queue,
		//and play the first element in the queue (which is the element you just added but formatted it like that to maintain design)
		else{
			myAnimationQueue.add(animation);
			myAnimationQueue.getFirst().play();
		}
	}
	
	/**
	 * Class used to implement line drawing in the addAnimationToQueue method.
	 * @author michaelseaberg
	 *
	 */
	private static class Location {
		double x;
		double y;
	}

	/**
	 * Method used to link a given slider to the speed of the animation of each turtle. 
	 * @param animationSpeedSlider
	 */
	public void linkAnimationSpeed(Slider animationSpeedSlider) {
		animationSpeedSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				setAnimationSpeed(400000/new_val.intValue());
			}
		});
	}
}