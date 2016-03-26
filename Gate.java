package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Gate extends Field {
	
	//--------Attribútumok--------
	
	private boolean opened;
	//protected Map<Direction, Field> neighbours;
	//protected Unit containedUnit;
	
	//-------Metódusok---------
	
	public Gate(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Gate - konstruktor", this, parameters);
		Skeleton.returnMethod("Gate - konstruktor", this, parameters);
	}
	
	public void open(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		opened = true;
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("open", this, parameters);
		Skeleton.returnMethod("open", this, parameters);
	}
	
	public void close(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		opened = false;
		if(containedUnit != null){
		containedUnit.kill();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("close", this, parameters);
		Skeleton.returnMethod("close", this, parameters);
		}
	}
	
	
	
	@Override
	public void doo(Player player){
		ArrayList<Object> parameters = new ArrayList<Object>();
		player.getAction();
		
		switch (player.getAction().getType()) {
        case MOVE:
        	if (containedUnit != null && opened == true){
				containedUnit.accept(this, player);
        	}
        	if (containedUnit == null && opened == true){
				player.step(this);
			}
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("doo MOVE", this, parameters);
    		Skeleton.returnMethod("doo MOVE", this, parameters);
        	break;
		
        case GRAB:
        	if (containedUnit != null){
    			containedUnit.accept(this, player);
        	}
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("doo GRAB", this, parameters);
    		Skeleton.returnMethod("doo GRAB", this, parameters);
        	break;
        default:
        	//TODO
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("doo default", this, parameters);
    		Skeleton.returnMethod("doo default", this, parameters);
        	break;
		}
		
		
	}
	
	@Override
	public void doo(Bullet bullet){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:
        	if (opened == true){
        		bullet.step(this);
        	}
        	else
        		containedUnit.kill();
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("doo MOVE", this, parameters);
    		Skeleton.returnMethod("doo MOVE", this, parameters);
        	break;
        default:
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("doo defalt", this, parameters);
    		Skeleton.returnMethod("doo defalt", this, parameters);
        	break;
		}
	}
	
	@Override
	public boolean addUnit(Unit unit){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		if (containedUnit == null && opened == true){
			containedUnit = unit;
			parameters.add("True");
    		Skeleton.callMethod("addUnit", this, parameters);
    		Skeleton.returnMethod("addUnit", this, parameters);
			return true;
		}
		else
			parameters.add("False");
		Skeleton.callMethod("addUnit", this, parameters);
		Skeleton.returnMethod("addUnit", this, parameters);
		return false;
	}
	
	/*@Override
	public void removeUnit(){
		containedUnit = null;
	}*/

}
