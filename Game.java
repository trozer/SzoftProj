package szoftProj;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private State state;
	private Stage stage;
	private ActionUnit actionUnit;
	//konstruktor
	Game(){
		state = State.MENU;
		List<Object> l = new ArrayList<Object>();
		l.add(Skeleton.getEmpty());
		Skeleton.callMethod("Game - Konstruktor", this, l);
		Skeleton.returnMethod("Game - Konstruktor", this, l);
	}
	
	public void update(){//Rendszeres idõközönként meghívódik, feladata a játék állapotai közti váltás, illetve a Stage.update() meghívásával egy-egy kör lefuttatása.
		List<Object> l = new ArrayList<Object>();
		l.add(Skeleton.getEmpty());
		Skeleton.callMethod("update", this, l);
		stage.update();
		stage.collectUnits();
		Skeleton.returnMethod("update", this, l);
	}
	
	public void loadGame(){}//játék betöltése
	public void saveGame(){}//játék elmentése
	public void control(){}//Konverzió a külvilág és a játék belsõ mûködése között: a felhasználói bemeneti eseményeknek megfelelõen helyesen beállítja a Player következõ update()-ben végrehajtandó cselekedetét.
	public void lose(){//Játék állapotának State.Lose-ra állítása.
		state = State.LOSE;
	}
	public void win(){//Játék állapotának State.WIN-re állítása.
		state = State.WIN;
	}
	public void addUnit(Unit unit){//A Stage units listájához új egység hozzáadása.
		stage.addUnit(unit);
	}
	public void newGame(){}//új játék indítása
	public void setStage(Stage stage){
		this.stage = stage;
	}
	public void setActionUnit(ActionUnit actionUnit){//actionunit beállítása
		this.actionUnit = actionUnit;
	}
}
