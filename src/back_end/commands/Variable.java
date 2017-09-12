package back_end.commands;

/**
 * @author DavidYoon
 *
 */
public class Variable extends Constant {

	public Variable(String name) {
		super();
		setName(name);
		setStorable(true);
	}

	public Variable(String name, double value) {
		super(value);
		setName(name);
		setValue(value);
	}
	
}
