package szoftProj;

public class Wall extends Field {

	public Wall() {
		super();
	}

	@Override
	public void doo(Player player) {
	}

	@Override
	public void doo(Bullet bullet) {
	}

	@Override
	public Field getNeighbourInDirection(Direction dir) {
		return super.getNeighbourInDirection(dir);
	}

	@Override
	public boolean addUnit(Unit unit) {
		return super.addUnit(unit);
	}

	@Override
	public void removeUnit() {
		super.removeUnit();
	}

	@Override
	public void addNeighbour(Direction direction, Field neighbour) {
		super.addNeighbour(direction, neighbour);
	}
	
	@Override
	public String toString(){
		return "fal " + super.toString();
	}

}
