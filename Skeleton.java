package szoftProj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

public class Skeleton {
	private static int deepness = 0;
	private static boolean outputEnabled = false;
	private static HashMap<Integer, String> objectName  = new HashMap<Integer, String>();
	private static String callDir = "->";
	//private static  Player player;
	
	public static void main(String[] args){
		consoleMenu();
	}
	
	public static void consoleMenu(){
		BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
		try{
			System.out.println("�dv�z�llek kedves felhaszn�l�! Men�pont v�laszt�shoz �rj be egy sz�mot a felsorolt lehet�s�gek k�z�l, majd nyomj entert!\n");
			System.out.println("A men�: ");
			System.out.println("1. J�t�kos l�p");
			System.out.println("2. J�t�kos fordul");
			System.out.println("3. J�t�kos l�");
			System.out.println("4. J�t�kos felvesz egy egys�get");
			System.out.println("5. J�t�kos lerak egy dobozt");
			System.out.println("6. L�ved�k mozog");
			System.out.println("7. Kil�p�s\n");
			String in=buffer.readLine(); //sz�m beolvas�sa
			int chosen = Integer.parseInt(in); //intt� alak�tjuk
			char[][] input;
			switch (chosen){
			case 1:
				System.out.println("Milyen t�pus� az ir�nyodba es� szomsz�dos mez�?");
				System.out.println("[f]al | [s]zakad�k | [k]apu | [m]�rleg | [u]t | [p]ort�lfal");
				in=buffer.readLine();
				char cases = in.charAt(0);
				switch (cases){
				case 'k':
					System.out.println("Nyitva van a kapu ? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'I'){
						System.out.println("Van egys�g elhelyezve a nyitott kapuban? (I/N)");
						in=buffer.readLine();
						cases = in.charAt(0);
						if(cases == 'I' || cases == 'i'){
							System.out.println("Milyen egys�g van a nyitott kapuban?");
							System.out.println("([z]pm | [d]oboz)");
							in=buffer.readLine();
							cases = in.charAt(0);
							if(cases == 'z'){
								char[][] table= {{'r','g','s'},{'>'},{'P','0','0','k','-'},{'z','0','1'},{'b','0','2'}};
								start(table,1);
							}
							else if(cases == 'd'){
								char[][] table= {{'r','g','s'},{'>'},{'P','0','0','k','-'},{'b','0','1'},{'b','0','2'}};
								start(table,1);
							}
							else{
								System.out.println("Helytelen v�lasz.. :(");
							} 
						}else if(cases == 'N' || cases == 'n'){
							char[][] table= {{'r','g','s'},{'>'},{'P','0','0','k','-'},{'b','0','2'}};
							start(table,1);
						}else{ System.out.println("Helytelen v�lasz.. :(");}
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','g','s'},{'>'},{'P','0','0','k','-'}};
						start(table,1);
					}
					else{ System.out.println("Helytelen v�lasz.. :(");}
				break;
				case 'f':
				break;
				default:
					System.out.println("Nincs ilyen t�pus");
				break;
				}
					
			break;
			case 2:
			break;
			default:
				System.out.println("Nincs ilyen men�pont!");
			break;			
			}
		} catch (IOException e){
			System.out.println(e.getMessage());
		} 
	}
	
	public static void start(char[][] table, int control){
		clearObjectName();
		//Stage stage = new Stage(table);
		//stage.init(table)
		//Game game = new Game(stage);
		switch (control){
		case 1:
			//player.move();
		break;
		case 2:
			//player.turn();
		break;
		case 3:
			//player.shoot();
		break;
		case 4:
			//player.grab();
		break;
		case 5:
			//player.drop();
		break;
		}
		//game.update();
		
	}
	
	public static void returnMethod(){
		callDir = "<-";
		deepness--;	
	}
	
	public static void callMethod() {
		callDir = "->";
		deepness++;	
	}
	
	public static void printMethod(String methodName, Object called, List<Object> parameters){
		deepness++;
		String method = "-";
		for(int i = 0; i < deepness; i++) method += "\t";
		method += callDir + "[:" + objectName.get(called.hashCode()) + "]." + methodName + "("; 
		for(Object i : parameters){
			method += objectName.get(i.hashCode());
		}
		method += ")";
		System.out.println(method);
	}
	
	public static void registerHashCode(Integer objectHash, String name){
		objectName.put(objectHash, name);
	}
	
	public static void clearObjectName(){
		objectName = new HashMap<Integer, String>();
	}
	
	//public static void addPlayer(Player player) { this.player = player; }
	
	
}

