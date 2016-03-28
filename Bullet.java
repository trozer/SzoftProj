package szoftProj;

import java.awt.Color;
import java.util.ArrayList;

public class Bullet extends ActionUnit{

    private Color color;

    public Bullet(Action action, Field currentField){
        this.nextAction = action;
        this.currentField = currentField;
    }

    public Color getColor(){ return color; }

    @Override
    public void step(Field target){

        target.addUnit(this);
        this.setCurrentField(target);
    }

    public void action(){
        
    }

    public void kill(){
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("kill", this, parameters);

        dead = true;

        Skeleton.returnMethod("kill", this, parameters);
    }

    public void setCurrentField(Field field){
        this.setCurrentField(field);
    }

}