package back_end.model;

import application.gui.interfaces.Observer;

public interface Subject {

	/**
	 * @author Jacob Warner
	 */

	/**
	 * method to notify observers of change
	 */
	public void notifyObservers();

	/**
	 * method to get updates from subject
	 */
	public Object getUpdate(Observer obj);

	/**
	 * method to register observers
	 */
	public void register(Observer obj);

	/**
	 * method to unregister observers
	 */
	public void unregister(Observer obj);
}
