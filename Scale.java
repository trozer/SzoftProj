package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Scale extends Field {

	//--------Attribútumok--------
	
	//protected Map<Direction, Field> neighbours;
	//protected Unit containedUnit;
	private Gate myGate;
	
	//-------Metódusok---------
	
	/*public Scale(Gate mygate, Unit containUnit){
		this.mygate = mygate;
		this.containedUnit = containUnit;
	}*/
	
	public Scale(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Scale - konstruktor", this, parameters);
		Skeleton.returnMethod("Scale - konstruktor", this, parameters);
	}
	
	public void setGate(Gate gate){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(gate);
		if(myGate == null) myGate = gate;
		else System.out.println("setGate: már van beállítva gate!");
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("setGate", this, parameters);
		Skeleton.returnMethod("setGate", this, parameters);
	}
	
	@Override
	public void doo(Player player){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(player);
		player.getAction();
		
		switch (player.getAction().getType()) {
        case MOVE:
		
		if (containedUnit != null){
				containedUnit.accept(this, player);
		}
		if (containedUnit == null){
				player.step(this);
				myGate.open();
			}
		break;
		
        case GRAB:
        	if (containedUnit != null){
    			containedUnit.accept(this, player);
    		}
    		myGate.close();
    		break;
    		
        default:
        	//TODO
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}
	
	@Override
	public void doo(Bullet bullet){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:
        	bullet.step(this);
        	break;
        default:
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}
	
	
	@Override
	public boolean addUnit(Unit unit){
		Object object = new Object();
		Skeleton.registerHashCode(object.hashCode(), "boolean");
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		if (containedUnit == null){
			containedUnit = unit;
			myGate.open();
			parameters.add(object);
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
	public void removeUnit(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		containedUnit = null;
		myGate.close();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("removeUnit", this, parameters);
		Skeleton.returnMethod("removeUnit", this, parameters);
	}
}
