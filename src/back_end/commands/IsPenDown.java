package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

public class IsPenDown extends ExecutableCommand {


	public IsPenDown(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		double penDownP = 0;
		for(Turtle t: model.getTurtles()) {
			if(t.getState().isActive() && !t.getState().isPenUp()) penDownP = 1;
		}
		return new Constant(penDownP);
	}

}