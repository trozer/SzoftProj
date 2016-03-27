package szoftProj;

public class ZPM extends Unit{
    protected boolean dead = false;
    protected Field currentField;

    public ZPM(Field currentfield) {}

    public void accept(Field launcher, Player target) {
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(bullet);
        switch (target.getAction().getType()) {
            case GRAB:
                if (target.grabBox(this) == true){
                    launcher.removeUnit(this);
                }
                break;
            default:
                break;
        }
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("accept", this, parameters);
        Skeleton.returnMethod("accept", this, parameters);


    }
}