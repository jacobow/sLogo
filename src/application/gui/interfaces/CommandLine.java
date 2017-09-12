package application.gui.interfaces;

import java.util.ResourceBundle;

/**
 * 
 * @author Walker Eacho
 *
 */
public interface CommandLine {
	/**
	 * String array of supported languages
	 */
	String[] SUPPORTED_LANGUAGES = {"Chinese","English","French","German","Italian","Portuguese","Russian","Spanish"};
	
	/**
	 * Returns if the command is ready for entry
	 * @deprecated Useless
	 * @return commandReady boolean
	 */
	abstract boolean isCommandReady();
	
	/**
	 * Sets the boolean commandReady. Used to show that a command has been received and should not be seen as ready 
	 * anymore
	 * @deprecated Useless
	 * @param isCommandReady - Have you used the command? Set to false. 
	 */
	abstract void setCommandReady(boolean isCommandReady);
	
	/**
	 * Gets the command from the textbox in order to send it to the backend
	 * @return command
	 */
	abstract String[] getCommand();
	
	/**
	 * Sends a command to the command line but does not run it
	 * 
	 * @param command
	 */
	abstract void setCommand(String command);
	
	/**
	 * Sets the command and runs it without waiting for user
	 * 
	 * @param command to be run
	 */
	abstract void setCommandImmediately(String command);
	
	/**
	 * Sets the langauge that the parser should be working with. 
	 * 
	 * @param newLanguage
	 */
	abstract void updateLanguage(String newLanguage);

	/**
	 * 
	 * @return the resource bundle in use so that commands can be set
	 */
	abstract ResourceBundle getResources();
	
	/**
	 * 
	 * @return The current language of parsing
	 */
	abstract String currentLanguage();
	
	
}
