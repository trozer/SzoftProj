package szoftProj;

import java.awt.Color;

public class Portal {

	private Field blue;
	private Field yellow;

	public Portal() {

	}

	public Field getBlue() {
		return blue;
	}

	public void setBlue(Field blue) {
		this.blue = blue;
	}

	public Field getYellow() {
		return yellow;
	}

	public void setYellow(Field yellow) {
		this.yellow = yellow;
	}

	public boolean amIPortal(Field field) {
		return false;
	}

	public void createPortal(Field field, Color color) {

	}

	public Field getPair(Field field) {
		return null;
	}

}
