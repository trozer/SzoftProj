package szoftProj;

public class Game {
	private State state;
	private Stage stage;
	private ActionUnit actionUnit;
	
	Game(){
		state = State.MENU;
	}
	
	public void update(){
		stage.update();
	}
	
	public void loadGame(){}
	public void saveGame(){}
	public void control(){}
	public void lose(){
		state = State.LOSE;
	}
	public void win(){
		state = State.WIN;
	}
	public void addUnit(Unit unit){
		stage.addUnit(unit);
	}
	public void newGame(){}
	public void setStage(Stage stage){
		this.stage = stage;
	}
	public void setActionUnit(ActionUnit actionUnit){
		this.actionUnit = actionUnit;
	}
}
