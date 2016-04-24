package szoftProj;

public class PortalWall extends Wall {

	private Portal portal;

	public PortalWall() {
		super();
	}

	public PortalWall(Portal portal) {
		this.portal = portal;
	}

	public Portal getPortal() {
		return portal;
	}

	public void setPortal(Portal portal) {
		this.portal = portal;
	}

	@Override
	public void doo(Player player) {
		if (portal.amIPortal(this)) {
			if (portal.getPair(this) != null) {
				player.step(this);
			}
		}
	}

	@Override
	public void doo(Bullet bullet) {
		if (portal.amIPortal(this)) {
			bullet.step(this);
			bullet.kill();
		} else {
			portal.createPortal(this, bullet.getColor());
			bullet.step(this);
			bullet.kill();
		}
	}

	@Override
	public Field getNeighbourInDirection(Direction dir) {
		// TODO Auto-generated method stub
		return super.getNeighbourInDirection(dir);
	}

	@Override
	public boolean addUnit(Unit unit) {
		// TODO Auto-generated method stub
		return super.addUnit(unit);
	}

	@Override
	public void removeUnit() {
		// TODO Auto-generated method stub
		super.removeUnit();
	}

	@Override
	public void addNeighbour(Direction direction, Field neighbour) {
		// TODO Auto-generated method stub
		super.addNeighbour(direction, neighbour);
	}

	@Override
	public String toString(){
		if(portal.amIPortal(this))
			return "port�l" + super.toString() + " port�l vagyok";
		else
			return "port�l" + super.toString() ;
	}
}
