import java.util.Random;

public class Map {

	Room roomLoc[][];
	int width;
	int height;

	// creates a 2D array of Room objects and will decide where each room will go
	// and what entrances the Room will have
	// later on will also determine what type of Room it is
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		this.roomLoc = new Room[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.roomLoc[i][j] = new Room(false, false, false, false, false, 0);
			}
		}

		chooseLayout();

	}

	public boolean roomCheck(int x, int y) {

		if (x < 0 || y < 0 || x == width || y == height) {
			return false;
		}

		if (roomLoc[x][y].getRoomType() != 0) {
			return true;
		}

		return false;
	}

	// Determines the layout by randomly selecting a premade layout
	public void chooseLayout() {

		Random r = new Random();

		switch (r.nextInt(1)) {

		case 0:
			this.roomLoc[0][0] = new Room(false, false, false, false, false, 1);
			this.roomLoc[1][0] = new Room(false, false, false, false, false, 1);
			this.roomLoc[2][0] = new Room(false, false, false, false, false, 1);

			this.roomLoc[2][1] = new Room(false, false, false, false, false, 1);
			this.roomLoc[3][1] = new Room(false, false, false, false, false, 1);

			this.roomLoc[1][2] = new Room(false, false, false, false, false, 1);
			this.roomLoc[2][2] = new Room(false, false, false, false, false, 1);
			this.roomLoc[3][2] = new Room(false, false, false, false, false, 1);
			this.roomLoc[4][2] = new Room(false, false, false, false, false, 1);
			this.roomLoc[5][2] = new Room(false, false, false, false, false, 1);
			this.roomLoc[6][2] = new Room(false, false, false, false, false, 1);

			this.roomLoc[3][3] = new Room(false, false, false, false, false, 1);

			this.roomLoc[0][4] = new Room(false, false, false, false, false, 1);
			this.roomLoc[3][4] = new Room(false, false, false, false, false, 1);

			this.roomLoc[0][5] = new Room(false, false, false, false, false, 1);
			this.roomLoc[1][5] = new Room(false, false, false, false, false, 1);
			this.roomLoc[2][5] = new Room(false, false, false, false, false, 1);
			this.roomLoc[3][5] = new Room(false, false, false, false, false, 1);

			this.roomLoc[3][6] = new Room(false, false, false, false, false, 1);
			this.roomLoc[4][6] = new Room(false, false, false, false, false, 1);
			this.roomLoc[5][6] = new Room(false, false, false, false, false, 1);
		}

		// Creates all the doors to the other rooms
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				if (i < width - 1) {
					if (roomLoc[i + 1][j].getRoomType() == 1) {
						roomLoc[i][j].east = true;
					}
				}

				if (i >= 1) {
					if (roomLoc[i - 1][j].getRoomType() == 1) {
						roomLoc[i][j].west = true;
					}
				}

				if (j < height - 1) {
					if (roomLoc[i][j + 1].getRoomType() == 1) {
						roomLoc[i][j].south = true;
					}
				}

				if (j >= 1) {
					if (roomLoc[i][j - 1].getRoomType() == 1) {
						roomLoc[i][j].north = true;
					}
				}

			}
		}

	}

}
