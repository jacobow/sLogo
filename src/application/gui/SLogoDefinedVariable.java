package application.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Holds the variables for the variable table
 * @author walker
 *
 */
public class SLogoDefinedVariable {
	StringProperty varName; 
	StringProperty varValue;
	
	SLogoDefinedVariable(String name, String value){
		varName=new SimpleStringProperty(name);
		varValue= new SimpleStringProperty(value);
	}
	/**
	 * Variable Name Property 
	 * @return variable name property
	 */
	StringProperty nameProperty(){
		return varName;
	}
	/**
	 * Variable value property
	 * @return variable value property
	 */
	StringProperty valueProperty(){
		return varValue;
	}
	
	/**
	 * Sets a variable value
	 * @param newVal
	 */
	void setVariableValue(String newVal){
		varValue.set(newVal);
	}
}
