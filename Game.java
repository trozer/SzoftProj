package szoftProj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.io.File;
import java.util.Random;

public class Game {
	private State state;
	private Stage stage;
	private ActionUnit Jaffa;
	private ActionUnit Oneill;
	private ActionUnit Replicator;
	private boolean pause;

	public static void main(String[] args){
		Game game = new Game();
		//later check args and run appropiate command
		//File argFile = new File("testMap.xml");
		//game.newGame(argFile);
		game.console();
	}

	Game(){
		state = State.MENU;
		pause = false;
	}

	public void setReplicator(Replicator replicator){
		this.Replicator = replicator;
	}

	public void update(){
		if(!pause){
			stage.update();
			stage.collectUnits();
		}
	}

	public void loadGame(){}
	public void saveGame(){}

	public void console(){

		BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				String inputLine = instream.readLine();
				StringTokenizer tokenizer = new StringTokenizer(inputLine, " -");
				String command = tokenizer.nextToken();

				if ("newGame".startsWith(command)) {
					if (tokenizer.hasMoreTokens()){
						newGame(new File(readString(tokenizer)));
					} else {
						newGame();
					}
				} else if ("update".startsWith(command)) {
					update();

					//kimenet kiíratása
					List<String> changes = stage.getLog();
					for(String str : changes) {
						System.out.println(str);
					}
					break;		//az update után végzett a program
				} else if ("loadGame".startsWith(command)) {
					//// TODO: 2016. 04. 23.
				} else if ("saveGame".startsWith(command)) {
					//// TODO: 2016. 04. 23.
				} else if ("exitGame".startsWith(command)) {
					//// TODO: 2016. 04. 23.
				//Cselekvések
				} else if ("move".startsWith(command)) {
					String executor = readString(tokenizer);
					String cmd = "move " + executor;
					control(cmd);

				} else if ("turn".startsWith(command)) {
					//// TODO: 2016. 04. 23.
				} else if ("grab".startsWith(command)) {
					//// TODO: 2016. 04. 23.
				} else if ("drop".startsWith(command)) {
					//// TODO: 2016. 04. 23.
				} else if ("getUnit".startsWith(command)) {
				} else if ("getZPM".startsWith(command)) {
				} else if ("getField".startsWith(command)) {
				} else if ("getPortal".startsWith(command)) {
				} else if ("listBoxes".startsWith(command)) {
				} else if ("listZPMs".startsWith(command)) {
				} else if ("setReplicatorDir".startsWith(command)) {
				} else if ("setUnitPos".startsWith(command)) {
				} else if ("killUnit".startsWith(command)) {
				} else if ("addBox".startsWith(command)) {
				} else if ("addZPM".startsWith(command)) {
				} else if ("addReplicator".startsWith(command)) {

				} else if ("e".startsWith(command)) {
					break;
				} else {
					throw new Exception("Hibas parancs! (" + inputLine + ")");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private String readString(StringTokenizer tokenizer) throws Exception{
		if (tokenizer.hasMoreElements()) {
			return tokenizer.nextToken();
		} else {
			throw new Exception("Keves parameter!");
		}
	}

	public void control(String cmd) throws Exception{
		/*? Konverzió a külvilág és a játék belsõ mûködése között: a 
		felhasználói bemeneti eseményeknek megfelelõen helyesen beállítja a Player 
		következõ update()­ben végrehajtandó cselekedetét. A cmd paraméterben kapott 
		információ alapján tudja, hogy mit kell beállítani. A mûködés egy egyszerû switch 
		case szerkezet és a parancs darabolás (parsolása). */

		try {
			StringTokenizer tokenizer = new StringTokenizer(cmd, " -");
			String command = tokenizer.nextToken();

			if("move".startsWith(command)){
				String executor = readString(tokenizer);
				if("oneill".startsWith(executor)){
					Oneill.move();
				} else if ("jaffa".startsWith(executor)) {
					Jaffa.move();
				} else if ("replicator".startsWith(executor)) {
					Replicator.move();
				} else {
					throw new Exception("Hibás végrehajtó a move cselekvésnél!");
				}
			} else if ("turn".startsWith(command)){
				// TODO: 2016. 04. 23. többi
			} else {
				throw new Exception("Hibás végrehajtandó parancsmegnevezés!");
			}

		} catch (Exception e){
			throw new Exception(e);
		}

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

	public void setOneill(ActionUnit Oneill){
		this.Oneill = Oneill;
	}

	public void setReplicatorDir(boolean random, Direction dir){
		if(random){
			int randomDir = new Random().nextInt(4) + 1;
			switch (randomDir){
				case 1:
					Replicator.turn(Direction.NORTH);
					break;
				case 2:
					Replicator.turn(Direction.WEST);
					break;
				case 3:
					Replicator.turn(Direction.EAST);
					break;
				case 4:
					Replicator.turn(Direction.SOUTH);
					break;
				default:
					break;
			}
		} else {
			Replicator.turn(dir);
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