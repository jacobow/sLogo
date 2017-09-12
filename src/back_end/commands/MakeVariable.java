package back_end.commands;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class MakeVariable extends ExecutableCommand {
	
	public MakeVariable(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	public MakeVariable() {
		super(1, "");
	}
	
	@Override
	public Storable execute(Model model) {
		Storable variable = getStorableArray()[0];
		if (getStorableArray().length>1) {
			Storable constant = getStorableArray()[1];
			variable.setValue(constant.getValue());
		}
		model.updateVariable(variable.getName(), variable.getValue());
		return variable;
	}
}
