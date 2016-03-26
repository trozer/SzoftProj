package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Scale extends Field {

	//--------Attribútumok--------
	
	protected Map<Direction, Field> neighbours;
	protected Unit containedUnit;
	private Gate myGate;
	
	//-------Metódusok---------
	
	/*public Scale(Gate mygate, Unit containUnit){
		this.mygate = mygate;
		this.containedUnit = containUnit;
	}*/
	
	public Scale(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(neighbours);
		parameters.add(containedUnit);
		parameters.add(myGate);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Scale - konstruktor", this, parameters);
	}
	
	public void setGate(Gate gate){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(gate);
		if(myGate == null) myGate = gate;
		else System.out.println("setGate: már van beállítva gate!");
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Scale - setGate", this, parameters);
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
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Scale - doo-player-MOVE", this, parameters);
		break;
		
        case GRAB:
        	if (containedUnit != null){
    			containedUnit.accept(this, player);
    		}
    		myGate.close();
    		parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("Scale - doo-player-GRAB", this, parameters);
    		break;
    		
        default:
        	//TODO
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("Scale - doo-player-default", this, parameters);
        	break;
		}
		
	}
	
	@Override
	public void doo(Bullet bullet){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:
        	bullet.step(this);
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("Scale - doo-bullet-MOVE", this, parameters);
        	break;
        default:
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("Scale - doo-bullet-default", this, parameters);
        	break;
		}
	}
	
	
	@Override
	public boolean addUnit(Unit unit){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		if (containedUnit == null){
			containedUnit = unit;
			myGate.open();
			parameters.add("True");
    		Skeleton.callMethod("Scale - addUnit", this, parameters);
			return true;
		}
		else
			parameters.add("False");
			Skeleton.callMethod("Scale - addUnit", this, parameters);
			return false;
	}
	
	@Override
	public void removeUnit(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		containedUnit = null;
		myGate.close();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Scale - removeUnit", this, parameters);
	}
}
