package application.gui.interfaces;

import javafx.scene.Group;
import javafx.scene.Scene;

/**
 * 
 * @author michaelseaberg
 *
 */
public interface UserInterface {
	
	/**
	 * Returns the full JavaFX Scene of the UserInterface to be placed in a stage
	 * @return Scene Object
	 */
	Scene getScene();
	
	/**
	 * Used to initialize a UserInterface object and populate it with an initial simulation.
	 * Should: Associate a group with the scene
	 */
	void initialize();

	/**
	 * Used to retrieve the JavaFX Group object that is associated with the UserInterface Scene.
	 * @return Group Object
	 */
	Group getGroup();
}
