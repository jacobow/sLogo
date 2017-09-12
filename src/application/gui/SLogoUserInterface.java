package application.gui;

import application.gui.interfaces.UserInterface;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * The SLogoUserInterface class implements the UserInterface API. It is used to instantiate specific SLogo windows, each of which are isolated
 * IDE's running our SLogo workspace environment. This class contains helper methods to manipulate JavaFX scene/stage data.
 * @author michaelseaberg
 *
 */
public class SLogoUserInterface implements UserInterface {
	private Scene myScene;
	private Group myGroup;
	private TabPane myTabPane;
	private Tab myNewTabButton;
	private double WIDTH,HEIGHT;
	private final double TAB_HEIGHT=35.0;
	
	/**
	 * Constructor for a new SLogo program.
	 */
	public SLogoUserInterface(double windowWidth, double windowHeight){
		WIDTH=windowWidth;
		HEIGHT=windowHeight;
		myGroup = new Group();
		myScene = new Scene(myGroup,WIDTH,HEIGHT);
		initialize();
	}

	@Override
	public Scene getScene() {
		return myScene;
	}

	@Override
	public void initialize() {
		myTabPane = new TabPane();
		myNewTabButton = new Tab("+");
		myNewTabButton.setClosable(false);
		myNewTabButton.setOnSelectionChanged(e->addNewTab());
		myTabPane.setTabMaxHeight(TAB_HEIGHT);
		
		myTabPane.getTabs().addAll(myNewTabButton);
		myGroup.getChildren().add(myTabPane);
	}

	private void addNewTab() {
		SLogoWindow newWindow = new SLogoWindow(WIDTH,HEIGHT-myTabPane.getTabMaxHeight());
		Tab newTab = new Tab(""+myTabPane.getTabs().size());
		newTab.setContent(newWindow);
		myTabPane.getTabs().add(newTab);
		myTabPane.getSelectionModel().select(newTab);
	}

	@Override
	public Group getGroup() {
		return myGroup;
	}

}
