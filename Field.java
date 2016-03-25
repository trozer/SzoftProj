package projlab;

import java.util.*;

public abstract class Field {
	
	//--------Attribútumok--------
	
	protected Map<Direction, Field> neighbours;
	protected Unit containedUnit;
	
	//-------Metódusok---------
	
	public abstract void doo(Player player);
	public abstract void doo(Bullet bullet);
	
	public Field getNeighbourInDirection(Direction dir){
		for (Map.Entry<Direction, Field> entry : neighbours.entrySet()) {
			if (entry.getKey() == dir){
				return entry.getValue();
			}
		}
		return null;
	}
	
	public boolean addUnit(Unit unit){
		if (containedUnit == null){
			containedUnit = unit;
			return true;
		}
		else
		return false;
	}
	
	public void removeUnit(){
		containedUnit = null;
	}
	
	public void addNeighbour(Direction direction, Field neighbour){
		for (Map.Entry<Direction, Field> entry : neighbours.entrySet()) {
			if (entry.getKey() == direction){
				entry.setValue(neighbour);
			}
		}
		
	}
	
}
