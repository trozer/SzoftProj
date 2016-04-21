package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Road extends Field {

	// --------Attrib�tumok--------


	


	// -------Met�dusok---------

	public Road() {	//konstruktor
		super();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());

		Skeleton.callMethod("Road - konstruktor", this, parameters);
		Skeleton.returnMethod("Road - konstruktor", this, parameters);

	}

	@Override
	public void doo(Player player){	//a j�t�kos cselekedet�re "reag�l"
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(player);
		
		player.getAction();
		switch (player.getAction().getType()) {
        case MOVE:	//ha a j�t�kos l�pni akar
        		if (containedUnit != null){
				containedUnit.accept(this, player);
        		}
        		if (containedUnit == null)
        			player.step(this);
        		break;
        
        case GRAB:	//ha a j�t�kos fel akar venni valamit
        		if (containedUnit != null){
        			containedUnit.accept(this, player);}
        		break;
        		
        default:	//minden m�s eset
        	//TODO
        	
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
		//drop -adunit int�zi
	}

	@Override
	public void doo(Bullet bullet) {	//a l�ved�k cselekedet�tre reag�l
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

	@Override
	public boolean addUnit(Unit unit) {	//ha nincs m�g unit az �ton r�tehet�nk egyet, ha van akkor nem
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
		return "�t " + super.toString();
	}
}
