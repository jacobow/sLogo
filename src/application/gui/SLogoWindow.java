package application.gui;

//TODO: Bring back Right column for completeness, add original parameters idea if we have time
import application.gui.display.SLogoDisplay;
import back_end.model.Model;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * SLogoWindow class used to encapsulate a fully functioning SLogo workspace. Each workspace contains the following sections:
 * 		-CommandLine
 * 		-Display
 * 		-Settings
 * 		-Parameters
 * Each section can be initialized in one of three columns, the left middle or right column. Each SLogo window also instantiates a new
 * SLogo model which can be passed to the different sections of the window.
 * @author michaelseaberg
 * @authro walker
 *
 */
public class SLogoWindow extends Pane{
	public final double LEFT_WIDTH_PERCENTAGE = .15;
	public final double MIDDLE_WIDTH_PERCENTAGE = 1-LEFT_WIDTH_PERCENTAGE;
	public final double COMMAND_LINE_HEIGHT_PERCENTAGE = .3;
	public final double DISPLAY_HEIGHT_PERCENTAGE = 1-COMMAND_LINE_HEIGHT_PERCENTAGE;
	public final double BORDER_BUFFER = 9;

	private Model mySLogoModel;
	private SLogoCommandController myCommandLine;
	private SLogoDisplay myDisplay;
	private SLogoSettings mySettings;
	private SLogoParameters myParameters;

	/**
	 * Constructor for a new SLogo window.
	 * @param width
	 * @param height
	 */
	public SLogoWindow(double width,double height){
		mySLogoModel = new Model(width*MIDDLE_WIDTH_PERCENTAGE, height*(DISPLAY_HEIGHT_PERCENTAGE)- SLogoToolbar.TOOLBAR_HEIGHT);
		this.setWidth(width-BORDER_BUFFER);
		this.setHeight(height);
		initializeCommandLine();
		initializeDisplay();
		initializeSettings();
		initializeMiddle();
		myCommandLine.init();
		initializeLeft();

	}

	/**
	 * Method used to create the left column of the display.
	 */
	private void initializeLeft() {
		VBox leftColumn = new VBox();
		leftColumn.setPrefSize(this.getWidth()*LEFT_WIDTH_PERCENTAGE, this.getHeight());
		leftColumn.setFillWidth(true);

		leftColumn.getChildren().add(mySettings.getArea());

		this.getChildren().add(leftColumn);
	}

	private void initializeMiddle(){
		VBox middleColumn = new VBox();

		middleColumn.getChildren().add(myDisplay.getArea());

		middleColumn.getChildren().add(myCommandLine.getArea());
		middleColumn.setLayoutX(this.getWidth()*LEFT_WIDTH_PERCENTAGE);
		this.getChildren().add(middleColumn);
	}

	/**
	 * Method used to initialize the myCommandLine instance variable
	 */
	private void initializeCommandLine(){
		myCommandLine = new SLogoCommandController((this.getWidth()*MIDDLE_WIDTH_PERCENTAGE),(this.getHeight()*COMMAND_LINE_HEIGHT_PERCENTAGE), mySLogoModel);
	}

	/**
	 * Method used to initialize the myDisplay instance variable
	 */
	private void initializeDisplay(){
		myDisplay = new SLogoDisplay((this.getWidth()*MIDDLE_WIDTH_PERCENTAGE),(this.getHeight()*DISPLAY_HEIGHT_PERCENTAGE),mySLogoModel,myCommandLine);
	}

	/**
	 * Method used to initialize the mySettings instance variable
	 */
	private void initializeSettings(){
		mySettings = new SLogoSettings((this.getWidth()*LEFT_WIDTH_PERCENTAGE),this.getHeight(),myCommandLine);
	}

}