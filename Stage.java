package szoftProj;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stage
{
    private int allZPM;
    private List<Unit> units;

    public Stage (int allZPM)
    {
        this.allZPM = allZPM;
        units =  new ArrayList<Unit>();

    }

    public void init(char[][] table, Game game)
    {
        //Portal létrehozása
/*        Portal portal = new Portal();*
        Skeleton.registerHashCode(portal.hashCode(), "portal");
*/
        //mezők és unitok létrehozása
        Field[][] fields = new Field[0][];   //ideiglenes tároló a Fieldeknek


        //tároló méretének beállítása
        for(int i = 0; i < table.length && table[i][0] != '>'; ++i)
        {
            fields = new Field[i+1][];
        }
        for(int i = 0; i < fields.length; ++i)
        {
            fields[i] = new Field[(table[i].length)];
        }

        int readingState = 0;       // 0 : táblát olvassuk; 1 : unit-információt olvassuk a table tömbből

        for (int i = 0; i < table.length; ++i) {
            if(table[i][0] == '>')  //tábla-unit információ szétválasztása
            {
                readingState = 1;
                continue;
            }
            if(readingState == 0) {     //táblát olvasunk: minden sor minden karakterét egyesével: Fieldek létrehozása
                for (int j = 0; j < table[i].length; ++j) {
                    switch (table[i][j]) {
                        case 'r':
                            Field r = new Road();
                            Skeleton.registerHashCode(r.hashCode(), "road");
                            fields[i][j] = r;
                            break;
/*                        case 'a':
                            Field a = new Abyss();
                            Skeleton.registerHashCode(a.hashCode(), "abyss");
                            fields[i][j] = a;
                            break;
                        case 'w':
                            Field w = new Wall();
                            Skeleton.registerHashCode(w.hashCode(), "wall");
                            fields[i][j] = w;
                            break;
                        case 'p':
                            Field pw = new PortalWall(portal);
                            Skeleton.registerHashCode(pw.hashCode(), "portalwall");
                            fields[i][j] = pw;
                            break;
*/                        case 's':
                            Field s = new Scale();
                            Skeleton.registerHashCode(s.hashCode(), "scale");
                            fields[i][j] = s;
                            break;
                        case 'g':
                            Field g = new Gate();
                            Skeleton.registerHashCode(g.hashCode(), "gate");
                            fields[i][j] = g;
                            break;
                        default:
                            System.out.println("Hiba: érvénytelen karakter a táblaleíró részben");
                            break;
                    }
                }
            }
            else if (readingState == 1) //ha már unit információt olvasunk be, akkor a sorok kezdőkaraterét viszgáljuk
            {

                switch (table[i][0])
                {
                    case 'P':           //Player információ
                        if(table[i].length == 5) //a helyesen paraméterezett player sor 5 elemű (P, x, y, dir, box)
                        {

                            //olvashatóság: a sor 1-es és 2-es karakterében lévő pozíció = fieldOntablei1i2
                            Field fieldOntablei1i2 = fields[Character.getNumericValue(table[i][1])][Character.getNumericValue(table[i][2])];

                            Player player = new Player(allZPM,this.charToDirection(table[i][3]),fieldOntablei1i2,game);
                            Skeleton.addPlayer(player);
                            Skeleton.registerHashCode(player.hashCode(), "player");     //regisztráluk a fvkiíratáshoz
                            this.units.add(player);

                            //field - player kapcsolat
                            fieldOntablei1i2.addUnit(player);

                            if(table[i][4] == '+')      //ha van nála doboz, akkor létrehozzuk azt
                            {
                                Box box = new Box(fieldOntablei1i2);
                                Skeleton.registerHashCode(box.hashCode(), "box");
                                player.grabBox(box);
                            }
                        }
                        else
                        {
                            System.out.println("Hiba: a player információ sora nem 5 karakter hosszú");
                        }
                        break;
                    case 'B':           //Bullet információ
                        if(table[i].length == 5)
                        {
                            Action moveAction = new Action(ActionType.MOVE,this.charToDirection(table[i][3]), this.charToColor(table[i][4]));
                            Skeleton.registerHashCode(moveAction.hashCode(), "action");

                            Field fieldOntablei1i2 = fields[Character.getNumericValue(table[i][1])][Character.getNumericValue(table[i][2])];
                            //lövedék kapcsolatai: elég, ha csak a lövedék tud a mezőről, amin van a mezőnek (amin épp a player is áll) nem kell tudni (úgyis egyből elmegy róla)
                            Bullet bullet = new Bullet(moveAction, fieldOntablei1i2);
                            Skeleton.registerHashCode(bullet.hashCode(), "bullet");

                            this.units.add(bullet);
                        }
                        else
                        {
                            System.out.println("Hiba: a bullet információ sora nem 5 karakter hosszú");
                        }
                        break;
                    case 'b':           //box információ
                        if(table[i].length == 3)
                        {
                            Field fieldOntablei1i2 = fields[Character.getNumericValue(table[i][1])][Character.getNumericValue(table[i][2])];
                            Box box = new Box(fieldOntablei1i2);
                            Skeleton.registerHashCode(box.hashCode(), "box");
                            fieldOntablei1i2.addUnit(box);
                        }
                        else
                        {
                            System.out.println("Hiba: a box információ sora nem 3 karakter hosszú");
                        }
                        break;
                    case 'z':           //ZPM információ
                        if(table[i].length == 3)
                        {
                            Field fieldOntablei1i2 = fields[Character.getNumericValue(table[i][1])][Character.getNumericValue(table[i][2])];

                            ZPM zpm = new ZPM(fieldOntablei1i2);
                            Skeleton.registerHashCode(zpm.hashCode(), "zpm");
                            fieldOntablei1i2.addUnit(zpm);
                        }
                        else
                        {
                            System.out.println("Hiba: a zpm információ sora nem 3 karakter hosszú");
                        }
                        break;
 /*                   case 'p':
                        if(table[i].length == 3)    //csak 1 kijárat nélküli portál van
                        {
                            Field fieldOntablei1i2 = fields[Character.getNumericValue(table[i][1])][Character.getNumericValue(table[i][2])];
                            portal.createPortal(fieldOntablei1i2, Color.blue);
                        }
                        else if(table[i].length == 5)
                        {
                            Field fieldOntablei1i2 = fields[Character.getNumericValue(table[i][1])][Character.getNumericValue(table[i][2])];
                            Field fieldOntablei3i4 = fields[Character.getNumericValue(table[i][3])][Character.getNumericValue(table[i][4])];

                            portal.createPortal(fieldOntablei1i2, Color.blue);
                            portal.createPortal(fieldOntablei3i4, Color.yellow);
                        }
                        else
                        {
                            System.out.println("Hiba: a portál kapcsolat információ sora nem 3 vagy 5 karakter hosszú");
                        }
 */                   case 'c':
                    if(table[i].length == 5)    //connection, gate sor, gate oszlop, scale sor, scale oszlop
                    {
                        Field fieldOntablei1i2 = fields[Character.getNumericValue(table[i][1])][Character.getNumericValue(table[i][2])];
                        Field fieldOntablei3i4 = fields[Character.getNumericValue(table[i][3])][Character.getNumericValue(table[i][4])];

                        ((Scale)fieldOntablei3i4).setGate((Gate)fieldOntablei1i2);
                    }
                    else
                    {
                        System.out.println("Hiba: a gate-scale kapcsolat információ sora nem 5 karakter hosszú");
                    }

                    default:
                        System.out.println("Hiba: valamelyik Unit információ sora nem megfelelően kezdődik");
                        break;

                }
            }
            else
            {
                System.out.println("Hiba: readingState nem 0 vagy 1");
            }
        }

        //mezők viszonyainak beállítása
        for(int i = 0; i < fields.length; ++i) {
            for (int j = 0; j < fields[i].length; ++j) {
                if (i > 0)                       //északi szomszéd
                {
                    fields[i][j].addNeighbour(Direction.NORTH, fields[i - 1][j]);
                }
                if (i < fields.length - 1)       //déli szomszéd
                {
                    fields[i][j].addNeighbour(Direction.SOUTH, fields[i + 1][j]);
                }
                if (j > 0)                       //nyugati szomszéd
                {
                    fields[i][j].addNeighbour(Direction.WEST, fields[i][j - 1]);
                }
                if (j < fields[i].length - 1)    //keleti nyomszéd
                {
                    fields[i][j].addNeighbour(Direction.EAST, fields[i][j + 1]);
                }
            }
        }
        fields = null; //az ideiglenes tároló megszüntetése
    }

    private Direction charToDirection(char dir) //segédfüggvény: karakterből annak betűje alapján irányt csinál
    {
        switch (dir)
        {
            case 'n' : return Direction.NORTH;
            case 's' : return Direction.SOUTH;
            case 'e' : return Direction.EAST;
            case 'w' : return Direction.WEST;
            default:
                System.out.println("Hiba: dir nem jó karakter");
                break;
        }

        return Direction.NONE;  //default return
    }

    private Color charToColor(char color) //segédfüggvény: karakterből annak betűje alapján színt csinál
    {
        if(color == 'b')
        {
            Color c = Color.blue;
            Skeleton.registerHashCode(c.hashCode(), "color");

            return c;
        }
        else if(color == 'y')
        {
            Color c = Color.yellow;
            Skeleton.registerHashCode(c.hashCode(), "color");

            return c;
        }
        System.out.println("Hiba: color nem jó karakter");
        return Color.black;
    }

    //lefuttat egy kört
    public void update()
    {
        if(units != null)
        {
            for (Unit unit : units)
                unit.action();
        }
    }

    //"halott" egységek törlése units listából
    public void collectUnits()
    {
        for(Iterator<Unit> it = units.iterator(); it.hasNext();)
        {
            if(it.next().isDead())
                it.remove();
        }
    }

    public void addUnit(Unit unit)
    {
        this.units.add(unit);   //hozzáad 1 unitot, feltételezzük, hogy a létrehozás helyén be lett regisztrálva a Skeletonba
    }
}