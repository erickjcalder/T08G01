import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creates a Map for the game to be played on
 *
 * 21/03/2019 - Code salvaged from decompiled class file, variable names and arbitrary spacing may be
 * slightly off
 *
 * @author Erick
 * @version Demo 3
 */
public class Map {

    //This array is the backbone of the map, as it stores every room in the 7x7 grid
    Room[][] roomLoc;

    //Store the dimensions of the map. May well be superfluous at this point, but hey, that's how it goes
    private int width;
    private int height;

    //SOMEBODY ELSE PLEASE EXPLAIN THE HANDLER I HAVE NO CLUE HOW THIS WORKS
    private LevelHandler handler;

    //Object of type random for various purposes
    private Random r = new Random();

    //The [x, y] co-ords that the player starts at
    private int startX;
    private int startY;

    //The current location that will be updated frequently
    private int currentX;
    private int currentY;

    //Simple bool to control console debug messages
    private boolean debug = false;

    /**
     * Saves map.
     * @param save Document to use.
     * @return Element to save to document.
     */
    Node Save(Document save)
    {
        Element e = null;

        Node rootElement = save.createElement("Map");

        e = save.createElement("width");
        e.appendChild(save.createTextNode(Integer.toString(this.width)));
        rootElement.appendChild(e);

        e = save.createElement("height");
        e.appendChild(save.createTextNode(Integer.toString(this.height)));
        rootElement.appendChild(e);

        e = save.createElement("startX");
        e.appendChild(save.createTextNode(Integer.toString(this.startX)));
        rootElement.appendChild(e);

        e = save.createElement("startY");
        e.appendChild(save.createTextNode(Integer.toString(this.startY)));
        rootElement.appendChild(e);

        e = save.createElement("currentX");
        e.appendChild(save.createTextNode(Integer.toString(this.currentX)));
        rootElement.appendChild(e);

        e = save.createElement("currentY");
        e.appendChild(save.createTextNode(Integer.toString(this.currentY)));
        rootElement.appendChild(e);

        e = save.createElement("roomLoc");

        // Saves all rooms in map.
        Node eStack = null;
        for(Room[] roomList : this.roomLoc)
        {
            eStack = e.appendChild(save.createElement("roomList"));
            for(Room room : roomList)
            {
                eStack.appendChild(room.Save(save));
            }

            rootElement.appendChild(eStack);
        }

        return rootElement;
    }
    /**
     * Creates a 2D array of Room objects and will decide where each room will go
     * and what entrances the Room will have later on will also determine what type
     * of Room it is
     */
    public Map(int var1, int var2, LevelHandler var3) {

        //Randomize the start position within the bounds of the map
        this.startX = this.r.nextInt(3) + 2;
        this.startY = this.r.nextInt(3) + 2;

        //Copy those values for the current positions
        this.currentX = this.startX;
        this.currentY = this.startY;

        //Set the width and height (It's always 7 for both but w/e)
        this.width = var1;
        this.height = var2;

        //Generate the Room array with appropriate dimensions
        this.roomLoc = new Room[var1][var2];

        //I STILL DON'T KNOW HOW THE HANDLER WORKS HELP
        this.handler = var3;

        //Create a 'blank' room for every position in the Room array
        for(int var4 = 0; var4 < var1; ++var4) {
            for(int var5 = 0; var5 < var2; ++var5) {
                this.roomLoc[var4][var5] = new Room(false, false, false, false, false, 0, var3);
            }
        }

        //Generate the map
        this.chooseLayout();
    }

    /**
     * Checks to see if there is a room at a specified set of coordinates
     *
     * @param var1
     *            the x coordinate of the room
     * @param var2
     *            the y coordinate of the room
     * @return boolean whether or not there is a room at the specified coordinates
     */
    public boolean roomCheck(int var1, int var2) {
        if (var1 >= 0 && var2 >= 0 && var1 != this.width && var2 != this.height) {
            return this.roomLoc[var1][var2].getRoomType() != 0;
        } else {
            return false;
        }
    }

