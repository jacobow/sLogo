package application;

import java.awt.Dimension;

import application.gui.SLogoMainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for SLogo IDE Project. Extends a JavaFX application. Instantiates a new SLogoUserInterface.
 * @author michaelseaberg
 *
 */
public class Main extends Application{
	//Size of screen window
	public static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
	//Default Resource package
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/English";
	
    /**
     * Initializes the JavaFX application by creating a new SLogoUserInterface. The SLogoUI is the encapsulating front-end class to 
     * create a new SLogo application
     */
	public void start(Stage stage){
		SLogoMainMenu mySLogoMenu = new SLogoMainMenu(stage);
		stage.setScene(mySLogoMenu.getScene());
		stage.show();
	}

    public static void main(String[] args) {
        launch(args);
    }

}