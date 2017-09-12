package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

/**
 * @author DavidYoon
 *
 */
public class Left extends ExecutableCommand {

	public Left(int arraySize, String userInput) {
		super(arraySize, userInput);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Constant execute(Model model) {
		for(Turtle turtle : model.getTurtles()) {
			if(turtle.getState().isActive()) turtle.turnLeft(getValueArray()[0]);
		}
		return new Constant(getValueArray()[0]);
	}

}