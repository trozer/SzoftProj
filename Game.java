package szoftProj;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private State state;
	private Stage stage;
	private ActionUnit actionUnit;
	
	Game(){
		state = State.MENU;
		List<Object> l = new ArrayList<Object>();
		l.add(Skeleton.getEmpty());
		Skeleton.callMethod("Game - Konstruktor", this, l);
		Skeleton.returnMethod("Game - Konstruktor", this, l);
	}
	
	public void update(){
		List<Object> l = new ArrayList<Object>();
		l.add(Skeleton.getEmpty());
		Skeleton.callMethod("update", this, l);
		stage.update();
		stage.collectUnits();
		Skeleton.returnMethod("update", this, l);
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
