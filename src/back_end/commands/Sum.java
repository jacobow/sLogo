package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class Sum extends ExecutableCommand{

	
	public Sum(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Storable execute(Model model) {
		return new Constant(getValueArray()[0]+getValueArray()[1]);
	}
	
}
