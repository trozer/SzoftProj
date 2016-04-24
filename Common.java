package szoftProj;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import szoftProj.Game;

public class Common {
	private static Game game;

	public static void main(String[] args) {
		System.out.println("Ez a program teszteli a ZPM-es játékunkat! \n");
		BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				System.out.println("Mit szeretnél csinálni?");
				System.out.println("A program parancssoros használatához ezt a parancsot add ki: console");
				System.out.println("Ha elõre definiált teszteseteket szeretnél futtatni ezt add ki: test");
				System.out.println("Ha valamelyikbe beléptél és ki szeretnél lépni, akkor ezt add ki : exit");
				System.out.print(">");
				String inputLine = instream.readLine();
				StringTokenizer tokenizer = new StringTokenizer(inputLine, "   ");
				String command = tokenizer.nextToken();

				if ("test".startsWith(command)) {
					System.out.println("A teszt futtatásához a megfelelõ számot add ki parancsként!");
					System.out.println(
							"Ha több tesztesetet szeretnél futtatni, akkor azt így tudod pl megadni: 10-15 (tehát tól-ig)");
					System.out.println("A testesetek listázásához add ki ezt: list");
					while (true) {
						System.out.print(">");
						inputLine = instream.readLine();
						tokenizer = new StringTokenizer(inputLine, "-");
						command = tokenizer.nextToken();
						if ("exit".startsWith(command))
							break;
						if ("list".startsWith(command)) {
							System.out.println("\nA tesztesetek:");
							// itt késõbb nagyon sok teszteset lesz.
							System.out.println("1: Játékos üres mezõre lép");
							System.out.println("2: Játékos nem üres mezõre lép");
							System.out.println("3: Játékos üres és nyitott kapura lép");
							System.out.println("4: Játékos nem üres és nyitott kapura lép");
							System.out.println("5: Játékos zárt kapura lép");
							System.out.println("6: Játékos abyssre lép");
							System.out.println("7: Játékos scale-re lép");
							System.out.println("8: Játékos karakter nem üres (doboz) scalre lép");
							System.out.println("9: Játékos karakter falnak megy");
							System.out.println("10: Játékos karakter portál-falnak megy");
							System.out.println("11: Játékos karakter mûködõ portálnak megy");
							System.out.println("12: Játékos karakter üres és nyitott kapuból lép ki");
							System.out.println("13: Játékos karakter üres és nyitott kapuból lép ki dobozzal elzárt útra");
							System.out.println("14: Játékos fordul egy úton");
							System.out.println("15: Játékos karakter scale-rõl lép le, üres útra");
							/***************************** megvalósítsásra vár************************/
							System.out.println("16: A játékos karakter dobozt rak egy üres mezõre");
							System.out.println("17: A játékos karakter dobozt rak egy már dobozt tartalmazó mezõre");
							System.out.println("18: A játékos karakter dobozt rak egy üres, nyitott kapura");
							System.out.println("19: A játékos karakter dobozt rak egy nem üres, nyitott kapura");
							System.out.println("20: A játékos karakter dobozt rak egy zárt kapura");
							System.out.println("21: A játékos karakter dobozt vesz fel útról");
							System.out.println("22: A játékos karakter dobozt vesz fel abbysrõl");
							System.out.println("23: A játékos karakter dobozt vesz fel zárt kapuról");
							System.out.println("24: A játékos karakter dobozt vesz fel nyitott portálról");
							System.out.println("25: O’Neill lövedéket indít");
							System.out.println("26: Jaffa lövedéket indít");
							System.out.println("27: Lövedék falba csapódik");
							System.out.println("28: Lövedék portál falba csapódik");
							System.out.println("29: Lövedék becsapódás portál falba, úgy, hogy nyitva van valahol egy portál pár");
							System.out.println("30: Lövedék portálba csapódik");
							System.out.println("31: Lövedék úton halad át");
							System.out.println("32: Lövedék mérlegen halad át");
							System.out.println("33: Lövedék abyssen halad át");
							System.out.println("34: JLövedék nyitott kapun halad át");
							System.out.println("35: Játékos ZPM-re lép");
							System.out.println("36: Játékos ZPM-et vesz fel");
							System.out.println("37: Dobozt rak mérlegre (0 doboz van rajta)");
							System.out.println("38: Dobozt rak mérlegre (1 doboz van rajta)");
							System.out.println("39: Dobozt rak mérlegre (2 doboz van rajta)");
							System.out.println("40: Dobozt rak mérlegre (3 doboz van rajta)");
							System.out.println("41: Dobozt rak abyss-ra");
							System.out.println("42: Dobozt rak falra");
							System.out.println("43: Dobozt rak nyitott portálra");
							System.out.println("44: Dobozt vesz fel nyitott kapuról (kapuban van doboz");
							System.out.println("45: Dobozt vesz fel nyitott kapuról (kapuban nincs doboz)");
							System.out.println("46: Dobozt vesz fel mérlegrõl (nincs rajta doboz)");
							System.out.println("47: Dobozt vesz fel mérlegrõl (1 doboz van rajta)");
							System.out.println("48: Dobozt vesz fel mérlegrõl (3 doboz van rajta)");
							System.out.println("49: Dobozt vesz fel mérlegrõl (4 doboz van rajta)");
							System.out.println("50: Replikátor lép (abyss)");
							System.out.println("51: Replikátor lép (zárt kapu)");
							System.out.println("52: Replikátor lép (mûködõ portál)");
							System.out.println("53: Replikátor lép (üres út)");
							System.out.println("54: Replikátor lép (dobozzal elzárt út)");
							System.out.println("55: Replikátor lép (O'Neill-el elzárt út)");
							System.out.println("56: Replikátor lép (lövedékkel találkozik)");
							System.out.println("57: Replikátor lép (scale, van hozzákapcsolt kapu)");
							System.out.println("58: Replikátor lép (fal)");
						} else {
							try {
								int testCase = Integer.parseInt(command);
								String lastCaseStr = readString(tokenizer);
								int lastCase = testCase + 1;
								if (lastCaseStr != null)
									lastCase = Integer.parseInt(lastCaseStr) + 1;
								for (int i = testCase; i < lastCase; i++) {
									game = new Game();
									List<String> commands = new ArrayList<String>();
									List<String> expected = new ArrayList<String>();

									File commandFile = null;
									File expectedFile = null;
									switch (i) {
									case 1:
										commandFile = new File("lep.txt");
										expectedFile = new File("lep_elvart.txt");
										break;
									case 2:
										commandFile = new File("lep_nu.txt");
										expectedFile = new File("lep_nu_elvart.txt");
										break;
									case 3:
										commandFile = new File("lep_rg.txt");
										expectedFile = new File("lep_rg_elvart.txt");
										break;
									case 4:
										commandFile = new File("lep_rgb.txt");
										expectedFile = new File("lep_rgb_elvart.txt");
										break;
									case 5:
										commandFile = new File("lep_rgo.txt");
										expectedFile = new File("lep_rgo_elvart.txt");
										break;
									case 6:
										commandFile = new File("lep_ra.txt");
										expectedFile = new File("lep_ra_elvart.txt");
										break;
									case 7:
										commandFile = new File("lep_rs.txt");
										expectedFile = new File("lep_rs_elvart.txt");
										break;
									case 8:
										commandFile = new File("lep_rsb.txt");
										expectedFile = new File("lep_rsb_elvart.txt");
										break;
									case 9:
										commandFile = new File("lep_rw.txt");
										expectedFile = new File("lep_rw_elvart.txt");
										break;
									case 10:
										commandFile = new File("lep_rp.txt");
										expectedFile = new File("lep_rp_elvart.txt");
										break;
									case 11:
										commandFile = new File("lep_rpo.txt");
										expectedFile = new File("lep_rpo_elvart.txt");
										break;
									case 12:
										commandFile = new File("lep_gr.txt");
										expectedFile = new File("lep_gr_elvart.txt");
										break;
									case 13:
										commandFile = new File("lep_grb.txt");
										expectedFile = new File("lep_grb_elvart.txt");
										break;
									case 14:
										commandFile = new File("fordul_r.txt");
										expectedFile = new File("fordul_r_elvart.txt");
										break;
									case 15:
										commandFile = new File("lep_sr.txt");
										expectedFile = new File("lep_sr_elvart.txt");
										break;
									default:
										System.out.println("Nincs ilyen teszteset!");
										continue;
									// break;
									}
									commands = loadFromFile(commandFile);
									expected = loadFromFile(expectedFile);

									List<String> result = new ArrayList<String>();

									StringBuilder inputText = new StringBuilder();
									StringBuilder expectedText = new StringBuilder();
									for (String comm : commands) {
										inputText.append(comm + "\n");
									}
									for (String expect : expected) {
										expectedText.append(expect + "\n");
									}
									System.out.println(inputText.toString());
									runCommand(inputText.toString(), expectedText.toString(), game);
								}
							} catch (Exception ex) {
								ex.printStackTrace();
								throw new Exception("Nem számot adtál meg vagy valami baj van.");
							}
						}
					}
				} else if ("console".startsWith(command)) {
					game = new Game();

					while (true) {
						System.out.println("parancsok listázásához írd be ezt: list");
						System.out.println("játék parancs kiadásához írd be ezt: start");
						System.out.print(">");
						inputLine = instream.readLine();

						if ("exit".startsWith(inputLine))
							break;
						else
						// TODO ,ezt lécci valaki szedje majd össze
						if ("list".startsWith(inputLine)) {
							System.out.println("update: frissíti a játék állapotát.");
							System.out.println("satöbbi");
						} else {
							game.console();
							System.out.println("Hibás parancs miatt vagy egyéb okból kiléptél (starttal kezdd újra)");
						}
					}
				} else {
					throw new Exception("Hibas parancs! (" + inputLine + ")");
				}
			} catch (Exception e) {
				System.out.println("? " + e.toString());
			}
		}
	}

	static String readString(StringTokenizer tokenizer) throws Exception {
		if (tokenizer.hasMoreElements()) {
			return tokenizer.nextToken();
		} else {
			return null;
		}
	}

	static List<String> loadFromFile(File file) {
		List<String> strList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					strList.add(line);
					line = br.readLine();
				}
				return strList;
			} finally {
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	static void runCommand(String inputText, String expectedOutputText, Game game) throws Exception {

		ByteArrayInputStream bais = new ByteArrayInputStream(inputText.getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		InputStream originalInput = System.in;
		PrintStream originalOutput = System.out;

		System.setIn(bais);
		System.setOut(new PrintStream(baos));

		game.console();

		System.setIn(originalInput);
		System.setOut(originalOutput);

		String outputText = baos.toString();
		System.out.println(outputText);

		String[] outputTextArray = outputText.split("\n");

		if (expectedOutputText != null) {
			boolean equals = true;
			String[] expectedOutputTextArray = expectedOutputText.split("\n");
			System.out.println("\n");
			System.out.println("az elvárt kimenet: \n");
			for (String str : expectedOutputTextArray) {
				System.out.println(str);
			}
			for (int i = 0; i < expectedOutputTextArray.length; i++) {
				// enterek, tabulátorok kiszûrése
				if (expectedOutputTextArray[i].length() <= 1)
					break;
				if (i < outputTextArray.length) {
					if (!outputTextArray[i].equals(expectedOutputTextArray[i] + "\r")) {
						System.out.println();
						System.out.println("A különbözõ sorok:");
						System.out.println(outputTextArray[i]);
						System.out.println(expectedOutputTextArray[i]);
						equals = false;
					}
				} else
					equals = false;
			}
			if (equals)
				System.out.println("\n A teszt sikeres");
			else
				System.out.println("\n A teszt sikertelen");
		}

	}

}
