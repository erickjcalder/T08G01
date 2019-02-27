public class Map {

    private boolean[][] rooms = new boolean[6][6];
    private int[][] roomTypes = new int[6][6];

    public Map() {

    }

    public boolean getRoomTruth(int index1, int index2) {
        return this.rooms[index1][index2];
    }

    public boolean[][] getRooms() {
        boolean[][] roomsCopy = new boolean[this.rooms.length][this.rooms.length];

        for (int i = 0; i < this.rooms.length; i++) {
            for (int j = 0; j < this.rooms.length; j++) {
                roomsCopy[i][j] = this.rooms[i][j];
            }
        }

        return roomsCopy;
    }

    public int getMapSize() {
        return this.rooms.length;
    }

    public void setRoomType(int index1, int index2, int type) {
        this.roomTypes[index1][index2] = type;
    }

    public int getRoomType(int index1, int index2) {
        return this.roomTypes[index1][index2];
    }

    public void setRoomTypesArray(int[][] roomTypes) {
        for (int i = 0; i < this.roomTypes.length; i++) {
            for (int j = 0; j < this.roomTypes.length; j++) {
                this.roomTypes[i][j] = roomTypes[i][j];
            }
        }
    }

    public int[][] getRoomTypesArray() {
        int[][] roomTypeCopy = new int[this.roomTypes.length][this.roomTypes.length];

        for (int i = 0; i < this.roomTypes.length; i++) {
            for (int j = 0; j < this.roomTypes.length; j++) {
                roomTypeCopy[i][j] = this.roomTypes[i][j];
            }

        }
        return roomTypeCopy;
    }

    public void printMap() {
        for (int i = 0; i < this.roomTypes.length; i++) {
            for (int j = 0; j < this.roomTypes.length; j++) {
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
