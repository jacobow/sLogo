package application.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Extends SLogoVBox. Added functionality of implementing a file chooser
 * @author walker
 *
 */
public abstract class SLogoUIAreaWithFileIO extends SLogoUIArea{
	public final String NEW_ELEMENT_MAGIC_PHRASE = "Happy Birthday Walker & Michael";
	public final String NEW_ELEMENT_MAGIC_PHRASE_2 = "Hail to the Redskins";
	private final int PREFERENCES_FILE=0;
	private final int LOGO_FILE=PREFERENCES_FILE+1;
	private final int PREFERENCES_FILE_REPRISE = LOGO_FILE+1;
	private final int HISTORY_FILE =PREFERENCES_FILE_REPRISE+1;
	private final int FUNCTION_FILE=HISTORY_FILE+1;
	private final int SKIP_TO_LOGO = FUNCTION_FILE+1;

	public final String DEFAULT_FILE_LOCATION = "defaultsettings";

	public final String HISTORY_EXTENSION = ".lgh";
	public final String FUNCTIONS_EXTENSION = ".lgf";
	public final String PREFERENCES_EXTENSION = ".lgp";

	private int fileType;

	/**
	 * Returns a JavaFX formatted filechooser with a label 
	 * @param label
	 * @return Label and Button Combination
	 */
	public VBox initializeFileChooser(String label) {
		VBox fileChooserVBox = new VBox();
		Button chooseFileBtn = makeButton("Choose File", e->chooseFile());
		fileChooserVBox.getChildren().add(new Label(label));
		fileChooserVBox.getChildren().add(chooseFileBtn);
		return fileChooserVBox;

	}
	
	/**
	 * Used for non-preference file choosing
	 */
	public void chooseFile(){
		chooseFile(false);
	}
	
