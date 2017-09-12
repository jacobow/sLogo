package back_end.commands;

import java.util.HashMap;
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
public class UserDefinedExecutableCommand extends ExecutableCommand {

	private Map<String, Executable> commandMap;
	private Storable commandList;
	private List<Stackable> variableList;
	private HashMap<String, Constant> variableMap;

	public UserDefinedExecutableCommand(List<Stackable> variableList, String userInput, Map<String, Executable> commandMap, Storable commandList) {
		super(variableList.size(), userInput);
		this.commandMap = commandMap;
		this.commandList = commandList;
		this.variableList = variableList;
		this.variableMap = new HashMap<String, Constant>();
	}

	@Override
	public Storable execute(Model model) {
		for (int i = 0; i< variableList.size(); i++){
			variableMap.put(variableList.get(i).getName(), new Constant(getStorableArray()[i].getValue()));
		}
		
		for (Stackable stackable : commandList.getCommandList()){
			if (commandMap.containsKey(stackable.getName())){
				commandMap.get(stackable.getName()).execute(model);
			}
			if (variableMap.containsKey(stackable.getName())){
				variableMap.get(stackable.getName()).execute(model);
			}
			else {
				stackable.execute(model);
			}
		}
		return new Constant();
	}

}
