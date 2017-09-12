package back_end.commands;

import java.util.List;

import back_end.commands.abstractCommands.RepetitionCommand;
import back_end.commands.abstractCommands.interfaces.Stackable;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class DoTimes extends RepetitionCommand {

	public DoTimes(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Storable execute(Model model) {
		List<Stackable> variableList = getStorableArray()[0].getCommandList();
		List<Stackable> commandList = getStorableArray()[1].getCommandList();

		for (int i= 1; i < variableList.get(0).execute(model).getValue(); i++){
			getRepeatedList().addAll(commandList);
		}
		return new ListCommand(getRepeatedList());
	}

}
