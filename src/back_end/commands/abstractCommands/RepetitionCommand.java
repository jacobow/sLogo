package back_end.commands.abstractCommands;

import java.util.ArrayList;
import java.util.List;

import back_end.commands.Constant;
import back_end.commands.abstractCommands.interfaces.Stackable;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * Purpose: The repetition Command is an extension of the executableCommand class. This class has a repeatedList which it uses to populate its own list of commands to execute.
 * Assumptions: The logic of the repetitions is to store the commands that you are planning to use, copy them x number of times and execute that entire list of commands.
 * Dependencies: This command relies on the command factory and the command stack to be created and executed respectively.
 * Examples: An example of this class is the For Command and the Repeat Command classes.
 * 
 * @author DavidYoon
 *
 */
public abstract class RepetitionCommand extends ExecutableCommand {

	private List<Stackable> repeatedList;

	public RepetitionCommand(int arraySize, String userInput) {
		super(arraySize, userInput);
		repeatedList = new ArrayList<Stackable>();
	}

	public List<Stackable> getRepeatedList() {
		return repeatedList;
	}

	public void setRepeatedList(List<Stackable> repeatedList) {
		this.repeatedList = repeatedList;
	}
	
	public Storable execute(Model model){
		for (Stackable executable: repeatedList){
			executable.execute(model);
		}
		return new Constant(0);
	}
	
}
