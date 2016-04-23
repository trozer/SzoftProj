package szoftProj;

public class Replicator extends ActionUnit{
	
	private Game game;
	
	public Replicator(Game game){
		this.game = game;
	}
	//TODO
	public void replaceField(){
		//game.replaceField(currentField);
		kill();
	}
	
	public String toString(){
    	return "Replikátor";
    }
}
