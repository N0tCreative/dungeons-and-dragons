package jerad;
import dnd.models.Exit;
import dnd.models.Trap;
import dnd.die.D10;
import dnd.die.D20;
import dnd.die.D6;

import java.util.ArrayList;
import java.util.Random;

public class Door {
    /** stores if the door is an arch.*/
    private boolean arch;
    /** stores if the door is open.*/
    private boolean open;
    /** stores if the door is locked.*/
    private boolean locked;
    /** stores if the door is trapped.*/
    private boolean trapped;
    /** stores desc.*/
    private String description;

    /** gens rand number.*/
    private Random rand;
    /**create a random number from 1-20.*/
    private D20 d20;
    /**create a random number from 1-10.*/
    private D10 d10;
    /**create a random number from 1-6.*/
    private D6 d6;
    /** holds a type of trap.*/
    private Trap trap;
    /** holds trap description. */
    private String trapDesc;
    /** points to the rooms the door is connected to.*/
    private ArrayList<Space> connected;
    /** exit for door. */
    private String exit;

    /** Door constructor.
     * sets default values for door
     */
    public Door() {
        //needs to set defaults
        this.exit = "";
        initRand();
        init();
        if (d10.roll() == d10.roll()) {
            this.arch = true;
        } else {
            initNArch();
        }
    }

    /** Door constructor.
     * sets default values for door
     * and adds the description of the exit
     * @param theExit describes the exit
     */
    public Door(Exit theExit) {
        initRand();
        init();
        if (this.d10.roll() == this.d10.roll()) {
            this.arch = true;
        } else {
            initNArch();
        }
        this.exit = theExit.getDirection();
        this.exit += theExit.getLocation();
    }

    /** initializes randomized values. */
    private void initRand() {
        this.d10 = new D10();
        this.d20 = new D20();
        this.d6 = new D6();
        this.rand = new Random();
    }

    /** initializes values. */
    private void init() {
        this.description = "";
        this.arch = false;
        this.open = true;
        this.locked = false;
        this.trapped = false;
        this.trap = new Trap();
        this.trapDesc = "";
        this.connected = new ArrayList<Space>();
    }

    /** initializes a non arch. */
    private void initNArch() {
        if (this.rand.nextInt() % 2 == 1) {
            initLock();
        } else {
            this.open = true;
        }
        if (this.d20.roll() == this.d20.roll()) {
            this.trapped = true;
        }
    }

    /** initializes a locked/unlocked door. */
    private void initLock() {
        this.open = false;
        if (this.d6.roll() == this.d6.roll()) {
            this.locked = true;
        }
    }

    /** sets the trap on the door.
     * @param flag trapped or not
     * @param roll roll for trapped or rand roll
     */
    public void setTrapped(boolean flag, int... roll) {
        // true == trapped.  Trap must be rolled if no integer is given
        int rolled = d20.roll();
        this.trapped = flag;

        if (roll.length != 0) {
            rolled = roll[0];
        }
        if (flag) {
            this.trap.setDescription(rolled);
            this.trapDesc = this.trap.getDescription();
        }
    }

    /** sets if door is open.
     * @param flag open or not
     */
    public void setOpen(boolean flag) {
        //true == open

        if (!this.locked && !this.arch) {
            this.open = flag;
        }
    }

    /** sets if door is an arch.
     * @param flag arch or not
     */
    public void setArchway(boolean flag) {
        //true == is archway
        this.arch = flag;
        if (flag) {
            this.open = true;
            this.locked = false;
            this.trapped = false;
        }
    }

    /** return true if trapped.
     * @return trapped or not
     */
    public boolean isTrapped() {
        return trapped;
    }

    /** return true if open.
     * @return open or not
     */
    public boolean isOpen() {
        return open;
    }

    /** return true if arch.
     * @return arch or not
     */
    public boolean isArchway() {
        return arch;
    }

    /** return trap description if trapped.
     * @return trapped or not
     */
    public String getTrapDescription() {
        return this.trapDesc;
    }

    /** return array of spaces connected to door.
     * @return spaces rooms connected to
     */
    public ArrayList<Space> getSpaces() {
        //returns the two spaces that are connected by the door
        return this.connected;
    }

    /** sets two spaces to be the ones connected to the door (never used).
     * @param spaceOne is first space
     * @param spaceTwo is second space
     */
    public void setSpaces(Space spaceOne, Space spaceTwo) {
        //identifies the two spaces with the door
        // this method should also call the addDoor method from Space
        this.connected = new ArrayList<Space>();
        this.connected.add(spaceOne);
        this.connected.add(spaceTwo);
    }

    /** sets spaces for single use.
     * @param space is a space
     */
    public void setSpace(Space space) {
        this.connected.add(space);
    }

    /** sets description of the door and adds extra string on the end if it exists.
     * @param append adds description of exit
     */
    public void setDescription(String... append) {
        this.description = "";
        isArchOpenClosedDesc();
        isLockedUnlockedDesc();
        isTrappedDesc();
        isDoorDesc();
        if (append.length == 1) {
            this.description += append[0];
        }
    }

    /** sets desc for arch/open/closed.*/
    private void isArchOpenClosedDesc() {
        if (this.arch) {
            this.description += "arch";
        } else if (this.open) {
            this.description += "open ";
        } else {
            this.description += "closed ";
        }
    }

    /** sets desc for locked/unlocked.*/
    private void isLockedUnlockedDesc() {
        if (this.locked) {
            this.description += "locked ";
        } else if (!this.arch && !this.open) {
            this.description += "unlocked ";
        }
    }

    /** sets desc for trapped.*/
    private void isTrappedDesc() {
        if (this.trapped) {
            this.description += "trapped door";
            this.description += this.trapDesc;
        }
    }

    /** sets desc for door.*/
    private void isDoorDesc() {
        if (!this.arch && !this.trapped) {
            this.description += "door";
        }
    }

    /** returns the description of the door.
     * @return description of door
     */
    public String getDescription() {
        this.setDescription(exit);
        return this.description;
    }
}
