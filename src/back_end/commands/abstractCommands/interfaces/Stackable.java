package back_end.commands.abstractCommands.interfaces;

import java.util.List;

import back_end.model.Model;

/**
 * Purpose: The stackable interface provides guidelines on how an stackable should behave. It is used to process and create a generic command in the parser and the factory.
 * Assumptions: The assumption is that the stackable interface will need to be able to check for its ability to execute and store.
 * Dependencies: The dependencies lies on the creation of a concrete object through the command factory.
 * 
 * @author DavidYoon
 *
 */
public interface Stackable {

	public boolean isExecutable();

	public boolean isStorable();

	public Storable execute(Model model);

	public double getValue();

	public String getName();

	public List<String> toStringList();
}
