package szoftProj;

public class Box extends Unit{
    protected boolean dead = false;
    protected Field currentField;

    public Box(Field currentfield) {}

    public void accept(Player launcher, Field target) {
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(launcher);
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("accept", this, parameters);

        switch (target.getAction().getType()) {
            case DROP:
                if (target.dropBox(this) == true){
                    launcher.dropBox(this);
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
                    launcher.removeUnit(this);
                }
                break;
            default:
                break;
        }

        Skeleton.returnMethod("accept", this, parameters);


    }
}