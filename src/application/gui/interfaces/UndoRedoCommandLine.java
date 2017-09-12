package application.gui.interfaces;

import java.io.PrintWriter;

public interface UndoRedoCommandLine extends CommandLine{
	/**
	 * Undoes the previous command. To be called after disabling animation
	 */
	abstract void undo();
	
	/**
	 * Redoes the previous command. 
	 */
	abstract void redo();
	
	/**
	 * Passes the ability to save a history
	 * @param fileWriter
	 */
	abstract void saveHistoryFile(PrintWriter fileWriter);
	
	/**
	 * Passes the ability to save a function set
	 * @param fileWriter
	 */
	abstract void saveFunctionsFile(PrintWriter fileWriter);
}
