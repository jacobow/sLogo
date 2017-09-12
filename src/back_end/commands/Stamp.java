package back_end.commands;

import java.util.List;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.commands.abstractCommands.interfaces.Executable;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

public class Stamp extends ExecutableCommand{

	public Stamp(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Storable execute(Model model) {
		return new Constant(model.addStamp());
	}

}