    /**
     * Generates the layout of the map. case 0 is legacy code from before implementation of random map algorithm.
     */
    public void chooseLayout() {

        //Generate a local version of r because you messed up and had to pull the decompiled code and it made everything weird
        Random r = new Random();

        //This will determine how many rooms will be added to the map
        int numOfRooms;

        //This will continuously be re-rolled to determine which of the four cardinal directions the map generating algorithm
        //should attempt to move in next
        int direction;

        //Switch statement that is effectively useless now, but isn't be removed because it allows for possible hand built
        //maps to be added as possible options later, also I'm just lazy
        switch(1) {

            //Legacy case that no longer functions properly
            case 0: {
                this.roomLoc[0][0] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[1][0] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[2][0] = new Room(false, false, false, false, false, 1, this.handler);

                this.roomLoc[2][1] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[3][1] = new Room(false, false, false, false, false, 1, this.handler);

                this.roomLoc[1][2] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[2][2] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[3][2] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[4][2] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[5][2] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[6][2] = new Room(false, false, false, false, false, 1, this.handler);

                this.roomLoc[3][3] = new Room(false, false, false, false, false, 1, this.handler);

                this.roomLoc[0][4] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[3][4] = new Room(false, false, false, false, false, 1, this.handler);

                this.roomLoc[0][5] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[1][5] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[2][5] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[3][5] = new Room(false, false, false, false, false, 1, this.handler);

                this.roomLoc[3][6] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[4][6] = new Room(false, false, false, false, false, 1, this.handler);
                this.roomLoc[5][6] = new Room(false, false, false, false, false, 1, this.handler);
        }

        //The bread and butter of the map generation algorithm
        case 1: {

            //Keeps track of how many times a room has been successfully created so that I can try to make my maps cooler
            int consecutiveSuccesses = 0;

            //Stores the values of x and y where a room has been created
            ArrayList<Integer> validXs = new ArrayList<Integer>();
            ArrayList<Integer> validYs = new ArrayList<Integer>();

            //Determines how many rooms exist in the map, with the '+ <int>' representing the minimum number of rooms,
            //and the 'r.nextInt(<int>)' representing the variance (note that actual variance is the binding int -1)
            numOfRooms = r.nextInt(16) + 10;

            //DEBUG
            if (debug) {
                System.out.println("Current room is [" + this.currentX + " " + this.currentY +
                        "] and room type should be 0. Actual: " +
                        this.roomLoc[this.currentX][this.currentY].getRoomType());
            }

            //Make sure the starting room is valid
            this.roomLoc[this.startX][this.startY].setRoomType(1);

            //Add it to list of valid rooms
            validXs.add(this.currentX);
            validYs.add(this.currentY);

            //DEBUG
            if (debug) {
                System.out.println("Current room is [" + this.currentX + " " + this.currentY +
                        "] and room type should be 1. Actual: " +
                        this.roomLoc[this.currentX][this.currentY].getRoomType());
            }

            //Stores the last direction moved in (in integer form), declared here as 0 because the print statement below
            //gets real mad if you don't do that
            int lastDirection = 0;


            //This loop is what actually places rooms
            for (int var6 = 0; var6 < numOfRooms; ++var6) {

                //DEBUG
                if (debug) {
                    System.out.println("i = " + var6 + " numOfRooms = " + numOfRooms + " Current X = " + this.currentX +
                            " Current Y = " + this.currentY + " Current room type: " +
                            this.roomLoc[this.currentX][this.currentY].getRoomType());
                }

                //Choose a random direction represented by an int in the range 1-4 with 1 = north, 2 = east, 3 = south and 4 = west
                direction = r.nextInt(4) + 1;

                //DEBUG
                if (debug) {
                if (this.debug && this.roomLoc[this.currentX][this.currentY].getRoomType() == 0) {
                    System.out.println("Rooms not updating properly. Last direction moved in was: " + lastDirection);
                    break;
                    }
                }

                //Essentially flips a coin to see if it should try the same direction again or not. Could be changed in
                //a future version
                if (r.nextInt(2) == 1 && lastDirection != 0) {
                    direction = lastDirection;
                }

                //Stores the direction chosen
                lastDirection = direction;

                //If there is an empty room in one of the four cardinal directions...
                if (this.emptyRoomAdjacent()) {

                    //Feed the chosen direction into this statement
                    switch (direction) {

                        //Each case is effectively identical beyond the direction of movement, so only this case will
                        //receive full documentation
                        case 1: {

                            //Checks to see if the room in the chosen direction is empty
                            if (this.checkEmptyRoomNorth()) {

                                //If it is, current position is updated to be in that room, and it is made valid
                                --this.currentY;
                                this.roomLoc[this.currentX][this.currentY].setRoomType(1);

                                //Do a bunch of extra stuff to increase randomization (theoretically)
                                validXs.add(this.currentX);
                                validYs.add(this.currentY);

                                consecutiveSuccesses += 1;

                                if (this.debug) {
                                    System.out.println("Added room at {" + this.currentX + " " + this.currentY + "} " +
                                            this.roomLoc[this.currentX][this.currentY].getRoomType());
                                }

                            } else {

                                //If a room cannot be made, the counter will be set back one. It's like recursion but dumber
                                --var6;
                            }
                            break;
                        }

                        case 2: {
                            if (this.checkEmptyRoomEast()) {
                                ++this.currentX;
                                this.roomLoc[this.currentX][this.currentY].setRoomType(1);
                                validXs.add(this.currentX);
                                validYs.add(this.currentY);

                                consecutiveSuccesses += 1;

                                if (this.debug) {
                                    System.out.println("Added room at {" + this.currentX + " " + this.currentY + "} " +
                                            this.roomLoc[this.currentX][this.currentY].getRoomType());
                                }
                            } else {
                                --var6;
                            }
                            break;
                        }

                        case 3: {
                            if (this.checkEmptyRoomSouth()) {
                                ++this.currentY;
                                this.roomLoc[this.currentX][this.currentY].setRoomType(1);
                                validXs.add(this.currentX);
                                validYs.add(this.currentY);

                                consecutiveSuccesses += 1;

                                if (this.debug) {
                                    System.out.println("Added room at {" + this.currentX + " " + this.currentY + "} " +
                                            this.roomLoc[this.currentX][this.currentY].getRoomType());
                                }
                            } else {
                                --var6;
                            }
                            break;
                        }

                        case 4: {
                            if (this.checkEmptyRoomWest()) {
                                --this.currentX;
                                this.roomLoc[this.currentX][this.currentY].setRoomType(1);
                                validXs.add(this.currentX);
                                validYs.add(this.currentY);

                                consecutiveSuccesses += 1;

                                if (this.debug) {
                                    System.out.println("Added room at {" + this.currentX + " " + this.currentY + "} " +
                                            this.roomLoc[this.currentX][this.currentY].getRoomType());
                                }
                            } else {
                                --var6;
                            }
                            break;
                        }
                    }

                    //After three rooms have been placed successfully, teleport to a random valid room
                    if (consecutiveSuccesses > 3) {
                        int moveToDifferentValidRoom = r.nextInt(validXs.size());
                        currentX = validXs.get(moveToDifferentValidRoom);
                        currentY = validYs.get(moveToDifferentValidRoom);
                        consecutiveSuccesses = 0;
                    }

                } else {

                    //Do the same sort of teleportation if there's no empty rooms around you
                    int moveToDifferentValidRoom = r.nextInt(validXs.size());
                    currentX = validXs.get(moveToDifferentValidRoom);
                    currentY = validYs.get(moveToDifferentValidRoom);

                    //Also make sure you lower the success count
                    --var6;
                }
            }
        }

        //This adds all the doorways after map generation is complete
        for(int var2 = 0; var2 < this.width; ++var2) {
            for(int var3 = 0; var3 < this.width; ++var3) {
                if (var2 < this.width - 1 && this.roomLoc[var2 + 1][var3].getRoomType() == 1) {
                    this.roomLoc[var2][var3].east = true;
                }
                if (var2 >= 1 && this.roomLoc[var2 - 1][var3].getRoomType() == 1) {
                    this.roomLoc[var2][var3].west = true;
                }
                if (var3 < this.height - 1 && this.roomLoc[var2][var3 + 1].getRoomType() == 1) {
                    this.roomLoc[var2][var3].south = true;
                }
                if (var3 >= 1 && this.roomLoc[var2][var3 - 1].getRoomType() == 1) {
                    this.roomLoc[var2][var3].north = true;
                }
            }
        }
        if (debug) {
            this.printMapToTerminal();
        }
        }
    }

