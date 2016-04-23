package szoftProj;

import java.awt.Color;

public class Bullet extends ActionUnit{

    private Color color;

    public Bullet(Action action, Field currentField){
        this.nextAction = action;
        this.currentField = currentField;
        this.color = action.getColor();
        this.weight = 0;
    }

    public Color getColor(){ return color; }

    @Override
    public void step(Field target){
    	currentField.removeUnit(this);
        target.addUnit(this);
        this.setCurrentField(target);
    }

    public void action(){
        if (nextAction.getType() == ActionType.MOVE){
        	currentField.getNeighbourInDirection(currentDirection).doo(this);
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
    	if (color == Color.BLUE) szin = "K�k";
    	else if (color == Color.YELLOW) szin = "S�rga";
    	else if (color == Color.GREEN) szin = "Z�ld";
    	else if (color == Color.RED) szin = "Piros";
    	return szin + " l�ved�k";
    }

}