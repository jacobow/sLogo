package back_end.commandInterpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import back_end.commands.abstractCommands.interfaces.Stackable;

/**
 * Purpose: The purpose of the command factory is to create commands from the commandName information that the Parser passes through the createCommand method.
 * Assumptions: The assumption is that the parser will pass commandName information that is valid and will create a command. If the class or constructor of the command is not found, it will
 * 		throw an exception that will be resolved in the parser. 
 * Dependencies: The command factory is instantiated from the parser and relies on the resource bundle containing the number of constants needed for each commands. 
 * Additional Details: The command factory is created using the double locking mechanism which insures that there is only one instance of this factory class.
 * 
 * @author DavidYoon
 *
 */
public class CommandFactory {

	private static CommandFactory instance;
	private Map<String, Integer> constantMap;
	private String commandPath = "back_end.commands.";

	public CommandFactory() {
		addConstantsList();
	}
	
	/**
	 * Double locking mechanism for a singleton
	 * @return
	 */
	public static CommandFactory getInstance(){
		if (instance == null){
			synchronized(CommandFactory.class){
				if (instance == null){
					instance = new CommandFactory();
				}
			}            
		}
		return instance;
	}

	/**
	 * Creates a command using the commandName to find the number of parameter the method needs through the resource bundle and store the userInput into the command itself.
	 * @param commandName
	 * @param userInput
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Stackable createCommand(String commandName, String userInput) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		Class commandClass = Class.forName(commandPath + commandName);
		Constructor commandConstructor = commandClass.getDeclaredConstructor(int.class, String.class);
		Stackable command = (Stackable) commandConstructor.newInstance(constantMap.get(commandName), userInput);
		return command;
	}


	private void addConstantsList () {
		constantMap = new HashMap<String, Integer>();
		ResourceBundle resources = ResourceBundle.getBundle("resources/languages/Commands");
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			int numberOfConstants = Integer.parseInt(resources.getString(key));
			constantMap.put(key, numberOfConstants);
		}
	}
}
