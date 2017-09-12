package back_end.commands;

import java.util.ArrayList;
import java.util.List;

import back_end.commands.abstractCommands.StorableCommand;
import back_end.commands.abstractCommands.interfaces.Stackable;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class ListCommand extends StorableCommand{

	private static final String LISTEND = "]";
	private static final String LISTSTART = "[";

	public ListCommand(int arraySize, String userInput) {
		super(userInput);
		setStorable(false);
	}

	public ListCommand(List<Stackable> listOfCommands) {
		setCommandList(listOfCommands);
		setStorable(true);
	}

	public ListCommand() {
		super();
		setName(LISTSTART);
	}

	public void addCommands(Stackable command){
		getCommandList().add(command);
	}

	@Override
	public double getValue() {
		return 0;
	}

	@Override
	public List<String> toStringList() {
		List<String> ret = new ArrayList<String>();
		for(Stackable s: getCommandList()) {
			ret.add(s.getName());
			if (s instanceof ListCommand){
				ret.addAll(s.toStringList());
			}
		}
		ret.add(LISTEND);
		return ret;
	}

	@Override
	public Constant execute(Model model) {
		for(Stackable s : getCommandList()) s.execute(model);
		return new Constant(0);
	}

}