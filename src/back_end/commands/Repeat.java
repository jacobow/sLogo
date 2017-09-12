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
public class Repeat extends RepetitionCommand {

	public Repeat(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Storable execute(Model model) {
		System.out.println(getStorableArray()[1]);
		System.out.println();
		List<Stackable> commandList = getStorableArray()[1].getCommandList();
		for (int i=0; i<getValueArray()[0]; i++){
			getRepeatedList().addAll(commandList);
		}
		for (Stackable executable: getRepeatedList()){
			executable.execute(model);
		}
		return new Constant(0);
	}

}