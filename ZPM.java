package szoftProj;

public class ZPM{
    protected boolean dead = false;
    protected Field currentField;

    public void kill(){
        dead = true;
    }

    public boolean isDead(){
        return dead;
    }

    public void action() {}
    public void accept(Player launcher, Field target) {}
    public void accept(Field launcher, Player target) {}
}