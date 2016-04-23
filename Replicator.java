package szoftProj;

public class Replicator extends ActionUnit{
	
	private Game game;
	
	public Replicator(Game game, Direction direction, Field field){
		this.game = game;
		this.currentDirection = direction;
		this.currentField = field;
		this.weight = 15;
		this.nextAction = new Action(ActionType.MOVE, direction, null);
	}

	//Kezdemenyezi a mezo cserejet (utra), majd megoli magat
	public void replaceField(){
		game.replaceField(currentField);
		currentField.removeUnit(this);
		kill();
	}
	
	//Eltavolitja magat a jelenlegi mezorol, majd hozzaadja magat a parameterkent kapott uj mezohoz,
	//es beallitja a currentFieldet az uj mezore. Illetve v�laszt 
	public void step(Field target){
		currentField.removeUnit();
		target.addUnit(this);
		currentField = target;
		
		//TODO ir�nyv�ltoztat�st meg kell m�g csin�lni
		nextAction = new Action(ActionType.MOVE, currentDirection, null);
	}
	
	public String toString(){
		String irany;
		String elet;
		
		if (currentDirection == Direction.NORTH) irany = "�szak";
		else if (currentDirection == Direction.EAST) irany = "kelet";
		else if (currentDirection == Direction.SOUTH) irany = "d�l";
		else irany = "nyugat";
		
		if (dead == false) elet = "�l";
		else elet = "halott";
		
    	return "    Replik�tor, mozg�s cselekv�st akar v�grehajtani, " + irany + " ir�nyba n�z, " + elet;
    }
}
