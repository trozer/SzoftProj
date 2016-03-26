package szoftProj;

import java.awt.Color;
import java.util.ArrayList;

public abstract class ActionUnit extends Unit{
	protected Direction currentDirection;
	protected Action nextAction;
	
	//Mozgas tipusu action-t hoz letre.
	public void move(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("move", this, parameters);
		
		nextAction = new Action(ActionType.MOVE, currentDirection, null);
		
		Skeleton.returnMethod("move", this, parameters);
	}
	
	//Forgas tipusu action-t hoz letre.
	public void turn(Direction direction){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(direction);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("turn", this, parameters);
		
		nextAction = new Action(ActionType.TURN, direction, null);
		
		Skeleton.returnMethod("turn", this, parameters);
	}
	
	
	public void shoot(Color color){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(color);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("shoot", this, parameters);
		Skeleton.returnMethod("shoot", this, parameters);
	}
	
	public void grab(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("grab", this, parameters);
		Skeleton.returnMethod("grab", this, parameters);
	}
	
	public void drop(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("drop", this, parameters);
		Skeleton.returnMethod("drop", this, parameters);
	}
	
	public void step(Field target){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(target);
		parameters.add(Skeleton.getEmpty());
		Skeleton.callMethod("step", this, parameters);
		Skeleton.returnMethod("step", this, parameters);
	}
	
	public Action getAction(){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(nextAction);
		Skeleton.callMethod("getAction", this, parameters);
		Skeleton.returnMethod("getAction", this, parameters);
		
		return nextAction;
	}
	
	
}
