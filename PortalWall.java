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
	//param�teres konstruktor
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

	public void setPortal(Portal portal) {//be�ll�thatjuk a port�lt
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(portal);
		this.portal = portal;
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("setPortal - konstruktor", this, parameters);
		Skeleton.returnMethod("setPortal - konstruktor", this, parameters);
		
	}

	@Override
	public void doo(Player player) {//player cselekedet�re reag�l
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
	public void doo(Bullet bullet) {//reag�l a bullet meg�rkez�s�re
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
	public Field getNeighbourInDirection(Direction dir) {//visszaadja a param�ter ir�ny� mez�t
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
	public void removeUnit() {//elt�vol�tja a unitot a fieldr�l
		// TODO Auto-generated method stub
		super.removeUnit();
	}

	@Override
	public void addNeighbour(Direction direction, Field neighbour) {//be�l�tja a szomsz�dos mez�t param�ter ir�nyban
		// TODO Auto-generated method stub
		super.addNeighbour(direction, neighbour);
	}

}
