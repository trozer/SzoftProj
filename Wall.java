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
	public void doo(Player player) {//ha a player megpróbál rálépni nem fog tudni
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}

	@Override
	public void doo(Bullet bullet) {//ha jön a lövedék akor azt magára húzza majd megsemmisíti
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:	//ha megérkezik/lépni akar
        	bullet.step(this);
    		containedUnit.kill();
        	break;
        default:	//minden más eset
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}

	@Override
	public Field getNeighbourInDirection(Direction dir) {//visszaadja aparaméter irány felõli szomszédját
		return super.getNeighbourInDirection(dir);
	}

	@Override
	public boolean addUnit(Unit unit) {//nem tehetünk rá unitot
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dir = new Object();
		Skeleton.registerHashCode(dir.hashCode(), "boolean");
		parameters.add(dir);
		Skeleton.callMethod("addUnit", this, parameters);
		Skeleton.returnMethod("addUnit", this, parameters);
		return false;
	}

	@Override
	public void removeUnit() {//leszedi róla a ráhelyezett unitot, nem tehetünk rá unitot
		super.removeUnit();
	}

	@Override
	public void addNeighbour(Direction direction, Field neighbour) {//hozzáad egy szomszédot a paraméteréül kapott irányba
		super.addNeighbour(direction, neighbour);
	}

}
