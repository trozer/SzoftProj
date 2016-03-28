package szoftProj;

import java.util.ArrayList;

public abstract class Unit {
	protected boolean dead = false;
	protected Field currentField;
	
	public void action(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("action", this, parameters);
		Skeleton.returnMethod("action", this, parameters);
	}
	
	public void kill(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("kill", this, parameters);
		
		dead = true;
		
		Skeleton.returnMethod("kill", this, parameters);
	}
	
	public boolean isDead(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		Skeleton.registerHashCode(Boolean.hashCode(dead), "boolean");
		parameters.add(new Boolean(dead));
		Skeleton.callMethod("isDead", this, parameters);
		Skeleton.returnMethod("isDead", this, parameters);
		return dead;
	}
	
	public void accept(Player launcher, Field target){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(launcher);
		parameters.add(target);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("accept", this, parameters);
		Skeleton.returnMethod("accept", this, parameters);
	}
	
	public void accept(Field launcher, Player target){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(target);
		parameters.add(launcher);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("accept", this, parameters);
		Skeleton.returnMethod("accept", this, parameters);
	}
}
