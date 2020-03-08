package jerad;

import dnd.die.D20;
import dnd.models.Monster;
import dnd.models.Stairs;

/* Represents a 10 ft section of passageway */

public class PassageSection {

    /** passage door.*/
    private Door door;
    /** passage type.*/
    private int type;
    /** passage description.*/
    private String desc;
    /** stairs. */
    private Stairs stairs;
    /** monster. */
    private Monster monster;

    /** section constructor.
     */
    public PassageSection() {
        //sets up the 10 foot section with default settings
        int roll = init();
        if (roll < 3) { //straight passage
            pass1();
        } else if (roll < 6) { //ends in chamber
            pass2();
        } else if (roll < 8) { //arch right
            pass3();
        } else if (roll < 10) { //arch left
            pass4();
        } else if (roll < 12) { //left passage
            pass5();
        } else if (roll < 14) { //right passage
            pass6();
        } else if (roll < 17) { //passage ends in arch
            pass7();
        } else if (roll < 18) { //stairs
            pass8();
        } else if (roll < 20) { //dead end
            pass9();
        } else {                //wandering monster
            pass10();
        }
    }

    /** section constructor.
     * @param description describes passage
     */
    public PassageSection(String description) {
        init();
        if (description.equalsIgnoreCase("passage goes straight for 10 ft")) {
            pass1();
        } else if (description.equalsIgnoreCase("passage ends in Door to a Chamber")) {
            pass2();
        } else if (description.equalsIgnoreCase("archway (door) to right (main passage continues straight for 10 ft)")) {
            pass3();
        } else if (description.equalsIgnoreCase("archway (door) to left (main passage continues straight for 10 ft)")) {
            pass4();
        } else if (description.equalsIgnoreCase("passage turns to left and continues for 10 ft")) {
            pass5();
        } else if (description.equalsIgnoreCase("passage turns to right and continues for 10 ft")) {
            pass6();
        } else if (description.equalsIgnoreCase("passage ends in archway (door) to a Chamber")) {
            pass7();
        } else if (description.equalsIgnoreCase("Stairs, (passage continues straight for 10 ft)")) {
            pass8();
        } else if (description.equalsIgnoreCase("Dead End")) {
            pass9();
        } else if (description.equalsIgnoreCase("Wandering Monster (passage continues straight for 10 ft)")) {
            pass10();
        } else {
            type = -1;
        }
        this.desc = description;
    }

    /** initializes values.
     * @return int the roll for passage selection
     */
    private int init() {
        D20 d20 = new D20();
        this.desc = "";
        this.type = 0;
        return d20.roll();
    }

    /** passage type 1.*/
    private void pass1() {
        this.type = 1;
        this.desc = "passage goes straight for 10 ft";
    }

    /** passage type 2.*/
    private void pass2() {
        this.type = 2;
        this.desc = "passage ends in Door to a Chamber";
        this.door = new Door();
    }

    /** passage type 3.*/
    private void pass3() {
        this.type = 3;
        this.desc = "archway (door) to right (main passage continues straight for 10 ft)";
        this.door = new Door();
        this.door.setArchway(true);
    }

    /** passage type 4.*/
    private void pass4() {
        this.type = 4;
        this.desc = "archway (door) to left (main passage continues straight for 10 ft)";
        this.door = new Door();
        this.door.setArchway(true);
    }

    /** passage type 5.*/
    private void pass5() {
        type = 5;
        this.desc = "passage turns left and continues for 10 ft";
    }

    /** passage type 6.*/
    private void pass6() {
        type = 6;
        this.desc = "passage turns right and continues for 10 ft";
    }

    /** passage type 7.*/
    private void pass7() {
        this.type = 7;
        this.desc = "passage ends in archway (door) to a Chamber";
        this.door = new Door();
        this.door.setArchway(true);
    }

    /** passage type 8.*/
    private void pass8() {
        type = 8;
        this.desc = "Stairs, (passage continues straight for 10 ft)";
        this.stairs = new Stairs();
        this.stairs.setType();
    }

    /** passage type 9.*/
    private void pass9() {
        this.type = 9;
        this.desc = "Dead End";
    }

    /** passage type 10.*/
    private void pass10() {
        this.type = 10;
        this.desc = "Wandering Monster (passage continues straight for 10 ft)";
        this.monster = new Monster();
        this.monster.setType();
    }

    /** returns the door located in this section.
     * @return door in section
     */
    public Door getDoor() {
        //returns the door that is in the passage section, if there is one
        //if (type == 2 || type == 3 || type == 7 || type == 8 || type == -1){
            return this.door;
        /*}
        else {
            return null;
        }*/
    }

    /** set the door in this passage section to the given door.
     * @param aDoor is door to be set
     */
    public void setDoor(Door aDoor) {
        this.door = aDoor;
    }

    /** return the monster in this section.
     * @return monster in section
    */
    public Monster getMonster() {
        //returns the monster that is in the passage section, if there is one
        //if(type == 10 || type == -1) {
            return this.monster;
        /*}
        else {
            return null;
        }*/
    }

    /** describes this passage section.
     * @return description of section
    */
    public String getDescription() {
        if (this.monster != null && !this.desc.contains(" with ")) {
            this.desc += " with " + this.monster.getDescription();
        }
        if (this.door != null && !this.desc.contains(" connected with ") && !this.desc.contains("archway")) {
            this.desc += " connected with " + this.door.getDescription();
        }
        return this.desc;
    }

    /** change the current monster to a different one.
     * @param aMonster is set
     */
    public void setMonster(Monster aMonster) {
        this.monster = aMonster;
    }
}
