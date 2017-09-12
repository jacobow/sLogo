package application.gui;

import java.util.Stack;

import application.gui.interfaces.CommandLine;
import application.gui.interfaces.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * 
 * @author walker eacho
 *
 */
public class SLogoHistoryListView implements History {
	
	ListView<String> myListView;
	Stack<String> commandList;
	private ObservableList<String> commands = FXCollections.observableArrayList();
	CommandLine myCommandLine;

	SLogoHistoryListView(CommandLine commandLine){
		myCommandLine=commandLine;
		
		myListView = new ListView<String>();
		commandList = new Stack<String>();
		commands.addAll(commandList);
		myListView.setItems(commands);
		
		myListView.setOnMouseClicked(e->setOnMouseClick(e));
	}

	public void clearList() {
		commandList.clear();
	}
	
	/**
	 * Encapsulates the list view from the rest of the historical functions 
	 * @return
	 */
	public ListView<String> getListView(){
		return myListView;
	}

	public Stack<String> getStack() {
		return commandList;
	}


	public String getElementAtIndex(int index) {
		return commandList.get(index);
	}


	public String getLast() {
		return commandList.get(commandList.size()-1);
	}


	public int getSize() {
		return commandList.size();
	}


	public void addCommandToList(String command, boolean currentCommandGood) {
		if(currentCommandGood){
			commandList.add(command);
			commands.add(0,command);
		}else{
			commands.add(0, "**"+command);

		}
	}
	
	private void setOnMouseClick(MouseEvent e) {
		if(myCommandLine.getCommand().length!=0){
			myCommandLine.setCommand("\n");
		}
		if(myListView.getSelectionModel().getSelectedItem()!=null){
			myCommandLine.setCommand(myListView.getSelectionModel().getSelectedItem());
		}
	}



}
