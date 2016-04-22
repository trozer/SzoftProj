package szoftProj;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
    private List<Field> roads;
    private Portal portal;
    private boolean log;

    public Stage (File file, Game game)
    {
        units =  new ArrayList<Unit>();
        fields = new ArrayList<Field>();
        roads = new ArrayList<Field>();
        init(file, game);
    }

    public void init(File stage, Game game){
    	try{
    		
    		portal = new Portal();
    		
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(stage);
	    	doc.getDocumentElement().normalize();
	    	NodeList fieldRows = doc.getElementsByTagName("row");
	    	
	    	List<List<Field>> buildFields = new ArrayList<List<Field>>();
	    	List<Scale> connectScales = new ArrayList<Scale>();
	    	List<Gate> connectGates = new ArrayList<Gate>();
	    	List<PortalWall> activatePortalWalls = new ArrayList<PortalWall>();
	    	
	    	//read fields row by row
	    	for(int i = 0; i < fieldRows.getLength(); i++){
	    		String row = fieldRows.item(i).getTextContent();
	    		buildFields.add(new ArrayList<Field>());
	    		
	    		for(int j = 0; j < row.length(); j++){
	    			Field field;
	                switch (row.charAt(j)) {
		                case 'r':
		                    field = new Road();
		                    roads.add(field);
		                    break;
		                case 'a':
		                    field = new Abyss();
		                    break;
		                case 'w':
		                    field = new Wall();
		                    break;
		                case 'p':
		                	PortalWall portalWall = new PortalWall(portal); 
		                	field = portalWall;
		                	activatePortalWalls.add(portalWall);
		                    break;
		                case 's':
		                	Scale scale = new Scale();
		                	field = scale;
		                	connectScales.add(scale);
		                    break;
		                case 'g':
		                	Gate gate = new Gate();
		                	field = gate;
		                	connectGates.add(gate);
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
	    	
	    	//make connection between scales and gates
	    	NodeList nConnections = doc.getElementsByTagName("connection");
	    	
	    	for(int i = 0; i < nConnections.getLength(); i++){
	    		Node nConnection = nConnections.item(i);
	    		if(nConnection.getNodeType() == Node.ELEMENT_NODE){
	    			Element connectionElement = (Element) nConnection;
	    			
	    			Point fromPos = new Point(Integer.parseInt(connectionElement.getAttribute("fCol")),
	    									  Integer.parseInt(connectionElement.getAttribute("fRow")));
	    			Point toPos = new Point(Integer.parseInt(connectionElement.getAttribute("toCol")),
							  				Integer.parseInt(connectionElement.getAttribute("toRow")));
	    			for(Scale scale : connectScales){
	    				for(Gate gate : connectGates){
	    					if(scale.getPosition().equals(fromPos) &&
	    					   gate.getPosition().equals(toPos)){
	    						scale.setGate(gate);
	    					}
	    				}
	    			}
	    		}
	    	}
	    	
	    	
	    	//activate portalwalls
	    	NodeList nPortalWallColors = doc.getElementsByTagName("portalwall_color");
	    	
	    	for(int i = 0; i < nPortalWallColors.getLength(); i++){
	    		Node nPortalWallColor = nPortalWallColors.item(i);
	    		if(nPortalWallColor.getNodeType() == Node.ELEMENT_NODE){
	    			Element portalWallColorElement = (Element) nPortalWallColor;
	    			
	    			Point Pos = new Point(Integer.parseInt(portalWallColorElement.getAttribute("col")),
	    									  Integer.parseInt(portalWallColorElement.getAttribute("row")));
					Color color = colorByString(portalWallColorElement.getAttribute("color"));
					for(PortalWall portalWall : activatePortalWalls){
						if(portalWall.getPosition().equals(Pos)){
							portal.createPortal(portalWall, color);
						}
					}
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
	    						Color color = colorByString(unitElement.getAttribute("color"));
	    						Direction dir = directionByChar(unitElement.getAttribute("direction").charAt(0));;
	    						Bullet bullet = new Bullet(new Action(ActionType.MOVE, dir, color),field);
	    						units.add(bullet);
	    						field.addUnit(bullet);
	    					}else
	    					if(unitType.equals("O'neil") || unitType.equals("Jaffa")){
	    						Direction dir = directionByChar(unitElement.getAttribute("direction").charAt(0));
	    						ActionType actionType = actionTypeByString(unitElement.getAttribute("action"));
	    						Direction turnDir = Direction.NORTH; //default, this is not affect actions (exclude turn) 
	    						Color color = null;
	    						Box box = null;
	    						if(actionType == ActionType.TURN)
	    							turnDir = directionByChar(unitElement.getAttribute("turndir").charAt(0));
	    						if(actionType == ActionType.SHOOT)
	    							color = colorByString(unitElement.getAttribute("color"));
	    						if(unitElement.getAttribute("box").equals("true"))
	    							box = new Box(field);
	    						ActionUnit player = new Player(allZPM, dir,new Action(actionType, turnDir, color), field, game,box);
	    						units.add(player);
	    						field.addUnit(player);
	    						if(unitType.equals("O'neil")){
	    							game.setOneil(player);
	    						}else
	    							game.setJaffa(player);
	    					}
	    					else
	    					if(unitType.equals("Box")){
	    						Box box = new Box(field);
	    						units.add(box);
	    						field.addUnit(box);
	    					}else
	    					if(unitType.equals("ZPM")){
	    						allZPM++;
	    						ZPM zpm = new ZPM(field);
	    						field.addUnit(zpm);
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
    
    public Direction directionByChar(char c) throws Exception{
		switch(c){
		case 'N': return Direction.NORTH;
		case 'E': return Direction.EAST;
		case 'W': return Direction.WEST;
		case 'S': return Direction.SOUTH;
		default:
			throw new Exception("Hiba: ÈrvÈnytelen a megadott ir·ny");
		}
    }
    
    public Color colorByString(String rColor) throws Exception{
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
		return color;
    }
    
    public ActionType actionTypeByString(String type) throws Exception{
		ActionType action = ActionType.NONE;
		if(type.equals("MOVE")){
			action = ActionType.MOVE;
		}else
		if(type.equals("TURN")){
			action = ActionType.TURN;
		}else
		if(type.equals("SHOOT")){
			action = ActionType.SHOOT;
		}else
		if(type.equals("GRAB")){
			action = ActionType.GRAB;
		}else
		if(type.equals("DROP")){
			action = ActionType.DROP;
		}else
			throw new Exception("Hiba: ÈrvÈnytelen a megadott akciÛ");
		return action;
    }
    
    
    //helper function
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
    
    public Portal getPortal(){
    	return portal;
    }
    
    public void logOff(){
    	log = false;
    }
    
    public void logOn(){
    	log = true;
    }
    
    public void createZPM(){
    	List<Field> emptyRoads = new ArrayList<Field>();
    	
    	for(Field road : roads){
    		if(road.containedUnits.isEmpty()){
    			emptyRoads.add(road);
    		}
    	}
    	
    	Random rand = new Random();
    	int n = rand.nextInt(emptyRoads.size());
    	
    	ZPM drop = new ZPM(emptyRoads.get(n));
    	emptyRoads.get(n).addUnit(drop);
    	units.add(drop);
    }
    
    //given field's must be initialized
    public void replaceField(Field field){
    	Road road = new Road();
    	Field oldField = fields.get(fields.indexOf(field));
    	road.setPosition(oldField.getPosition());
    	road.addNeighbour(Direction.NORTH, field.getNeighbourInDirection(Direction.NORTH));
    	road.addNeighbour(Direction.WEST, field.getNeighbourInDirection(Direction.WEST));
    	road.addNeighbour(Direction.EAST, field.getNeighbourInDirection(Direction.EAST));
    	road.addNeighbour(Direction.SOUTH, field.getNeighbourInDirection(Direction.SOUTH));
    	fields.set(fields.indexOf(field), road);
    	
    	boolean isRoad = false;
    	for(Field r : roads){
    		if(r.getPosition().equals(field.getPosition()))
    			isRoad = true;
    	}
    	if(!isRoad)
    		roads.add(road);
    	else
    		roads.set(roads.indexOf(field), road);
    }
    
    public List<Unit> getUnit(Field field){
    	return fields.get(fields.indexOf(field)).getUnits();
    }
    
    public int getZPM(){
    	return allZPM;
    }
    
    //helper query, check boxes
    public List<Unit> listBoxes(){
    	List<Unit> boxes = new ArrayList<Unit>();
    	for(Unit box : units){
    		if(box instanceof Box){
    			boxes.add(box);
    		}
    	}
    	return boxes;
    }
    
    //helper query, check zpm's
    public List<Unit> listZPM(){
    	List<Unit> zpms = new ArrayList<Unit>();
    	for(Unit zpm : units){
    		if(zpm instanceof ZPM){
    			zpms.add(zpm);
    		}
    	}
    	return zpms;
    }
    
    public Field getField(Point position){
    	for(Field field : fields){
    		if(field.getPosition().equals(position))
    			return field;
    	}
    	return null;
    }
    
    public void update(){
    	List<String> before = new ArrayList<String>();
    	List<String> after = new ArrayList<String>();
    	
    	if(log){
	        for(Unit unit : units){
	        	before.add(unit.toString());
	        }
    	}
    	
        for(Unit unit : units){
        	unit.action();
        }
        
    	if(log){
	        for(Unit unit : units){
	        	after.add(unit.toString());
	        }
    	}
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