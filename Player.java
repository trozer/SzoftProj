package szoftProj;

import java.awt.Color;
import java.util.ArrayList;

public class Player extends ActionUnit{
	
	private Box box;
	private ArrayList<ZPM> zpm;
	private int allZPM;
	private Game game;
	
	//Konstruktor
	public Player(int allZPM, Direction direction,Action action, Field currentField, Game game, Box box){
		this.box = box;
		this.allZPM = allZPM;
		this.currentDirection = direction;
		this.currentField = currentField;
		this.game = game;
		this.zpm = new ArrayList<ZPM>();
		this.weight = 15;
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
		else {
			return false;
		}
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
	//A nextAction alapjan csinal valamit.
	public void action(){
		//Ha move vagy grab, akkor a megfelelo iranyban levo szomszedos mezo doo-jat hivja meg.
		if (nextAction.getType() == ActionType.MOVE ||
			nextAction.getType() == ActionType.GRAB){
				currentField.getNeighbourInDirection(currentDirection).doo(this);
		}
		//Ha turn, akkor beallitja az uj iranyt.
		else if (nextAction.getType() == ActionType.TURN){
			currentDirection = nextAction.getDirection();
		}
		//Ha drop, es van nala doboz, akkor megprobalja lerakni a megfelelo szomszedos mezore. Ha sikerult akkor a sajat box null lesz.
		else if (nextAction.getType() == ActionType.DROP && box != null){
			boolean success = currentField.getNeighbourInDirection(currentDirection).addUnit(box);
			if (success) box = null;
		}
		//Ha shoot, letrehozzuk a lovedeket es atadjuk a game-nek.
		else if (nextAction.getType() == ActionType.SHOOT){
			Bullet newBullet = new Bullet(nextAction, currentField.getNeighbourInDirection(currentDirection));
			game.addUnit(newBullet);
		}
	}
	
	//TODO Az lenne az igazi ha tudná, hogy O'Neill vagy Jaffa.
	public String toString(){
    	return "Játékos";
    }
}
