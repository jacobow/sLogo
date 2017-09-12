package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

public class SetPosition extends ExecutableCommand {

	public SetPosition(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		for(Turtle turtle : model.getTurtles()) {
			if(turtle.getState().isActive()) turtle.setXY(getValueArray()[0], getValueArray()[1]);
		}
		return new Constant(0);
	}

}