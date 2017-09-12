package application.gui;

import java.util.Formatter;
import java.util.TreeMap;

import javafx.collections.MapChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 * A new stage which visualizes the available color palettes. 
 * @author walker
 *
 */
public class SLogoPaletteVisualizer {

	private final double WIDTH = 500.0;
	private final double HEIGHT = 500.0;

	private Group paletteGroup;
	private Stage paletteStage;
	private Scene paletteScene;
	private TreeMap<Double, Color> myColorMap;
	/**
	 * initializes the visualizer
	 */
	SLogoPaletteVisualizer(){
		paletteStage = new Stage();
		paletteGroup = new Group();
		paletteScene = new Scene(paletteGroup, WIDTH,HEIGHT);
		paletteStage.setScene(paletteScene); 
		myColorMap = new TreeMap<Double,Color>();
	}
	/**
	 * MapListener for the palette map in the backend
	 * @return
	 */
	MapChangeListener<Double,int[]> getMapListener(){
		return new MapChangeListener<Double,int[]>(){
			@Override
			public void onChanged(
					javafx.collections.MapChangeListener.Change<? extends Double, ? extends int[]> change) {
				if(change.wasAdded()){
					myColorMap.put(change.getKey(), getColor(change.getValueAdded()));
					update();
				}
			}

			private Color getColor(int[] valueAdded) {
				if(valueAdded.length!=3) return null;
				return Color.rgb(valueAdded[0], valueAdded[1], valueAdded[2]);
			}
		};
	}
	/**
	 * Determines whether to show the palette or not
	 * @param toShow
	 */
	void showPalette(boolean toShow){
		if(toShow) paletteStage.show();
		if(!toShow) paletteStage.close();
	}
	
	/**
	 * updates the palette such that its in index order
	 */
	void update(){
		paletteGroup = new Group();
		ScrollPane scrollPane = new ScrollPane();
		FlowPane colorBox = new FlowPane();
		scrollPane.setContent(colorBox);
		scrollPane.setMaxHeight(HEIGHT);
		for(Double key : myColorMap.keySet()){
			colorBox.getChildren().add(getRectangle(myColorMap.get(key)));
		}
		paletteGroup.getChildren().add(scrollPane);
		paletteScene.setRoot(paletteGroup);
	}

	private VBox getRectangle(Color color) {
		VBox labeledColor = new VBox();
		labeledColor.getChildren().add(new Label(hexToRGB(color.toString())));
		labeledColor.getChildren().add(new Rectangle(100,100,color));
		return labeledColor;
	}

	private String hexToRGB(String string) {
		int r= Integer.valueOf(string.substring(2,4), 16);
		int g=Integer.valueOf(string.substring(4,6), 16);
		int b=Integer.valueOf(string.substring(6,8), 16);
		Formatter formatter = new Formatter();
		return formatter.format("%03d%03d%03d", r,g,b).toString();
	}

}
