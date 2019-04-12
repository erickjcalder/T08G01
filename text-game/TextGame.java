import java.util.Scanner;

/**
 * This class exists to handle all events in the text based version of the game. It will not serve any purpose in the
 * final 2D iteration of the game.
 */
public class TextGame {

    //INSTANCE VARIABLES
    /**
     * gameMap is a map object that contains the 2D array of room values that are in turn used to generate all rooms
     * necessary for the floor on which the game is played. Length and height are provided on map generation, which
     * determines the number of rooms along the x and y axis respectively. Map generation is currently static, but
     * these parameters will eventually be randomized so as to make each game unique
     */
    private static Map gameMap = new Map(3,3);

    /**
     * row1, row2, and row3 are simple integer arrays that contain reference numbers for different varieties of rooms.
     * When a game is started, these arrays are sent to the game map object to tell it what rooms it needs for the Rooms
     * array. Like gameMap, these rooms are currently static, but will be made to be dynamic in the future
     */
    private static int[] row1 = {2, 9, 7};
    private static int[] row2 = {0, 6, 8};
    private static int[] row3 = {0, 5, 4};


    /**
     * This scanner object exists to handle general payer input, pressing enter to advance text, submitting commands,
     * etc. It will likely be replaced by JavaFX event handlers for the 2D version of our game
     */
    private static Scanner utilityScanner = new Scanner(System.in);


    /**
     * lastDirection stores the direction from which the room was entered. This would theoretically communicate with
     * the RPGLayer to enable certain functions like retreat to function properly, however, that was not implemented
     * in our text game
     */
    private static String lastDirection;

    /**
     * xCoord and yCoord determine which room the player is currently in. In the text game this means that they
     * increment whenever the player types a direction to move in, but the 2D game equivalent will increment when the
     * player touches the edge of the screen at a valid exit point
     */
    private static int yCoord;
    private static int xCoord;


    /**
     * Stores the input of the player from the console. Again, a band-aid solution for the text game which will be
     * obsoleted by event handlers in the 2D animated version
     */
    private static String lastInput;


    //METHODS

    /**
     * Checks the current room and outputs all possible exits to the console in a single line
     */
    private static void printPossibleExits() {
        boolean previousEntry = false;

        System.out.print("\n");
        System.out.print("You see exits to the: ");

        if (gameMap.currentFloor[yCoord][xCoord].checkRoomExit(0)) {
            System.out.print("North");
            previousEntry = true;
        }

        if (gameMap.currentFloor[yCoord][xCoord].checkRoomExit(1)) {
            if (previousEntry) {
                System.out.print(", ");
            }
            System.out.print("East");
            previousEntry = true;
        }

        if (gameMap.currentFloor[yCoord][xCoord].checkRoomExit(2)) {
            if (previousEntry) {
                System.out.print(", ");
            }
            System.out.print("South");
            previousEntry = true;
        }

        if (gameMap.currentFloor[yCoord][xCoord].checkRoomExit(3)) {
            if (previousEntry) {
                System.out.print(", ");
            }
            System.out.print("West");
        }
    }

    /**
     * Is called whenever a room transition is needed. Increments the player's x and y coordinates appropriately. Will
     * tell the player if their action is invalid.
     * @param direction tells the transition handler which direction the player is attempting to move, and how to adjust
     *                  the x and y coordinates if the move is valid
     */
    private static void roomTransition(String direction) {
        switch (direction) {

            case "north": {
                if (gameMap.currentFloor[yCoord][xCoord].checkRoomExit(0)) {
                    yCoord -= 1;
                    lastDirection = "north";
                    System.out.println("You move to the North");
                } else {
                    System.out.println("There is no path to the North");
                }
                break;
            }

            case "east": {
                if (gameMap.currentFloor[yCoord][xCoord].checkRoomExit(1)) {
                    xCoord += 1;
                    lastDirection = "east";
                    System.out.println("You move to the East");
                } else {
                    System.out.println("There is no path to the East");
                }
                break;
            }

            case "south": {
                if (gameMap.currentFloor[yCoord][xCoord].checkRoomExit(2)) {
                    yCoord += 1;
                    lastDirection = "south";
                    System.out.println("You move to the South");
                } else {
                    System.out.println("There is no path to the South");
                }
                break;
            }

            case "west": {
                if (gameMap.currentFloor[yCoord][xCoord].checkRoomExit(3)) {
                    xCoord -= 1;
                    lastDirection = "west";
                    System.out.println("You move to the West");
                } else {
                    System.out.println("There is no path to the West");
                }
                break;
            }
        }
    }

