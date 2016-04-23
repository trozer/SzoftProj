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

	public void replaceField(){
		//game.replaceField(currentField); //TODO
		currentField.removeUnit(this);
		kill();
	}
	
	public String toString(){
    	return "Replikátor";
    }
}
