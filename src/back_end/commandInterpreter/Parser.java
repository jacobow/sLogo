package back_end.commandInterpreter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import back_end.commands.Constant;
import back_end.commands.ListCommand;
import back_end.commands.MakeUserInstruction;
import back_end.commands.MakeVariable;
import back_end.commands.Variable;
import back_end.commands.abstractCommands.interfaces.Executable;
import back_end.commands.abstractCommands.interfaces.Stackable;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * 
 * Purpose: The parser checks for the validity of the user input in the command line using the checkCommand method. If the user input is valid, it creates appropriate commands through 
 * 		the command factory and put them into the command stack.The parser adds regex patterns for different languages.
 * Assumptions: One of the main assumption is that the command line will parse the user input into individual string. Also, the command line needs to add the patterns of the different languages after it initializes the parser.
 * Dependencies: The parser needs to be initialized and passed Strings to do its job. It also needs a resource bundle file to match regex expressions.
 * Examples: If the user inputs 'fd 50', the parser recognizes it as two inputs, one 'fd' and '50'. THe parser recognizes the first expression through the pattern it stored and returns "Forward"
 * 		the parser then calls the command factory to create the forward command and pushes it to the command stack. The '50' is recognized as a number and a constant command is created and passed to the command stack.
 * Additional Details: The parser initializes the command factory which creates commands, and the command stack which stores created commands in a stack.
 * 
 * @author DavidYoon
 *
 */
public class Parser {
	private static final String UNABLE_TO_INSTANTIATE_COMMAND = "Unable to Instantiate Command";
	private static final String CONSTRUCTOR_NOT_FOUND = "Constructor Not Found";
	private static final String COMMAND_NOT_FOUND = "Command Not Found";
	private static final boolean debuggingMode = false;
	private List<Entry<String, Pattern>> mySymbols;
	private CommandFactory commandFactory;
	private CommandStack commandStack;
	private Model model;
	private Map<String, Executable> commandMap;

	public Parser(Model model) {
		mySymbols = new ArrayList<>();
		commandStack = new CommandStack(model);
		commandFactory = new CommandFactory();
		this.model = model;
		commandMap = new HashMap<String, Executable>();
	}

	public void addPatterns (String syntax) {
		ResourceBundle resources = ResourceBundle.getBundle(syntax);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}

	public String checkCommand(String userInput){
		for (Entry<String, Pattern> e : mySymbols) {
			if (match(userInput, e.getValue())) {
				if (e.getKey().equals("ListStart")) addListCommand();
				else if (e.getKey().equals("ListEnd")) completeListCommand();
				else if (e.getKey().equals("Variable")) addVariable(userInput);
				else if (e.getKey().equals("MakeUserInstruction")) addUserCommand(userInput);
				else if (e.getKey().equals("Command")) addUserDefinedCommand(userInput);
				else {
					try {
						Stackable command = commandFactory.createCommand(e.getKey(), userInput);
						if (command instanceof Storable){
							commandStack.addCommand((Storable) command);
						}
						if (command instanceof Executable){
							commandStack.addCommand((Executable) command);
						}
						if (debuggingMode) System.out.println(commandStack.getStack());
					} catch (ClassNotFoundException e1) {
						commandStack.clearStacks();
						return COMMAND_NOT_FOUND;
					} catch (NoSuchMethodException e1) {
						commandStack.clearStacks();
						return CONSTRUCTOR_NOT_FOUND;
					} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
						commandStack.clearStacks();
						return UNABLE_TO_INSTANTIATE_COMMAND;
					}
				}
				return userInput;
			}
		}
		return userInput;
	}

	private void addUserCommand(String userInput) {
		MakeUserInstruction makeUserInstruction = new MakeUserInstruction(userInput, commandMap);
		commandStack.addCommand(makeUserInstruction);
	}

	private void addUserDefinedCommand(String commandName) {
		if (commandMap.containsKey(commandName)){
			Executable command = commandMap.get(commandName);
			commandStack.addCommand(command);
		}
		else if (commandStack.getStack().peek() instanceof MakeUserInstruction){
			Variable variable = new Variable(commandName);
			commandStack.addCommand(variable);
		}
	}
	
	private void addVariable(String commandName) {
		if (model.getVariables().containsKey(commandName)){
			Variable variable = new Variable(commandName, model.getVariableValue(commandName));
			commandStack.addCommand(new Constant(variable.getValue()));
		}
		else{
			if (!(commandStack.getStack().peek() instanceof MakeVariable)){
				commandStack.addCommand(new MakeVariable());
			}
			Variable variable = new Variable(commandName);
			commandStack.addCommand(variable);
		}
	}

	private void addListCommand() {
		ListCommand listCommand = new ListCommand();
		commandStack.getStack().push(listCommand);
		commandStack.getListCommandStack().add(listCommand);
	}
	
	private void completeListCommand() {
		if (!commandStack.getStack().isEmpty()){
			while (!(commandStack.getStack().peek() instanceof ListCommand)){
				commandStack.getStack().pop().execute(model);
				if (commandStack.getStack().isEmpty()) return;
			}
			ListCommand listCommand = (ListCommand) commandStack.getStack().pop();
			commandStack.getListCommandStack().pop();
			listCommand.setStorable(true);
			commandStack.addCommand(listCommand);
		}
	}

	private boolean match (String text, Pattern regex) {
		return regex.matcher(text).matches();
	}
}
