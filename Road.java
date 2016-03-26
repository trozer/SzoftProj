package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Road extends Field {

	// --------Attribútumok--------


	//protected Map<Direction, Field> neighbours;
	//protected Unit containedUnit;


	// -------Metódusok---------

	public Road() {
		super();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());

		Skeleton.callMethod("Road - konstruktor", this, parameters);
		Skeleton.returnMethod("Road - konstruktor", this, parameters);

	}

	@Override
	public void doo(Player player){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(player);
		
		player.getAction();
		switch (player.getAction().getType()) {
        case MOVE:
        		if (containedUnit != null){
				containedUnit.accept(this, player);
        		}
        		if (containedUnit == null)
        			player.step(this);
        		break;
        
        case GRAB:
        		if (containedUnit != null){
        			containedUnit.accept(this, player);}
        		break;
        		
        default:
        	//TODO
        	
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
		//drop -adunit intézi
	}

	@Override
	public void doo(Bullet bullet) {
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:
        	bullet.step(this);
        	break;
        default:
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}

	@Override
	public boolean addUnit(Unit unit) {
		Object object = new Object();
		Skeleton.registerHashCode(object.hashCode(), "boolean");
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		if (containedUnit == null) {
			containedUnit = unit;
			parameters.add(object);
    		Skeleton.callMethod("addUnit", this, parameters);
    		Skeleton.returnMethod("addUnit", this, parameters);
			return true;
		} else
			parameters.add(object);
			Skeleton.callMethod("addUnit", this, parameters);
			Skeleton.returnMethod("addUnit", this, parameters);
			return false;
	}

	/*
	 * @Override public void removeUnit(){ containedUnit = null; }
	 */
}
