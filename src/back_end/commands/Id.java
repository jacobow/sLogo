package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

public class Id extends ExecutableCommand {

	public Id(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		double index = -1;
		for(Turtle t : model.getTurtles()) {
			if(t.getState().isActive()) index = t.getID();
		}
		return new Constant(index);
	}

}
