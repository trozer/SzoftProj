package szoftProj;

import java.awt.Color;
import java.util.ArrayList;

public class Bullet extends ActionUnit{

    private Color color;
    protected Direction currentDirection;
    protected Action nextAction;
    protected boolean dead;
    protected Field currentField;

    public Bullet(Action action, Field currentField){
        this.action = action;
        this.currentField = currentField;
    }

    public Color getColor(){ color = this.color; }


    public void shoot(Color color){
        nextAction = new Action(ActionType.SHOOT, currentDirection, color);
    }

    public void grab(){
        nextAction = new Action(ActionType.GRAB, currentDirection, null);
    }

        public void drop(){
        nextAction = new Action(ActionType.DROP, currentDirection, null);
    }

    public void kill(){
        dead = true;
    }

    public void action(){
        
    }

}