package application.gui;

import java.awt.Dimension;
import java.io.File;
import application.Main;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * 
 * @author michaelseaberg
 *
 */
public class SLogoMainMenu extends SLogoUIArea{
	private static final Dimension MAIN_MENU_SIZE = new Dimension(500, 500); 
	private static final String MENU_BACKGROUND = "resources/slogomainmenu.png";
	public final File HTML_HELP_FILE = new File("src/resources/html_help_file.html");
	private static final int BUTTON_BOX_OFFSET = 300;
	private Scene myScene;
	private Group myGroup;
	private Stage myStage;
	
	public SLogoMainMenu(Stage applicationStage){
		myStage = applicationStage;
		myGroup = new Group();
		myScene = new Scene(myGroup,MAIN_MENU_SIZE.getWidth(), MAIN_MENU_SIZE.getHeight());
		initializeMenu();
	}
	
	private void initializeMenu() {
		Image background = new Image(getClass().getClassLoader().getResourceAsStream(MENU_BACKGROUND));
		createMenuButtons();

		myGroup.getChildren().addAll(new ImageView(background));
		myGroup.getChildren().add(myArea);
	}

	private void createMenuButtons() {
		myArea = new VBox();
		
		myArea.setPrefSize(MAIN_MENU_SIZE.getWidth(), MAIN_MENU_SIZE.getHeight()-BUTTON_BOX_OFFSET);
		myArea.setLayoutY(BUTTON_BOX_OFFSET);
		myArea.setStyle("-fx-background-color: transparent;");
		((VBox) myArea).setAlignment(Pos.CENTER);
		((VBox) myArea).setSpacing(15);
		Button simulationButton = makeButton("New Simulation", e->constructUI(), 220, 40);
		Button helpButton = makeButton("Help File", e->getHTMLHelpFile(), 75, 16);
		simulationButton.setFont(new Font(18));
		getArea().getChildren().addAll(simulationButton,helpButton);
	}

	private void getHTMLHelpFile() {
		Stage webStage = new Stage();
		Group webGroup = new Group();
		Scene webScene = new Scene(webGroup,webStage.getWidth(),webStage.getHeight());

		WebView helpView = new WebView();
		WebEngine helpEngine = helpView.getEngine();
		helpEngine.load("file:"+HTML_HELP_FILE.getAbsolutePath()); 
		webGroup.getChildren().add(helpView);
		webStage.setScene(webScene); 
		webStage.show();

	}

	private void constructUI() {
        myStage.setX(0);
        myStage.setY(0);
		SLogoUserInterface mySLogoProgram = new SLogoUserInterface(Main.DEFAULT_SIZE.getWidth(),Main.DEFAULT_SIZE.getHeight());
		myStage.setScene(mySLogoProgram.getScene());
		myStage.show();
	}

	public Scene getScene() {
		return myScene;
	}
}
