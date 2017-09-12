package application.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.text.Font;
/**
 * 
 * @author michaelseaberg
 *
 */
public class SLogoToolbar extends ToolBar{
	private String toolbarTitle;
	public final double BUTTON_HEIGHT = 20;
	public final double BUTTON_WIDTH = 100;
	public final static double TOOLBAR_HEIGHT = 60;
	
	/**
	 * Initializes toolbar of width and title
	 * @param prefWidth
	 * @param title
	 */
	public SLogoToolbar(double prefWidth, String title){
		this.setPrefHeight(TOOLBAR_HEIGHT);
		this.setPrefWidth(prefWidth);
		toolbarTitle = title;
		initialize();
	}

	private void initialize(){
		Label titleNode = new Label(toolbarTitle);
		titleNode.autosize();
		this.getItems().add(titleNode);
	}
	/**
	 * Changes the title of the toolbar
	 * @param title
	 */
	public void setToolBarTitle(String title){
		toolbarTitle = title;
	}
	
	/**
	 * adds a slider with preferences to the toolbar 
	 * @param name
	 * @param min
	 * @param max
	 * @param defaultVal
	 * @return
	 */
	public Slider addSlider(String name, int min, int max, int defaultVal){
		Slider slider = new Slider(min, max, defaultVal);
		slider.setBlockIncrement(1);
		Label sliderName = new Label(name);
		Label sliderValue = new Label(String.format("%.0f", slider.getValue()));
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				sliderValue.setText(String.format("%.0f", new_val));

			}
		});
		this.getItems().add(sliderName);
		this.getItems().add(slider);
		this.getItems().add(sliderValue);
		return slider;
	}
}
