package szoftProj;

public class ZPM extends Unit{

    public ZPM(Field currentField) {
        this.currentField = currentField;
    }

    public void accept(Field launcher, Player target) {
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(launcher);
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("accept", this, parameters);

        switch (target.getAction().getType()) {
            case GRAB:
                if (target.grabBox(this) == true){
                    launcher.removeUnit(this);
                }
                break;
            default:
                break;
        }

        Skeleton.returnMethod("accept", this, parameters);


    }
}