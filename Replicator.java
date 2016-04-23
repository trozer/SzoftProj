package szoftProj;

public class Replicator extends ActionUnit{
	
	private Game game;
	
	public Replicator(Game game, Direction direction, Field field){
		this.game = game;
		this.currentDirection = direction;
		this.currentField = field;
		this.weight = 15;
		
		//TODO a replikator mozgatását még ki kell találni
		this.nextAction = new Action(ActionType.MOVE, Direction.NORTH, null);
	}

	//Kezdemenyezi a mezo cserejet (utra), majd megoli magat
	public void replaceField(){
		game.replaceField(currentField);
		currentField.removeUnit(this);
		kill();
	}
	
	//Eltavolitja magat a jelenlegi mezorol, majd hozzaadja magat a parameterkent kapott uj mezohoz,
	//es beallitja a currentFieldet az uj mezore.
	public void step(Field target){
		currentField.removeUnit();
		target.addUnit(this);
		currentField = target;
	}
	
	public String toString(){
    	return "Replikátor";
    }
}
