package szoftProj;

import java.util.*;

public abstract class Field {
	
	//--------Attrib�tumok--------
	
	protected Map<Direction, Field> neighbours = new TreeMap<Direction, Field>();
	protected Unit containedUnit;
	
	//-------Met�dusok---------
	
	Field(){
		neighbours.put(Direction.NORTH, null);
		neighbours.put(Direction.SOUTH, null);
		neighbours.put(Direction.WEST, null);
		neighbours.put(Direction.EAST, null);
		containedUnit = null;
	}
	
	public abstract void doo(Player player);
	public abstract void doo(Bullet bullet);
	
	//Visszaadja a pram�ter kapott ir�ny fel�li szomsz�dj�t
	public Field getNeighbourInDirection(Direction dir){
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dire = new Object();
		Skeleton.registerHashCode(dire.hashCode(),"Direction");
		parameters.add(dire);
		
		for (Map.Entry<Direction, Field> entry : neighbours.entrySet()) {	//�gy l�pked�k v�gig egy Map objektumon
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
		return null;	//ha nincs szomsz�dja a megadott ir�nyban
	}
	
	public boolean addUnit(Unit unit){	//�j unitot adunk a mez�h�z; csak akkor tehetj�k meg, ha az �res
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
	
	public void removeUnit(){	//leszedj�k a mez�r�l a unitot
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("removeUnit", this, parameters);
		Skeleton.returnMethod("removeUnit", this, parameters);
		containedUnit = null;
	}
	
	public void addNeighbour(Direction direction, Field neighbour){	//be�ll�tjuk egy mez� param�ter�l kapott ir�nybeli szomsz�dj�t, egy param�re�l kapott t�p�s� mez�re
		
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
