package dungeon;

import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.DnDElement;
import dnd.models.Monster;
import dnd.models.Stairs;
import dnd.models.Trap;
import dnd.models.Treasure;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import jerad.*;

/**
 *
 * @author Le
 */
public class ChamberTest {
    ChamberShape theShape;
    ChamberContents theContents;
    Monster myMonster;

    public ChamberTest() {
    }

    @Before
    public void setup(){
        theShape = new ChamberShape();
        theShape.setShape();
        theShape.setNumExits();
        theContents = new ChamberContents();

    }

    /**
     * Test of Chamber(ChamberShape, ChamberContents), of class Chamber
     */
    @Test
    public void testChamberConstructor() {
        System.out.println("chamberConstructor with given values");
        Chamber instance = new Chamber(theShape, theContents);

        assertTrue(instance.getDescription().contains(theContents.getDescription()) && instance.getDescription().contains(theShape.getShape()));
    }

    /**
     * Test of setShape method, of class Chamber.
     */
    @Test
    public void testSetShape() {
        System.out.println("setShape");
        ChamberShape theShape = new ChamberShape();
        theShape.setNumExits(1);

        Chamber instance = new Chamber();        
        instance.setShape(theShape);
        //ChamberShape theShape1 = instance.getChamberShape();

        //int area = theShape1.getArea();
        int numberExit = instance.getDoors().size();
        int target = theShape.getNumExits();
        if (target == 0)
        {
            target++;
        }
        assertEquals(target, numberExit );
    }


    /**
     * Test of getDoors method, of class Chamber.
     */
    @Test    
    public void testGetDoors() {
        System.out.println("getDoors");
        ChamberShape cs = new ChamberShape();
        int numberOfExit = cs.getNumExits();
        ChamberContents cc = new ChamberContents();
        if(numberOfExit == 0){
            numberOfExit++;
        }
        Chamber instance = new Chamber(cs,cc);
        int numberOfDoor = instance.getDoors().size();
        assertEquals(numberOfExit, numberOfDoor);

    }

    /**
     * Test of getMonsters method, of class Chamber.
     */
    @Test
    public void testGetMonsters() {
        System.out.println("getMonsters");
        Chamber instance = new Chamber();
        int m = instance.getMonsters().size();

        instance.addMonster(new Monster());
        instance.addMonster(new Monster());
        instance.addMonster(new Monster());
        instance.addMonster(new Monster());
        Monster myMonster = new Monster();
        instance.addMonster(myMonster);

        int  expResult = 5 + m;
        ArrayList<Monster> result = instance.getMonsters();
        if(result.size() < expResult){
            assertEquals(myMonster,result.get(0));
        }else
        {
            assertEquals(expResult, result.size());
        }

    }

    /**
     * Test of getMonsters method, of class Chamber.
     */
    @Test
    public void testGetMonsters2() {
        System.out.println("getMonsters2");
        Chamber instance = new Chamber();
        Monster mon1 = new Monster();
        Monster mon2 = new Monster();
        Monster mon3 = new Monster();
        Monster mon4 = new Monster();

        instance.addMonster(mon1);
        instance.addMonster(mon2);
        instance.addMonster(mon3);
        instance.addMonster(mon4);

        assertTrue(instance.getMonsters().contains(mon1) && instance.getMonsters().contains(mon2) && instance.getMonsters().contains(mon3) && instance.getMonsters().contains(mon4));

    }

    /**
     * Test of addTreasure method, of class Chamber.
     */
    @Test
    public void testAddTreasure() {
        System.out.println("addTreasure");

        Chamber instance = new Chamber();
        int t = instance.getTreasureList().size();

        instance.addTreasure(new Treasure());
        instance.addTreasure(new Treasure());
        instance.addTreasure(new Treasure());
        instance.addTreasure(new Treasure());
        int size = instance.getTreasureList().size();

        System.out.println("Treasure list size = " + size);

        int result = instance.getTreasureList().size();
        int expResult = 4 + t;

        assertEquals(expResult, result);
    }

    /**
     * Test of getTreasureList method, of class Chamber.
     */
    @Test
    public void testGetTreasureList() {
        System.out.println("getTreasureList");
        Chamber instance = new Chamber();
        int t = instance.getTreasureList().size();
        instance.addTreasure(new Treasure());

        int expResult = 1 + t;
        ArrayList<Treasure> result = instance.getTreasureList();
        assertEquals(expResult, result.size());

    }

    /**
     * Test of getTreasureList method, of class Chamber.
     */
    @Test
    public void testGetTreasureList2() {
        System.out.println("getTreasureList2");
        Chamber instance = new Chamber();
        Treasure treas1 = new Treasure();
        Treasure treas2 = new Treasure();
        Treasure treas3 = new Treasure();
        Treasure treas4 = new Treasure();

        instance.addTreasure(treas1);
        instance.addTreasure(treas2);
        instance.addTreasure(treas3);
        instance.addTreasure(treas4);

        assertTrue(instance.getTreasureList().contains(treas1) && instance.getTreasureList().contains(treas2) && instance.getTreasureList().contains(treas3) && instance.getTreasureList().contains(treas4));

    }

    /**
     * Test of getDescription method, of class Chamber.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Chamber instance = new Chamber(theShape, theContents);
        //String expResult = "Room1";
        //instance.setDescription(expResult);

        String result = instance.getDescription();
        assertTrue(instance.getDescription().contains(theContents.getDescription()));
        //assertEquals(expResult, result);

    }

    /**
     * Test of setDoor method, of class Chamber.
     */
    @Test
    public void testSetDoor() {
        System.out.println("setDoor");
        Door newDoor = new Door();
        Chamber instance = new Chamber(theShape, theContents);
        
        instance.setDoor(newDoor);
        String result = newDoor.getSpaces().get(0).getDescription();
        assertTrue(result.contains(theContents.getDescription()));



    }

    /**
     * Test of setDoor method for inputting doors after there are no more exits, of class Chamber.
     */
    @Test
    public void testSetDoor2() {
        System.out.println("setDoor2");
        int exits;
        Door newDoor = new Door();
        Door newDoor2 = new Door();
        ChamberShape aShape = new ChamberShape();
        while (aShape.getNumExits() >= 2) {
            aShape = new ChamberShape();
        }
        Chamber instance = new Chamber(aShape, theContents);

        instance.setDoor(newDoor);
        instance.setDoor(newDoor2);
        if(aShape.getNumExits() == 0) {
            exits = 1;
        }
        else {
            exits = aShape.getNumExits();
        }
        assertEquals(exits, instance.getDoors().size());



    }


}