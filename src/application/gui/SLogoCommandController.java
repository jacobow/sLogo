package application.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.Stack;

import application.gui.interfaces.CommandLine;
import application.gui.interfaces.UndoRedoCommandLine;
import back_end.commandInterpreter.Parser;
import back_end.model.Model;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Walker Eacho
 *
 */
public class SLogoCommandController extends SLogoUIAreaWithFileIO implements UndoRedoCommandLine,CommandLine{
	public final double TEXTAREA_HEIGHT_PERCENTAGE = 0.750;
	public final double TEXTAREA_WIDTH_PERCENTAGE = (2.0/5.0);
	public final double HISTORY_WIDTH_PERCENTAGE = (1.0/5.0);
	public final double VARIABLE_WIDTH_PERCENTAGE = (1.0/5.0);
	public final double USERCOMMAND_WIDTH_PERCENTAGE = (1.0/5.0);
	public final double SAVE_LOAD_BUTTON_WIDTH_PERCENTAGE = HISTORY_WIDTH_PERCENTAGE/2;
	public final double BUTTON_HEIGHT_PERCENTAGE = 1-TEXTAREA_HEIGHT_PERCENTAGE;
	public final double BORDER_BUFFER = 8;
	
	public final String COLOR_PALETTE = "Color Palette";
	public final String LOAD_USER_FUNCTIONS = "Load User Functions";
	public final String LOAD_HISTORY = "Load History";
	public final String SUBMIT = "Submit";
	
	public final String[] INITIAL_PALETTE = {"0 000 000 000", "1 255 255 255","2 255 000 000", "3 000 255 000","4 000 000 255",
			"5 255 255 000","6 255 000 255","7 000 255 255"};
	
	private SLogoHistoryListView myHistory;
	private SLogoDefinedCommandView myUserDefinedCommands;
	private TextArea commandLine;
	private String[] currentCommand;
	private Parser backEndParser;
	private ResourceBundle myResources;
	private String currentLanguage;
	private Model myModel;
	private SLogoVariableTable myVariables;
	private Stack<String> redoStack;
	private File defaultSettings = new File(DEFAULT_FILE_LOCATION+PREFERENCES_EXTENSION);
	
	/**
	 * Creates the user interface area below the display
	 * @param prefWidth - Width of interface area
	 * @param prefHeight - Height thereof
	 * @param model - Previously initialized model
	 */
	SLogoCommandController(double prefWidth, double prefHeight, Model model){
		myArea = new VBox();
		myArea.setPrefHeight(prefHeight);
		myArea.setPrefWidth(prefWidth);
		setCSSStyle();
		myModel=model;
		updateLanguage(SUPPORTED_LANGUAGES[1]);
		initializeNonButtonAreas(prefWidth, prefHeight);
		initializeButtons(prefWidth, prefHeight);
	}
	
	/**
	 * Initializes the default settings 
	 */
	void init(){
		if(defaultSettings.exists()){
			loadFile(defaultSettings);
		}
	}
	
	//////////////////////////////////////////////////////////////////
	//																//
	//	API Methods | See interfaces for documentation				//
	//																//
	//////////////////////////////////////////////////////////////////
	
	public void setCommand(String command) {
		commandLine.appendText(command);
	}

	public void setCommandImmediately(String command) {
		command=replaceNewLine(command);
		currentCommand = command.split(" ");
		parseText(backEndParser,currentCommand);
	}
	
	public String[] getCommand() {
		if(commandLine.getText().isEmpty()){
			currentCommand = new String[]{};
		}else{
			currentCommand = new String[]{commandLine.getText()};
		}
		return currentCommand;
	}

	public void updateLanguage(String newLanguage){
		currentLanguage=newLanguage;
		myResources = ResourceBundle.getBundle("resources/languages/"+currentLanguage);
		initializeParser();
	}
	
	public String currentLanguage(){
		return currentLanguage;
	}
	
	public ResourceBundle getResources(){
		return myResources;
	}
	
	public void undo(){
		if(myHistory.getSize()>0){
			if(redoStack==null){
				redoStack = new Stack<String>();
			}
			redoStack.push(myHistory.getStack().pop());
			resetBoard();
			for(String s : myHistory.getStack()){
				setCommandImmediately(s);
			}
		}
	}
	
	public void redo(){
		if(redoStack!=null && redoStack.size() !=0){
			setCommandImmediately(myHistory.getStack().push(redoStack.pop()));
		}
	}
	
	
	//////////////////////////////////////////////////////////////////
	//																//
	//	Private Methods 											//
	//																//
	//////////////////////////////////////////////////////////////////
	
