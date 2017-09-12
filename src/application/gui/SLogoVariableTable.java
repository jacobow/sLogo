package application.gui;

import application.gui.interfaces.CommandLine;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * @author walker
 * 
 * Heavily sampled from Ensemble sample code for an editable table. 
 * Some Documentation below supplied by Ensemble
 * ==============
 * A simple table that uses cell factories to add a control to a table
 * column and to enable editing of first/last name and email.
 *
 * @see javafx.scene.control.TableCell
 * @see javafx.scene.control.TableColumn
 * @see javafx.scene.control.TablePosition
 * @see javafx.scene.control.TableRow
 * @see javafx.scene.control.TableView
 */
public class SLogoVariableTable  {
	
	private CommandLine myCommandLine;
	
	private TableView tableView;
	//TODO Remove:
	private ObservableList<SLogoDefinedVariable> data = FXCollections.observableArrayList(
			/*new SLogoDefinedVariable("asdf1","asdf2")*/);

	/**
	 * Creates the initial table and sets up cell factories
	 * @param commandLine | Only receives a few methods required to set up mouse listeners
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	SLogoVariableTable(CommandLine commandLine) {
		myCommandLine = commandLine;
		//"First Name" column
		TableColumn variableName = new TableColumn<SLogoDefinedVariable,String>();
		variableName.setText("Variable Name");
		variableName.setCellValueFactory(new PropertyValueFactory("name"));

		//"Last Name" column
		TableColumn variableValue = new TableColumn<SLogoDefinedVariable,String>();
		variableValue.setText("Value");
		variableValue.setCellValueFactory(new PropertyValueFactory("value"));

		//Set cell factory for cells that allow editing
		Callback<TableColumn, TableCell> cellFactory =
				new Callback<TableColumn, TableCell>() {

			public TableCell call(TableColumn p) {
				return new SLogoEditableCell();
			}
		};

		variableName.setCellFactory(cellFactory);
		variableName.setEditable(false);
		variableValue.setCellFactory(cellFactory);

		tableView = new TableView();

		tableView.setItems(data);
		tableView.setEditable(true);
		tableView.getColumns().addAll(variableName, variableValue);
		TableColumn<SLogoDefinedVariable,String> myVariableColumn = (TableColumn<SLogoDefinedVariable,String>) tableView.getColumns().get(1);
		myVariableColumn.setOnEditCommit((CellEditEvent<SLogoDefinedVariable,String> t )-> editVariable(t));
	
	}
	/**
	 * encapsulates the TableView from the outside
	 * @return Variable table
	 */
	TableView getTableView(){
		return tableView;
	}

	/**
	 * Change listener for the backend variable map. 
	 * @return MapChangeListener for variable map
	 */
	MapChangeListener<String,Double> getMapListener(){
		return new MapChangeListener<String,Double>(){
			@Override
			public void onChanged(
					javafx.collections.MapChangeListener.Change<? extends String, ? extends Double> change) {
				if(change.wasAdded()){
					boolean contained = false;
					for(SLogoDefinedVariable var : data){
						if(var.nameProperty().get().equals(change.getKey())){
							var.valueProperty().set(change.getValueAdded().toString());
							contained = true;
						}
					}
					if(!contained){
						data.add(new SLogoDefinedVariable(change.getKey(),change.getValueAdded().toString()));
					}

				}else if(change.wasRemoved()){
				}

			}
		};
	}
	/**
	 * Handles the onclick edit function
	 * @param t - CellEditEvent to prove that the cell was edited. 
	 */
	protected void editVariable(CellEditEvent<SLogoDefinedVariable, String> t){
		String varName = t.getTableView().getItems().get(t.getTablePosition().getRow()).nameProperty().get();
		String newVal = t.getNewValue();
		myCommandLine.setCommandImmediately(
		myCommandLine.getResources().getString("MakeVariable").split("\\|")[0] +" "+varName +" "+newVal);
	}

}