	/**
	 * Brings up a filechooser to load information
	 * @param isForPrefFile
	 */
	public void chooseFile(boolean isForPrefFile){
		FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();
		File newFile = fileChooser.showOpenDialog(stage);
		try{
			if(checkFileType(newFile)==SKIP_TO_LOGO || !isForPrefFile){
				loadFile(newFile);
			}else{
				showFileAlert();
			}
		}catch(NullPointerException npe){
			System.out.println("Image npe");
			showFileAlert();
		}catch(IllegalArgumentException iae){
			System.out.println("Illegal argument exception");
			showFileAlert();
		}
	}
	/**
	 * Reads information from a file
	 * @param newFile
	 * @return
	 */
	boolean loadFile(File newFile){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(newFile));
			fileType = checkFileType(newFile);
			String functionName="";
			String functionValue ="";
			String nextLine="";
			boolean firstTime = true;
			readLines(functionName,functionValue,nextLine,reader,firstTime, fileType);
			reader.close();
		} catch (FileNotFoundException FNFe) {
			showFileAlert();
		} catch(IOException ioe){
			showFileAlert();
		}
		return false;
	}

	private void readLines(String functionName, String functionValue, String nextLine,BufferedReader reader,boolean firstTime, int fileType) throws IOException{
		boolean skip=false;
		while((nextLine=reader.readLine())!=null){
			if(fileType<=PREFERENCES_FILE_REPRISE){
				functionValue = readPreferenceLines(nextLine,skip,functionValue,reader);
			}else if(fileType==SKIP_TO_LOGO){
				readLOGOFile(nextLine);
			}else if(fileType==FUNCTION_FILE){
				if(nextLine.contains(NEW_ELEMENT_MAGIC_PHRASE)){
					firstTime=true;
					functionName = reader.readLine();
					functionValue = reader.readLine();
				}
				readFunctionsFile(functionName," ",functionValue);
			}else if(fileType==HISTORY_FILE){
				if(nextLine.contains(NEW_ELEMENT_MAGIC_PHRASE)){
					readHistoryFile(functionValue);
					functionValue="";
				}else{
					functionValue+=nextLine+"\n";
				}
			}
		}

		//if(fileType==FUNCTION_FILE) readFunctionsFile(functionName,functionValue,null,false);
		if(fileType==HISTORY_FILE) readHistoryFile(functionValue);

	}
	
	private String readPreferenceLines(String nextLine,boolean skip,String functionValue,BufferedReader reader) throws IOException{
		boolean firstTime=true;
		if(nextLine.contains(NEW_ELEMENT_MAGIC_PHRASE_2)){
			fileType+=1;
			nextLine = reader.readLine();
			functionValue ="";
			if(fileType==PREFERENCES_FILE_REPRISE) firstTime=false;
		}
		if(fileType==PREFERENCES_FILE || fileType==PREFERENCES_FILE_REPRISE) readPreferencesFile(nextLine,firstTime);

		if(fileType==LOGO_FILE){
			readLOGOFile(functionValue);
		}
		functionValue="";
		if(!nextLine.contains(NEW_ELEMENT_MAGIC_PHRASE_2)) functionValue+=nextLine+"\n";
		return functionValue;
	}

	private int checkFileType(File newFile) {
		int fileType=PREFERENCES_FILE;
		if(newFile.toString().endsWith(".logo")){
			fileType=SKIP_TO_LOGO;
		}else if(newFile.toString().endsWith(HISTORY_EXTENSION)){
			fileType=HISTORY_FILE;
		}else if(newFile.toString().endsWith(FUNCTIONS_EXTENSION)){
			fileType=FUNCTION_FILE;
		}
		return fileType;
	}

	/**
	 * Used for reading history files into the existing history
	 * @param oneHistoryElement
	 */
	public abstract void readHistoryFile(String oneHistoryElement);
	/**
	 * Used for reading function files into the existing functions section
	 *  
	 * @param functionName 
	 * @param variableValue
	 * @param functionValue
	 * @throws IOException Caught by file loader
	 */
	public abstract void readFunctionsFile(String functionName,String variableValue, String functionValue) throws IOException;
	/**
	 * Used to read preference files into the program
	 * 
	 * @param nextLine
	 * @param firstTime
	 */
	public abstract void readPreferencesFile(String nextLine, boolean firstTime);
	/**
	 * Used to read in LOGO files
	 * @param oneLOGOElement
	 */
	public abstract void readLOGOFile(String oneLOGOElement);

	/**
	 * Used to save file
	 * @param type - Use the public file extensions
	 * @param outputFile - Name of the output file sans extension
	 */
	public void saveFile(String type,String outputFile) {
		try {
			PrintWriter fileWriter= new PrintWriter(outputFile+type,"UTF-8");
			if(type.equals(HISTORY_EXTENSION)){
				saveHistoryFile(fileWriter);
			}else if(type.equals(FUNCTIONS_EXTENSION)){
				saveFunctionsFile(fileWriter);
			}else if(type.equals(PREFERENCES_EXTENSION)){
				savePreferencesFile(fileWriter);
			}
			fileWriter.close();

		} catch (FileNotFoundException e) {
			//	showSaveFileAlert();
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			//	showSaveFileAlert();
			e.printStackTrace();
		}

	}
	/**
	 * Used to save existing preferences
	 * @param fileWriter
	 */
	public abstract void savePreferencesFile(PrintWriter fileWriter);
	
	/**
	 * Used to save functions to file
	 * @param fileWriter
	 */
	
	public abstract void saveFunctionsFile(PrintWriter fileWriter);

	/**
	 * Used to save history to file
	 * @param fileWriter
	 */
	public abstract void saveHistoryFile(PrintWriter fileWriter);

	/**
	 * Shows file alert with specific string
	 * @param alertString
	 */
	public void showAlert(String alertString){
		Alert alert = new Alert(AlertType.INFORMATION);
		final String title = "Error";
		final String header = alertString;
		final String alertTxt = "sorry";
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(alertTxt);
		alert.show();
	}
	
	/**
	 * Default file alert string set
	 */
	public void showFileAlert () {
		final String alertString = "File IO Error";
		showAlert(alertString);
	}
}
