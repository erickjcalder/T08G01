/**
 * This class is used to store the basic properties of all rooms. Currently it stores all possible exits as well as
 * whether or not the room has an event that need to be resolved. More detailed properties will be added later
 */
public class Rooms {


    //INSTANCE VARIABLES
    /**
     * Simple boolean array that stores the existence of room exits in the North, East, South and West in that order
     */
    private boolean[] doorways;

    /**
     * Simple boolean that can tell the game whether or not the current room has an event that is still active. Possible
     * events include item pickups, enemy encounters, and any other similar interaction
     */
    private boolean unresolvedEvent;


    //CONSTRUCTORS

    /**
     * If no arguments have been provided, all arguments are set to false automatically, and it is assumed that the
     * room is empty and inaccessible
     */
    public Rooms() {
        doorways = new boolean[] {false, false, false, false};
        unresolvedEvent = false;
    }

    /**
     * Allows creation of rooms with custom variables
     * @param north determines if an exit exists in direct north
     * @param east determines if an exit exists in direct east
     * @param south determines if an exit exists in direct south
     * @param west determines if an exit exists in direct west
     * @param event determines if room has an unresolved event
     */
    public Rooms(boolean north, boolean east, boolean south, boolean west, boolean event) {
        doorways = new boolean[] {north, east, south, west};
        unresolvedEvent = event;
    }

    /**
     * Copy constructor used to return copies of template rooms that exist in the Map class
     * @param toCopy the Rooms object to copy
     */
    public Rooms(Rooms toCopy) {
        doorways = toCopy.getDoorways();
        unresolvedEvent = toCopy.getEvent();
    }


    //GETTERS

    /**
     * Checks to verify that the room has an exit in a specific direction
     * @param index determines which direction to check, with 0 = North, 1 = East, 2 = South, and 3 = West
     * @return True if exit exists and False if not
     */
    public boolean checkRoomExit(int index) {
        return doorways[index];
    }

    /**
     * Checks to see if the room has an event
     * @return True if event exists, False otherwise
     */
    public boolean getEvent() {
        return unresolvedEvent;
    }

    /**
     * Used to retrieve a full array of all exits
     * @return boolean array of all exits
     */
    public boolean[] getDoorways() {
        boolean[] doorwaysCopy = new boolean[this.doorways.length];
        for (int i = 0; i < this.doorways.length; i++) {
            doorwaysCopy[i] = this.doorways[i];
        }
        return doorwaysCopy;
    }


    //SETTER

    /**
     * Used to open new doorways on map. For example, if a locked door exists, and the player unlocks it, this will
     * change the status of that exit
     * @param index Determines the direction in which a path is opened
     */
    public void openNewDoorway(int index) {
        doorways[index] = true;
    }
}
