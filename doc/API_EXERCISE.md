API EXERCISE
===========

# Simulation
### Internal API
```java
public class Neighbors { 
      public Neighbors()
    public Iterator<Cell> getList()
    public int getSize()
    public void shuffle()
    public void add(Cell c)
    public Cell get(int index)
    public void clear()
    public void addNeighbors(Cell c, Simulation s)
    public void addCardinalNeighbors(Cell c, Simulation s)
    public void addCardinalTordialNeighbors(Cell c, Simulation s)
    public void addTordialNeighbors(Cell c, Simulation s)
}
```
```java
public class FireCell extends Cell{ 
      public FireCell(State state, int x, int y, double chanceOfFire)
    public State getState()
    public void setState(State s)
    public double getProbCatch()
	public void addNeighbors(Simulation s) 
}
 

public class FireHexagonalSimulation extends FireSimulation{ 
      public FireHexagonalSimulation(SceneManager s, XMLParser newParser, int stateIn)
    public Simulation resetState()
}
 

public abstract class FireSimulation extends Simulation{ 
      public FireSimulation(SceneManager s, XMLParser newParser, int stateIn)
    public abstract Simulation resetState();
}
 

public class FireSqSimulation extends FireSimulation{ 
      public FireSqSimulation(SceneManager s, XMLParser newParser, int stateIn)
    public Simulation resetState()
}
 

public class FireState extends InitialState{ 
  	public FireState(Node stateNode, int gridDim) 
}

public class FireTriangleSimulation extends FireSimulation{ 
      public FireTriangleSimulation(SceneManager s, XMLParser newParser, int stateIn)
    public Simulation resetState()
}
 

public class GameOfLifeCell extends Cell{ 
  	public GameOfLifeCell(State isAlive, int x, int y)
    public State checkAlive()
	public void changeAlive(State b)
	public void addNeighbors(Simulation s) 
}
 

public class GameOfLifeHexagonalSimulation extends GameOfLifeSimulation{ 
  	public GameOfLifeHexagonalSimulation(SceneManager s, XMLParser newParser, int stateIn)
	public Simulation resetState() 
}
 

public abstract class GameOfLifeSimulation extends Simulation{ 
      public GameOfLifeSimulation(SceneManager s,XMLParser newParser, int stateIn)
    public abstract Simulation resetState();
}
 

public class GameOfLifeSqSimulation extends GameOfLifeSimulation{ 
  	public GameOfLifeSqSimulation(SceneManager s, XMLParser newParser, int stateIn)
	public Simulation resetState() 
}
 

public class GameOfLifeState extends InitialState { 
  	public GameOfLifeState(Node stateNode, int gridDim) 
}
 

public class GameOfLifeTriangleSimulation extends GameOfLifeSimulation{ 
  	public GameOfLifeTriangleSimulation(SceneManager s,XMLParser newParser, int stateIn)
	public Simulation resetState() 
}
 

public class PredatorPreyCell extends Cell { 
      public PredatorPreyCell(State state, int x, int y)
    public void addNeighbors(Simulation s)
    public State getState()
    public void setState(State s)
    public PredatorPreyCell randNeighborFish()
    public PredatorPreyCell randNeighborShark()
}
 

public class PredatorPreyHexagonalSimulation extends PredatorPreySimulation{ 
      public PredatorPreyHexagonalSimulation(SceneManager s,XMLParser newParser, int stateIn)
    public Simulation resetState()
}
 

public abstract class PredatorPreySimulation extends Simulation{ 
      public PredatorPreySimulation(SceneManager s, XMLParser newParser,int stateIn)
    public abstract Simulation resetState();
}
 

public class PredatorPreySqSimulation extends PredatorPreySimulation{ 
      public PredatorPreySqSimulation(SceneManager s,XMLParser newParser, int stateIn)
    public Simulation resetState()
}

public class PredatorPreyState extends InitialState { 
  	public PredatorPreyState(Node stateNode, int gridDim) 
}
 

public class PredatorPreyTriangleSimulation extends PredatorPreySimulation { 
      public PredatorPreyTriangleSimulation(SceneManager s,XMLParser newParser, int stateIn)
    public Simulation resetState()
}
 

public class SegregationCell extends Cell{ 
      public SegregationCell(State state, int x, int y, double similarity)
    public State getState()
    public void setState(State s)
	public void addNeighbors(Simulation s) 
}
 

public class SegregationHexagonalSimulation extends SegregationSimulation{ 
      public SegregationHexagonalSimulation(SceneManager s, XMLParser newParser, int stateIn)
}
 

public abstract class SegregationSimulation extends Simulation{ 
      public SegregationSimulation(SceneManager s,XMLParser newParser,int stateIn)
	public Simulation resetState() 
}
 

public class SegregationSqSimulation extends SegregationSimulation{ 
      public SegregationSqSimulation(SceneManager s, XMLParser newParser, int stateIn)
}
 

public class SegregationState extends InitialState { 
  	public SegregationState(Node stateNode, int gridDim) 
}
 

public class SegregationTriangleSimulation extends SegregationSimulation { 
      public SegregationTriangleSimulation(SceneManager s, XMLParser newParser, int stateIn)
}
```
### External API
```java
public abstract class Cell { 
  	public Cell(int x, int y) 
    public abstract void addNeighbors(Simulation s);
	public Neighbors getNeighbors()
	public Rectangle getCell()
	public Polygon getHexagon()
	public Polygon getTriangle()
	public void setRows(int rows) gridRows = rows;}
	public void setCols(int cols) gridCols = cols;}
	public int getRow()return row;}
	public int getCol()return col;}
}
```
```java
public class Grid { 
      public Grid(int row, int col)
    public Cell[][] getGrid()
    public void setCell(Cell c, int row, int col)
    public Cell getCell(int row, int col)
    public int getNumRows()
    public int getNumCols()
}
```
```java
public abstract class InitialState { 
  	public InitialState(Node stateNode, int gridDim)
	public String getStateName() 
	public Grid getGrid() 
	public Element getStateElement() 
}
```
# Configuration
### Internal API
```java
public abstract class Simulation { 
	public Simulation(SceneManager s, XMLParser newParser, int stateIn)
    public XMLParser getParser()
	public int getRows()
	public int getCols()
    public ResourceBundle getMyResources()
	public void setRows(int rows)
	public void setCols(int cols) 
	public int getCurrentState()
	public void setCurrentState(int state)
	public void setSizeCell(double newSize)
	public Map<Enum, Color> getStates()
	public abstract Simulation resetState();
}
```

