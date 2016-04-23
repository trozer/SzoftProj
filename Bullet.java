package szoftProj;

import java.awt.Color;

public class Bullet extends ActionUnit{

    private Color color;

    public Bullet(Action action, Field currentField){
        this.nextAction = action;
        this.currentField = currentField;
        this.color = action.getColor();
    }

    public Color getColor(){ return color; }

    @Override
    public void step(Field target){
        target.addUnit(this);
        this.setCurrentField(target);
    }

    public void action(){
        if (nextAction.getType() == ActionType.MOVE || nextAction.getType() == ActionType.GRAB){
        	
        }
    }

    public void kill(){
        dead = true;
    }

    public void setCurrentField(Field field){
        this.setCurrentField(field);
    }
    
    public String toString(){
    	String szin = "";
    	if (color == Color.BLUE) szin = "Kék";
    	else if (color == Color.YELLOW) szin = "Sárga";
    	else if (color == Color.GREEN) szin = "Zöld";
    	else if (color == Color.RED) szin = "Piros";
    	return szin + " lövedék";
    }

}