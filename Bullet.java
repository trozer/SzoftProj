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

    public Color getColor(){ return color; }//visszaadja a l�ved�k sz�n�t

    @Override
    public void step(Field target){//Adott mez�re val� l�p�st hajtja v�gre. A saj�t mez�j�r�l let�rli mag�t, a param�terben kapott mez�h�z hozz�adja mag�t �s be�ll�tja az aktu�lis mez�t amin innent�l �llni fog (teh�t a param�terben kapotton). 

        target.addUnit(this);
        this.setCurrentField(target);
    }

    public void action(){		//Mozg�sban tartja a l�ved�kek, a tov�bbiakat az adott mez�re b�zza.
        if (nextAction.getType() == ActionType.MOVE ||
                nextAction.getType() == ActionType.GRAB){

        }
    }

    public void kill(){//egsemmis�ti a l�ved�ket, azaz a dead attrib�tum�t true-ra �ll�tja
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("kill", this, parameters);

        dead = true;

        Skeleton.returnMethod("kill", this, parameters);
    }

    public void setCurrentField(Field field){//be�ll�tja a l�ved�k tart�zkod�si hely�t a param�ter�l kapott mez�re
        this.setCurrentField(field);
    }

}