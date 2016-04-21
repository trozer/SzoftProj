package szoftProj;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Game {
	private State state;
	private Stage stage;
	private ActionUnit Jaffa;
	private ActionUnit Oneil;
	
	public static void main(String[] args){
		Game game = new Game();
		//later check args and run appropiate command
		File argFile = new File("testMap.xml");
		game.newGame(argFile);
	}
	
	Game(){
		state = State.MENU;
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
	
	public void setJaffa(ActionUnit Jaffa){
		this.Jaffa = Jaffa;
	}
	
	public void setOneil(ActionUnit Oneil){
		this.Oneil = Oneil;
	}
	
	
	public void win(){
		state = State.WIN;
	}
	public void addUnit(Unit unit){
		stage.addUnit(unit);
	}
	public void newGame(File file){
		this.stage = new Stage(file, this);
	}
	public void newGame(){
		this.stage = new Stage(new File("finalMap.xml"), this);
	}
	public void setStage(Stage stage){
		this.stage = stage;
	}

}
