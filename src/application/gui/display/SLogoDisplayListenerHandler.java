package application.gui.display;

import application.gui.interfaces.UndoRedoCommandLine;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

public class SLogoDisplayListenerHandler {
	private SLogoDisplay myDisplay;
	private UndoRedoCommandLine myCommandLine;

	public SLogoDisplayListenerHandler(SLogoDisplay display){
		myDisplay=display;
		myCommandLine = myDisplay.getMyCommandLine();
	}


	public ListChangeListener<Integer> getBackgroundColorListener(){
		return new ListChangeListener<Integer>(){
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Integer> c) {
				//Set Background Color
					myDisplay.setBackgroundColor(c.getList().get(0), c.getList().get(1), c.getList().get(2));
			}
		};
	}

	public EventHandler<MouseEvent> getTurtleSelectionHandler(TurtleFXShell newTurtleShell){
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				String selectTurtles = myCommandLine.getResources().getString("Tell").split("\\|")[0]+" [ ";
				selectTurtles+=newTurtleShell.getTurtleObject().getID()+" ] ";
				myCommandLine.setCommandImmediately(selectTurtles);

			}
		};
	}


	public ChangeListener<? super Boolean> getHoverListener(TurtleFXShell newTurtleShell) {
		return new ChangeListener<Boolean>(){

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue){
					String info="Turtle "+newTurtleShell.getTurtleObject().getID()+"\n";
					info+="Heading: "+newTurtleShell.getTurtleObject().getOrientation()+"\n";
					info+="Pen Status: ";
					if(newTurtleShell.getTurtleObject().getState().isPenUp()){
						info+="UP\n";
					}else{
						info+="DOWN\n";
					}
					info+="X: "+ newTurtleShell.getTurtleObject().getX();
					info+="\tY: "+ newTurtleShell.getTurtleObject().getY();
					
					Tooltip t = new Tooltip(info);
					Tooltip.install(newTurtleShell, t);
				}

			}

		};
	}


}
