package szoftProj;

public class Box extends Unit{
    protected boolean dead = false;
    protected Field currentField;

    public Box(Field currentfield) {}

    public void accept(Player launcher, Field target) {
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(bullet);
        switch (target.getAction().getType()) {
            case DROP:
                target.addUnit(this);
                if (DROP == true){
                    launcher.dropBox();
                }
                break;
            default:
                break;
        }
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("accept", this, parameters);
        Skeleton.returnMethod("accept", this, parameters);
    }
    public void accept(Field launcher, Player target) {
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(bullet);
        switch (target.getAction().getType()) {
            case GRAB:
                target.grabBox(this);
                if (GRAB == true){
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