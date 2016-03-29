package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Gate extends Field {
	
	//--------Attrib�tumok--------
	
	private boolean opened;
	
	//-------Met�dusok---------
	
	public Gate(){	//konstruktor
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Gate - konstruktor", this, parameters);
		Skeleton.returnMethod("Gate - konstruktor", this, parameters);
	}
	
	public void open(){	//nyitottra �ll�tja (kinyitja) a kaput
		ArrayList<Object> parameters = new ArrayList<Object>();
		opened = true;
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("open", this, parameters);
		Skeleton.returnMethod("open", this, parameters);
	}
	
	public void close(){	//bez�rja a kaput, a kapuban l�v� unitokat megsemmis�ti
		ArrayList<Object> parameters = new ArrayList<Object>();
		opened = false;
		if(containedUnit != null){
		containedUnit.kill();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("close", this, parameters);
		Skeleton.returnMethod("close", this, parameters);
		}
	}
	
	
	
	@Override
	public void doo(Player player){		//a j�t�kos cselekedet�re "reag�l"
		ArrayList<Object> parameters = new ArrayList<Object>();
		player.getAction();
		
		switch (player.getAction().getType()) {
        case MOVE:	//ha a j�t�kos l�pni akar
        	if (containedUnit != null && opened == true){
				containedUnit.accept(this, player);
        	}
        	if (containedUnit == null && opened == true){
				player.step(this);
			}
        	break;
		
        case GRAB:	//ha a j�t�kos fel akar venni valamit
        	if (containedUnit != null){
    			containedUnit.accept(this, player);
        	}
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
	public void doo(Bullet bullet){		//a l�ved�k cselekedet�tre reag�l
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:	//ha meg�rkezik/l�pni akar
        	if (opened == true){
        		bullet.step(this);
        	}
        	else
        		bullet.step(this);
        		containedUnit.kill();
        	break;
        default:	//minden m�s eset
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}
	
	@Override
	public boolean addUnit(Unit unit){	//elhelyez ez unitot a kapuban, ha az nyitva van �s nincs m�g benne/rajta semmi
		Object object = new Object();
		Skeleton.registerHashCode(object.hashCode(), "boolean");
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(unit);
		if (containedUnit == null && opened == true){
			containedUnit = unit;
			parameters.add(object);
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
	
	/*@Override
	public void removeUnit(){
		containedUnit = null;
	}*/

}
