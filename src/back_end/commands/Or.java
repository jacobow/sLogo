package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class Or extends ExecutableCommand {

	public Or(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Storable execute(Model model) {
		if (getValueArray()[0] !=0 || getValueArray()[1] !=0){
			return new Constant(1);
		}
		return new Constant(0);
	}

}
