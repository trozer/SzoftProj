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
		containedUnit = null;
	}
	
	public abstract void doo(Player player);
	public abstract void doo(Bullet bullet);
	
	//Visszaadja a praméter kapott irány felõli szomszédját
	public Field getNeighbourInDirection(Direction dir){
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dire = new Object();
		Skeleton.registerHashCode(dire.hashCode(),"Direction");
		parameters.add(dire);
		
		for (Map.Entry<Direction, Field> entry : neighbours.entrySet()) {	//így lépkedük végig egy Map objektumon
			if (entry.getKey() == dir){
				
				parameters.add(entry.getValue());
				Skeleton.callMethod("getNeighbourInDirection", this, parameters);
				Skeleton.returnMethod("getNeighbourInDirection", this, parameters);
				return entry.getValue();
			}
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("getNeighbourInDirection", this, parameters);
		Skeleton.returnMethod("getNeighbourInDirection", this, parameters);
		return null;	//ha nincs szomszédja a megadott irányban
	}
	
	public boolean addUnit(Unit unit){	//új unitot adunk a mezõhöz; csak akkor tehetjük meg, ha az üres
		Object object = new Object();
		Skeleton.registerHashCode(object.hashCode(), "boolean");
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		
		if (containedUnit == null){
			containedUnit = unit;
			parameters.add(object);
			Skeleton.callMethod("addUnit", this, parameters);
			Skeleton.returnMethod("addUnit", this, parameters);
			return true;
		}
		else
			parameters.add(object);
			Skeleton.callMethod("addUnit", this, parameters);
			Skeleton.returnMethod("addUnit", this, parameters);
			return false;
	}
	
	public void removeUnit(){	//leszedjük a mezõrõl a unitot
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("removeUnit", this, parameters);
		Skeleton.returnMethod("removeUnit", this, parameters);
		containedUnit = null;
	}
	
	public void addNeighbour(Direction direction, Field neighbour){	//beállítjuk egy mezõ paraméterül kapott iránybeli szomszédját, egy paraméreül kapott típúsú mezõre
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dire = new Object();
		Skeleton.registerHashCode(dire.hashCode(),"Direction");
		parameters.add(dire);
		parameters.add(neighbour);
		
		for (Map.Entry<Direction, Field> entry : neighbours.entrySet()) {
			if (entry.getKey() == direction){
				entry.setValue(neighbour);
			}
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("addNeighbour", this, parameters);
		Skeleton.returnMethod("addNeighbour", this, parameters);
		
	}
	
}
