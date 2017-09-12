package back_end.commands.abstractCommands.interfaces;

/**
 * Purpose: The executable interface provides guidelines on how an executable should behave. It is also used to differentiate between different types of commands in the parser and the factory.
 * Assumptions: The assumption is that the executable interface extends from stackable which has some essential features that an executable command needs.
 * Dependencies: The dependencies lies on the creation of a concrete object that will implement these methods to good use.
 * 
 * @author DavidYoon
 *
 */
public interface Executable extends Stackable {
	
	/**
	 * This command takes in a storable and stores it inside a data structure to know when the executable command becomes executable. 
	 * @param storable
	 */
	public void addStorable(Storable storable);
	
}
