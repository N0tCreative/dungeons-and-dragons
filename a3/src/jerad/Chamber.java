package jerad;

import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.Monster;
import dnd.models.Treasure;
import dnd.models.Stairs;
import dnd.models.Trap;
import dnd.exceptions.NotProtectedException;
import dnd.exceptions.UnusualShapeException;

import java.util.ArrayList;

public class Chamber extends Space {

    /** content of chamber.*/
    private ChamberContents content;
    /** size of chamber.*/
    private ChamberShape shape;
    /** content type of chamber. */
    private int contentType;
    /** treasure type. */
    private ArrayList<Treasure> treasure;
    /** trap type. */
    private Trap trap;
    /** stairs type. */
    private Stairs stairs;
    /** doors. */
    private ArrayList<Door> doors;
    /** monsters. */
    private ArrayList<Monster> monsters;
    /** doors set. */
    private int dSet;

    /** this is a constructor for a random chamber.
     */
    public Chamber() {
        init();
        this.shape.setShape();
        this.shape.setNumExits();
        this.content.setDescription();
        this.dSet = 0;
        initDoors();
        setType();
    }

    /** this is a constructor for a chamber with content and shape set.
     * @param theShape is the shape and size to set chamber to
     * @param theContents is what to set inside the chamber
     */
    public Chamber(ChamberShape theShape, ChamberContents theContents) {
        init();
        this.shape = theShape;
        this.content = theContents;
        this.dSet = 0;
        initDoors();
        setType();
    }

    /** initializes values. */
    private void init() {
        this.shape = new ChamberShape();
        this.content = new ChamberContents();
        this.monsters = new ArrayList<Monster>();
        this.treasure = new ArrayList<Treasure>();
        this.trap = new Trap();
        this.stairs = new Stairs();
    }

    /** initializes doors. */
    private void initDoors() {
        int i;
        this.doors = new ArrayList<Door>();
        for (i = 0; i < this.shape.getNumExits(); i++) {
            Door door = new Door();
            door.setSpace(this);
            this.doors.add(door);
        }
        if (this.doors.size() == 0) {
            Door door = new Door();
            this.doors.add(door);
        }
    }

    /** sets type based on description.*/
    private void setType() {
        if (this.content.getDescription().compareTo("monster only") == 0) { //generates and displays monsters
            setMonst();
        } else if (this.content.getDescription().compareTo("monster and treasure") == 0) { // generates and displays treasure and monsters
            setMonstTreas();
        } else if (this.content.getDescription().compareTo("treasure") == 0) { //generates and displays treasure
            setTreas();
        } else if (this.content.getDescription().compareTo("trap") == 0) { //generates and displays trap
            setTrap();
        } else if (this.content.getDescription().compareTo("stairs") == 0) { //generates and displays stairs
            setStairs();
        }
    }

    /** sets up monsters. */
    private void setMonst() {
        Monster monster = new Monster();
        this.monsters.add(monster);
        this.contentType = 1;
    }

    /** sets up treasure. */
    private void setTreas() {
        Treasure treas = new Treasure();
        treas.setDescription();
        treas.setContainer();
        treasure.add(treas);
        this.contentType = 3;
    }

    /** sets monster and treasure. */
    private void setMonstTreas() {
        setMonst();
        setTreas();
        this.contentType = 2;
    }

    /** sets up trap. */
    private void setTrap() {
        this.trap.setDescription();
        this.contentType = 4;
    }

    /** set up stairs. */
    private void setStairs() {
        this.stairs.setType();
        this.contentType = 5;
    }

    /** sets the shape and size of the chamber.
     * @param theShape is the shape and size to set chamber to
     */
    public void setShape(ChamberShape theShape) {
        int i = this.shape.getNumExits();
        while (i > 1) {
            this.doors.remove(1);
            i--;
        }
        this.shape = theShape;
        for (i = 1; i < this.shape.getNumExits(); i++) {
            Door door = new Door();
            this.doors.add(i, door);
        }
    }

    /** returns the list of doors in the chamber.
     * @return chambers doors
     */
    public ArrayList<Door> getDoors() {
        return this.doors;
    }

    /** adds a monster to the monster list in chamber.
     * @param theMonster monster to add
     */
    public void addMonster(Monster theMonster) {
        this.monsters.add(theMonster);
    }

    /** retruns the list of monsters in the chamber.
     * @return ArrayList<Monster> monsters in chamber
     */
    public ArrayList<Monster> getMonsters() {

    return this.monsters;
    }

    /** adds a treasure to the treasure list in chamber.
     * @param theTreasure treasure to add
     */
    public void addTreasure(Treasure theTreasure) {
        this.treasure.add(theTreasure);
    }

    /** retruns the list of treasures in the chamber.
     * @return ArrayList<Treasure> treasure in chamber
     */
    public ArrayList<Treasure> getTreasureList() {

    return this.treasure;
    }


    /** describes chamber and its contents.
     * @return String gives description of the chamber
     */
    @Override
    public String getDescription() {
        String description = "";
        try {
            description = (shape.getLength() + " X " + shape.getWidth());
        } catch (UnusualShapeException e) {
            description = (shape.getArea() + "'");
        }
        description += (" " + shape.getShape() + "\n");
        description += ("with " + content.getDescription() + " ");
        description += outputContent();
        return description;
    }

    /** returns a string with all the relevent information about the chambers content.
     * @return the relevent content of chamber
     */
    public String outputContent() {
        String description = "";
        if (this.contentType == 1) {
            description = monstDesc();
        } else if (this.contentType == 2) {
            description = monstTreasDesc();
        } else if (this.contentType == 3) {
            description = treasDesc();
        } else if (this.contentType == 4) {
            description = this.trap.getDescription();
        } else if (this.contentType == 5) {
            description = this.stairs.getDescription();
        }
        return description;
    }

    /** set the description of a monster.
     * @return String the description of the monster
    */
    private String monstDesc() {
        String description;
        description = (this.monsters.get(0).getMinNum() + "-" + this.monsters.get(0).getMaxNum() + " " + this.monsters.get(0).getDescription());
        return description;
    }

    /** set the description of a treasure.
     * @return String the description of the treasure
    */
    private String treasDesc() {
        String description;
        description = (this.treasure.get(0).getDescription() + " in " + this.treasure.get(0).getContainer());
        try {
            description += (" Protected by " + this.treasure.get(0).getProtection());
        } catch (NotProtectedException e) {
        }
        return description;
    }

    /** set the description of a monster and a treasure.
     * @return String the description of the monster and treasure
    */
    private String monstTreasDesc() {
        String description;
        description = monstDesc();
        description += "\n";
        description += treasDesc();
        return description;
    }

    /** adds a door to the chamber and overwrites unset doors.
     * @param newDoor door to add
     */
    @Override
    public void setDoor(Door newDoor) {
        //should add a door connection to this room so long as there are that many exits or make at least 1 exit
        if (this.dSet < this.shape.getNumExits() - 1 || this.dSet == 0) {
            newDoor.setSpace(this);
            this.doors.add(dSet, newDoor);
            this.dSet++;
            this.doors.remove(dSet);
        }
    }

}
