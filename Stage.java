package szoftProj;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.awt.Point;

public class Stage
{
    private int allZPM;
    private List<Unit> units;
    private List<Field> fields;

    public Stage (File file, Game game)
    {
        units =  new ArrayList<Unit>();
        fields = new ArrayList<Field>();
        init(file, game);
    }

    public void init(File stage, Game game){
    	try{
    		
    		Portal portal = new Portal();
    		
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(stage);
	    	doc.getDocumentElement().normalize();
	    	NodeList fieldRows = doc.getElementsByTagName("row");
	    	
	    	List<List<Field>> buildFields = new ArrayList<List<Field>>();
	    	
	    	//read fields row by row
	    	for(int i = 0; i < fieldRows.getLength(); i++){
	    		String row = fieldRows.item(i).getTextContent();
	    		buildFields.add(new ArrayList<Field>());
	    		
	    		for(int j = 0; j < row.length(); j++){
	    			Field field;
	                switch (row.charAt(j)) {
		                case 'r':
		                    field = new Road();
		                    break;
		                case 'a':
		                    field = new Abyss();
		                    break;
		                case 'w':
		                    field = new Wall();
		                    break;
		                case 'p':
		                	field = new PortalWall(portal);
		                    break;
		                case 's':
		                	field = new Scale();
		                    break;
		                case 'g':
		                	field = new Gate();
		                    break;
		                default:
		                    throw new Exception("Hiba: ÈrvÈnytelen karakter a t·blaleÌrÛ rÈszben");       
	                }
	                buildFields.get(i).add(field);
	    		}
	    	}
	    	
	    	//set neighbours
	    	for(int i = 0; i < buildFields.size(); i++){
	    		for(int j = 0; j < buildFields.get(i).size(); j++){
	    			if((j - 1) >= 0){
	    				buildFields.get(i).get(j).addNeighbour(Direction.WEST,
	    						buildFields.get(i).get(j - 1));
	    			}
	    			if((j + 1) < buildFields.get(i).size()){
	    				buildFields.get(i).get(j).addNeighbour(Direction.EAST,
	    						buildFields.get(i).get(j + 1));
	    			}
	    			if((i - 1) >= 0){
	    				buildFields.get(i).get(j).addNeighbour(Direction.NORTH,
	    						buildFields.get(i - 1).get(j));
	    			}
	    			if((i + 1) < buildFields.size()){
	    				buildFields.get(i).get(j).addNeighbour(Direction.SOUTH,
	    						buildFields.get(i + 1).get(j));
	    			}
	    			buildFields.get(i).get(j).setPosition(new Point(j,i));
	    			fields.add(buildFields.get(i).get(j));
	    		}
	    	}
	    	
	    	NodeList nUnits = doc.getElementsByTagName("unit");
	    	//add units
	    	for (Field field : fields){
	    		for(int i = 0; i < nUnits.getLength(); i++){
	    			Node nUnit = nUnits.item(i);
	    			if(nUnit.getNodeType() == Node.ELEMENT_NODE){
	    				Element unitElement = (Element) nUnit;
	    				
	    				Point unitPos = new Point( Integer.parseInt(unitElement.getAttribute("col")),
	    										   Integer.parseInt(unitElement.getAttribute("row")));
	    				
	    				if(field.getPosition().equals(unitPos)){
	    					String unitType = unitElement.getTextContent();
	    					if(unitType.equals("Bullet")){
	    						String rColor = unitElement.getAttribute("color");
	    						Color color = null;
	    						if(rColor.equals("blue")){
	    							color = Color.BLUE;
	    						}else
	    						if(rColor.equals("yellow")){
	    							color = Color.YELLOW;
	    						}else
	    						if(rColor.equals("red")){
	    							color = Color.RED;
	    						}else
	    						if(rColor.equals("green")){
	    							color = Color.GREEN;
	    						}else
	    							throw new Exception("Hiba: ÈrvÈnytelen a megadott szÌn");
	    						
	    						Direction dir;
	    						switch(unitElement.getAttribute("direction").charAt(0)){
	    						case 'N':
	    							dir = Direction.NORTH;
	    						break;
	    						case 'E':
	    							dir = Direction.EAST;
	    						break;
	    						case 'W':
	    							dir = Direction.WEST;
	    						break;
	    						case 'S':
	    							dir = Direction.SOUTH;
	    						break;
	    						default:
	    							throw new Exception("Hiba: ÈrvÈnytelen a megadott ir·ny");
	    						}
	    						field.addUnit(new Bullet(new Action(ActionType.MOVE, dir, color),field));
	    					}else
	    					if(unitType.equals("O'neil") || unitType.equals("Jaffa")){
	    						Direction dir;
	    						switch(unitElement.getAttribute("direction").charAt(0)){
	    						case 'N':
	    							dir = Direction.NORTH;
	    						break;
	    						case 'E':
	    							dir = Direction.EAST;
	    						break;
	    						case 'W':
	    							dir = Direction.WEST;
	    						break;
	    						case 'S':
	    							dir = Direction.SOUTH;
	    						break;
	    						default:
	    							throw new Exception("Hiba: ÈrvÈnytelen a megadott ir·ny");
	    						}
	    						ActionUnit player = new Player(0, dir, field, game);
	    						field.addUnit(player);
	    						if(unitType.equals("O'neil")){
	    							game.setOneil(player);
	    						}else
	    							game.setJaffa(player);
	    					}
	    					else
	    					if(unitType.equals("Box")){
	    						field.addUnit(new Box(field));
	    					}else
	    					if(unitType.equals("ZPM")){
	    						field.addUnit(new ZPM(field));
	    					}else
	    						throw new Exception("Hiba: ismeretlen egysÈgtÌpus");
	    				}
	    			}
	    		}
	    	}
	    	
	    	checkMap();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void checkMap(){
    	
    	//list fields and neighbours
    	for (Field field : fields) {
    		field.getPosition();
    		String north = (field.getNeighbourInDirection(Direction.NORTH) == null) ? "null" : field.getNeighbourInDirection(Direction.NORTH).toString();
    		String west = (field.getNeighbourInDirection(Direction.WEST) == null) ? "null" : field.getNeighbourInDirection(Direction.WEST).toString();
    		String east = (field.getNeighbourInDirection(Direction.EAST) == null) ? "null" : field.getNeighbourInDirection(Direction.EAST).toString();
    		String south = (field.getNeighbourInDirection(Direction.SOUTH) == null) ? "null" : field.getNeighbourInDirection(Direction.SOUTH).toString();
			System.out.println( field.toString() + " szomszÈdok: "
					+ " \n…szak: " +north
					+ " \nNyugat: " + west
					+ " \nKelet: " + east
					+ " \nDÈl: " + south + "\n");
			field.showUnits();
			System.out.println("\n\n");
		}
    }
    

    //lefuttat egy k√∂rt
    public void update()
    {
        List<Object> parameters = new ArrayList<Object>();
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("update", this, parameters);

        if(units != null)
        {
           // for (Unit unit : units)
        	for(int i = 0; i< units.size() ; i++)
                //unit.action();
        		units.get(i).action();
        }

        Skeleton.returnMethod("update", this, parameters);
    }

    //"halott" egys√©gek t√∂rl√©se units list√°b√≥l
    public void collectUnits()
    {
        List<Object> parameters = new ArrayList<Object>();
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("collectUnits", this, parameters);

        for(Iterator<Unit> it = units.iterator(); it.hasNext();)
        {
            if(it.next().isDead())
                it.remove();
        }

        Skeleton.returnMethod("collectUnits", this, parameters);
    }

    public void addUnit(Unit unit)
    {
        List<Object> parameters = new ArrayList<Object>();
        parameters.add(unit);
        parameters.add(Skeleton.getEmpty());
        Skeleton.callMethod("addUnit", this, parameters);

        this.units.add(unit);   //hozz√°ad 1 unitot, felt√©telezz√ºk, hogy a l√©trehoz√°s hely√©n be lett regisztr√°lva a Skeletonba

        Skeleton.returnMethod("addUnit", this, parameters);
    }
}