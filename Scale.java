package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Scale extends Field {

	//--------Attrib�tumok--------
	
	
	private Gate myGate;
	
	//-------Met�dusok---------
	
	
	
	public Scale(){	//konstruktor
		super();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Scale - konstruktor", this, parameters);
		Skeleton.returnMethod("Scale - konstruktor", this, parameters);
		myGate = null;
	}
	
	public void setGate(Gate gate){	//be�ll�tja a saj�t kapuj�t, azaz a hozz� tartoz� kaput, ha m�g nincs neki
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(gate);
		if(myGate == null) myGate = gate;
		else System.out.println("setGate: m�r van be�ll�tva gate!");
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("setGate", this, parameters);
		Skeleton.returnMethod("setGate", this, parameters);
	}
	
	@Override
	public void doo(Player player){	//a j�t�kos cselekedet�re "reag�l"
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(player);
		player.getAction();
		
		switch (player.getAction().getType()) {
        case MOVE:	//ha a j�t�kos r�l�p mag�ra h�zza �s kinyitja a hozz� tartoz� kaput
		
		if (containedUnit != null){
				containedUnit.accept(this, player);
		}
		if (containedUnit == null){
				player.step(this);
				myGate.open();
			}
		break;
		
        case GRAB:	//ha a j�t�kos leszed r�la egy t�rgyat, a hozz� tartoz� kapu bez�rul
        	if (containedUnit != null){
    			containedUnit.accept(this, player);
    		}
    		myGate.close();
    		break;
    		
        default:	//minden m�s eset
        	//TODO
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}
	
	@Override
	public void doo(Bullet bullet){		//l�ved�kre reag�l
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:	//mag�ra h�zza a l�ved�ket, a l�ved�k nem nyitja ki a kaput
        	bullet.step(this);
        	break;
        default:	//minden m�s eset
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}
	
	
	@Override
	public boolean addUnit(Unit unit){	//unitot hejez el a m�rlegen, ha nincs rajta semmi
		Object object = new Object();
		Skeleton.registerHashCode(object.hashCode(), "boolean");
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		if (containedUnits.size() < 3 ){
			containedUnits.add(unit);
			parameters.add(object);
			myGate.open();
			Skeleton.callMethod("addUnit", this, parameters);
			Skeleton.returnMethod("addUnit", this, parameters);
			return true;
		}
		else
			parameters.add(object);
			Skeleton.callMethod("addUnit", this, parameters);
			Skeleton.returnMethod("addUnit", this, parameters);
			return false;
	}

	@Override
	public void removeUnit(){	//elt�vol�tja a unitot ami a m�rlegen van, ilyenkor a kapu bez�rul 
		ArrayList<Object> parameters = new ArrayList<Object>();
		containedUnit = null;
		myGate.close();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("removeUnit", this, parameters);
		Skeleton.returnMethod("removeUnit", this, parameters);
	}
	
	//TODO,testing phase, correct this
	@Override
	public String toString(){
		if(myGate != null)
			return "m�rleg " + super.toString() + " " + myGate.toString();
		else
			return "m�rleg " + super.toString();
	}
}
