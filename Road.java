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
		//newAction.getType();
		//move
		switch (ewAction.getType()) {
        case MOVE:
        		if (containedUnit != null){
				containedUnit.accept(this, player);
        		}
        		else
        			if (containedUnit == null)
        				player.step(this);
        		break;
        
        case GRAB:
        		if (containedUnit != null){
        			containedUnit.accept(this, player);
		}	
				
		//grab
		/*if (containedUnit != null){
			containedUnit.accept(this, player);
		}*/
		
		//drop -adunit intézi
	}

	@Override
	public void doo(Bullet bullet) {
		bullet.step(this);
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
