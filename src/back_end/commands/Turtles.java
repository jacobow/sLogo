package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;

public class Turtles extends ExecutableCommand {

	public Turtles(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		return new Constant(model.getTurtles().size());
	}

}
