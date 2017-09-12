package back_end.commands;

import java.util.ArrayList;
import java.util.List;
import back_end.commands.abstractCommands.RepetitionCommand;
import back_end.commands.abstractCommands.interfaces.Stackable;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class For extends RepetitionCommand {


	public For(int arraySize, String userInput) {
		super(arraySize, userInput);
		setRepeatedList(new ArrayList<Stackable>());
	}

	@Override
	public Storable execute(Model model) {
		List<Stackable> variableList = getStorableArray()[0].getCommandList();
		List<Stackable> commandList = getStorableArray()[1].getCommandList();

		for (int i=(int) variableList.get(0).getValue(); i <  variableList.get(1).getValue(); i+= (int)variableList.get(2).getValue()){
			getRepeatedList().addAll(commandList);
		}
		for (Stackable executable: getRepeatedList()){
			executable.execute(model);
		}
		return new Constant(0);
		}

}
