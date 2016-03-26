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
		Skeleton.callMethod("Gate - open", this, parameters);
		Skeleton.returnMethod("Gate - open", this, parameters);
	}
	
	public void close(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		opened = false;
		if(containedUnit != null){
		containedUnit.kill();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Gate - close", this, parameters);
		Skeleton.returnMethod("Gate - close", this, parameters);
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
    		Skeleton.callMethod("Gate - doo-player-MOVE", this, parameters);
    		Skeleton.returnMethod("Gate - doo-player-MOVE", this, parameters);
        	break;
		
        case GRAB:
        	if (containedUnit != null){
    			containedUnit.accept(this, player);
        	}
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("Gate - doo-player-GRAB", this, parameters);
    		Skeleton.returnMethod("Gate - doo-player-GRAB", this, parameters);
        	break;
        default:
        	//TODO
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("Gate - doo-player-default", this, parameters);
    		Skeleton.returnMethod("Gate - doo-player-default", this, parameters);
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
    		Skeleton.callMethod("Gate - doo-bullet-MOVE", this, parameters);
    		Skeleton.returnMethod("Gate - doo-bullet-MOVE", this, parameters);
        	break;
        default:
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("Gate - doo-bullet-defalt", this, parameters);
    		Skeleton.returnMethod("Gate - doo-bullet-defalt", this, parameters);
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
    		Skeleton.callMethod("Gate - addUnit", this, parameters);
    		Skeleton.returnMethod("Gate - addUnit", this, parameters);
			return true;
		}
		else
			parameters.add("False");
		Skeleton.callMethod("Gate - addUnit", this, parameters);
		Skeleton.returnMethod("Gate - addUnit", this, parameters);
		return false;
	}
	
	/*@Override
	public void removeUnit(){
		containedUnit = null;
	}*/

}
