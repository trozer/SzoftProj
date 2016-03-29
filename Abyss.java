package szoftProj;

import java.util.ArrayList;

public class Abyss extends Field {

	public Abyss() {
	}

	@Override
	public void doo(Player player) {	//a player ha r�l�p meghal
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(player);
		switch (player.getAction().getType()) {
        case MOVE:	//ha a j�t�kos l�pni akar
        		player.step(this);
        		player.kill();
        		break;
        		
        default:	//minden m�s eset
        	//TODO
        	
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}

	@Override
	public void doo(Bullet bullet) {	//a bullet, ha r�l�p tov�bbl�pteti
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:	//ha meg�rkezik/l�pni akar
        	bullet.step(this);
        	break;
        default:	//minden m�s eset
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}

	/*@Override
	public Field getNeighbourInDirection(Direction dir) {	//visszaadja 
		// TODO Auto-generated method stub
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dire = new Object();
		Skeleton.registerHashCode(dir.hashCode(), "Direction");
		parameters.add(dire);
		return super.getNeighbourInDirection(dir);
		Skeleton.callMethod("getNeighbourInDirection", this, parameters);
		Skeleton.returnMethod("getNeighbourInDirection", this, parameters);
	}*/

	@Override
	public boolean addUnit(Unit unit) {//ha szakad�kba rakunk egy t�rgyat akkor az mag�ra rakja majd r�gt�n ki is ny�rja
		Object object = new Object();
		Skeleton.registerHashCode(object.hashCode(), "boolean");
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		containedUnit = unit;
		containedUnit.kill();
		parameters.add(object);
		Skeleton.callMethod("addUnit", this, parameters);
		Skeleton.returnMethod("addUnit", this, parameters);
		/* TODO Auto-generated method stub
		return super.addUnit(unit);*/
		return true;
	}

	@Override
	public void removeUnit() {//elt�vol�tja a r� helyezett unitot, soha nem fog lefutni, mert nem lehet rajta unit
		// TODO Auto-generated method stub
		super.removeUnit();
	}

	/*@Override
	public void addNeighbour(Direction direction, Field neighbour) {
		// TODO Auto-generated method stub
		super.addNeighbour(direction, neighbour);
	}*/

}
