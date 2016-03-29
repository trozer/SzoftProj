package szoftProj;

import java.util.ArrayList;

public class ZPM extends Unit{
	//konstruktor
    public ZPM(Field currentField) {
        this.currentField = currentField;
    }

    public void accept(Field launcher, Player target) {//Egys�gek, mint Player -> Field tranzakci� t�rgyak�nt val� alap�rtelmezett viselked�se, ez esetben nem csin�l semmit. Ha egy ZPM-et felvett�nk, azt nem tehetj�k le.
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