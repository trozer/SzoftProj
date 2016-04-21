package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Road extends Field {

	// --------Attribútumok--------


	


	// -------Metódusok---------

	public Road() {	//konstruktor
		super();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());

		Skeleton.callMethod("Road - konstruktor", this, parameters);
		Skeleton.returnMethod("Road - konstruktor", this, parameters);

	}

	@Override
	public void doo(Player player){	//a játékos cselekedetére "reagál"
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(player);
		
		player.getAction();
		switch (player.getAction().getType()) {
        case MOVE:	//ha a játékos lépni akar
        		if (containedUnit != null){
				containedUnit.accept(this, player);
        		}
        		if (containedUnit == null)
        			player.step(this);
        		break;
        
        case GRAB:	//ha a játékos fel akar venni valamit
        		if (containedUnit != null){
        			containedUnit.accept(this, player);}
        		break;
        		
        default:	//minden más eset
        	//TODO
        	
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
		//drop -adunit intézi
	}

	@Override
	public void doo(Bullet bullet) {	//a lövedék cselekedetétre reagál
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:	//ha megérkezik/lépni akar
        	bullet.step(this);
        	break;
        default:	//minden más eset
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}

	@Override
	public boolean addUnit(Unit unit) {	//ha nincs még unit az úton rátehetünk egyet, ha van akkor nem
		Object object = new Object();
		Skeleton.registerHashCode(object.hashCode(), "boolean");
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		if (containedUnits.size() < 3 ){
			containedUnits.add(unit);
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
	@Override
	public String toString(){
		return "út " + super.toString();
	}
}
