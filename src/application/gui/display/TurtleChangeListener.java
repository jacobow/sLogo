package application.gui.display;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class TurtleChangeListener{
	private TurtleFXShell myTurtleShell;
	
	public TurtleChangeListener(TurtleFXShell turtleShell){
		myTurtleShell = turtleShell;
		
	}
	
	/**
	 * Listener method to update x position
	 * @return X Listener
	 */
	public ChangeListener<Number> getXListener(){
		return new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				//myTurtleShell.setTranslateX(myTurtleShell.getTurtleObject().getX());
				//System.out.println("X Position Changed");
			}
			
		};
	}
	
	/**
	 * Listener method to update y position
	 * @return Y Listener
	 */
	public ChangeListener<Number> getYListener(){
		return new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				//myTurtleShell.setTranslateY(myTurtleShell.getTurtleObject().getY());
				
				//System.out.println("Y Position Changed");
			}
			
		};
	}

	/**
	 * Listener method to update angle
	 * @return Angle Listener
	 */
	public ChangeListener<Number> getThetaListener(){
		return new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				//myTurtleShell.setRotate(myTurtleShell.getTurtleObject().getOrientation());
				//System.out.println("Angle Changed");
			}
			
		};
	}
	
	/**
	 * Listener method to update turtle image
	 * @return Angle Listener
	 */
	public ChangeListener<Number> getImageListener(){
		return new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myTurtleShell.setTurtleImage((double) newValue);
			}
			
		};
	}
	
	/**
	 * Listener method to update turtle visibility
	 * @return Angle Listener
	 */
	public ChangeListener<Boolean> getVisibilityListener(){
		return new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				myTurtleShell.setVisible(newValue);
				
			}
			
		};
	}

	public ChangeListener<? super Boolean> getSelectionStatusListener() {
		return new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue == true){
					turnOnSelectionEffects();
				}
				else{
					turnOffSelectionEffects();
				}
				
			}

			
		};
	}
	
	void turnOnSelectionEffects() {
		DropShadow ds = new DropShadow(50, Color.BLACK);
		myTurtleShell.setEffect(ds);
	}
	void turnOffSelectionEffects(){
		myTurtleShell.setEffect(null);
	}

}
