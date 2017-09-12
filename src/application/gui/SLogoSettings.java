package application.gui;

import java.io.File;
import java.util.Formatter;

import application.gui.interfaces.CommandLine;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * 
 * This class handles a lot of user input and sends it to the command line
 * @author walker
 *
 */
public class SLogoSettings extends SLogoUIArea {
	public final double TOOLBAR_HEIGHT = 35;
	public final int NUMBER_OF_SETTINGS_IN_VBOX = 6;
	public final File HTML_HELP_FILE = new File("src/resources/html_help_file.html");

	public final String[] SUPPORTED_TURTLES={"BC", "Clemson","Duke","FSU","GTech","---","Miami",
			"UNC","NC State","---","---","---","UVA","VaTech","Wake Forest" };
	public final String[] SUPPORTED_PEN_SIZES = {"1","2","3","4","5","6","7","8","9","10"};
	public final String LANGUAGE_STRING="Language";
	public final String BACKGROUND_STRING = "Background";
	public final String HELP_STRING = "Help File";
	public final String PEN_STRING = "Pen";
	public final String TURTLE_IMAGE_CHOOSER_STRING = "Select Turtle Image";
	public final String PEN_SIZE = "Pen Size";
	
	private String setPenColorCommand;
	private String setPenSizeCommand;
	private String setBGColorCommand;
	private String setTurtleImageCommand;

	private Color backgroundColor;
	private Color penColor;
	private CommandLine myCommandLine;
	private double boxCombinedHeight=400;

	/**
	 * Initilaizes the Settings area 
	 * @param prefWidth
	 * @param prefHeight
	 * @param commandLine
	 */
	public SLogoSettings(double prefWidth, double prefHeight, CommandLine commandLine){
		myArea = new VBox();
		myArea.setPrefHeight(prefHeight);
		myArea.setPrefWidth(prefWidth);
		setCSSStyle();
		
		myCommandLine = commandLine;
		updateCommands();
		initializePreferenceChooser(LANGUAGE_STRING,myCommandLine.SUPPORTED_LANGUAGES);
		initializePreferenceChooser(TURTLE_IMAGE_CHOOSER_STRING,SUPPORTED_TURTLES);
		initializeColorChooser(BACKGROUND_STRING);
		initializeColorChooser(PEN_STRING);
		initializePreferenceChooser(PEN_SIZE,SUPPORTED_PEN_SIZES);
		initializeHTMLHelpFileButton(HELP_STRING);
		
		//Bad casting, but the alternative would be to add random boxes which I don't like... 
		((VBox) myArea).setSpacing((prefHeight-boxCombinedHeight)/NUMBER_OF_SETTINGS_IN_VBOX);

	}

	private void initializeHTMLHelpFileButton(String helpString){
		Button htmlHelpButton = makeButton(helpString,e->getHTMLHelpFile());
		myArea.getChildren().add(htmlHelpButton);

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


	private void initializeColorChooser(String label) {
		VBox colorChooserWithLabel = new VBox();
		ColorPicker colorPicker = new ColorPicker();
		setColorPickerAction(label, colorPicker);
		colorChooserWithLabel.getChildren().addAll(new Label(label),colorPicker);
		myArea.getChildren().add(colorChooserWithLabel);
	}

	private void setColorPickerAction(String label, ColorPicker colorPicker) {
		if(label.equals(BACKGROUND_STRING)){
			colorPicker.setOnAction(t->setBackgroundColor(colorPicker));
		}else{
			colorPicker.setOnAction(t->setPenColor(colorPicker));
		}
	}

	private void setPenColor(ColorPicker penColorPicker) {
		penColor = penColorPicker.getValue();
		myCommandLine.setCommandImmediately(setPenColorCommand + " " + getFormattedRGB(penColor));
	}


	private void setBackgroundColor(ColorPicker bgColorPicker){
		backgroundColor=bgColorPicker.getValue();
		myCommandLine.setCommandImmediately(setBGColorCommand+ " " + getFormattedRGB(backgroundColor));
	}
	
	private String getFormattedRGB(Color myColor){
		StringBuilder colorStringBuilder = new StringBuilder();
		Formatter colorStringFormatter =  new Formatter(colorStringBuilder);
		colorStringFormatter.format("%03d%03d%03d", (int) Math.round(255*myColor.getRed()),
				(int) Math.round(255*myColor.getGreen()), (int) Math.round(255*myColor.getBlue()));
		return colorStringFormatter.toString();
	}

	private void initializePreferenceChooser(String label,String[] contents) {
		VBox optionChooserAndLabel= new VBox();
		ComboBox<String> optionChooser = new ComboBox<String>();
		optionChooser.setItems(FXCollections.observableArrayList(contents));
		setComboBoxAction(contents, optionChooser);
		optionChooserAndLabel.getChildren().addAll(new Label(label),optionChooser);
		
		myArea.getChildren().add(optionChooserAndLabel);
	}

	private void setComboBoxAction(String[] contents, ComboBox<String> optionChooser) {
		if(contents==SUPPORTED_TURTLES){
			optionChooser.setOnAction(t->setTurtle(optionChooser));
		}else if(contents==SUPPORTED_PEN_SIZES){
			optionChooser.setOnAction(t->setPenSize(optionChooser));
		}else{
			//Assume it is from command line to limit access calls to the command line. 
			optionChooser.setOnAction(t->setLanguage(optionChooser));
		}
	}
	
	private void setTurtle(ComboBox<String> turtleBox) {
		myCommandLine.setCommandImmediately(setTurtleImageCommand+" "+turtleBox.getSelectionModel().getSelectedIndex());
	}
	private void setPenSize(ComboBox<String> penSizeBox){
		myCommandLine.setCommandImmediately(setPenSizeCommand + " " + penSizeBox.getSelectionModel().getSelectedItem());
	}

	private void setLanguage(ComboBox<String> languageBox){
		myCommandLine.updateLanguage(languageBox.getSelectionModel().getSelectedItem());
		updateCommands();
	}

	private void updateCommands() {
		setPenColorCommand = myCommandLine.getResources().getString("SetPenColor").split("\\|")[0];
		setPenSizeCommand = myCommandLine.getResources().getString("SetPenSize").split("\\|")[0];
		setBGColorCommand = myCommandLine.getResources().getString("SetBackground").split("\\|")[0];
		setTurtleImageCommand = myCommandLine.getResources().getString("SetShape").split("\\|")[0];
	}

}
