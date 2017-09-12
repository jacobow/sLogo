package application.gui.interfaces;

import java.util.Stack;

/**
 * 
 * @author walker eacho
 * @author michaelseaberg
 */
public interface History{

	/**
	 * Clears the history
	 */
	void clearList();
	
	/**
	 * Receives the entire history of commands
	 * @return List of commands
	 */
    Stack<String> getStack();
    
    /**
     * Adds new command to the history
     * 
     * @param command : new command for the history
     */
    
    void addCommandToList(String command,boolean isGoodCommand);
    
    /**
     * Receives a specific command from the History
     * @param index : How many commands from present are we going back to
     * @return String command at that index
     */
    String getElementAtIndex(int index);
    
    /**
     * Returns the most recent command
     * @return String most recent command
     */
    String getLast();
    
    /**
     * Returns how many elements of the History there are
     * @return history's size
     */
    int getSize();
}
