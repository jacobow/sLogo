package application.gui;

import java.util.List;

import application.gui.interfaces.CommandLine;
import javafx.collections.MapChangeListener;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * View for commands
 * Choice not to encapsulate was done on purpose as there are no extra functions the class provides other than the changelistener
 * @author walker
 *
 */
public class SLogoDefinedCommandView extends TreeView<String>{
	
	private CommandLine myCommandLine;
	private TreeItem<String> commandTreeRoot;
	
	SLogoDefinedCommandView(CommandLine commandLine){
		myCommandLine=commandLine;
		commandTreeRoot = new TreeItem<String>("User Defined Functions");
		commandTreeRoot.setExpanded(true);		
		
		this.setShowRoot(true);
		this.setRoot(commandTreeRoot);
		this.setOnMouseClicked(e->initializeFunctionsOnClick());
	}

	private void initializeFunctionsOnClick(){
		TreeItem<String> selectedNode;
		if((selectedNode = this.getSelectionModel().getSelectedItem())!=null
				&& selectedNode.getParent()!=null && selectedNode.isLeaf()){
			if(selectedNode.getChildren().size()>0){
				selectedNode = selectedNode.getChildren().get(0);
			}
			if(myCommandLine.getCommand().length!=0){
				myCommandLine.setCommand("\n");
			}
			myCommandLine.setCommand(selectedNode.getValue());

		}
	}
	/**
	 * Gets changes to the backend map and applies them to the frontend 
	 * @return mapchangelistener
	 */
	MapChangeListener<String,List<String>> getMapListener(){
		return new MapChangeListener<String,List<String>>(){
			@Override
			public void onChanged(
					javafx.collections.MapChangeListener.Change<? extends String, ? extends List<String>> change) {
				if(change.wasAdded()){
					List<String> added = change.getValueAdded();
					update(change.getKey(),added);
				}

			}
		};
	}
	
	private void update(String changedKey, List<String> newList){
		boolean contained = false;
		for(TreeItem<String> currentCommand : commandTreeRoot.getChildren()){
			if(currentCommand.getValue().equals(changedKey)){
				//Command changed
				contained = true;
			}
		}
		if(!contained){
			TreeItem<String> newCommandName = new TreeItem<String>(changedKey);
			String function="";
			for(String asdf : newList){
				function += asdf + " ";
			}
			TreeItem<String> newCommandValue= new TreeItem<String>(function);
			commandTreeRoot.getChildren().add(newCommandName);
			newCommandName.getChildren().add(newCommandValue);
		}
	}

}
