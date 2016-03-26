package szoftProj;

import java.awt.Color;
import java.util.ArrayList;

public class Player extends ActionUnit{
	
	private Box box;
	private ArrayList<ZPM> zpm;
	private int allZPM;
	private Game game;
	
	//Konstruktor
	public Player(int allZPM, Direction direction, Field currentField, Game game){
		this.allZPM = allZPM;
		this.currentDirection = direction;
		this.currentField = currentField;
		this.game = game;
	}
	
	//ZPM felvetele. Ha elfogyott a palyarol az osszes, akkor nyertunk.
	public void addZPM(ZPM zpm){
		this.zpm.add(zpm);
		allZPM--;
		if (allZPM == 0){
			game.win();
		}
	}
	
	//Doboz felvetele csak akkor, ha meg nincs nala doboz.
	public boolean grabBox(Box box){
		if (this.box == null){
			this.box = box;
			return true;
		}
		else return false;
	}
	
	//Doboz lerakasa.
	public void dropBox(){
		this.box = null;
	}
	
	//Loves tipusu action-t hoz letre a parameterkent kapott szinnel.
	public void shoot(Color color){
		nextAction = new Action(ActionType.SHOOT, currentDirection, color);
	}
	
	//Felvetel tipusu action-t hoz letre.
	public void grab(){
		nextAction = new Action(ActionType.GRAB, currentDirection, null);
	}
	
	//Lerakas tipus action-t hoz letre.
	public void drop(){
		nextAction = new Action(ActionType.DROP, currentDirection, null);
	}
	
	//Meghal a jatekos, veget er a jatek.
	public void kill(){
		dead = true;
		game.lose();
	}
	
	//Eltavolitja magat a jelenlegi mezorol, majd hozzaadja magat a parameterkent kapott uj mezohoz,
	//es beallitja a currentFieldet az uj mezore.
	public void step(Field target){
		currentField.removeUnit();
		target.addUnit(this);
		currentField = target;
	}
	
	public void action(){
		//TODO
	}
	
	public void accept(Player launcher, Field target){
		//TODO
	}
	
	public void accept(Field launcher, Player target){
		//TODO
	}
}
