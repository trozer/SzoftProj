package szoftProj;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stage
{
    private int allZPM;         //a játéktéren található összes ZPM darabszáma
    private List<Unit> units;   //a játékban lévő összes még élő egység listája

    public Stage (int allZPM)
    {
        this.allZPM = allZPM;
        units =  new ArrayList<Unit>(); //feltételezzük, hogy a Skeleton minden kör futtatásához új Stage példányt hoz létre
    }

    public void init(char[][] table, Game game)
    {
        //Portal létrehozása
        Portal portal = new Portal();
        Skeleton.registerHashCode(portal.hashCode(), "portal");

        //mezők és unitok létrehozása
        Field[][] fields = null;    // a kapcsolatok beállításának megkönnyítése érdekében létrehozunk egy ideiglenes tárolót a Fieldeknek
        int readingState = 0;       // a karaktertömb nemcsak a táblát tartalmazza, hanem információt is a rajta elhelyezett egységekről, így nem mindegy, hogyan olvassuk: readingState = 0 : táblát olvassuk, readingState = 1 : unit-információt olvassuk


        for (int i = 0; i < table.length; ++i) {
            if(table[i][0] == '>')  //tábla-unit információ szétválasztása
            {
                readingState = 1;
                //continue;
            }
            if(readingState == 0) {     //táblát olvasunk: minden sor minden karakterét egyesével: Fieldek létrehozása
                for (int j = 0; j < table[i].length; ++j) {
                    switch (table[i][j]) {
                        case 'r':
                            Field r = new Road();
                            Skeleton.registerHashCode(r.hashCode(), "road");
                            fields[i][j] = r;
                            break;
                        case 'a':
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
                        case 's':
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
                break;
            }
            else if (readingState == 1) //ha már unit információt olvasunk be, akkor a sorok kezdőkaraterét viszgáljuk és csak megegyezés szerinti sorokat fogadunk el
            {
                Field fieldOni1i2 = fields[(int)(table[i][1])][(int)(table[i][2])]; //olvashatóság: a sornak mindig az 1-es és 2-es karaktere a pozíció

                switch (table[i][0])
                {
                    case 'P':           //Player információ
                        if(table[i].length == 5) //a helyesen paraméterezett player sor 5 elemű (P, x, y, dir, box)
                        {
                            Player player = new Player(allZPM,this.charToDirection(table[i][3]),fieldOni1i2,game);

                            Skeleton.addPlayer(player);
                            Skeleton.registerHashCode(player.hashCode(), "player");     //regisztráluk a fvkiíratáshoz

                            this.units.add(player);         //
                            fieldOni1i2.addUnit(player); //player elhelyezése a pályán (mire ide eljutunk, a fields már fel lett töltve a pályával

                            if(table[i][2] == '+')      //ha van nála doboz, akkor létrehozzuk azt
                            {
                                Box box = new Box(fieldOni1i2);
                                Skeleton.registerHashCode(box.hashCode(), "box");
                                player.grabBox(box);
                            }
                        } else {System.out.println("Hiba: a player információ sora nem 5 karakter hosszú");}
                        break;
                    case 'B':           //Bullet információ
                        if(table[i].length == 5)
                        {
                            Action moveAction = new Action(ActionType.Move,this.charToDirection(table[i][3]), this.charToColor(table[i][4]));
                            Skeleton.registerHashCode(moveAction.hashCode(), "action");

                            Bullet bullet = new Bullet(moveAction, fieldOni1i2);
                            Skeleton.registerHashCode(bullet.hashCode(), "bullet");

                            this.units.add(bullet);
                        } else {System.out.println("Hiba: a bullet információ sora nem 5 karakter hosszú");}
                        break;
                    case 'b':           //box információ
                        if(table[i].length == 3)
                        {
                            Box box = new Box(fieldOni1i2);
                            Skeleton.registerHashCode(box.hashCode(), "box");
                            fieldOni1i2.addUnit(box);

                        } else {System.out.println("Hiba: a box információ sora nem 3 karakter hosszú");}
                        break;
                    case 'z':           //ZPM információ
                        if(table[i].length == 3)
                        {
                            ZPM zpm = new ZPM(fieldOni1i2);
                            Skeleton.registerHashCode(zpm.hashCode(), "zpm");
                            fieldOni1i2.addUnit(zpm);
                        } else {System.out.println("Hiba: a zpm információ sora nem 3 karakter hosszú");}
                        break;
                    case 'p':
                        if(table[i].length == 3)    //csak 1 kijárat nélküli portál van
                        {
                            portal.createPortal(fieldOni1i2, Color.blue);
                        }
                        else if(table[i].length == 5)
                        {
                            portal.createPortal(fieldOni1i2, Color.blue);
                            portal.createPortal(fields[(int)(table[i][3])][(int)(table[i][4])], Color.blue);
                        }  else {System.out.println("Hiba: a portál kapcsolat információ sora nem 3 vagy 5 karakter hosszú");}

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
                    fields[i][j].addNeighbour(Direction.North, fields[i - 1][j]);
                }
                if (i < fields.length - 1)       //déli szomszéd
                {
                    fields[i][j].addNeighbour(Direction.South, fields[i + 1][j]);
                }
                if (j > 0)                       //nyugati szomszéd
                {
                    fields[i][j].addNeighbour(Direction.West, fields[i][j - 1]);
                }
                if (j < fields[i].length - 1)    //keleti nyomszéd
                {
                    fields[i][j].addNeighbour(Direction.East, fields[i][j + 1]);
                }
            }
        }
        fields = null; //az ideiglenes tároló megszüntetése
    }

    private Direction charToDirection(char dir) //segédfüggvény: karakterből annak betűje alapján irányt csinál
    {
        switch (dir)
        {
            case 'n' : return Direction.North;
            case 's' : return Direction.South;
            case 'e' : return Direction.East;
            case 'w' : return Direction.West;
            default:
                System.out.println("Hiba: dir nem jó karakter");
                break;
        }

        return Direction.None;  //default return
    }

    private Color charToColor(char color) //segédfüggvény: karakterből annak betűje alapján színt csinál
    {
        if(color == 'b')
        {
            return Color.blue;
        }
        else if(color == 'y')
        {
            return Color.yellow;
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

    //
    public void collectUnits()
    {
        for(Iterator<Unit> it = units.iterator(); it.hasNext();)
        {
            if(it.next().isDead)
                it.remove();
        }
    }

    public void addUnit(Unit unit)
    {
        this.units.add(unit);   //hozzáad 1 unitot, feltételezzük, hogy a létrehozás helyén be lett regisztrálva a Skeletonba
    }
}
