package szoftProj;

import java.awt.*;
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
					//kimenet ki�rat�sa
					List<String> changes = stage.getLog();
					if(changes != null) {
						for (String str : changes) {
							System.out.println(str);
						}
						break;        //az update ut�n kil�p a program
					} else {
						System.out.println("");		//nem t�rt�nt v�ltoz�s
					}
				} else if ("loadGame".startsWith(command)) {
					//// TODO: 2016. 04. 23.
				} else if ("saveGame".startsWith(command)) {
					//// TODO: 2016. 04. 23.
				} else if ("exitGame".startsWith(command)) {
					//// TODO: 2016. 04. 23.
				//Cselekv�sek
				} else if ("move".startsWith(command)) {
					String executor = readString(tokenizer);
					String cmd = "move " + executor;
					control(cmd);

				} else if ("turn".startsWith(command)) {
					String executor = readString(tokenizer);
					String dir = readString(tokenizer);
					String cmd = "turn " + executor + " " + dir;
					control(cmd);
				} else if ("grab".startsWith(command)) {
					String executor = readString(tokenizer);
					String cmd = "grab " + executor;
					control(cmd);
				} else if ("drop".startsWith(command)) {
					String executor = readString(tokenizer);
					String cmd = "drop " + executor;
					control(cmd);
				} else if ("shoot".startsWith(command)) {
					String executor = readString(tokenizer);
					String color = readString(tokenizer);
					String cmd = "shoot " + executor + " " + color;
					control(cmd);
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
		/*? Konverzi�a�k�lvil�g��s�a�j�t�k�bels��m�k�d�se�k�z�tt:�a�
		felhaszn�l�i�bemeneti�esem�nyeknek�megfelel�en�helyesen�be�ll�tja�a�Player�
		k�vetkez��update()�ben�v�grehajtand�cselekedet�t.�A�cmd�param�terben�kapott�
		inform�ci�alapj�n�tudja,�hogy�mit�kell�be�ll�tani.�A�m�k�d�s�egy�egyszer��switch�
		case�szerkezet��s�a�parancs�darabol�s�(parsol�sa).�*/

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
					throw new Exception("Hib�s v�grehajt� a move cselekv�sn�l!");
				}
			} else if ("turn".startsWith(command)){
				String executor = readString(tokenizer);
				String direction = readString(tokenizer);
				Direction dir;

				if("north".startsWith(direction)){
					dir = Direction.NORTH;
				} else if ("west".startsWith(direction)) {
					dir = Direction.WEST;
				} else if ("east".startsWith(direction)) {
					dir = Direction.EAST;
				} else if ("sout".startsWith(direction)) {
					dir = Direction.SOUTH;
				} else {
					throw new Exception("Hib�s fordul�si ir�ny!");
				}

				if("oneill".startsWith(executor)){
					Oneill.turn(dir);
				} else if ("jaffa".startsWith(executor)) {
					Jaffa.turn(dir);
				} else if ("replicator".startsWith(executor)) {
					Replicator.turn(dir);
				} else {
					throw new Exception("Hib�s v�grehajt� a turn cselekv�sn�l!");
				}
			} else if ("grab".startsWith(command)){
				String executor = readString(tokenizer);
				if("oneill".startsWith(executor)){
					Oneill.grab();
				} else if ("jaffa".startsWith(executor)) {
					Jaffa.grab();
				} else {
					throw new Exception("Hib�s v�grehajt� a grab cselekv�sn�l!");
				}
			} else if ("drop".startsWith(command)){
				String executor = readString(tokenizer);
				if("oneill".startsWith(executor)){
					Oneill.drop();
				} else if ("jaffa".startsWith(executor)) {
					Jaffa.drop();
				} else {
					throw new Exception("Hib�s v�grehajt� a drop cselekv�sn�l!");
				}
			} else if ("shoot".startsWith(command)){
				String executor = readString(tokenizer);
				String colorStr = readString(tokenizer);
				Color color;

				if("blue".startsWith(colorStr)){
					color = Color.BLUE;
				} else if ("yellow".startsWith(colorStr)) {
					color = Color.YELLOW;
				} else if ("red".startsWith(colorStr)) {
					color = Color.RED;
				} else if ("green".startsWith(colorStr)) {
					color = Color.GREEN;
				} else {
					throw new Exception("Hib�s l�ved�k sz�n!");
				}

				if("oneill".startsWith(executor)){
					Oneill.shoot(color);
				} else if ("jaffa".startsWith(executor)) {
					Jaffa.shoot(color);
				} else {
					throw new Exception("Hib�s v�grehajt� a shoot cselekv�sn�l!");
				}
			} else {
				throw new Exception("Hib�s v�grehajtand� parancsmegnevez�s!");
			}

		} catch (Exception e){
			throw new Exception(e);
		}

	}

	String command(String cmd){
		/*Lek�rdez��parancsokat�(pl.�getField,�getUnit),�(a�
		k�lvil�gi)�j�t�kost�l�f�gg��manipul�ci�s�parancsokat�(l�nyeg�ben�amit�a�control�tud),�
		valamint�j�t�kost�l�f�ggetlen�manipul�ci�s�parancsokat�(pl.�killUnit,�addBox)�
		hajthatunk�v�gre�vele.�A�kapott�param�ter�maga�a�parancs.�Visszat�r�a�kimenettel�(ha�van�neki).
		�A�m�k�d�s�egy�egyszer��switch�case�szerkezet,�illetvet�a�parancs�
		darabol�sa.�(A�lek�rdez�sek�kiv�tel�n�lk�l�toString�fel�ldefini�l�s�val�t�rt�nnek�a�
		megfelel��objektum�elk�r�se�ut�n,�pl.�Stage�t�l)*/

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