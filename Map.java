public class Map {
	
	/**
	* This class handles everything to do with the map for the game.
	*/

    //VARIABLES
    private int[][] roomTypes;
    public Rooms[][] currentFloor;

    /**
     * private boolean[][] rooms = new boolean[6][6];
     */
    //ROOM TYPES//
    /**
     * All basic room types are represented here, sorted by number of exits
     */
    //NO EXIT
    public Rooms room0 = new Rooms(false, false, false, false, false);

    //SINGLE EXIT
    //North
    public Rooms room1 = new Rooms(true, false, false, false, false);
    //East
    public Rooms room2 = new Rooms(false, true, false, false, false);
    //South
    public Rooms room3 = new Rooms(false, false, true, false, false);
    //West
    public Rooms room4 = new Rooms(false, false, false, true, false);

    //DOUBLE EXIT
    //North, East
    public Rooms room5 = new Rooms(true, true, false, false, false);
    //South, East
    public Rooms room6 = new Rooms(false, true, true, false, false);
    //South, West
    public Rooms room7 = new Rooms(false, false, true, true, false);
    //North, West
    public Rooms room8 = new Rooms(true, false, false, true, false);
    //East, West
    public Rooms room9 = new Rooms(false, true, false, true, false);
    //North, South
    public Rooms room10 = new Rooms(true, false, true, false, false);

    //TRIPLE EXIT
    //South, East, West
    public Rooms room11 = new Rooms(false, true, true, true, false);
    //North, South, West
    public Rooms room12 = new Rooms(true, false, true, true, false);
    //North, East, West
    public Rooms room13 = new Rooms(true, true, false, true, false);
    //North, South, East
    public Rooms room14 = new Rooms(true, true, true, false, false);

    //ALL FOUR EXITS
    public Rooms room15 = new Rooms(true, true, true, true, false);

    /**
     * Room types end here
     */
    //CONSTRUCTORS
	
	/**
	* Creates a new Map object using default 2D array sizes of 3 x 3
	*/
    public Map() {
        roomTypes = new int[3][3];
        currentFloor = new Rooms[3][3];
    }

	/**
	* Creates a new Map object using given dimensions of the map
	*
	* @param  int  the length of the 2D arrays
	* @param  int  the width of the 2D arrays
	*/
    public Map(int length, int height) {
        roomTypes = new int[length][height];
        currentFloor = new Rooms[length][height];
    }

    //GETTERS
	
	/**
	* Returns an int that describes the type of room at a specific index in the roomTypes array of the map object
	*
	* @param  int  the first index for the 2D array
	* @param  int  the second index for the 2D array
	* @return      the int at the specified index
	*/
    public int getRoomType(int index1, int index2) {
        return this.roomTypes[index1][index2];
    }

	/**
	* Returns a copy of the 2D int array roomTypesArray() belonging to the map object 
	*
	* @return      a copy of the roomTypesArray() belonging to the map object
	*/
    public int[][] getRoomTypesArray() {
        int[][] roomTypeCopy = new int[this.roomTypes.length][this.roomTypes.length];

        for (int i = 0; i < this.roomTypes.length; i++) {
            for (int j = 0; j < this.roomTypes.length; j++) {
                roomTypeCopy[i][j] = this.roomTypes[i][j];
            }

        }
        return roomTypeCopy;
    }

	/**
	* Returns a Rooms object 
	*
	* @param  int  the room number 
	* @return      the Rooms object corresponding to the given int
	*/
    public Rooms getRoomCopy(int roomNumber) {
        if (roomNumber > 0 && roomNumber <= 15) {
            switch (roomNumber) {

                case 1: {
                    return new Rooms(room1);
                }

                case 2: {
                    return new Rooms(room2);
                }

                case 3: {
                    return new Rooms(room3);
                }

                case 4: {
                    return new Rooms(room4);
                }

                case 5: {
                    return new Rooms(room5);
                }

                case 6: {
                    return new Rooms(room6);
                }

                case 7: {
                    return new Rooms(room7);
                }

                case 8: {
                    return new Rooms(room8);
                }

                case 9: {
                    return new Rooms(room9);
                }

                case 10: {
                    return new Rooms(room10);
                }

                case 11: {
                    return new Rooms(room11);
                }

                case 12: {
                    return new Rooms(room12);
                }

                case 13: {
                    return new Rooms(room13);
                }

                case 14: {
                    return new Rooms(room14);
                }

                case 15: {
                    return new Rooms(room15);
                }
            }
        }
        return new Rooms(room0);
    }

    //SETTERS
	
	/**
	* Sets the room type at a specific index in the Map object's roomTypes array
	*
	* @param  int  the first index for the 2D array 
	* @param  int  the second index for the 2D array
	*/
    public void setRoomType(int index1, int index2, int type) {
        this.roomTypes[index1][index2] = type;
    }

	/**
	* Sets a row of the Map object's roomTypes int array to a given int array
	*
	* @param  int  the first for the row of the 2D array 
	* @param  int[]  an array of a row to add to the Map object's roomTypes array
	*/
    public void setRoomTypeByRow(int rowIndex, int[] rowToAdd) {
        roomTypes[rowIndex] = rowToAdd;
    }

	/**
	* Sets the room type at a specific index in the Map object's roomTypes array
	*
	* @param  int  the first for the row of the 2D array 
	* @param  int[]  an array of a row to add to the Map object's roomTypes array
	*/
    public void setRoomTypesArray(int[][] roomTypes) {
        for (int i = 0; i < this.roomTypes.length; i++) {
            for (int j = 0; j < this.roomTypes.length; j++) {
                this.roomTypes[i][j] = roomTypes[i][j];
            }
        }
    }

	/**
	* Builds the current floor of a map by looping through the Map object's currentFloor array and adding all the Rooms
	*/
    public void buildCurrentFloor() {
        for (int i = 0; i < roomTypes.length; i++) {
            for (int j = 0; j < roomTypes[i].length; j++) {
                currentFloor[i][j] = new Rooms(getRoomCopy(roomTypes[i][j]));
            }
        }
    }

	/**
	* Prints a visual representation of the map to the console
	*/
    public void printMap() {
        for (int i = 0; i < this.roomTypes.length; i++) {
            for (int j = 0; j < this.roomTypes[i].length; j++) {
                if (this.roomTypes[i][j] != 0) {
                    System.out.print("X   ");
                } else {
                    System.out.print("-   ");
                }
            }
            System.out.println("");
        }
    }
}