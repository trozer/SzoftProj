package szoftProj;

import java.util.ArrayList;
import java.util.Map;

public class Gate extends Field {
	
	//--------Attribútumok--------
	
	private boolean opened;
	
	//-------Metódusok---------
	
	public Gate(){	//konstruktor
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("Gate - konstruktor", this, parameters);
		Skeleton.returnMethod("Gate - konstruktor", this, parameters);
	}
	
	public void open(){	//nyitottra állítja (kinyitja) a kaput
		ArrayList<Object> parameters = new ArrayList<Object>();
		opened = true;
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("open", this, parameters);
		Skeleton.returnMethod("open", this, parameters);
	}
	
	public void close(){	//bezárja a kaput, a kapuban lévõ unitokat megsemmisíti
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
	public void doo(Player player){		//a játékos cselekedetére "reagál"
		ArrayList<Object> parameters = new ArrayList<Object>();
		player.getAction();
		
		switch (player.getAction().getType()) {
        case MOVE:	//ha a játékos lépni akar
        	if (containedUnit != null && opened == true){
				containedUnit.accept(this, player);
        	}
        	if (containedUnit == null && opened == true){
				player.step(this);
			}
        	break;
		
        case GRAB:	//ha a játékos fel akar venni valamit
        	if (containedUnit != null){
    			containedUnit.accept(this, player);
        	}
        	break;
        default:	//minden más eset
        	//TODO
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
		
		
	}
	
	@Override
	public void doo(Bullet bullet){		//a lövedék cselekedetétre reagál
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(bullet);
		switch (bullet.getAction().getType()) {
        case MOVE:	//ha megérkezik/lépni akar
        	if (opened == true){
        		bullet.step(this);
        	}
        	else
        		bullet.step(this);
        		containedUnit.kill();
        	break;
        default:	//minden más eset
        	break;
		}
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("doo", this, parameters);
		Skeleton.returnMethod("doo", this, parameters);
	}
	
	@Override
	public boolean addUnit(Unit unit){	//elhelyez ez unitot a kapuban, ha az nyitva van és nincs még benne/rajta semmi
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
