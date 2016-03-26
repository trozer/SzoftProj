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
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(allZPM);
		parameters.add(direction);
		parameters.add(currentField);
		parameters.add(game);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Konstruktor", this, parameters);
		
		this.allZPM = allZPM;
		this.currentDirection = direction;
		this.currentField = currentField;
		this.game = game;
		
		Skeleton.returnMethod("Konstruktor", this, parameters);
	}
	
	//ZPM felvetele. Ha elfogyott a palyarol az osszes, akkor nyertunk.
	public void addZPM(ZPM zpm){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(zpm);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("addZPM", this, parameters);
		
		this.zpm.add(zpm);
		allZPM--;
		if (allZPM == 0){
			game.win();
		}
		
		Skeleton.returnMethod("addZPM", this, parameters);
	}
	
	//Doboz felvetele csak akkor, ha meg nincs nala doboz.
	public boolean grabBox(Box box){
		if (this.box == null){
			ArrayList<Object> parameters = new ArrayList<Object>();
			parameters.add(box);
			parameters.add(true);
			Skeleton.callMethod("grabBox", this, parameters);
			Skeleton.returnMethod("grabBox", this, parameters);
			
			this.box = box;
			return true;
		}
		
		else {
			ArrayList<Object> parameters = new ArrayList<Object>();
			parameters.add(box);
			parameters.add(false);
			Skeleton.callMethod("grabBox", this, parameters);
			Skeleton.returnMethod("grabBox", this, parameters);
			
			return false;
		}
	}
	
	//Doboz lerakasa.
	public void dropBox(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("dropBox", this, parameters);
		
		this.box = null;
		
		Skeleton.returnMethod("dropBox", this, parameters);
	}
	
	//Loves tipusu action-t hoz letre a parameterkent kapott szinnel.
	public void shoot(Color color){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(color);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("shoot", this, parameters);
		
		nextAction = new Action(ActionType.SHOOT, currentDirection, color);
		
		Skeleton.returnMethod("shoot", this, parameters);
	}
	
	//Felvetel tipusu action-t hoz letre.
	public void grab(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("grab", this, parameters);
		
		nextAction = new Action(ActionType.GRAB, currentDirection, null);
		
		Skeleton.returnMethod("grab", this, parameters);
	}
	
	//Lerakas tipus action-t hoz letre.
	public void drop(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("drop", this, parameters);
		
		nextAction = new Action(ActionType.DROP, currentDirection, null);
		
		Skeleton.returnMethod("drop", this, parameters);
	}
	
	//Meghal a jatekos, veget er a jatek.
	public void kill(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("kill", this, parameters);
		
		dead = true;
		game.lose();
		
		Skeleton.returnMethod("kill", this, parameters);
	}
	
	//Eltavolitja magat a jelenlegi mezorol, majd hozzaadja magat a parameterkent kapott uj mezohoz,
	//es beallitja a currentFieldet az uj mezore.
	public void step(Field target){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(target);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("step", this, parameters);
		
		currentField.removeUnit();
		target.addUnit(this);
		currentField = target;
		
		Skeleton.returnMethod("step", this, parameters);
	}
	//A nextAction alapjan csinal valamit.
	public void action(){
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("action", this, parameters);
		
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
		
		Skeleton.returnMethod("action", this, parameters);
	}
	
}
