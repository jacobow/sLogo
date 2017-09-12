package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class ArcTangent extends ExecutableCommand {

	public ArcTangent(int arraySize, String userInput) {
		super(arraySize, userInput);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Storable execute(Model model) {
		return new Constant(Math.atan(getValueArray()[0]));
	}

}
