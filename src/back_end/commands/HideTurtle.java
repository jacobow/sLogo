package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

public class HideTurtle extends ExecutableCommand {

	public HideTurtle(int arraySize, String userInput) {
		super(arraySize, userInput);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Constant execute(Model model) {
		for(Turtle turtle : model.getTurtles()) {
			if(turtle.getState().isActive()) turtle.getState().hideTurtle();
		}
		return new Constant(0);
	}

}