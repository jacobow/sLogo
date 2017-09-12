package back_end.commands;

import back_end.commands.abstractCommands.interfaces.Storable;
import back_end.model.Model;

/**
 * @author DavidYoon
 *
 */
public class Pi extends Constant{

	public Pi() {
		super(Math.PI);
		setStorable(true);
	}

	@Override
	public Storable execute(Model model) {
		return new Constant(getValue());
	}

}
