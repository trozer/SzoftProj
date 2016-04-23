package szoftProj;

import java.awt.Color;

public class Portal {

	private Field blue;
	private Field yellow;
	private Field red;
	private  Field green;

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
		return (blue == field || yellow == field);
	}

	public void createPortal(Field field, Color color) {
		if (color == Color.BLUE) {
			blue = field;
		} else if (color == Color.YELLOW) {
			yellow = field;
		}
	}

	public Field getPair(Field field) {
		if (blue == field) {
			return yellow;
		} else if (yellow == field) {
			return blue;
		}
		return null;
	}

}
