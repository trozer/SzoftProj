package szoftProj;

import java.util.Map;

public class Road extends Field {

	// --------Attribútumok--------

	protected Map<Direction, Field> neighbours;
	protected Unit containedUnit;
	
	
	// -------Metódusok---------

	public Road() {
	}

	@Override
	public void doo(Player player){
		player.getAction();
		
		switch (newAction.getType()) {
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
		
		//drop -adunit intézi
	}

	@Override
	public void doo(Bullet bullet) {
		switch (newAction.getType()) {
        case MOVE:
        	bullet.step(this);
        	break;
        default:
        	break;
		}
	}

	@Override
	public boolean addUnit(Unit unit) {
		if (containedUnit == null) {
			containedUnit = unit;
			return true;
		} else
			return false;
	}

	/*
	 * @Override public void removeUnit(){ containedUnit = null; }
	 */
}
