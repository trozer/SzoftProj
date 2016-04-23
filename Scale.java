package szoftProj;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

public class Scale extends Field {

	//--------Attribútumok--------
	
	
	private Gate myGate;
	int openLimit;
	boolean blockAddUnit;
	
	//-------Metódusok---------
	
	
	
	public Scale(){	//konstruktor
		super();	
		this.myGate = null;
		this.openLimit = 15;
		this.blockAddUnit = false;
		//ez így csudálatos, szóljatok, ha visszaírhatom :-)
	}
	
	public void setGate(Gate gate){	//beállítja a saját kapuját, azaz a hozzá tartozó kaput, ha még nincs neki
		if(myGate == null) 
			myGate = gate;
		else 
			System.out.println("setGate: már van beállítva gate!");
	}
	
	@Override
	public void doo(Player player){	//a játékos cselekedetére "reagál"
		int hatar = 0;
		switch (player.getAction().getType()) {
        case MOVE:	//ha a játékos rálép magára húzza és kinyitja a hozzá tartozó kaput
		
        	if (!containedUnits.isEmpty()){
        		for(Unit u : containedUnits) u.accept(this, player);
        	}
        	if (containedUnits.isEmpty()){
				player.step(this);
				containedUnits.add(player);
				myGate.open();
			}
        	break;
		
        case GRAB:	//ha a játékos leszed róla egy tárgyat, a hozzá tartozó kapu bezárul
        	if (!containedUnits.isEmpty()){
        		for(Unit u : containedUnits) u.accept(this, player);
    		}
        	for(Unit u : containedUnits) hatar += u.getWeight();
			if (hatar < openLimit){
    			myGate.close();
        	}
    		break;
    		
        default:	//minden más eset
        	//TODO
        	break;
		}
	}
	
	@Override
	public void doo(Bullet bullet){		//lövedékre reagál
		
		switch (bullet.getAction().getType()) {
        case MOVE:	//magára húzza a lövedéket, a lövedék nem nyitja ki a kaput
        	if (containedUnits.isEmpty()){
        		bullet.step(this);
        		containedUnits.add(bullet);
        		break;
        	}
        	else{
        		for(Unit u : containedUnits) u.accept(bullet, this);
        	}
        default:	//minden más eset
        	break;
		}
	}
	
	@Override
	 public void doo(Replicator replicator){
		switch (replicator.getAction().getType()) {
        case MOVE:
        	replicator.step(this);
        	containedUnits.add(replicator);
        	break;
    	default:
			break;
		}
	}
	
	@Override
	public void forceAddUnit(Unit unit){
		containedUnits.add(unit);
	}
	
	@Override
	public boolean addUnit(Unit unit){
		int hatar = 0;
		if (!blockAddUnit){
		containedUnits.add(unit);
		}
		blockAddUnit = true;
			for(Unit u : containedUnits) u.accept(unit, this);
		blockAddUnit = false;
		
		for(Unit u : containedUnits) hatar += u.getWeight();
		if (hatar >= openLimit){
			myGate.open();
		}
		return true;
		/*if (containedUnits.size() < 3 ){
			containedUnits.add(unit);
			myGate.open();
			return true;
		}
		else
			return false;*/
	}

	@Override
	public void removeUnit(){	//eltávolítja a unitot ami a mérlegen van, ilyenkor a kapu bezárul 
		containedUnits = new ArrayList<Unit>();
		myGate.close();
	}
	
	@Override
	public void removeUnit(Unit unit){
		int hatar = 0;
		if (!containedUnits.isEmpty()){
			containedUnits.remove(unit);
			for(Unit u : containedUnits) hatar += u.getWeight();
		}
		if (hatar < openLimit){
			myGate.close();
		}
	}
	
	@Override
	public Field getNeighbourInDirection(Direction dir) {
		// TODO Auto-generated method stub
		return super.getNeighbourInDirection(dir);
	}

	@Override
	public void addNeighbour(Direction direction, Field neighbour) {
		// TODO Auto-generated method stub
		super.addNeighbour(direction, neighbour);
	}
	
	//TODO,testing phase, correct this
	@Override
	public String toString(){
		int sulyok = 0;
		for (int i = 0; i < containedUnits.size(); i++){
			sulyok += containedUnits.get(i).getWeight();
		}
		if(myGate != null)
			if(sulyok >= openLimit)
				return "mérleg: (" + position.getX() + "," + position.getY() + ") pozíció, "
					+ openLimit + " súlyhatár, lenyomva , van hozzákapcsolt kapu, " 
					+ containedUnits.size() + "darab tárolt egység";
			else
				return "mérleg: (" + position.getX() + "," + position.getY() + ") pozíció, "
				+ openLimit + " súlyhatár, nincs lenyomva , van hozzákapcsolt kapu, " 
				+ containedUnits.size() + "darab tárolt egység";
		else
			if(sulyok >= openLimit)
					return "mérleg: (" + position.getX() + "," + position.getY() + ") pozíció, "
						+ openLimit + " súlyhatár, lenyomva , nincs hozzákapcsolt kapu, " 
						+ containedUnits.size() + "darab tárolt egység";
				else
					return "mérleg: (" + position.getX() + "," + position.getY() + ") pozíció, "
					+ openLimit + " súlyhatár, nincs lenyomva , nincs hozzákapcsolt kapu, " 
					+ containedUnits.size() + "darab tárolt egység";
	}
}
