package application.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


/**
 * A simple class built to be extended. Adds button making to panes so that multiple sections of the UI can use the same makeButton method with ease. 
 * Any button based methods used in multiple cases can be here as well. 
 * 
 * @author walker
 *
 */
public class SLogoUIArea {
	
	protected Pane myArea;
	/**
	 * Css built by Michael, applied to all areas.
	 */
	protected void setCSSStyle(){
		myArea.setStyle("-fx-background-color: #EFEFEF;" +
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 2;" +
                "-fx-border-insets: 2;" + 
                "-fx-border-radius: 5;" + 
                "-fx-border-color: grey;");
	}
	/**
	 * Makes a button with a string and an event handler
	 * @param string
	 * @param handler
	 * @return JavaFX Button
	 */
	protected Button makeButton(String string, EventHandler<ActionEvent> handler) {
		Button toReturn = new Button();
		toReturn.setText(string);
		toReturn.setOnAction(handler);
		toReturn.setFont(new Font(11));
		return toReturn;
	}
	/**
	 * Makes a button of a specific size
	 * @param string
	 * @param handler
	 * @param prefWidth
	 * @param prefHeight
	 * @return JavaFX Button
	 */
	protected Button makeButton(String string, EventHandler<ActionEvent> handler,double prefWidth,double prefHeight){
		Button toReturn = makeButton(string,handler);
		toReturn.setPrefHeight(prefHeight);
		toReturn.setPrefWidth(prefWidth);
		toReturn.setFont(new Font(11));
		return toReturn;
	}
	/**
	 * Encapsualtes the pane itself
	 * @return
	 */
	protected Pane getArea(){
		return myArea;
	}
	
}
