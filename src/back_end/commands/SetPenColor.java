package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

public class SetPenColor extends ExecutableCommand {


	public SetPenColor(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		for(Turtle t : model.getTurtles()) {
			if(t.getState().isActive()) t.getState().setPenColor(getValueArray()[0]);
		}
		return new Constant(getValueArray()[0]);
	}
}