	private void resetBoard() {
		setCommandImmediately(myResources.getString("ClearScreen").split("\\|")[0]);
		setCommandImmediately(myResources.getString("Home").split("\\|")[0]);
		setCommandImmediately(myResources.getString("SetHeading").split("\\|")[0]+ " 0");
	}
	
	//////////////////////////////////////////////////////////////////
	//																//
	//	User Input Area Initializers								//
	//																//
	//////////////////////////////////////////////////////////////////
	
	private void initializeNonButtonAreas(double prefWidth, double prefHeight) {
		HBox inputAreaPane = new HBox();
		initializeHistory(prefWidth,prefHeight);
		initializeTextArea(prefWidth,prefHeight);
		initializeUserCommandsArea(prefWidth,prefHeight);
		initializeUserVariablesArea(prefWidth,prefHeight);
		
		inputAreaPane.getChildren().addAll(myHistory.getListView(),
				commandLine,
				myUserDefinedCommands,
				myVariables.getTableView());
		myArea.getChildren().add(inputAreaPane);
	}

	private void initializeTextArea(double prefWidth, double prefHeight){
		commandLine = new TextArea();
		commandLine.setPrefWidth(prefWidth*TEXTAREA_WIDTH_PERCENTAGE);
		commandLine.setPrefHeight(TEXTAREA_HEIGHT_PERCENTAGE*prefHeight);
		commandLine.setWrapText(false);
	}
	private void initializeHistory(double prefWidth, double prefHeight) {
		myHistory=new SLogoHistoryListView(this);
		myHistory.getListView().setPrefWidth(HISTORY_WIDTH_PERCENTAGE*prefWidth);
		myHistory.getListView().setPrefHeight(TEXTAREA_HEIGHT_PERCENTAGE*prefHeight);
	}
	
	private void initializeUserCommandsArea(double prefWidth, double prefHeight) {
		myUserDefinedCommands = new SLogoDefinedCommandView(this); 
		myUserDefinedCommands.setPrefWidth(USERCOMMAND_WIDTH_PERCENTAGE*prefWidth);
		myUserDefinedCommands.setPrefHeight(TEXTAREA_HEIGHT_PERCENTAGE*prefHeight);
		
		myModel.getCommands().addListener(myUserDefinedCommands.getMapListener());
	}
	
	private void initializeUserVariablesArea(double prefWidth, double prefHeight){
		myVariables = new SLogoVariableTable(this);
		myVariables.getTableView().setPrefHeight(TEXTAREA_HEIGHT_PERCENTAGE*prefHeight);
		myVariables.getTableView().setPrefWidth((VARIABLE_WIDTH_PERCENTAGE*prefWidth)-BORDER_BUFFER);
		
		myModel.getVariables().addListener(myVariables.getMapListener());
	}
	
	private void initializeButtons(double prefWidth, double prefHeight) {
		FlowPane buttonFlow = new FlowPane();
		VBox saveUserFunctionsVBox=initializeSaveVBox(prefWidth,prefHeight,FUNCTIONS_EXTENSION);
		Button loadUserFunctions = makeButton(LOAD_USER_FUNCTIONS,e->chooseFile(), SAVE_LOAD_BUTTON_WIDTH_PERCENTAGE*prefWidth,BUTTON_HEIGHT_PERCENTAGE*prefHeight);

		Button submitCommandButton = makeButton(SUBMIT,e->pressSubmitAction(),TEXTAREA_WIDTH_PERCENTAGE*prefWidth,BUTTON_HEIGHT_PERCENTAGE*prefHeight);
		VBox saveHistoryVBox = initializeSaveVBox(prefWidth, prefHeight, HISTORY_EXTENSION);
		Button loadButton = makeButton(LOAD_HISTORY, e->chooseFile(),SAVE_LOAD_BUTTON_WIDTH_PERCENTAGE*prefWidth,BUTTON_HEIGHT_PERCENTAGE*prefHeight);

		Button paletteButton = makePaletteVisualizerButton();
		
		buttonFlow.getChildren().addAll(saveHistoryVBox,loadButton,submitCommandButton,saveUserFunctionsVBox,loadUserFunctions,paletteButton);
		myArea.getChildren().add(buttonFlow);
	}

	private Button makePaletteVisualizerButton() {
		SLogoPaletteVisualizer paletteVisuals = new SLogoPaletteVisualizer();
		myModel.getPalette().addListener(paletteVisuals.getMapListener());
		Button paletteButton = makeButton(COLOR_PALETTE, e->paletteVisuals.showPalette(true));
		populateBasicPalette();
		return paletteButton;
	}
	private void populateBasicPalette(){
		for(int i=0;i<INITIAL_PALETTE.length;i++) setCommandImmediately(myResources.getString("SetPalette").split("\\|")[0] + " "+ INITIAL_PALETTE[i]);
	}
	
