package back_end.commands.abstractCommands.interfaces;

import java.util.List;

/**
 * Purpose: The storable interface provides guidelines on how a storable should behave. It is also used to differentiate between different types of commands in the parser and the factory.
 * Assumptions: The assumption is that the storable interface extends from stackable which has some essential features that an storable command needs.
 * Dependencies: The dependencies lies on the creation of a concrete object that will implement these methods to good use.
 * 
 * @author DavidYoon
 *
 */
public interface Storable extends Stackable{

	public void setStorable(boolean storable);

	public List<Stackable> getCommandList();

	public void setValue(double value);
	
}
