package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

public class IsShowing extends ExecutableCommand {

	public IsShowing(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		double showing = 0;
		for(Turtle t: model.getTurtles()) {
			if(t.getState().isActive() && t.getState().isVisible().getValue()) showing = 1;
		}
		return new Constant(showing);
	}

}