package jerad;

import dnd.models.Monster;

import java.util.ArrayList;
import java.util.HashMap;
/*
A passage begins at a door and ends at a door.  It may have many other doors along
the way

You will need to keep track of which door is the "beginning" of the passage
so that you know how to
*/

public class Passage extends Space {
    //these instance variables are suggestions only
    //you can change them if you wish.

    /**stores all sections in a list for ease of access.*/
    private ArrayList<PassageSection> thePassage;
    /**stores info on all the doors in the passage.*/
    private HashMap<PassageSection, Door> doorMap;
    /** stores the num of sections. */
    private int secNum;
    /** array list of doors. */
    private ArrayList<Door> doors;
    /** door to previous. */
    private Door door;
    /** description. */
    private String desc;

    /** passage constructor.
     */
    public Passage() {
        this.thePassage = new ArrayList<PassageSection>();
        this.doorMap =  new HashMap<PassageSection, Door>();
        this.secNum = 0;
        this.doors = new ArrayList<Door>();
        this.desc = "";
    }

    /** returns a list of all doors connected to the passage.
     * @return list of doors
     */
    public ArrayList<Door> getDoors() {
        //gets all of the doors in the entire passage
        int i;
        this.doors.clear();
        for (i = 0; i < secNum; i++) {
            this.doors.add(getDoor(i));
        }
        return this.doors;
    }

    /** returns a door that is connected to a specific passage section.
     * @param i is index
     * @return door at section i
     */
    public Door getDoor(int i) {
        //returns the door in section 'i'. If there is no door, returns null
        PassageSection temp = this.thePassage.get(i);

        return this.doorMap.get(temp);
    }

    /** adds a monster to the passage section.
     * @param theMonster is type of monster
     * @param i is the passage with the monster
     */
    public void addMonster(Monster theMonster, int i) {
        // adds a monster to section 'i' of the passage
        if (i <= secNum) {
            this.thePassage.get(i).setMonster(theMonster);
        }
    }

    /** returns a monster in the passage section.
     * @param i is the monster at section i
     * @return the monster in the section
     */
    public Monster getMonster(int i) {
        //returns Monster door in section 'i'. If there is no Monster, returns null
        if (i > secNum) {
            return null;
        } else {
            return this.thePassage.get(i).getMonster();
        }
    }


    /** adds a new passage section after the previous one.
     * @param toAdd the section
     */
    public void addPassageSection(PassageSection toAdd) {
        //adds the passage section to the passageway
        if (toAdd.getDoor() != null) {
            toAdd.getDoor().setSpace(this);
        }
        this.thePassage.add(toAdd);
        this.doorMap.put(toAdd, toAdd.getDoor());
        this.secNum++;
    }

    /** sets the spaces and stuff for the door and passage equal to each other.
     * @param newDoor is door to connect to passage section
     */
    @Override
    public void setDoor(Door newDoor) {
        //should add a door connection to the current Passage Section
        PassageSection temp;
        //im only doing this for the junit tests, in use idk why youd put a door before a passage is put in
        if (secNum == 0) {
            firstDoor(newDoor);
        } else {
            temp = this.thePassage.get(secNum - 1);
            newDoor.setSpace(this);
            temp.setDoor(newDoor);
            this.doorMap.replace(temp, temp.getDoor());
        }
    }

    /** for adding door before any sections.
     * @param newDoor door to add
     */
    private void firstDoor(Door newDoor) {
        PassageSection temp = new PassageSection("passage goes straight for 10 ft");
        newDoor.setSpace(this);
        temp.setDoor(newDoor);
        this.thePassage.add(temp);
        this.doorMap.put(temp, temp.getDoor());
    }

    /** set the description of the passage.
     * @return description of passage
     */
    @Override
    public String getDescription() {
        int i;
        this.desc = "";
        this.desc += (thePassage.get(0).getDescription());
        for (i = 1; i < secNum; i++) {
            this.desc += " then\n";
            this.desc += (thePassage.get(i).getDescription());
        }
        return this.desc;
    }

    /** sets previous door.
     * @param newDoor the door to set
     */
    public void setPrevious(Door newDoor) {
        this.door = newDoor;
        this.door.setSpace(this);
    }
}
