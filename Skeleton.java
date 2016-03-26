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
			System.out.println("Üdvözöllek kedves felhasználó! Menüpont választáshoz írj be egy számot a felsorolt lehetõségek közül, majd nyomj entert!\n");
			System.out.println("A menü: ");
			System.out.println("1. Játékos lép");
			System.out.println("2. Játékos fordul");
			System.out.println("3. Játékos lõ");
			System.out.println("4. Játékos felvesz egy egységet");
			System.out.println("5. Játékos lerak egy dobozt");
			System.out.println("6. Lövedék mozog");
			System.out.println("7. Kilépés\n");
			String in=buffer.readLine(); //szám beolvasása
			//int chosen = Integer.parseInt(in); //intté alakítjuk
			char cases = in.charAt(0);
			switch (cases){
			case '1':
				System.out.println("Milyen típusú az irányodba esõ szomszédos mezõ?");
				System.out.println("[f]al | [s]zakadék | [k]apu | [m]érleg | [u]t | [p]ortálfal");
				in=buffer.readLine();
				cases = in.charAt(0);
				switch (cases){
				case 'k':
					System.out.println("Nyitva van a kapu ? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						System.out.println("Van egység elhelyezve a nyitott kapuban? (I/N)");
						in=buffer.readLine();
						cases = in.charAt(0);
						if(cases == 'I' || cases == 'i'){
							System.out.println("Milyen egység van a nyitott kapuban?");
							System.out.println("([z]pm | [d]oboz)");
							in=buffer.readLine();
							cases = in.charAt(0);
							if(cases == 'z'){
								char[][] table= {{'r','g','s'},{'>'},{'c',0,1,0,2},{'P','0','0','e','-'},{'z','0','1'},{'b','0','2'}};
								start(table,1);
							}
							else if(cases == 'd'){
								char[][] table= {{'r','g','s'},{'>'},{'c',0,1,0,2},{'P','0','0','e','-'},{'b','0','1'},{'b','0','2'}};
								start(table,1);
							}
							else{
								System.out.println("Helytelen válasz.. :(");
							} 
						}else if(cases == 'N' || cases == 'n'){
							char[][] table= {{'r','g','s'},{'>'},{'c','0','1','0','2'},{'P','0','0','e','-'},{'b','0','2'}};
							start(table,1);
						}else{ System.out.println("Helytelen válasz.. :(");}
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','g','s'},{'>'},{'c','0','1','0','2'},{'P','0','0','e','-'}};
						start(table,1);
					}
					else{ System.out.println("Helytelen válasz.. :(");}
				break;
				case 'm':
					System.out.println("Van egység elhelyezve rajta? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						System.out.println("Milyen egység van rajta elhelyezve?");
						System.out.println("([z]pm | [d]oboz)");
						in=buffer.readLine();
						cases = in.charAt(0);
						if(cases == 'z'){
							char[][] table= {{'r','s','g'},{'>'},{'c','0','2','0','1'},{'P','0','0','e','-'},{'z','0','1'},{'b','0','2'}};
							start(table,1);
						}
						else if(cases == 'd'){
							char[][] table= {{'r','s','g'},{'>'},{'c','0','2','0','1'},{'P','0','0','e','-'},{'b','0','1'},{'b','0','2'}};
							start(table,1);
						}
						else{
							System.out.println("Helytelen válasz.. :(");
						} 
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','s','g'},{'>'},{'c','0','2','0','1'},{'P','0','0','e','-'}};
						start(table,1);
					}else{ System.out.println("Helytelen válasz.. :(");}
				break;
				case 'u':
					System.out.println("Van egység elhelyezve rajta? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						System.out.println("Milyen egység van rajta elhelyezve?");
						System.out.println("([z]pm | [d]oboz)");
						in=buffer.readLine();
						cases = in.charAt(0);
						if(cases == 'z'){
							char[][] table= {{'r','r'},{'>'},{'P','0','0','e','-'},{'z','0','1'}};
							start(table,1);
						}
						else if(cases == 'd'){
							char[][] table= {{'r','r'},{'>'},{'P','0','0','e','-'},{'b','0','1'}};
							start(table,1);
						}
						else{
							System.out.println("Helytelen válasz.. :(");
						} 
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','r'},{'>'},{'P','0','0','e','-'}};
						start(table,1);
					}else{ System.out.println("Helytelen válasz.. :(");}
				break;
				case 'p':
					System.out.println("Van rajta portálkapu? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						System.out.println("Van kijárata? (I/N)");
						in=buffer.readLine();
						cases = in.charAt(0);
						if(cases == 'I' || cases == 'i'){
							char[][] table= {{'r','p','p'},{'>'},{'p','0','1','0','2'},{'P','0','0','e','-'}};
							start(table,1);
						}
						else if(cases == 'N' || cases == 'n'){
							char[][] table= {{'r','p'},{'>'},{'p','0','1'},{'P','0','0','e','-'}};
							start(table,1);
						}
						else{
							System.out.println("Helytelen válasz.. :(");
						} 
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','p'},{'>'},{'P','0','0','e','-'}};
						start(table,1);
					}else{ System.out.println("Helytelen válasz.. :(");}
				case 'f':
				{
					char[][] table= {{'r','w'},{'>'},{'P','0','0','e','-'}};
					start(table,1);
				}

				break;
				case 's':
				{		
					char[][] table= {{'r','a'},{'>'},{'P','0','0','e','-'}};
					start(table,1);
				}
				break;
				default:
					System.out.println("Nincs ilyen típus");
				break;
				}
			break;
			case '2':
			{
				System.out.println("Melyik irányba forduljon?");
				System.out.println("([e]szak | [d]él | [k]elet | [n]yugat)");
				in=buffer.readLine();
				cases = in.charAt(0);
				switch(cases){
				case 'e':
				{
					char[][] table = {{'r'},{'>'},{'P','0','0','s','-'}};
					start(table,2);
				}
				break;
				case 'd':
				{
					char[][] table = {{'r'},{'>'},{'P','0','0','n','-'}};
					start(table,2);
				}
				break;
				case 'k':
				{
					char[][] table = {{'r'},{'>'},{'P','0','0','w','-'}};
					start(table,2);
				}
				break;
				case 'n':
				{
					char[][] table = {{'r'},{'>'},{'P','0','0','e','-'}};
					start(table,2);
				}
				break;
				default:
					System.out.println("Helytelen válasz.. :(");
				break;
				}
			}
			break;
			case '3':
			{	
				System.out.println("Milyen színû lövedéket lõjön?");
				System.out.println("([k]ék | [s]árga)");
				in=buffer.readLine();
				cases = in.charAt(0);
				if(cases == 'k'){
					char[][] table = {{'r'},{'>'},{'P','0','0','e','-'}};
					start(table,3);
				}else if(cases == 's'){
					char[][] table = {{'r','r'},{'>'},{'P','0','0','e','-'}};
					start(table,3);
				}else{
					System.out.println("Helytelen válasz.. :(");
				}
			}
			break;
			case '4':
				System.out.println("Milyen típusú az irányodba esõ szomszédos mezõ?");
				System.out.println("[f]al | [s]zakadék | [k]apu | [m]érleg | [u]t | [p]ortálfal");
				in=buffer.readLine();
				cases = in.charAt(0);
				switch (cases){
				case 'k':
					System.out.println("Nyitva van a kapu ? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						System.out.println("Van egység elhelyezve a nyitott kapuban? (I/N)");
						in=buffer.readLine();
						cases = in.charAt(0);
						if(cases == 'I' || cases == 'i'){
							System.out.println("Milyen egység van a nyitott kapuban?");
							System.out.println("([z]pm | [d]oboz)");
							in=buffer.readLine();
							cases = in.charAt(0);
							if(cases == 'z'){
								char[][] table= {{'r','g','s'},{'>'},{'c',0,1,0,2},{'P','0','0','e','-'},{'z','0','1'},{'b','0','2'}};
								start(table,4);
							}
							else if(cases == 'd'){
								char[][] table= {{'r','g','s'},{'>'},{'c',0,1,0,2},{'P','0','0','e','-'},{'b','0','1'},{'b','0','2'}};
								start(table,4);
							}
							else{
								System.out.println("Helytelen válasz.. :(");
							} 
						}else if(cases == 'N' || cases == 'n'){
							char[][] table= {{'r','g','s'},{'>'},{'c','0','1','0','2'},{'P','0','0','e','-'},{'b','0','2'}};
							start(table,4);
						}else{ System.out.println("Helytelen válasz.. :(");}
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','g','s'},{'>'},{'c','0','1','0','2'},{'P','0','0','e','-'}};
						start(table,4);
					}
					else{ System.out.println("Helytelen válasz.. :(");}
				break;
				case 'm':
					System.out.println("Van egység elhelyezve rajta? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						System.out.println("Milyen egység van rajta elhelyezve?");
						System.out.println("([z]pm | [d]oboz)");
						in=buffer.readLine();
						cases = in.charAt(0);
						if(cases == 'z'){
							char[][] table= {{'r','s','g'},{'>'},{'c','0','2','0','1'},{'P','0','0','e','-'},{'z','0','1'},{'b','0','2'}};
							start(table,4);
						}
						else if(cases == 'd'){
							char[][] table= {{'r','s','g'},{'>'},{'c','0','2','0','1'},{'P','0','0','e','-'},{'b','0','1'},{'b','0','2'}};
							start(table,4);
						}
						else{
							System.out.println("Helytelen válasz.. :(");
						} 
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','s','g'},{'>'},{'c','0','2','0','1'},{'P','0','0','e','-'}};
						start(table,4);
					}else{ System.out.println("Helytelen válasz.. :(");}
				break;
				case 'u':
					System.out.println("Van egység elhelyezve rajta? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						System.out.println("Milyen egység van rajta elhelyezve?");
						System.out.println("([z]pm | [d]oboz)");
						in=buffer.readLine();
						cases = in.charAt(0);
						if(cases == 'z'){
							char[][] table= {{'r','r'},{'>'},{'P','0','0','e','-'},{'z','0','1'}};
							start(table,4);
						}
						else if(cases == 'd'){
							System.out.println("Van a játékosnaál korábban felvett doboz? (I/N)");
							in=buffer.readLine();
							cases = in.charAt(0);
							if(cases == 'I' || cases == 'i'){
								char[][] table= {{'r','r'},{'>'},{'P','0','0','e','b'},{'b','0','1'}};
								start(table,4);
							}else if(cases == 'N' || cases == 'n'){
								char[][] table= {{'r','r'},{'>'},{'P','0','0','e','-'},{'b','0','1'}};
								start(table,4);
							}else{ System.out.println("Helytelen válasz.. :(");}
						}
						else{
							System.out.println("Helytelen válasz.. :(");
						} 
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','r'},{'>'},{'P','0','0','e','-'}};
						start(table,4);
					}else{ System.out.println("Helytelen válasz.. :(");}
				break;
				case 'p':
				{
					char[][] table= {{'r','p'},{'>'},{'p','0','1'},{'P','0','0','e','-'}};
					start(table,4);
				}
				break;
				case 'f':
				{
					char[][] table= {{'r','w'},{'>'},{'P','0','0','e','-'}};
					start(table,4);
				}

				break;
				case 's':
				{		
					char[][] table= {{'r','a'},{'>'},{'P','0','0','e','-'}};
					start(table,4);
				}
				break;
				default:
					System.out.println("Nincs ilyen típus");
				break;
				}
			break;
			case '5':
				System.out.println("Milyen típusú az irányodba esõ szomszédos mezõ?");
				System.out.println("[f]al | [s]zakadék | [k]apu | [m]érleg | [u]t | [p]ortálfal");
				in=buffer.readLine();
				cases = in.charAt(0);
				switch (cases){
				case 'k':
					System.out.println("Nyitva van a kapu ? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						System.out.println("Van egység elhelyezve a nyitott kapuban? (I/N)");
						in=buffer.readLine();
						cases = in.charAt(0);
						if(cases == 'I' || cases == 'i'){
							char[][] table= {{'r','g','s'},{'>'},{'c',0,1,0,2},{'P','0','0','e','b'},{'b','0','1'},{'b','0','2'}};
							start(table,5);
						}else if(cases == 'N' || cases == 'n'){
							char[][] table= {{'r','g','s'},{'>'},{'c','0','1','0','2'},{'P','0','0','e','b'},{'b','0','2'}};
							start(table,5);
						}else{ System.out.println("Helytelen válasz.. :(");}
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','g','s'},{'>'},{'c','0','1','0','2'},{'P','0','0','e','b'}};
						start(table,5);
					}
					else{ System.out.println("Helytelen válasz.. :(");}
				break;
				case 'm':
					System.out.println("Van egység elhelyezve rajta? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						char[][] table= {{'r','s','g'},{'>'},{'c','0','2','0','1'},{'P','0','0','e','b'},{'b','0','1'},{'b','0','2'}};
						start(table,5);
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','s','g'},{'>'},{'c','0','2','0','1'},{'P','0','0','e','b'}};
						start(table,5);
					}else{ System.out.println("Helytelen válasz.. :(");}
				break;
				case 'u':
					System.out.println("Van egység elhelyezve rajta? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						char[][] table= {{'r','r'},{'>'},{'P','0','0','e','b'},{'b','0','1'}};
						start(table,5);
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','r'},{'>'},{'P','0','0','e','b'}};
						start(table,5);
					}else{ System.out.println("Helytelen válasz.. :(");}
				break;
				case 'p':
				{
					char[][] table= {{'r','p'},{'>'},{'p','0','1'},{'P','0','0','e','b'}};
					start(table,5);
				}
				break;
				case 'f':
				{
					char[][] table= {{'r','w'},{'>'},{'P','0','0','e','b'}};
					start(table,5);
				}

				break;
				case 's':
				{		
					char[][] table= {{'r','a'},{'>'},{'P','0','0','e','b'}};
					start(table,5);
				}
				break;
				default:
					System.out.println("Nincs ilyen típus");
				break;
				}
			break;
			case '6':
				System.out.println("Milyen típusú a lövedék irányába esõ szomszédos mezõ");
				System.out.println("[f]al | [s]zakadék | [k]apu | [m]érleg | [u]t | [p]ortálfal");
				in=buffer.readLine();
				cases = in.charAt(0);
				switch (cases){
				case 'k':
					System.out.println("Nyitva van a kapu ? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						char[][] table= {{'r','g','s'},{'>'},{'c','0','1','0','2'},{'B','0','0','e'},{'b','0','2'}};
						start(table,6);
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','g','s'},{'>'},{'c','0','1','0','2'},{'B','0','0','e','-'}};
						start(table,6);
					}
					else{ System.out.println("Helytelen válasz.. :(");}
				break;
				case 'm':
				{
					char[][] table= {{'r','s','g'},{'>'},{'c','0','2','0','1'},{'B','0','0','e'},{'z','0','1'},{'b','0','2'}};
					start(table,6);
				}
				break;
				case 'u':
				{
					char[][] table= {{'r','r'},{'>'},{'B','0','0','e'}};
					start(table,1);
				}
				break;
				case 'p':
					System.out.println("Van rajta portálkapu? (I/N)");
					in=buffer.readLine();
					cases = in.charAt(0);
					if(cases == 'I' || cases == 'i'){
						System.out.println("Van olyan színû portálbakpu a pályán, mint a lövedék? (I/N)");
						in=buffer.readLine();
						cases = in.charAt(0);
						if(cases == 'I' || cases == 'i'){
							char[][] table= {{'r','p','p'},{'>'},{'p','0','2','0','1'},{'B','0','0','e'}};
							start(table,6);
						}
						else if(cases == 'N' || cases == 'n'){
							char[][] table= {{'r','p'},{'>'},{'p','0','1'},{'B','0','0','e'}};
							start(table,6);
						}
						else{
							System.out.println("Helytelen válasz.. :(");
						} 
					}else if(cases == 'N' || cases == 'n'){
						char[][] table= {{'r','p'},{'>'},{'B','0','0','e'}};
						start(table,6);
					}else{ System.out.println("Helytelen válasz.. :(");}
				case 'f':
				{
					char[][] table= {{'r','w'},{'>'},{'B','0','0','e',}};
					start(table,6);
				}

				break;
				case 's':
				{		
					char[][] table= {{'r','a'},{'>'},{'B','0','0','e'}};
					start(table,6);
				}
				break;
				default:
					System.out.println("Nincs ilyen típus");
				break;
				}
			break;
			case 7:
				System.out.println("kilépés");
			break;
			default:
				System.out.println("Nincs ilyen menüpont!");
			break;			
			}
		} catch (IOException e){
			System.out.println(e.getMessage());
		} 
	}
	
	public static void start(char[][] table, int control){
		clearObjectName();
		Game game = new Game();
		Stage stage = new Stage(2);
		stage.init(table,game);
		game.setStage(stage);
		
		switch (control){
		case 1:
			player.move();
		break;
		case 2:
			player.turn();
		break;
		case 3:
			player.shoot();
		break;
		case 4:
			player.grab();
		break;
		case 5:
			player.drop();
		break;
		}
		game.update();
		
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
	
	public static void addPlayer(Player player) { this.player = player; }
	
	
}

