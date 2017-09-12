package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

public class Heading extends ExecutableCommand {

	public Heading(int arraySize, String userInput) {
		super(arraySize, userInput);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Constant execute(Model model) {
		double heading = 0;
		for(Turtle t: model.getTurtles()) {
			if(t.getState().isActive()) heading = t.getOrientation();
		}
		return new Constant(heading);
	}

}