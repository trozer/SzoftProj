package szoftProj;

public abstract class Unit {
	protected boolean dead = false;
	protected Field currentField;
	
	public void action(){}
	
	public void kill(){
		dead = true;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public void accept(Player launcher, Field target){}
	
	public void accept(Field launcher, Player target){}
}
