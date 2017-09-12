package back_end.commands;

import java.util.ArrayList;
import java.util.List;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.commands.abstractCommands.interfaces.Stackable;
import back_end.model.Model;
import back_end.model.Turtle;

public class Tell extends ExecutableCommand {


	public Tell(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		List<Integer> IDList = new ArrayList<Integer>();
		if (getStorableArray()[0].getCommandList() != null) {
			for(Stackable s : getStorableArray()[0].getCommandList()) {
				IDList.add((int) s.getValue());
			}
			for(int id : IDList) {
				model.addTurtle(id);
				model.notifyObservers();
			}
			for(Turtle t : model.getTurtles()) {
				if(IDList.contains(t.getID())) model.activateTurtle(t.getID());
				else model.deactivateTurtle(t.getID());
			}
			if(IDList.size() == 0) return new Constant(0);
			return new Constant(IDList.get(IDList.size() - 1));
		}
		else {
			return new Constant(0);
		}
	}

}
