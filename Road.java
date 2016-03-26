package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Road extends Field {

	// --------Attribútumok--------

	
	
	// -------Metódusok---------

	public Road() {
		super();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());

		Skeleton.callMethod("konstruktor", this, parameters);
		Skeleton.returnMethod("konstruktor", this, parameters);

		Skeleton.callMethod("konstruktor", null, parameters);
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
        		Skeleton.callMethod("doo MOVE", this, parameters);
        		Skeleton.returnMethod("doo MOVE", this, parameters);
        		break;
        
        case GRAB:
        		if (containedUnit != null){
        			containedUnit.accept(this, player);}
        		parameters.add(Skeleton.getEmpty());
        		Skeleton.callMethod("doo GRAB", this, parameters);
        		Skeleton.returnMethod("doo GRAB", this, parameters);
        		break;
        		
        default:
        	//TODO
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("doo default", this, parameters);
    		Skeleton.returnMethod("doo default", this, parameters);
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
    		Skeleton.callMethod("doo MOVE", this, parameters);
    		Skeleton.returnMethod("doo MOVE", this, parameters);
        	break;
        default:
        	parameters.add(Skeleton.getEmpty());
    		Skeleton.callMethod("doo-bullet-default", this, parameters);
    		Skeleton.returnMethod("doo default", this, parameters);
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
    		Skeleton.callMethod("addUnit", this, parameters);
    		Skeleton.returnMethod("addUnit", this, parameters);
			return true;
		} else
			parameters.add("False");
			Skeleton.callMethod("addUnit", this, parameters);
			Skeleton.returnMethod("addUnit", this, parameters);
			return false;
	}

	/*
	 * @Override public void removeUnit(){ containedUnit = null; }
	 */
}