	private VBox initializeSaveVBox(double prefWidth, double prefHeight, String type) {
		VBox saveVBox = new VBox();
		TextField saveFileName = new TextField();
		saveFileName.setPrefWidth(SAVE_LOAD_BUTTON_WIDTH_PERCENTAGE*prefWidth);
		saveFileName.setPrefHeight((BUTTON_HEIGHT_PERCENTAGE/2)*prefHeight);
		Button saveButton = makeButton("Save "+type, e->saveFile(type,saveFileName.getText()),SAVE_LOAD_BUTTON_WIDTH_PERCENTAGE*prefWidth,(BUTTON_HEIGHT_PERCENTAGE/2)*prefHeight);
		saveVBox.getChildren().add(saveFileName);
		saveVBox.getChildren().add(saveButton);
		return saveVBox;
	}

	//////////////////////////////////////////////////////////////////
	//																//
	//	Parsing 													//
	//																//
	//////////////////////////////////////////////////////////////////
	
	private void initializeParser() {
		backEndParser = new Parser(myModel);
		backEndParser.addPatterns("resources/languages/"+currentLanguage);
		backEndParser.addPatterns("resources/languages/Syntax");
	}
	
	private void pressSubmitAction(){
		String totalInput = replaceNewLine(commandLine.getText());
		
		if(!totalInput.isEmpty()){
			if(redoStack!=null) redoStack.clear(); 
			currentCommand = totalInput.split(" ");
			boolean currentCommandGood=parseText(backEndParser, currentCommand);
			myHistory.addCommandToList(commandLine.getText(),currentCommandGood);
			commandLine.clear();
		}
	}

	private boolean parseText (Parser myParser, String[] text) {
		String errorCheckingString;
		for (String s : text) {
			if (s.trim().length() > 0) {
				if((errorCheckingString=myParser.checkCommand(s.trim())) !=s.trim()){
					showAlert(errorCheckingString);
					return false;
				}
			}
		}
		return true;
	}

	private String replaceNewLine(String s) {
		char[] toCheck = s.toCharArray();
		StringBuffer toReturn = new StringBuffer();
		for(int i=0;i<toCheck.length;i++){
			if(toCheck[i]=='\n') toCheck[i]=' ';
		}
		toReturn.append(toCheck);
		return toReturn.toString();
	}

	//////////////////////////////////////////////////////////////////
	//																//
	//	File IO	| See superclass for documentation					//
	//																//
	//////////////////////////////////////////////////////////////////
	
	
	public void saveHistoryFile(PrintWriter fileWriter){
		for(String historyElement : myHistory.getStack()){
			fileWriter.println(historyElement);
			fileWriter.println(NEW_ELEMENT_MAGIC_PHRASE);
		}
	}
	
	public void readHistoryFile(String oneHistoryElement){
		myHistory.addCommandToList(oneHistoryElement, true);
	}
	
	public void readLOGOFile(String oneLOGOElement){
		if(oneLOGOElement.startsWith("null")){
			oneLOGOElement=oneLOGOElement.substring(4);
		}
		setCommand(oneLOGOElement);
	}
	
	public void saveFunctionsFile(PrintWriter fileWriter){
		for(TreeItem<String> currentFunction : myUserDefinedCommands.getRoot().getChildren()){
			fileWriter.println(NEW_ELEMENT_MAGIC_PHRASE);
			fileWriter.println(currentFunction.getValue());
			fileWriter.println(currentFunction.getChildren().get(0).getValue());
		}
	}
	
	public void readFunctionsFile(String functionName, String variableValue, String functionValue) {
		setCommandImmediately(myResources.getString("MakeUserInstruction")+" "+functionName + " [ "+variableValue +  " ] [ " + functionValue + " ] ");
	}
	
	/**
	 * Should be refactored
	 * @deprecated Use savePreferenceFile, but not this one. 
	 */
	public void savePreferencesFile(PrintWriter fileWriter){
		
	}
	
	public void readPreferencesFile(String nextLine, boolean firstTime){
		if(firstTime){
			setCommandImmediately(nextLine);
		}else{
			updateLanguage(nextLine.split("/")[nextLine.split("/").length-1]);
		}
	}

	//////////////////////////////////////////////////////////////////
	//																//
	//	Deprecated Methods 											//
	//																//
	//////////////////////////////////////////////////////////////////

	/**
	 * @deprecated
	 */
	public boolean isCommandReady() {
		return false;
	}

	/**
	 * @deprecated
	 */
	public void setCommandReady(boolean isCommandReady) {
		commandLine.clear();
	}
	
	


}