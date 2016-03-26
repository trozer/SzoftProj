package szoftProj;

public class PortalWall extends Wall {

	private Portal portal;

	public PortalWall() {

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
		// TODO Auto-generated method stub
		super.doo(player);
	}

	@Override
	public void doo(Bullet bullet) {
		// TODO Auto-generated method stub
		super.doo(bullet);
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

}
