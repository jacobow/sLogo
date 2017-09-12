package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class IfElse extends ExecutableCommand {

	public IfElse(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		if (getStorableArray()[0].getValue() != 0) getStorableArray()[1].execute(model);
		else getStorableArray()[2].execute(model);
		return new Constant(0);
	}

}