    /**
     * This method is the first stop for any player input once the game has begun.
     * @param input Is used to determine what needs to be called next. Each possible input is explained in greater
     *              detail in the line comments below
     */
    private static void inputHandler(String input) {
        input = input.toLowerCase();
        switch (input) {

            //Simply tells the player that their input is invalid and nothing has happened
            default: {
                System.out.println("Invalid input");
                break;
            }

            //Used to end the game prematurely
            case "exit game": {
                System.out.println("GAME OVER");
                System.exit(0);
            }

            //Reprints the help message which tells the player what actions they are able to do at any time
            case "help": {
                printHelpMessage();
                break;
            }

            //Redirects to the room transition handler with direction north
            case "move north": {
                roomTransition("north");
                break;
            }

            //Redirects to the room transition handler with direction east
            case "move east": {
                roomTransition("east");
                break;
            }

            //Redirects to the room transition handler with direction south
            case "move south": {
                roomTransition("south");
                break;
            }

            //Redirects to the room transition handler with direction west
            case "move west": {
                roomTransition("west");
                break;
            }

            //Also used to end the game prematurely, but in a way that makes sense in regards to the scenario. Intended
                //to be a joke
            case "kill self": {
                System.out.println("You take a rock out from your pouch, load your sling, and hold it against your" +
                        " head.\nYou pull back the elastic as far as you can and fire a rock directly at your own" +
                        " skull, killing you instantly.\n\nGAME OVER\n");
                System.exit(0);
            }

            //Prints the map for the player to see
            case "map": {
                gameMap.printMap();
                break;
            }
        }
    }

    /**
     * Called at the start of the game and whenever the player enters "help". Informs the player of all commands which
     * they can enter at that time
     */
    private static void printHelpMessage() {
        System.out.println("- To move to another room, type 'move <direction you would like to move>'\n" +
                "- To view the map, type 'map'\n" +
                "- To view this message again, type 'help'\n");
    }



    //MAIN

    public static void main(String[] args) {

        //All three row arrays are taken and added to the gameMaps roomType array
        gameMap.setRoomTypeByRow(0, row1);
        gameMap.setRoomTypeByRow(1, row2);
        gameMap.setRoomTypeByRow(2, row3);

        //Reading the values provided by the rows, the appropriate room objects are created for each position
        gameMap.buildCurrentFloor();

        //Co-ordinates are set to the default position
        yCoord = 0;
        xCoord = 0;

        //A simple title screen is printed out
        System.out.println("________________________________________\n");
        System.out.println("             <working title>            ");
        System.out.println("________________________________________");

        //User is prompted to press enter to begin the game, scanner is used to wait for that input
        System.out.println("Press enter to begin");
        utilityScanner.nextLine();

        //Basic message outlining player scenario is printed
        System.out.println("You awaken in a dimly lit room with nothing but a slingshot and a small pouch filled with" +
                " rocks.\nYou see a map on the wall, this seems to be some sort of maze. There appears to be an exit" +
                " \nsomewhere nearby.\n");

        //Help message is automatically printed to inform the player of possible actions
        printHelpMessage();

        //Solid lines are used to make reading events more easy
        System.out.println("________________________________________");

        //Main game play loop is initiated
        do {

            //At the start of each loop, all possible exits are stated for the user to see
            printPossibleExits();

            //User is then prompted for their input
            System.out.print("\nWhat would you like to do?: ");

            //Their input is taken by the scanner and stored in the lastInput variable
            lastInput = utilityScanner.nextLine();

            //Newline printed for ease of reading
            System.out.print("\n");

            //User input is sent to the input handler
            inputHandler(lastInput);

            //Another line for ease of reading
            System.out.println("________________________________________");

        //Game checks for end condition, which is current that the players roomType corresponds to a 4
        } while (gameMap.getRoomType(yCoord, xCoord) != 4);

        //Message is printed to inform payer they have achieved the end goal
        if (gameMap.getRoomType(yCoord, xCoord) == 4) {
            System.out.println("Congratulations! You've made it to the end of the maze");
        }
    }
    }
