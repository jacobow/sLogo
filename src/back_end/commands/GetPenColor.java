package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.model.Model;
import back_end.model.Turtle;

public class GetPenColor extends ExecutableCommand {

	public GetPenColor(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		double index = -1;
		for(Turtle t : model.getTurtles()) {
			if(t.getState().isActive()) index = t.getState().getPenColorIndex();
		}
		return new Constant(index);
	}

}
