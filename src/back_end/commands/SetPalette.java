package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;

public class SetPalette extends ExecutableCommand {


	public SetPalette(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		model.setPalette(getValueArray()[0],
						(int) getValueArray()[1],
						(int) getValueArray()[2],
						(int) getValueArray()[3]);
		return new Constant(getValueArray()[0]);
	}

}
