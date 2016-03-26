package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Road extends Field {

	// --------Attribútumok--------

	//protected Map<Direction, Field> neighbours;
	//protected Unit containedUnit;
	
	
	// -------Metódusok---------

	public Road() {
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
        		parameters.add(Skeleton.getEmpty());
        		Skeleton.callMethod("Road - doo-player-MOVE", this, parameters);
        		Skeleton.returnMethod("Road - doo-player-MOVE", this, parameters);
        		break;
        
        case GRAB:
        		if (containedUnit != null){
        			containedUnit.accept(this, player);}
        		parameters.add(Skeleton.getEmpty());
        		Skeleton.callMethod("Road - doo-player-GRAB", this, parameters);
        		Skeleton.returnMethod("Road - doo-player-GRAB", this, parameters);
        		break;
        		
        default:
        	//TODO
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("Road - doo-player-default", this, parameters);
    		Skeleton.returnMethod("Road - doo-player-default", this, parameters);
        	break;
		}	
		
		//drop -adunit intézi
	}

	@Override
	public void doo(Bullet bullet) {
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:
        	bullet.step(this);
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("Road - doo-bullet-MOVE", this, parameters);
    		Skeleton.returnMethod("Road - doo-bullet-MOVE", this, parameters);
        	break;
        default:
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("Road - doo-bullet-default", this, parameters);
    		Skeleton.returnMethod("Road - doo-bullet-default", this, parameters);
        	break;
		}
	}

	@Override
	public boolean addUnit(Unit unit) {
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		if (containedUnit == null) {
			containedUnit = unit;
			parameters.add("True");
    		Skeleton.callMethod("Road - addUnit", this, parameters);
    		Skeleton.returnMethod("Road - addUnit", this, parameters);
			return true;
		} else
			parameters.add("False");
			Skeleton.callMethod("Road - addUnit", this, parameters);
			Skeleton.returnMethod("Road - addUnit", this, parameters);
			return false;
	}

	/*
	 * @Override public void removeUnit(){ containedUnit = null; }
	 */
}
