import java.util.Scanner;

public class TextGame {

    //INSTANCE VARIABLES
    /**
     * These handle the general size of map, as well as the room types contained in the map array. In the 2D version of
     * the game, map creation will be handled differently, but as far as the text game is concerned, a more rigid map is
     * better, as it gives us tighter control on how the game can play out, which is important as player agency is
     * limited
     */
    private static Map gameMap = new Map(3,3);
    private static int[] row1 = {2, 9, 7};
    private static int[] row2 = {0, 6, 8};
    private static int[] row3 = {0, 5, 4};


    /**
     * General scanner used to handle player input
     */
    private static Scanner utilityScanner = new Scanner(System.in);


    /**
     * This is effectively the player class, but simplified for the text game
     */
    private static String lastDirection;
    private static int yCoord;
    private static int xCoord;


    /**
     * Stores the most recent player input
     */
    private static String lastInput;


    //METHODS

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

    private static void inputHandler(String input) {
        input = input.toLowerCase();
        switch (input) {

            default: {
                System.out.println("Invalid input");
                break;
            }

            case "exit game": {
                System.out.println("GAME OVER");
                System.exit(0);
            }

            case "help": {
                printHelpMessage();
                break;
            }

            case "move north": {
                roomTransition("north");
                break;
            }

            case "move east": {
                roomTransition("east");
                break;
            }

            case "move south": {
                roomTransition("south");
                break;
            }

            case "move west": {
                roomTransition("west");
                break;
            }

            case "kill self": {
                System.out.println("You take a rock out from your pouch, load your sling, and hold it against your" +
                        " head.\nYou pull back the elastic as far as you can and fire a rock directly at your own" +
                        " skull, killing you instantly.\n\nGAME OVER\n");
                System.exit(0);
            }

            case "map": {
                gameMap.printMap();
                break;
            }
        }
    }

    private static void printHelpMessage() {
        System.out.println("- To move to another room, type 'move <direction you would like to move>'\n" +
                "- To view the map, type 'map'\n" +
                "- To view this message again, type 'help'\n");
    }

    //MAIN

    public static void main(String[] args) {
        //Build the map
        gameMap.setRoomTypeByRow(0, row1);
        gameMap.setRoomTypeByRow(1, row2);
        gameMap.setRoomTypeByRow(2, row3);
        gameMap.buildCurrentFloor();
        yCoord = 0;
        xCoord = 0;

        //Title screen
        System.out.println("________________________________________\n");
        System.out.println("             <working title>            ");
        System.out.println("________________________________________");
        System.out.println("Press enter to begin");
        utilityScanner.nextLine();

        //Setup scenario
        System.out.println("You awaken in a dimly lit room with nothing but a slingshot and a small pouch filled with" +
                " rocks.\nYou see a map on the wall, this seems to be some sort of maze. There appears to be an exit" +
                " \nsomewhere nearby.\n");
        printHelpMessage();
        System.out.println("________________________________________");

        //Main loop
        do {
            printPossibleExits();
            System.out.print("\nWhat would you like to do?: ");
            lastInput = utilityScanner.nextLine();
            System.out.print("\n");
            inputHandler(lastInput);
            System.out.println("________________________________________");
        } while (gameMap.getRoomType(yCoord, xCoord) != 4);

        //Ending message
        if (gameMap.getRoomType(yCoord, xCoord) == 4) {
            System.out.println("Congratulations! You've made it to the end of the maze");
        }
    }
    }
