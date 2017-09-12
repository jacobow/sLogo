package application.gui.interfaces;

import back_end.model.Subject;

public interface Observer {
	/**
	 * Observer in observer design interface.
	 *
	 * @author Jacob Warner
	 */

    /**
     * method to update the observer, used by subject
     */
	public void update();

	/**
	 * attach with subject to observe
	 */
	public void setSubject(Subject sub);
}
