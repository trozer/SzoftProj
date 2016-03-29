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
	
	public void update(){//Rendszeres id�k�z�nk�nt megh�v�dik, feladata a j�t�k �llapotai k�zti v�lt�s, illetve a Stage.update() megh�v�s�val egy-egy k�r lefuttat�sa.
		List<Object> l = new ArrayList<Object>();
		l.add(Skeleton.getEmpty());
		Skeleton.callMethod("update", this, l);
		stage.update();
		stage.collectUnits();
		Skeleton.returnMethod("update", this, l);
	}
	
	public void loadGame(){}//j�t�k bet�lt�se
	public void saveGame(){}//j�t�k elment�se
	public void control(){}//Konverzi� a k�lvil�g �s a j�t�k bels� m�k�d�se k�z�tt: a felhaszn�l�i bemeneti esem�nyeknek megfelel�en helyesen be�ll�tja a Player k�vetkez� update()-ben v�grehajtand� cselekedet�t.
	public void lose(){//J�t�k �llapot�nak State.Lose-ra �ll�t�sa.
		state = State.LOSE;
	}
	public void win(){//J�t�k �llapot�nak State.WIN-re �ll�t�sa.
		state = State.WIN;
	}
	public void addUnit(Unit unit){//A Stage units list�j�hoz �j egys�g hozz�ad�sa.
		stage.addUnit(unit);
	}
	public void newGame(){}//�j j�t�k ind�t�sa
	public void setStage(Stage stage){
		this.stage = stage;
	}
	public void setActionUnit(ActionUnit actionUnit){//actionunit be�ll�t�sa
		this.actionUnit = actionUnit;
	}
}
