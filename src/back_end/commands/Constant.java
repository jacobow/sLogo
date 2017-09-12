package back_end.commands;

import back_end.commands.abstractCommands.StorableCommand;

/**
 * @author DavidYoon
 *
 */
public class Constant extends StorableCommand {
	
	public Constant(int arraySize, String userInput) {
		super(userInput);
		setValue(Double.parseDouble(userInput));
		setStorable(true);
	}

	public Constant(double value) {
		this.setValue(value);
		setStorable(true);
	}

	public Constant() {
		super();
		this.setValue(0);
	}

	
}
