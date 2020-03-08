package dungeon;

import dnd.models.Exit;
import dnd.models.Monster;
import dnd.models.Stairs;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import jerad.*;

/**
 *
 * @author Le
 */
public class PassageTest {
    
    public PassageTest() {
    }

    /** test of passageConstructor, of class Passage.
     */
    @Test
    public void testPassageConstructor() {
        System.out.println("PassageConstructor");
        Passage instance = new Passage();
        PassageSection ps = new PassageSection();
        instance.addPassageSection(ps);
        
        assertTrue(instance.getDescription().contains(ps.getDescription()));
    }
    
    /**
     * Test of getDoors method, of class Passage.
     * Add 1 doors, associated with 1 section 
     */
    @Test
    public void testGetDoors() {
        
        System.out.println("getDoors");
        Passage instance = new Passage();
        PassageSection ps = new PassageSection();
        instance.addPassageSection(ps);
        
        Door d = new Door();
        
        instance.setDoor(d);
        PassageSection ps1 = new PassageSection();
        Door d1 = new Door();
        
        instance.addPassageSection(ps1);
        instance.setDoor(d1);
        
        PassageSection ps2 = new PassageSection();
        Door d2 = new Door();
        
        instance.addPassageSection(ps2);
        instance.setDoor(d2);
  
        int result = instance.getDoors().size();
        int expResult = 3;
        assertEquals(expResult, result);        
        


    }
    
    /**
     * Test of getDoor method, of class Passage.
     */
    @Test
    public void testGetDoor() {
        System.out.println("getDoor");
       
        Passage instance = new Passage();
        PassageSection ps = new PassageSection();
        Door d = new Door();
        
        instance.addPassageSection(ps);
        instance.setDoor(d);

        PassageSection ps1 = new PassageSection();
        Door d1 = new Door();
        
        instance.addPassageSection(ps1);
        instance.setDoor(d1);
        
        PassageSection ps2 = new PassageSection("passage goes straight for 10 ft");
        instance.addPassageSection(ps2);         
        ArrayList<Door> doors = instance.getDoors();
        
        Door result =  instance.getDoor(2);
        Door expResult = null;
               
        assertEquals(expResult, result);
    }


    /**
     * Test of addMonster method, of class Passage.
     */
    @Test
    public void testAddMonster() {
        System.out.println("addMonster");
        Monster theMonster = new Monster();
        PassageSection ps = new PassageSection();
        
        Passage instance = new Passage();
        instance.addPassageSection(ps);
        int i = 0;
        instance.addMonster(theMonster, i);
        
        Monster m = instance.getMonster(i);
        
        boolean result = (theMonster.equals(m));
        boolean result1 = (theMonster.equals(ps.getMonster()));
        boolean expResult = true;
        assertEquals(expResult,result);
        assertEquals(expResult,result1);
        
    }

    /**
     * Test of getMonster method, of class Passage.
     */
    @Test
    public void testGetMonster() {
        System.out.println("getMonster");
        Monster theMonster = new Monster();
        PassageSection ps = new PassageSection();
        PassageSection ps1 = new PassageSection();
        
        Passage instance = new Passage();
        instance.addPassageSection(ps);
        instance.addPassageSection(ps1);
        
        
        int i = 1;
        instance.addMonster(theMonster, i);
        
        Monster m = instance.getMonster(i);
        
        boolean result = (theMonster.equals(m));
        boolean result1 = (theMonster.equals(ps1.getMonster()));
        boolean expResult = true;
        assertEquals(expResult,result);
        assertEquals(expResult,result1);
    }

   
   
    /**
     * Test of addPassageSection method, of class Passage.
     */
    @Test
    public void testAddPassageSection() {
        System.out.println("addPassageSection");
        PassageSection toAdd = new PassageSection("test");
        Passage instance = new Passage();
        instance.addPassageSection(toAdd);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(instance.getDescription().contains("test"));

    }

    /**
     * Test of setDoor method, of class Passage.
     */
    @Test
    public void testSetDoor() {
        System.out.println("setDoor");
        Door newDoor = new Door();
        Passage instance = new Passage();
        instance.setDoor(newDoor);
        Door door = instance.getDoor(0);
        assertEquals(newDoor, door);
        // TODO review the generated test code and remove the default call to fail.
        
    }

   
   
    /**
     * Test of getDescription method, of class Passage.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Passage instance = new Passage();
        instance.addPassageSection(new PassageSection("test"));
        Monster m = new Monster();
        instance.addMonster(m, 0);
     
        String result = instance.getDescription();
        assertTrue(result.contains(m.getDescription()) && result.contains("test"));
        // TODO review the generated test code and remove the default call to fail.
        
    }

  
}
