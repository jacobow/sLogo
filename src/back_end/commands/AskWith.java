package back_end.commands;

import java.util.ArrayList;
import java.util.List;

import back_end.commands.abstractCommands.ExecutableCommand;
import back_end.commands.abstractCommands.interfaces.Stackable;
import back_end.model.Model;
import back_end.model.Turtle;

public class AskWith extends ExecutableCommand {

	public AskWith(int arraySize, String userInput) {
		super(arraySize, userInput);
	}

	@Override
	public Constant execute(Model model) {
		List<Integer> IDList = new ArrayList<Integer>();
		List<Integer> oldTurtles = new ArrayList<Integer>();

		for(Turtle t : model.getTurtles()) {
			if(t.getState().isActive()) {
				oldTurtles.add(t.getID());
			}
		}
		for(int id : IDList) {
			model.addTurtle(id);
			model.notifyObservers();
		}
		for(Turtle t : model.getTurtles()) {
			if(IDList.contains(t.getID())) model.activateTurtle(t.getID());
			else model.deactivateTurtle(t.getID());
		}
		double ret = getStorableArray()[1].execute(model).getValue();
		for(Turtle t : model.getTurtles()) {
			if(oldTurtles.contains(t.getID())) model.activateTurtle(t.getID());
			else model.deactivateTurtle(t.getID());
		}
		return new Constant(ret);
	}

}
