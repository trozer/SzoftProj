package szoftProj;

import java.awt.Color;
import java.util.ArrayList;

public class Bullet extends ActionUnit{

    private Color color;
    //konstruktor
    public Bullet(Action action, Field currentField){
        this.nextAction = action;
        this.currentField = currentField;
        this.color = action.getColor();
    }

    public Color getColor(){ return color; }//visszaadja a lövedék színét

    @Override
    public void step(Field target){//Adott mezõre való lépést hajtja végre. A saját mezõjérõl letörli magát, a paraméterben kapott mezõhöz hozzáadja magát és beállítja az aktuális mezõt amin innentõl állni fog (tehát a paraméterben kapotton). 

        target.addUnit(this);
        this.setCurrentField(target);
    }

    public void action(){		//Mozgásban tartja a lövedékek, a továbbiakat az adott mezõre bízza.
        if (nextAction.getType() == ActionType.MOVE ||
                nextAction.getType() == ActionType.GRAB){

        }
    }

    public void kill(){//egsemmisíti a lövedéket, azaz a dead attribútumát true-ra állítja
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("kill", this, parameters);

        dead = true;

        Skeleton.returnMethod("kill", this, parameters);
    }

    public void setCurrentField(Field field){//beállítja a lövedék tartózkodási helyét a paraméterül kapott mezõre
        this.setCurrentField(field);
    }

}