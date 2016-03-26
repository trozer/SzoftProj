package szoftProj;

import java.util.Map;

public class Scale extends Field {

	//--------Attrib�tumok--------
	
	protected Map<Direction, Field> neighbours;
	protected Unit containedUnit;
	private Gate myGate;
	
	//-------Met�dusok---------
	
	/*public Scale(Gate mygate, Unit containUnit){
		this.mygate = mygate;
		this.containedUnit = containUnit;
	}*/
	
	public Scale(){}
	
	public void setGate(Gate gate){
		if(myGate == null) myGate = gate;
		else System.out.println("setGate: m�r van be�ll�tva gate!");
	}
	
	@Override
	public void doo(Player player){
		player.getAction();
		
		switch (ewAction.getType()) {
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
		
	}
	
	@Override
	public void doo(Bullet bullet){
		switch (newAction.getType()) {
        case MOVE:
        	bullet.step(this);
        	break;
        default:
        	break;
		}
	}
	
	
	@Override
	public boolean addUnit(Unit unit){
		if (containedUnit == null){
			containedUnit = unit;
			myGate.open();
			return true;
		}
		else
		return false;
	}
	
	@Override
	public void removeUnit(){
		containedUnit = null;
		myGate.close();
	}
}
