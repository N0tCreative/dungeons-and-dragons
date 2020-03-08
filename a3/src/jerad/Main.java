package jerad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public final class Main {

    /**this constructor is to get rid of the checkstyle error and does nothing.
     */
    private Main() {
    }

    /** this is the function that creates 5 chambers and passages between them.
     * @param args command line arg
     */
    public static void main(String[] args) {
        Random rand = new Random();
        int num = rand.nextInt(5);
        int num2;
        boolean placed = false;
        Chamber tempCham;
        Door tempDoor;
        Passage tempP;
        PassageSection tempS = new PassageSection("passage goes straight for 10 ft");
        ArrayList<Chamber> chamberList = new ArrayList<Chamber>();
        HashMap<Door, ArrayList<Chamber>> map = new HashMap<Door, ArrayList<Chamber>>();
        ArrayList<Chamber> tempList = new ArrayList<Chamber>();

        for (int i = 0; i < 5; i++) { //generate 5 chambers
            tempCham = new Chamber();
            chamberList.add(tempCham);

        }
        for (int i = 0; i < 5; i++) { // for all chambers
            for (int j = 0; j < chamberList.get(i).getDoors().size(); j++) { //go to each door

                /*tempDoor = new Door(); should have doors on initialization
                tempDoor.setSpace(chamberList.get(i));
                chamberList.get(i).setDoor(tempDoor);
                tempDoor.setSpace(chamberList.get(i));*/

                //put the door in the hash map connected to 1 random chamber
                if (!map.containsKey(chamberList.get(i).getDoors().get(j))) { // if the door isnt targeting anything give it a target
                    //choose a random chamber that isnt the one connected to the door
                    do {
                        num = (rand.nextInt(5));
                    } while (num == i);

                    tempList.add(chamberList.get(num));
                    map.put(chamberList.get(i).getDoors().get(j), tempList);
                    tempList = new ArrayList<Chamber>();

                    tempP = new Passage();
                    tempP.addPassageSection(tempS);
                    tempP.setDoor(chamberList.get(i).getDoors().get(j)); //set source chamber door to add passage to its space
                    tempP.addPassageSection(tempS);

                    for (int k = 0; k < chamberList.get(num).getDoors().size(); k++) { //checks if any doors at target have no target and if so it sets their target to its chamber
                        if (!map.containsKey(chamberList.get(num).getDoors().get(k))) {
                            tempList.add(chamberList.get(i));
                            map.put(chamberList.get(num).getDoors().get(k), tempList);
                            tempList = new ArrayList<Chamber>();

                            tempP.setDoor(chamberList.get(num).getDoors().get(k)); //set target chamber door to add passage to its space

                            placed = true;
                            break;
                        }
                    }
                    if (!placed) { // if there were no doors without a target then choose a random one and set that to also target its chamber
                        num2 = rand.nextInt(chamberList.get(num).getDoors().size());
                        map.get(chamberList.get(num).getDoors().get(num2)).add(chamberList.get(i));
                        tempP.setDoor(chamberList.get(num).getDoors().get(num2)); //set target chamber door to add passage to its space
                    }
                    placed = false;
                }
                /*map.put(chamberList.get(i).getDoors().get(j), tempList);
                tempList = new ArrayList<Chamber>();*/
            }
        } // end of giving all chamber doors a target

        //output
        for (int i = 0; i < 5; i++) { // go to each chamber
            System.out.println("**********Chamber " + (i + 1) + "**********");
            System.out.println(chamberList.get(i).getDescription());
            for (int j = 0; j < chamberList.get(i).getDoors().size(); j++) { //go to each door
                System.out.println("");
                System.out.println("Door " + (j + 1) + ":");
                for (int k = 0; k < (map.get(chamberList.get(i).getDoors().get(j)).size()); k++) { //go to each passage connected to the door except the chamber
                    //System.out.println(chamberList.get(i).getDoors().get(j).getSpaces().get(k + 1).getDescription()); doesnt really tell you anything but works
                    System.out.print("targeting chamber ");

                    for (int l = 0; l < 5; l++) {
                        if (map.get(chamberList.get(i).getDoors().get(j)).get(k) == chamberList.get(l)) {
                            System.out.println(l + 1);
                            break;
                        }
                    }
                    System.out.println("Which contains:" + map.get(chamberList.get(i).getDoors().get(j)).get(k).getDescription());

                }


            }
            System.out.println("");
        }

    } // end of main
}
