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
		/*? Konverzi�a�k�lvil�g��s�a�j�t�k�bels��m�k�d�se�k�z�tt:�a�
		felhaszn�l�i�bemeneti�esem�nyeknek�megfelel�en�helyesen�be�ll�tja�a�Player�
		k�vetkez��update()�ben�v�grehajtand�cselekedet�t.�A�cmd�param�terben�kapott�
		inform�ci�alapj�n�tudja,�hogy�mit�kell�be�ll�tani.�A�m�k�d�s�egy�egyszer��switch�
		case�szerkezet��s�a�parancs�darabol�s�(parsol�sa).�*/
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