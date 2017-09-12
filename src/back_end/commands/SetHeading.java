package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

public class SetHeading extends ExecutableCommand {

	public SetHeading(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		for(Turtle turtle : model.getTurtles()) {
			if(turtle.getState().isActive()) turtle.setHeading(getValueArray()[0]);
		}
		return new Constant(getValueArray()[0]);
	}

}