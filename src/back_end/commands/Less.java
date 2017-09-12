package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class Less extends ExecutableCommand{

	public Less(int arraySize, String userInput) {
		super(arraySize, userInput);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Storable execute(Model model) {
		if(getValueArray()[0] < getValueArray()[1]) return new Constant(0);
		else return new Constant(1);
	}

}
