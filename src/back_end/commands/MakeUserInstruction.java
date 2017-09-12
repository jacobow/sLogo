package back_end.commands;

import java.util.List;
import java.util.Map;
import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.commands.abstractCommands.interfaces.Executable;
import back_end.commands.abstractCommands.interfaces.Stackable;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class MakeUserInstruction extends ExecutableCommand {

	private Map<String, Executable> commandMap;

	public MakeUserInstruction(String userInput, Map<String, Executable> commandMap) {
		super(3, userInput);
		this.commandMap = commandMap;
	}

	@Override
	public Storable execute(Model model) {
		Storable variable = getStorableArray()[0];
		List<Stackable> variableList = getStorableArray()[1].getCommandList();
		Storable commandList =  getStorableArray()[2];
		Executable executable = new UserDefinedExecutableCommand(variableList, variable.getName(), commandMap, commandList); 
		model.updateCommand(variable.getName(), commandList.toStringList().subList(0, commandList.toStringList().size()-1));
		
		commandMap.put(variable.getName(), executable);
		
		return commandList;
	}

}