### External API
```java
public class ErrorChecker { 
      public ErrorChecker()
    public void fileError()
    public void outOfBoundsError()
    public void gridError()
    public void invalidSimName()
}
```
```java
public class XMLParser { 
  	public XMLParser()
	public void loadFile(String fileName)
	public void init()
	public List<String> getStateNames()
	public String getTitle() 
	public void setGridDim(int dim)
	public int getGridDim() 
	public List<InitialState> getStateList() 
}
```
# Visualization
### Internal API
```java
public class Menu {	 
      public Menu(SceneManager s)
    public Scene getScene()
    public void placeHeader() 
}
```
```java
public class SceneManager { 
  	public SceneManager(Stage s)
	public void switchToSimulation(Simulation sim)
	public void switchToMenu(Menu menu)
}
```
```java
public abstract class Simulation { 
	public Scene getScene()
	public Group getGroup()
	public void setScene()
}
```
```java
public class UIGenerator { 
      public UIGenerator(Simulation s)
    public void placeHeader() 
    public void placeToolbar() 
}
```
```java
public class HLayout { 
  	public HLayout(int space,int top,int right,int bottom,int left) 
	public void addHeader(String text) 
	public void placeHbox(Group group) 
}
```
```java
public class VLayout { 
  	public VLayout(int space,int top,int right,int bottom,int left) 
	public void addText(String text) 
	public void placeVbox(Group group) 
	public void addMyButton(Button button) 
	public void addComboBox(ComboBox<String> cbox) 
	public void addLineBreak() 
	public void addMyTextfield(TextField myTextField) 
}
```
 
### External API
```java
public abstract class Simulation { 
  	public void handle(long currTime)
    public void stop()
    public void start()
	public Grid getGrid()
	public void setGrid(Grid g)
}
```