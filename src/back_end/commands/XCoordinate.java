package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

public class XCoordinate extends ExecutableCommand {


	public XCoordinate(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		double x = 0;
		for(Turtle t: model.getTurtles()) {
			if(t.getState().isActive()) x = t.getX();
		}
		return new Constant(x);
	}

}
