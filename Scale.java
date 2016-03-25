package projlab;

import java.util.Map;

public class Scale extends Field {

	//--------Attribútumok--------
	
	protected Map<Direction, Field> neighbours;
	protected Unit containedUnit;
	private Gate mygate;
	
	//-------Metódusok---------
	
	public Scale(Gate mygate, Unit containUnit){
		this.mygate = mygate;
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
			if (containedUnit == null){
				player.step(this);
				mygate.open();
			}
	}
	
	@Override
	public void doo(Bullet bullet){
		bullet.step(this);
	}
	
	
	@Override
	public boolean addUnit(Unit unit){
		if (containedUnit == null){
			containedUnit = unit;
			mygate.open();
			return true;
		}
		else
		return false;
	}
	
	@Override
	public void removeUnit(){
		containedUnit = null;
		mygate.close();
	}
}
