import java.util.Random;

public class Map {

    private boolean[][] rooms = new boolean[6][6];

    public Map() {

        Random r = new Random();

        //this.rooms[0][0] = true;
        //this.rooms[rooms.length - 1][rooms.length - 1] = true;
        int mapType = r.nextInt(3);

        switch (mapType) {
            //Ring on outside of map with random feature in middle
            case 0:
                for (int i = 0; i < this.rooms.length; i++) {
                    this.rooms[i][0] = true;
                    this.rooms[i][this.rooms.length - 1] = true;
                }

                for (int i = 0; i < this.rooms.length; i++) {
                    this.rooms[0][i] = true;
                    this.rooms[this.rooms.length - 1][i] = true;
                }

                int randomElement = r.nextInt(3);

                //adds a random element to the map
                switch (randomElement) {
                    //corners
                    case 0:
                        this.rooms[1][1] = true;
                        this.rooms[this.rooms.length - 2][1] = true;
                        this.rooms[1][this.rooms.length - 2] = true;
                        this.rooms[this.rooms.length - 2][this.rooms.length - 2] = true;
                        break;
                    //horizontal line in middle
                    case 1:
                        for (int i = 1; i < this.rooms.length - 1; i++) {
                            this.rooms[i][2] = true;
                        }
                        break;

                    //horizontal line, corners, no outer corners
                    case 2:
                        this.rooms[0][0] = false;
                        this.rooms[0][this.rooms.length - 1] = false;
                        this.rooms[this.rooms.length - 1][0] = false;
                        this.rooms[this.rooms.length - 1][this.rooms.length - 1] = false;

                        this.rooms[1][1] = true;
                        this.rooms[this.rooms.length - 2][1] = true;
                        this.rooms[1][this.rooms.length - 2] = true;
                        this.rooms[this.rooms.length - 2][this.rooms.length - 2] = true;
                        for (int i = 1; i < this.rooms.length - 1; i++) {
                            this.rooms[i][2] = true;
                        }
                        break;
                }
                break;

            //Traditional maze type
            case 2:
            case 1:

                boolean keepGenerating = true;

                while (keepGenerating) {

                    for (int i = 0; i < this.rooms.length; i++) {
                        for (int j = 0; j < this.rooms.length; j++) {
                            this.rooms[i][j] = false;
                        }
                    }

                    this.rooms[0][0] = true;

                    for (int i = 0; i < this.rooms.length - 1; i++) {
                        for (int j = 0; j < this.rooms.length - 1; j++) {
                            if (this.rooms[i][j] && !this.rooms[i + 1][j] && !this.rooms[i][j + 1]) {
                                switch (r.nextInt(2)) {
                                    case 0:
                                        this.rooms[i + 1][j] = true;
                                        break;
                                    case 1:
                                        this.rooms[i][j + 1] = true;
                                        break;
                                }
                            }
                        }
                    }

                    if (!this.rooms[this.rooms.length - 1][0] && !this.rooms[this.rooms.length - 1][1] && !this.rooms[this.rooms.length - 2][0]) {
                        this.rooms[this.rooms.length - 1][0] = true;

                        boolean findPath = false;
                        int xIndex = this.rooms.length - 1;
                        int yIndex = 0;

                        while (!findPath) {
                            if (yIndex + 1 > this.rooms.length - 1 || xIndex < 1) {
                                break;
                            }
                            if (!this.rooms[xIndex - 1][yIndex] && !this.rooms[xIndex][yIndex + 1]) {
                                switch (r.nextInt(2)) {
                                    case 0:
                                        this.rooms[xIndex - 1][yIndex] = true;
                                        xIndex--;
                                        break;
                                    case 1:
                                        this.rooms[xIndex][yIndex + 1] = true;
                                        yIndex++;
                                        break;
                                }

                            } else {
                                findPath = true;
                            }
                        }

                    }

                    if (!this.rooms[0][this.rooms.length - 1] && !this.rooms[0][this.rooms.length - 2] && !this.rooms[1][this.rooms.length - 1]) {
                        this.rooms[0][this.rooms.length - 1] = true;

                        boolean findPath = false;
                        int xIndex = 0;
                        int yIndex = this.rooms.length - 1;

                        while (!findPath) {
                            if (xIndex > this.rooms.length - 2 || yIndex < 1) {
                                break;
                            }
                            if (!this.rooms[xIndex + 1][yIndex] && !this.rooms[xIndex][yIndex - 1]) {
                                switch (r.nextInt(2)) {
                                    case 0:
                                        this.rooms[xIndex + 1][yIndex] = true;
                                        xIndex++;
                                        break;
                                    case 1:
                                        this.rooms[xIndex][yIndex - 1] = true;
                                        yIndex--;
                                        break;
                                }

                            } else {
                                findPath = true;
                            }
                        }
                    }

                    if (!this.rooms[this.rooms.length - 1][this.rooms.length - 1] && !this.rooms[this.rooms.length - 1][this.rooms.length - 2] && !this.rooms[this.rooms.length - 1][this.rooms.length - 2]) {

                        boolean findPath = false;
                        int xIndex = this.rooms.length - 1;
                        int yIndex = this.rooms.length - 1;

                        while (!findPath) {
                            if (!this.rooms[xIndex - 1][yIndex] && !this.rooms[xIndex][yIndex - 1]) {
                                switch (r.nextInt(2)) {
                                    case 0:
                                        this.rooms[xIndex - 1][yIndex] = true;
                                        xIndex--;
                                        break;
                                    case 1:
                                        this.rooms[xIndex][yIndex - 1] = true;
                                        yIndex--;
                                        break;
                                }

                            } else {
                                findPath = true;
                            }
                        }
                    }

                    boolean blankRow = true;
                    int blankRows = 0;

                    for (int i = 0; i < this.rooms.length; i++) {
                        blankRow = true;
                        for (int j = 0; j < this.rooms.length; j++) {
                            if (this.rooms[j][i]) {
                                blankRow = false;
                            }
                        }
                        if (blankRow) {
                            blankRows++;
                        }
                    }

                    boolean blankColumn = true;
                    int blankColumns = 0;

                    for (int i = 0; i < this.rooms.length; i++) {
                        blankColumn = true;
                        for (int j = 0; j < this.rooms.length; j++) {
                            if (this.rooms[i][j]) {
                                blankColumn = false;
                            }
                        }
                        if (blankColumn) {
                            blankColumns++;
                        }
                    }

                    if (blankRows == 0 && blankColumns == 0) {
                        keepGenerating = false;
                    }

                }
        }
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

}
