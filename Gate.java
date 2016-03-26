package szoftProj;

import java.util.Map;

public class Gate extends Field {
	
	//--------Attribútumok--------
	
	private boolean opened;
	protected Map<Direction, Field> neighbours;
	protected Unit containedUnit;
	
	//-------Metódusok---------
	
	public Gate(){}
	
	public void open(){
		opened = true;
	}
	
	public void close(){
		opened = false;
		if(containedUnit != null){
		containedUnit.kill();
		}
	}
	
	
	
	@Override
	public void doo(Player player){
		player.getAction();
		
		switch (player.getAction().getType()) {
        case MOVE:
        	if (containedUnit != null && opened == true){
				containedUnit.accept(this, player);
        	}
        	if (containedUnit == null && opened == true){
				player.step(this);
			}
        	break;
		
        case GRAB:
        	if (containedUnit != null){
    			containedUnit.accept(this, player);
        	}
        	break;
        default:
        	//TODO
        	break;
		}
		
		
	}
	
	@Override
	public void doo(Bullet bullet){
		/*switch (bullet.getAction().getType()) {
        case MOVE:
        	if (opened == true){
        		bullet.step(this);
        	}
        	else
        		containedUnit.kill();
        	break;
        default:
        	break;
		}*/
	}
	
	@Override
	public boolean addUnit(Unit unit){
		if (containedUnit == null && opened == true){
			containedUnit = unit;
			return true;
		}
		else
		return false;
	}
	
	/*@Override
	public void removeUnit(){
		containedUnit = null;
	}*/

}
