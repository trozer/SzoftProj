package szoftProj;

public class Box extends Unit{
    protected boolean dead = false;
    protected Field currentField;

    public Box(Field currentfield) {}

    public void kill() { dead = true; }

    public boolean isDead() { return dead; }

    public void action() {}
    public void accept(Player launcher, Field target) {}
    public void accept(Field launcher, Player target) {}
}