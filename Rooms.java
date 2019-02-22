public class Rooms {

    //VARIABLES
    private boolean[] doorways;
    private boolean unresolvedEvent;

    //CONSTRUCTORS
    public Rooms() {
        doorways = new boolean[] {false, false, false, false};
        unresolvedEvent = false;
    }

    public Rooms(boolean north, boolean east, boolean south, boolean west, boolean event) {
        doorways = new boolean[] {north, east, south, west};
        unresolvedEvent = event;
    }

    public Rooms(Rooms toCopy) {
        doorways = toCopy.getDoorways();
        unresolvedEvent = toCopy.getEvent();
    }

    //GETTERS
    public boolean checkRoomExit(int index) {
        return doorways[index];
    }

    public boolean getEvent() {
        return unresolvedEvent;
    }

    public boolean[] getDoorways() {
        boolean[] doorwaysCopy = new boolean[this.doorways.length];
        for (int i = 0; i < this.doorways.length; i++) {
            doorwaysCopy[i] = this.doorways[i];
        }
        return doorwaysCopy;
    }

    //SETTER
    public void openNewDoorway(int index) {
        doorways[index] = true;
    }
}
