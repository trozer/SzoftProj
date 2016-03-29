package szoftProj;

import java.util.ArrayList;

public class PortalWall extends Wall {

	private Portal portal;

	//konstruktor
	public PortalWall() {
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());

		Skeleton.callMethod("PortalWall - konstruktor", this, parameters);
		Skeleton.returnMethod("PortalWall - konstruktor", this, parameters);
	}
	//paraméteres konstruktor
	public PortalWall(Portal portal) {
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(portal);
		this.portal = portal;
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("PortalWall - konstruktor", this, parameters);
		Skeleton.returnMethod("PortalWall - konstruktor", this, parameters);
	}

	public Portal getPortal() {//visszaadja a portal 
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dir = new Object();
		Skeleton.registerHashCode(dir.hashCode(), "Portal");
		parameters.add(dir);
		Skeleton.callMethod("getPortal - konstruktor", this, parameters);
		Skeleton.returnMethod("getPortal - konstruktor", this, parameters);
		return portal;
	}

	public void setPortal(Portal portal) {//beállíthatjuk a portált
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(portal);
		this.portal = portal;
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("setPortal - konstruktor", this, parameters);
		Skeleton.returnMethod("setPortal - konstruktor", this, parameters);
		
	}

	@Override
	public void doo(Player player) {//player cselekedetére reagál
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(player);
		if (portal.amIPortal(this)) {
			if (portal.getPair(this) != null) {
				player.move();
			}
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo - konstruktor", this, parameters);
		Skeleton.returnMethod("doo - konstruktor", this, parameters);
	}

	@Override
	public void doo(Bullet bullet) {//reagál a bullet megérkezésére
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		if (portal.amIPortal(this)) {
			bullet.kill();
		} else {
			portal.createPortal(this, bullet.getColor());
			bullet.kill();
		}
		Skeleton.callMethod("doo - konstruktor", this, parameters);
		Skeleton.returnMethod("doo - konstruktor", this, parameters);
	}

	@Override
	public Field getNeighbourInDirection(Direction dir) {//visszaadja a paraméter irányú mezõt
		// TODO Auto-generated method stub
		return super.getNeighbourInDirection(dir);
	}

	@Override
	public boolean addUnit(Unit unit) {//unitot tesz a fieldre
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dir = new Object();
		Skeleton.registerHashCode(dir.hashCode(), "boolean");
		parameters.add(dir);
		Skeleton.callMethod("addUnit", this, parameters);
		Skeleton.returnMethod("addUnit", this, parameters);
		return false;
	}

	@Override
	public void removeUnit() {//eltávolítja a unitot a fieldrõl
		// TODO Auto-generated method stub
		super.removeUnit();
	}

	@Override
	public void addNeighbour(Direction direction, Field neighbour) {//beálítja a szomszédos mezõt paraméter irányban
		// TODO Auto-generated method stub
		super.addNeighbour(direction, neighbour);
	}

}
