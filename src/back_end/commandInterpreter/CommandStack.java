package back_end.commandInterpreter;
import java.util.Stack;
import back_end.commands.ListCommand;
import back_end.commands.abstractCommands.interfaces.Executable;
import back_end.commands.abstractCommands.interfaces.Stackable;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 *
 * Purpose: The main purpose of this class is to store commands that are created by the command factory. The command stack has an instance of the model that is passed from the parser to
 * 		execute commands when they are popped out of the stack. The command stack also has a list command stack that keeps track of the list commands in the stack.
 * Assumptions: The assumption is that the command factory passes a stackable object to the stack. If they do not provide the right command the command stack will not be able to add commands.
 * Dependencies: This class is created by the parser class and requires the parser to use the command factory and pass the command to this command stack class.
 * Examples:
 * Additional Details: The command stack uses the stack database to store commands. It allows for recursion.
 *
 * @author DavidYoon
 *
 */
public class CommandStack {
	private Stack<Stackable> stack;
	private Model model;
	private Stack<ListCommand> listCommandStack;

	public CommandStack(Model model) {
		stack = new Stack<Stackable>();
		this.model = model;
		this.listCommandStack = new Stack<ListCommand>();
	}

	public void addCommand(Executable command){
		if (command.isExecutable()){
			addCommandToListCommand(command);
			if (listCommandStack.isEmpty()) {
				execute(command);
			}
		}
		else {
			stack.push(command);
		}
	}

	public void addCommand(Storable command){
		if (command.isStorable()){
			if (!stack.isEmpty()){
				addCommandToListCommand(command);
				if (stack.peek() instanceof Executable){
					Executable poppedCommand = (Executable) stack.pop();
					poppedCommand.addStorable(command);
					addCommandToListCommand(command);
					addCommand(poppedCommand);
				}
				else if (command instanceof ListCommand){
					command.execute(model);
				}
			}
		}
		else {
			stack.push(command);
		}
	}

	private void addCommandToListCommand(Stackable command) {
		if (!listCommandStack.isEmpty()){
			listCommandStack.peek().addCommands(command);
		}
	}

	private void execute(Stackable command) {
		addCommand(command.execute(model));
		model.notifyObservers();
	}

	public void clearStacks(){
		stack.clear();
		listCommandStack.clear();
	}

	public Stack<Stackable> getStack() {
		return stack;
	}

	public Stack<ListCommand> getListCommandStack() {
		return listCommandStack;
	}

}