package back_end.commands.abstractCommands;

import back_end.commands.abstractCommands.interfaces.Stackable;

/**
 * The StackableCommand implements the Stackable interface and it is a super class for StorableCommands and ExecutableCommands.
 * The StackableCommand has a name and value and can perform any function that is required by the commandStack.
 * @author DavidYoon
 *
 */
public abstract class StackableCommand implements Stackable {

	private String name;
	private double value;

	public StackableCommand() {
	}

	public StackableCommand(int arraySize, String userInput) {
		setName(userInput);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}
