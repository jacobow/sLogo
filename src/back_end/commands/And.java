package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class And extends ExecutableCommand {


	public And(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Storable execute(Model model) {
		if (getValueArray()[0] !=0 && getValueArray()[1] !=0){
			return new Constant(1);
		}
		return new Constant(0);
	}

}
