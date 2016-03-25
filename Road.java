package szoftProj;

import java.util.Map;

public class Road extends Field {
	
	//--------Attribútumok--------

	protected Map<Direction, Field> neighbours;
	protected Unit containedUnit;
	
	//-------Metódusok---------
	
	public Road(Unit containUnit){
		this.containedUnit = containUnit;
	};
	
	@Override
	public void doo(Player player){
		player.getAction();
		//newAction.getType();
		
		if (containedUnit != null){
				containedUnit.accept(this, player);
		}
		else
			if (containedUnit == null)
				player.step(this);
	}
	
	@Override
	public void doo(Bullet bullet){
		bullet.step(this);
	}
	
	@Override
	public boolean addUnit(Unit unit){
		if (containedUnit == null){
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
