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
		//newAction.getType();
		//move
		if (containedUnit != null){
				containedUnit.accept(this, player);
		}
		else
			if (containedUnit == null){
				player.step(this);
			}
		
		//grab
		/*if (containedUnit != null){
			containedUnit.accept(this, player);
		}*/
	}
	
	@Override
	public void doo(Bullet bullet){
		if (opened == true){
		bullet.step(this);
		}
		else
			containedUnit.kill();
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
