package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;

public class SetBackground extends ExecutableCommand {


	public SetBackground(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		model.setBackgroundColor(getValueArray()[0]);
		return new Constant(getValueArray()[0]);
	}

}
