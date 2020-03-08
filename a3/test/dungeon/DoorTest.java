package dungeon;

import dnd.models.Trap;
import dnd.models.Exit;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import jerad.*;

/**
 *
 * @author Le
 */
public class DoorTest {
    
    public DoorTest() {
        

    }


    /**
     * test of doorConstructor, of class Door.
     */
    @Test
    public void testDoorConstructor() {
        System.out.println("doorConstructor");
        Door instance = new Door();
        
        assertTrue(instance.getDescription() != null);
    }

    /**
     * test of doorConstructor, of class Door.
     */
    @Test
    public void testDoorConstructor2() {
        System.out.println("doorConstructor2");
        Exit exit = new Exit();
        Door instance = new Door(exit);
        
        assertTrue(instance.getDescription().contains(exit.getDirection()) && instance.getDescription().contains(exit.getLocation()));
    }


    /**
     * Test of setTrapped method, of class Door.
     * Case: flag == true without roll
     * Expected result, Trap is set.
     */
    @Test
    public void testSetTrappedOne() {
        System.out.println("setTrapped");
        boolean flag = true;

        Door instance = new Door();
        instance.setArchway(false);
        instance.setTrapped(flag);
        assertEquals(true, instance.isTrapped());
        
    }
    
    /**
     * Test of setTrapped method, of class Door.
     * Case: flag == true, with roll
     * Expected result, Trap is set 1/20. 
     * 
     */
    @Test
    public void testSetTrappedTwo() {
        System.out.println("setTrapped");
        boolean flag = true;
        Door instance = new Door();
        instance.setArchway(false);
        instance.setTrapped(true,6);
        String expect = " Pit, 10' deep, 3 in 6 to fall in.";
        // TODO review the generated test code and remove the default call to fail.
        //Case one if flag == true, trap is set
        System.out.println("Trap description =" + (instance.getTrapDescription())+"-");
        assertEquals(true, instance.isTrapped());
        assertEquals(expect,instance.getTrapDescription());
    }    

    /**
     * Test of setOpen method, of class Door.
     * Test the door can be open only if unlocked or close
     * assume default door is close and unlocked.
     */
    /**
     * Test of setOpen method, of class Door.
     * Case: Archway cannot set to close
     * 
     */
    @Test
    public void testSetOpenOne() {
        System.out.println("setOpen");
      
        Door instance = new Door();
        instance.setArchway(true);
        //set to close an Archway
        instance.setOpen(false);
        
        //the result is still open due to archway constraint
        boolean expResult = true;
        assertEquals(expResult, instance.isOpen());
        
    }
    /**
     * Test of setOpen method, of class Door.
     * Case: It is not an archway. Set door close is ok
     * 
     */
    @Test
    public void testSetOpenThree() {
        System.out.println("setOpen");
      
        Door instance = new Door();
        instance.setArchway(false);
        //set to close an Archway
        instance.setOpen(false);
        
        //the result is still open due to archway constraint
        boolean expResult = false;
        assertEquals(expResult, instance.isOpen());
        
    }    
    /**
     * Test of setArchway method, of class Door.
     */
    @Test
    public void testSetArchway() {
        System.out.println("setArchway");
        boolean flag = true;
        Door instance = new Door();
        instance.setArchway(flag);
        // TODO review the generated test code and remove the default call to fail.
        boolean result = instance.isArchway();
        assertEquals(result, flag);
               
    }

    /**
     * Test of isTrapped method, of class Door.
     * SetTrap to true
     */
    @Test
    public void testIsTrappedOne() {
        System.out.println("isTrapped");
        Door instance = new Door();
        instance.setArchway(false);
        instance.setTrapped(true);
        boolean expResult = true;
        boolean result = instance.isTrapped();
        assertEquals(expResult, result);

    }
    /**
     * Test of isTrapped method, of class Door.
     * SetTrap to false
     */
    @Test
    public void testIsTrappedTwo() {
        System.out.println("isTrapped");
        Door instance = new Door();
        instance.setArchway(false);     
        instance.setTrapped(false);
        boolean expResult = false;
        boolean result = instance.isTrapped();
        assertEquals(expResult, result);    
        
        
    }    

    /**
     * Test of isOpen method, of class Door.
     * Case door is closed
     */
    @Test
    public void testIsOpenOne() {
        System.out.println("isOpen");
        Door instance = new Door();
        
        instance.setOpen(true);
        instance.setOpen(false);
        boolean expResult = false;
        //Islocked
        if(instance.isArchway())
            expResult = true;
                
        boolean result = instance.isOpen();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of isOpen method, of class Door.
     * Case door is open
     */
    @Test
    public void testIsOpenTwo() {
        System.out.println("isOpen");
        Door instance = new Door();
        instance.setArchway(true);
        
        boolean expResult = true;
        boolean result = instance.isOpen();
        assertEquals(expResult, result);

    }

    /**
     * Test of isArchway method, of class Door.
     * Check case archway is false;
     */
    @Test
    public void testIsArchwayOne() {
        System.out.println("isArchway");
        Door instance = new Door();
        boolean expResult = false;
        instance.setArchway(false);
        boolean result = instance.isArchway();
        assertEquals(expResult, result);
    }
    /**
     * Test of isArchway method, of class Door.
     * Check case archway is true;
     */
    @Test
    public void testIsArchwayTwo() {
        System.out.println("isArchway");
        Door instance = new Door();
        boolean expResult = true;
        instance.setArchway(true);
        boolean result = instance.isArchway();
        assertEquals(expResult, result);
    }
    /**
     * Test of getTrapDescription method, of class Door.
     */
    @Test
    public void testGetTrapDescription() {
        System.out.println("getTrapDescription");
        Door instance = new Door();
        instance.setArchway(false);
        instance.setTrapped(true, 6);
        String expResult = " Pit, 10' deep, 3 in 6 to fall in.";
        
        String result = instance.getTrapDescription();
        assertEquals(expResult, result);

    }
    
    /**
    * Test of setSpaces method, of class Door.
    * Case: Just kip the references to the spaces.
    * Don't consider the case of Door need to be existed in Chamber.
    */ 
    @Test
    public void testSetSpaces() {
        System.out.println("setSpaces");
        Chamber c = new Chamber();
 

        Chamber c1 = new Chamber();

        
        Door instance = new Door();
        instance.setSpaces(c, c1);
        
        
        ArrayList<Space> result = instance.getSpaces();
        int expResult = 2;
        assertEquals(expResult,result.size());        
    }

    /**
     * Test of getSpaces method, of class Door.
     */
    @Test
    public void testGetSpaces() {
        System.out.println("getSpaces");
        Chamber c = new Chamber();

        Chamber c1 = new Chamber();

        
        Door instance = new Door();
        instance.setSpaces(c, c1);
        
        
        ArrayList<Space> result = instance.getSpaces();
        int expResult = 2;
        assertEquals(expResult,result.size());

    }


    /**
     * Test of getDescription method, of class Door.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Door instance = new Door();
        instance.setArchway(false);
        instance.setTrapped(true, 15);
        
        String expResult = "trap";
        String result = instance.getDescription();
        assertTrue(result.contains(expResult));

    }
    
}
