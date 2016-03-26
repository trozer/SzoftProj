package szoftProj;

import java.util.*;

public abstract class Field {
	
	//--------Attribútumok--------
	
	protected Map<Direction, Field> neighbours = new TreeMap<Direction, Field>();
	protected Unit containedUnit;
	
	//-------Metódusok---------
	
	Field(){
		neighbours.put(Direction.NORTH, null);
		neighbours.put(Direction.SOUTH, null);
		neighbours.put(Direction.WEST, null);
		neighbours.put(Direction.EAST, null);
	}
	
	public abstract void doo(Player player);
	public abstract void doo(Bullet bullet);
	
	public Field getNeighbourInDirection(Direction dir){
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(dir);
		
		for (Map.Entry<Direction, Field> entry : neighbours.entrySet()) {
			if (entry.getKey() == dir){
				
				parameters.add(entry.getValue());
				Skeleton.callMethod("Field - getNeighbourInDirection", this, parameters);
				
				return entry.getValue();
			}
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Field - getNeighbourInDirection", this, parameters);
		return null;
	}
	
	public boolean addUnit(Unit unit){
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		
		if (containedUnit == null){
			containedUnit = unit;
			
			parameters.add("True");
			Skeleton.callMethod("Field - addTnit", this, parameters);
			
			return true;
		}
		else
			parameters.add("False");
		Skeleton.callMethod("Field - addUnit", this, parameters);
		return false;
	}
	
	public void removeUnit(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Field - reoveUnit", this, parameters);
		containedUnit = null;
	}
	
	public void addNeighbour(Direction direction, Field neighbour){
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(direction);
		parameters.add(neighbour);
		
		for (Map.Entry<Direction, Field> entry : neighbours.entrySet()) {
			if (entry.getKey() == direction){
				entry.setValue(neighbour);
			}
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Field - addNeighbour", this, parameters);
		
	}
	
}
