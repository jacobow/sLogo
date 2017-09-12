package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

public class ClearStamps extends ExecutableCommand{

	public ClearStamps(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Storable execute(Model model) {
		if(model.clearStamps()) return new Constant(1);
		else return new Constant(1);
	}

}
