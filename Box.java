package szoftProj;

import java.util.ArrayList;

public class Box extends Unit{

    public Box(Field currentField) {//beállítja a dobozt a paraméterül kapott mezőre
        this.currentField = currentField;
    }

    public void accept(Player launcher, Field target) {//Egységek, mint Player → Field tranzakció tárgyaként való alapértelmezett viselkedése. Ha a játékos le szeretné tenni a dobozt, akkor itt próbáljuk átadni a dobozt a szomszédos mezőnek.
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(launcher);
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("accept", this, parameters);

        switch (launcher.getAction().getType()) {
            case DROP:
                if (target.addUnit(this) == true){
                    launcher.dropBox();
                }
                break;
            default:
                break;
        }

        Skeleton.returnMethod("accept", this, parameters);
    }
    public void accept(Field launcher, Player target) {//gységek, mint Field -> Player tranzakció tárgyaként való alapértelmezett viselkedése. Ez esetben megnézzük, hogy a player mit szeretne csinálni. Ha az akció alapján fel szeretné venni a dobozt, akkor megpróbáljuk felvetetni dobozt a játékossal a mezőről.
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(target);
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("accept", this, parameters);

        switch (target.getAction().getType()) {
            case GRAB:
                if (target.grabBox(this) == true){
                    launcher.removeUnit();
                }
                break;
            default:
                break;
        }

        Skeleton.returnMethod("accept", this, parameters);

    }
}