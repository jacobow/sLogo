package back_end.commands.abstractCommands;

import java.util.ArrayList;
import java.util.List;

import back_end.commands.abstractCommands.interfaces.Executable;
import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * Purpose: The Executable Command is a abstract implementation of the Executable interface and a sub class of the Stackable Command. It contains  necessary elements and methods 
 * 		shared by all executable command.
 * Assumptions: The assumption is that the combination of the StackableCommand superclass and the executable interface has all the necessary information for this command to function.
 * Dependencies: This class is dependent on the CommandFactory to be created and the CommandStack to be executed.
 * Examples: An instance of an ExecutableCommand is the MakeVariable command which takes a variable and a constant and creates a variable with that constant's value.
 * 
 * @author DavidYoon
 *
 */
public abstract class ExecutableCommand extends StackableCommand implements Executable{

	private int index;
	private Storable[] storableArray;
	private double[] valueArray;
	private boolean executable;

	public ExecutableCommand (int arraySize, String userInput){
		super(arraySize, userInput);
		index = 0;
		if (arraySize == 0){
			executable = true;
		}
		else {
			this.storableArray = new Storable[arraySize];
			this.valueArray = new double[arraySize];
		}

	}

	public void addStorable(Storable storable){
		if (index < storableArray.length){
			storableArray[index] = storable;
			valueArray[index] = storableArray[index].getValue();
			index++;
		}
		if (index == storableArray.length) {
			executable = true;
		}
	}

	@Override
	public List<String> toStringList() {
		List<String> list = new ArrayList<String>();
		list.add(getName());
		if (storableArray != null){
			for(Storable s: storableArray) {
				list.add(s.toString());
			}
		}
		return list;
	}

	public boolean isExecutable() {
		return executable;
	}

	public boolean isStorable(){
		return false;
	}

	public abstract Storable execute(Model model);

	public double[] getValueArray() {
		return valueArray;
	}

	public Storable[] getStorableArray() {
		return storableArray;
	}

}