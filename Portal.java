package szoftProj;

import java.awt.Color;
import java.util.ArrayList;

public class Portal {

	private Field blue;
	private Field yellow;

		//konstruktor
	public Portal() {
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());

		Skeleton.callMethod("Portal - konstruktor", this, parameters);
		Skeleton.returnMethod("Portal - konstruktor", this, parameters);
	}

	public Field getBlue() {//visszadja a kék portált
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dir = new Object();
		Skeleton.registerHashCode(dir.hashCode(), "Field");
		parameters.add(dir);
		Skeleton.callMethod("getBlue", this, parameters);
		Skeleton.returnMethod("getBlue", this, parameters);
		return blue;
	}

	public void setBlue(Field blue) {//beállítja a kék portált
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(blue);
		this.blue = blue;
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("PsetBlue", this, parameters);
		Skeleton.returnMethod("setBlue", this, parameters);
	}

	public Field getYellow() {//visszaadja a sárga portált
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dir = new Object();
		Skeleton.registerHashCode(dir.hashCode(), "Field");
		parameters.add(dir);
		Skeleton.callMethod("getYellow", this, parameters);
		Skeleton.returnMethod("getYellow", this, parameters);
		return yellow;
	}

	public void setYellow(Field yellow) {//beállítja a sárga portált
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(yellow);
		this.yellow = yellow;
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("setBlue", this, parameters);
		Skeleton.returnMethod("setBlue", this, parameters);
	}

	public boolean amIPortal(Field field) {//igazzal tér vissza ha a mezõ portál
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dir = new Object();
		Skeleton.registerHashCode(dir.hashCode(), "boolean");
		parameters.add(field);
		parameters.add(dir);
		Skeleton.callMethod("amIPortal", this, parameters);
		Skeleton.returnMethod("amIPortal", this, parameters);
		return (blue == field || yellow == field);
	}

	public void createPortal(Field field, Color color) {//beállítja a portálok színét
		ArrayList<Object> parameters = new ArrayList<Object>();
		Object dir = new Object();
		Skeleton.registerHashCode(dir.hashCode(), "Color");
		parameters.add(field);
		parameters.add(dir);
		if (color == Color.BLUE) {
			blue = field;
		} else if (color == Color.YELLOW) {
			yellow = field;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("amIPortal", this, parameters);
		Skeleton.returnMethod("amIPortal", this, parameters);
	}

	public Field getPair(Field field) {//visszaadja a portál párját
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(field);
		if (blue == field) {
			return yellow;
		} else if (yellow == field) {
			Skeleton.callMethod("amIPortal", this, parameters);
			Skeleton.returnMethod("amIPortal", this, parameters);
			return blue;
		}
		Skeleton.callMethod("amIPortal", this, parameters);
		Skeleton.returnMethod("amIPortal", this, parameters);
		return null;
	}

}
