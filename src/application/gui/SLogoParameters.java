package application.gui;

import application.gui.interfaces.Parameters;
import back_end.model.Model;
import back_end.model.Subject;
import back_end.model.Turtle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author michaelseaberg
 *
 */
public class SLogoParameters extends SLogoUIArea implements Parameters {
	private Model mySLogoModel;
	private ObservableList<Turtle> myTurtleList;
	private TreeView<Turtle> myTurtleListView;
	
	public SLogoParameters(double prefWidth, double prefHeight, Model sLogoModel){
		myArea.setPrefHeight(prefHeight);
		myArea.setPrefWidth(prefWidth);
		mySLogoModel = sLogoModel;
		myTurtleList = FXCollections.observableArrayList();
		((VBox) myArea).setFillWidth(true);
		//initialize();
	}
	
/*	private void initialize(){
		mySLogoModel.register(this);
		this.setSubject(mySLogoModel);
		SLogoToolbar parametersToolbar = new SLogoToolbar(myArea.getWidth(),"Parameters");
		myTurtleListView = new TreeView<Turtle>();
		Rectangle fakeParamsWindow = new Rectangle();
		fakeParamsWindow.setHeight(myArea.getPrefHeight()-parametersToolbar.getPrefHeight());
		fakeParamsWindow.setWidth(myArea.getPrefWidth());
		fakeParamsWindow.setFill(Color.BLUE);
		myArea.getChildren().get(0).add(parametersToolbar);
		myArea.getChildren().add(fakeParamsWindow);
	}*/

	public void update() {
		System.out.println("Parameters has been notified!!");
		//myTurtleList.setAll(mySLogoModel.getUpdate(myArea));
		
	}

	public void setSubject(Subject sub) {
		// TODO Auto-generated method stub
		
	}
}
