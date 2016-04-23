package szoftProj;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Random;

public class Game {
	private State state;
	private Stage stage;
	private ActionUnit Jaffa;
	private ActionUnit Oneil;
	private ActionUnit Replicator;
	private boolean pause;

	public static void main(String[] args){
		Game game = new Game();
		//later check args and run appropiate command
		File argFile = new File("testMap.xml");
		game.newGame(argFile);
	}

	Game(){
		state = State.MENU;
		pause = false;
	}

	public void update(){
		if(!pause){
			stage.update();
			stage.collectUnits();
		}
	}

	public void loadGame(){}
	public void saveGame(){}
	public void control(String cmd){
		/*? Konverzió a külvilág és a játék belsõ mûködése között: a 
		felhasználói bemeneti eseményeknek megfelelõen helyesen beállítja a Player 
		következõ update()­ben végrehajtandó cselekedetét. A cmd paraméterben kapott 
		információ alapján tudja, hogy mit kell beállítani. A mûködés egy egyszerû switch 
		case szerkezet és a parancs darabolás (parsolása). */
	}

	String command(String cmd){
		/*Lekérdezõ parancsokat (pl. getField, getUnit), (a 
		külvilági) játékostól függõ manipulációs parancsokat (lényegében amit a control tud), 
		valamint játékostól független manipulációs parancsokat (pl. killUnit, addBox) 
		hajthatunk végre vele. A kapott paraméter maga a parancs. Visszatér a kimenettel (ha van neki).
		 A mûködés egy egyszerû switch case szerkezet, illetvet a parancs 
		darabolása. (A lekérdezések kivétel nélkül toString felüldefiniálásával történnek a 
		megfelelõ objektum elkérése után, pl. Stage­tõl)*/

		return null;
	}

	public void talkativeStage(){
		stage.logOn();
	}

	public void muteStage(){
		stage.logOff();
	}

	public void pause(){
		pause = true;
	}

	public void resume(){
		pause = false;
	}

	public void lose(){
		state = State.LOSE;
	}

	public void setJaffa(ActionUnit Jaffa){
		this.Jaffa = Jaffa;
	}

	public void setOneil(ActionUnit Oneil){
		this.Oneil = Oneil;
	}

	public void setReplicatorDir(boolean random, Direction dir){
		if(random){
			int randomDir = new Random().nextInt(4) + 1;
			switch (randomDir){
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				default:
					break;
			}
		} else {

		}
	}

	public void win(){
		state = State.WIN;
	}
	public void addUnit(Unit unit){
		stage.addUnit(unit);
	}
	public void createZPM(){
		stage.createZPM();
	}
	public void replaceField(Field field){
		stage.replaceField(field);
	}
	public void newGame(File file){
		this.stage = new Stage(file, this);
	}
	public void newGame(){
		this.stage = new Stage(new File("finalMap.xml"), this);
	}
	public void setStage(Stage stage){
		this.stage = stage;
	}

}