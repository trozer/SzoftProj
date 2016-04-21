package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Scale extends Field {

	//--------Attribútumok--------
	
	
	private Gate myGate;
	
	//-------Metódusok---------
	
	
	
	public Scale(){	//konstruktor
		super();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Scale - konstruktor", this, parameters);
		Skeleton.returnMethod("Scale - konstruktor", this, parameters);
		myGate = null;
	}
	
	public void setGate(Gate gate){	//beállítja a saját kapuját, azaz a hozzá tartozó kaput, ha még nincs neki
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(gate);
		if(myGate == null) myGate = gate;
		else System.out.println("setGate: már van beállítva gate!");
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("setGate", this, parameters);
		Skeleton.returnMethod("setGate", this, parameters);
	}
	
	@Override
	public void doo(Player player){	//a játékos cselekedetére "reagál"
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(player);
		player.getAction();
		
		switch (player.getAction().getType()) {
        case MOVE:	//ha a játékos rálép magára húzza és kinyitja a hozzá tartozó kaput
		
		if (containedUnit != null){
				containedUnit.accept(this, player);
		}
		if (containedUnit == null){
				player.step(this);
				myGate.open();
			}
		break;
		
        case GRAB:	//ha a játékos leszed róla egy tárgyat, a hozzá tartozó kapu bezárul
        	if (containedUnit != null){
    			containedUnit.accept(this, player);
    		}
    		myGate.close();
    		break;
    		
        default:	//minden más eset
        	//TODO
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}
	
	@Override
	public void doo(Bullet bullet){		//lövedékre reagál
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:	//magára húzza a lövedéket, a lövedék nem nyitja ki a kaput
        	bullet.step(this);
        	break;
        default:	//minden más eset
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}
	
	
	@Override
	public boolean addUnit(Unit unit){	//unitot hejez el a mérlegen, ha nincs rajta semmi
		Object object = new Object();
		Skeleton.registerHashCode(object.hashCode(), "boolean");
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		if (containedUnits.size() < 3 ){
			containedUnits.add(unit);
			parameters.add(object);
			myGate.open();
			Skeleton.callMethod("addUnit", this, parameters);
			Skeleton.returnMethod("addUnit", this, parameters);
			return true;
		}
		else
			parameters.add(object);
			Skeleton.callMethod("addUnit", this, parameters);
			Skeleton.returnMethod("addUnit", this, parameters);
			return false;
	}

	@Override
	public void removeUnit(){	//eltávolítja a unitot ami a mérlegen van, ilyenkor a kapu bezárul 
		ArrayList<Object> parameters = new ArrayList<Object>();
		containedUnit = null;
		myGate.close();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("removeUnit", this, parameters);
		Skeleton.returnMethod("removeUnit", this, parameters);
	}
	
	//TODO,testing phase, correct this
	@Override
	public String toString(){
		if(myGate != null)
			return "mérleg " + super.toString() + " " + myGate.toString();
		else
			return "mérleg " + super.toString();
	}
}
