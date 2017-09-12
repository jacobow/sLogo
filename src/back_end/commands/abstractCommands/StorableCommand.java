package back_end.commands.abstractCommands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import back_end.commands.abstractCommands.interfaces.Stackable;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * Purpose: This StorableCommand class inherits from the StackableCommand and implements the Storable interface. The main objective of this class is to
 * 		serve as a command that can be stored by the executable commands.
 * Assumptions: The assumption is that all storableCommands have a commandList with them. They might not use the commandList but if they are listCommands,
 * 		they will use these to store commands.
 * Dependencies: This class depends on the command factory and the command stack to be created and stored respectively.
 * Examples: There are two types of StorableCommands - a listCommand and a constant. 
 * 
 * @author DavidYoon
 *
 */
public abstract class StorableCommand extends StackableCommand implements Storable {

	private boolean storable;
	private List<Stackable> commandList;

	
	public StorableCommand(String userInput) {
		super(0, userInput);
		setCommandList(new ArrayList<Stackable>());
	}

	public StorableCommand() {
		super(0, "");
		setStorable(false);
		setCommandList(new ArrayList<Stackable>());
	}

	public boolean isExecutable(){
		return false;
	}

	public boolean isStorable() {
		return storable;
	}

	public void setStorable(boolean storable) {
		this.storable = storable;
	}

	@Override
	public Storable execute(Model model) {
		return this;
	}

	@Override
	public List<String> toStringList() {
		List<String> list = new ArrayList<String>();
		list.add(getName());
		return list;
	}

	public List<Stackable> getCommandList(){
		return commandList;
	}

	public void setCommandList(List<Stackable> commandList) {
		this.commandList = commandList;
	}
}