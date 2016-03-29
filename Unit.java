package szoftProj;

import java.util.ArrayList;

public abstract class Unit {
	protected boolean dead = false;
	protected Field currentField;
	
	public void action(){//Az egység cselekvésének végrehajtásához szükséges metódus, alapértelmezetten nem csinál semmit.
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("action", this, parameters);
		Skeleton.returnMethod("action", this, parameters);
	}
	
	public void kill(){//A dead attributumot true-ra állítja.
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("kill", this, parameters);
		
		dead = true;
		
		Skeleton.returnMethod("kill", this, parameters);
	}
	
	public boolean isDead(){//A dead attributum aktuális értékét adja vissza.
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object boolObj = new Object();
		Skeleton.registerHashCode(boolObj.hashCode(),"boolean");
		parameters.add(boolObj);
		Skeleton.callMethod("isDead", this, parameters);
		Skeleton.returnMethod("isDead", this, parameters);
		return dead;
	}
	
	public void accept(Player launcher, Field target){//Egységek, mint Player -> Field tranzakció tárgyaként való alapértelmezett viselkedése, nem csinál semmit.
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(launcher);
		parameters.add(target);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("accept", this, parameters);
		Skeleton.returnMethod("accept", this, parameters);
	}
	
	public void accept(Field launcher, Player target){//Egységek, mint Field -> Player tranzakció tárgyaként való alapértelmezett viselkedése, nem csinál semmit.
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(target);
		parameters.add(launcher);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("accept", this, parameters);
		Skeleton.returnMethod("accept", this, parameters);
	}
}
