package szoftProj;

import java.util.ArrayList;

public class Box extends Unit{

    public Box(Field currentField) {
        this.currentField = currentField;
    }

    public void accept(Player launcher, Field target) {
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
    public void accept(Field launcher, Player target) {
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