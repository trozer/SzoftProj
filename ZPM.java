package szoftProj;

import java.util.ArrayList;

public class ZPM extends Unit{
	//konstruktor
    public ZPM(Field currentField) {
        this.currentField = currentField;
    }

    public void accept(Field launcher, Player target) {//Egységek, mint Player -> Field tranzakció tárgyaként való alapértelmezett viselkedése, ez esetben nem csinál semmit. Ha egy ZPM-et felvettünk, azt nem tehetjük le.
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(launcher);
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("accept", this, parameters);

        switch (target.getAction().getType()) {
            case GRAB:
                target.addZPM(this);
                launcher.removeUnit();
                break;
            case MOVE:
                target.addZPM(this);
                launcher.removeUnit();
                break;
            default:
                break;
        }

        Skeleton.returnMethod("accept", this, parameters);

    }
}