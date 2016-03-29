package szoftProj;

import java.util.ArrayList;

public class Wall extends Field {
	//konstruktor
	public Wall() {
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());

		Skeleton.callMethod("Wall - konstruktor", this, parameters);
		Skeleton.returnMethod("Wall - konstruktor", this, parameters);
	}

	@Override
	public void doo(Player player) {//ha a player megpr�b�l r�l�pni nem fog tudni
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}

	@Override
	public void doo(Bullet bullet) {//ha j�n a l�ved�k akor azt mag�ra h�zza majd megsemmis�ti
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:	//ha meg�rkezik/l�pni akar
        	bullet.step(this);
    		containedUnit.kill();
        	break;
        default:	//minden m�s eset
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}

	@Override
	public Field getNeighbourInDirection(Direction dir) {//visszaadja aparam�ter ir�ny fel�li szomsz�dj�t
		return super.getNeighbourInDirection(dir);
	}

	@Override
	public boolean addUnit(Unit unit) {//nem tehet�nk r� unitot
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dir = new Object();
		Skeleton.registerHashCode(dir.hashCode(), "boolean");
		parameters.add(dir);
		Skeleton.callMethod("addUnit", this, parameters);
		Skeleton.returnMethod("addUnit", this, parameters);
		return false;
	}

	@Override
	public void removeUnit() {//leszedi r�la a r�helyezett unitot, nem tehet�nk r� unitot
		super.removeUnit();
	}

	@Override
	public void addNeighbour(Direction direction, Field neighbour) {//hozz�ad egy szomsz�dot a param�ter��l kapott ir�nyba
		super.addNeighbour(direction, neighbour);
	}

}
