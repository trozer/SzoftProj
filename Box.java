package szoftProj;

public class Box extends Unit{

    public Box(Field currentField) {
        this.currentField = currentField;
        this.weight = 5;
    }
    
    @Override
    public void accept(Player launcher, Field target) {

        switch (launcher.getAction().getType()) {
            case DROP:
                if (target.addUnit(this) == true){
                    launcher.dropBox();
                }
                break;
            default:
                break;
        }
    }
    
    @Override
    public void accept(Field launcher, Player target) {
        switch (target.getAction().getType()) {
            case GRAB:
                if (target.grabBox(this) == true){
                    launcher.removeUnit();
                }
                break;
            default:
                break;
        }
    }
    
    @Override
    public String toString(){
    	return "    Doboz";
    }
}