    /**
     * Check to see if the room to the north is empty
     * @return True if true, false if false
     */
    public boolean checkEmptyRoomNorth() {
        if (this.currentY - 1 >= 0) {
            return this.roomLoc[this.currentX][this.currentY - 1].getRoomType() == 0;
        } else {
            return false;
        }
    }

    /**
     * Check to see if the room to the east is empty
     * @return True if true, false if false
     */
    public boolean checkEmptyRoomEast() {
        if (this.currentX + 1 <= 6) {
            return this.roomLoc[this.currentX + 1][this.currentY].getRoomType() == 0;
        } else {
            return false;
        }
    }

    /**
     * Check to see if the room to the south is empty
     * @return True if true, false if false
     */
    public boolean checkEmptyRoomSouth() {
        if (this.currentY + 1 <= 6) {
            return this.roomLoc[this.currentX][this.currentY + 1].getRoomType() == 0;
        } else {
            return false;
        }
    }

    /**
     * Check to see if the room to the west is empty
     * @return True if true, false if false
     */
    public boolean checkEmptyRoomWest() {
        if (this.currentX - 1 >= 0) {
            return this.roomLoc[this.currentX - 1][this.currentY].getRoomType() == 0;
        } else {
            return false;
        }
    }

    /**
     * Prints a ascii version of the map to the terminal, used for debug purposes
     */
    public void printMapToTerminal() {
        for(int var1 = 0; var1 < this.width; ++var1) {
            for(int var2 = 0; var2 < this.height; ++var2) {
                if (this.roomLoc[var2][var1].getRoomType() == 0) {
                    System.out.print(" X ");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    /**
     * Checks to see if there are any empty rooms adjacent to the current location
     * @return True if there is a single room empty, False if otherwise
     */
    public boolean emptyRoomAdjacent() {
        return this.checkEmptyRoomNorth() || this.checkEmptyRoomEast() || this.checkEmptyRoomSouth() || this.checkEmptyRoomWest();
    }

    /**
     * Used by Player to get the starting x value
     * @return x value
     */
    public int getStartX() {
        return this.startX;
    }

    /**
     * Used by Player to get the starting y value
     * @return y value
     */
    public int getStartY() {
        return this.startY;
    }

    /**
     * Sets starting X.
     * @param startX starting X.
     */
    public void setStartX(int startX)
    {
        this.startX = startX;
    }

    /**
     * Sets starting Y.
     * @param startY starting Y.
     */
    public void setStartY(int startY)
    {
        this.startY = startY;
    }

    /**
     * Sets current X.
     * @param currentX current X.
     */
    public void setCurrentX(int currentX)
    {
        this.currentX = currentX;
    }

    /**
     * Sets current Y.
     * @param currentY current Y.
     */
    public void setCurrentY(int currentY)
    {
        this.currentY = currentY;
    }

    /**
     * Sets map of rooms.
     * @param roomLoc map of rooms.
     */
    public void setRoomLoc(Room[][] roomLoc)
    {
        this.roomLoc = roomLoc;
    }
}
