package szoftProj;

public class ZPM extends Unit{
    protected boolean dead = false;
    protected Field currentField;

    public ZPM(Field currentfield) {}

    public void action() {}
    public void accept(Player launcher, Field target) {}
    public void accept(Field launcher, Player target) {}
}