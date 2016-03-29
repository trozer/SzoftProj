package szoftProj;

import java.util.ArrayList;

public abstract class Unit {
	protected boolean dead = false;
	protected Field currentField;
	
	public void action(){//Az egys�g cselekv�s�nek v�grehajt�s�hoz sz�ks�ges met�dus, alap�rtelmezetten nem csin�l semmit.
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("action", this, parameters);
		Skeleton.returnMethod("action", this, parameters);
	}
	
	public void kill(){//A dead attributumot true-ra �ll�tja.
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("kill", this, parameters);
		
		dead = true;
		
		Skeleton.returnMethod("kill", this, parameters);
	}
	
	public boolean isDead(){//A dead attributum aktu�lis �rt�k�t adja vissza.
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object boolObj = new Object();
		Skeleton.registerHashCode(boolObj.hashCode(),"boolean");
		parameters.add(boolObj);
		Skeleton.callMethod("isDead", this, parameters);
		Skeleton.returnMethod("isDead", this, parameters);
		return dead;
	}
	
	public void accept(Player launcher, Field target){//Egys�gek, mint Player -> Field tranzakci� t�rgyak�nt val� alap�rtelmezett viselked�se, nem csin�l semmit.
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(launcher);
		parameters.add(target);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("accept", this, parameters);
		Skeleton.returnMethod("accept", this, parameters);
	}
	
	public void accept(Field launcher, Player target){//Egys�gek, mint Field -> Player tranzakci� t�rgyak�nt val� alap�rtelmezett viselked�se, nem csin�l semmit.
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(target);
		parameters.add(launcher);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("accept", this, parameters);
		Skeleton.returnMethod("accept", this, parameters);
	}
}
