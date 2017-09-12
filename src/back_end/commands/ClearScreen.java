package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

/**
 * @author DavidYoon
 *
 */
public class ClearScreen extends ExecutableCommand {

	public ClearScreen(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		double distance = 0;
		for(Turtle turtle : model.getTurtles()) {
			if(turtle.getState().isActive()) {
				distance = turtle.clear();
			}
			model.setClear(true);
		}
		return new Constant(distance);
	}

}