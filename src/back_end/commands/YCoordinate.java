package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

public class YCoordinate extends ExecutableCommand {

	public YCoordinate(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		double y = 0;
		for(Turtle t: model.getTurtles()) {
			if(t.getState().isActive()) y = t.getY();
		}
		return new Constant(y);
	}

}