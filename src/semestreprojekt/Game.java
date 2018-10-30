package semestreprojekt;

import entities.Materials;
import fightsystem.*;
/**
 * This class is the games center frame. 
 * You use it to create a new game object and start the game from it.
 * It contains two private attributes: parser and currentRoom.
 * @author Gruppe 33
 */
public class Game 
{
    //Attributes
    private Parser parser;
    private Room currentRoom;
    private fightsystem.Character protagonist;
    private fightsystem.AttackCatalogue browser;
        
    //Constructor
    /**
     * This constructor takes no arguments but constructs a object, runs the private method createRooms and assigns the attribute: parser, a new object from the Parser class
     */
    public Game(fightsystem.Character protagonist, fightsystem.AttackCatalogue browser) 
    {
        this.browser = browser; 
        this.protagonist = protagonist;
        createRooms();
        parser = new Parser();
    }

    /**
     * This is a private method, it cannot return and it takes no arguments.
     * It's functionality is to create the rooms used in the game.
     */
    private void createRooms()
    {
        Room home, townSquare, caveEntrance, cave1, cave2, cave3, cave4, cave5, cave6, cave7, cave8, cave9, cave10, cave11;
        Attack[] attack = {browser.getAttack("Hack"), browser.getAttack("Slice"), browser.getAttack("Chop")};
        
        home = new Room("You are in your loving home");
        townSquare = new Room("You are in the town square");
        caveEntrance = new Room("You are at the mine entrance", true, 1); // displays description, boolean for first time event and room id to distinguish between different first time event rooms.
        cave1 = new Room("You stand in the first mine room");
        cave3 = new Room("You stand in the third mine room");
        cave2 = new Room("You stand in the second mine room");
        cave4 = new Room("You stand in the fourth mine room");
        cave5 = new Room("You stand in the fifth mine room", true, 2);
        cave6 = new Room("You stand in the sixth mine room");
        cave7 = new Room("You stand in the seventh mine room");
        cave8 = new Room("You stand in the eighth mine room");
        cave9 = new Room("You stand in the ninth mine room");
        cave10 = new Room("You stand in the tenth mine room");
        cave11 = new Room("You stand in the eleventh mine room");
        
        home.setExit("east", townSquare);

        townSquare.setExit("west", home);
        townSquare.setExit("south", caveEntrance);

        caveEntrance.setExit("east", cave1);
        caveEntrance.setExit("north", townSquare);
        
        
        cave1.setExit("west", caveEntrance);
        cave1.setExit("north", cave3);
        cave1.setExit("south", cave2);
        
        fightsystem.Character wombat = new fightsystem.Character("Wombat", 25, 75, 50);
        wombat.setAttacks(attack);
        cave1.setEnemy(wombat);

        cave2.setExit("west", cave11);
        cave2.setExit("east", cave8);
        cave2.setExit("north", cave1);
        
        Materials iron = new Materials("Iron", 1, "Piece of iron.", 10, 250);
        
        cave3.setExit("south", cave1);
        cave3.setExit("north", cave4);
        cave3.setExit("east", cave5);
        cave3.setItems(iron);
        
        cave4.setExit("south", cave3);
        cave4.setExit("east", cave6);
        
        cave5.setExit("west", cave3);
        cave5.setExit("north", cave6);
        cave5.setExit("south", cave7);
        
        cave6.setExit("north", cave10);
        cave6.setExit("west", cave4);
        cave6.setExit("south", cave5);
        
        cave7.setExit("east", cave9);
        cave7.setExit("north", cave5);
        cave7.setExit("south", cave8);
        
        cave8.setExit("north", cave7);
        cave8.setExit("west", cave2);
        
        cave9.setExit("west", cave7);
        
        cave10.setExit("south", cave6);
        
        cave11.setExit("east", cave2);

        currentRoom = home;
    }
    
    /**
     * This is a public method, it cannot return and it takes no arguments.
     * It's functionality is to start the game, and check if game is completed
     */
    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.INVENTORY) {
            // Checks inventory
        } else if (commandWord == CommandWord.USE) {
            // Use an item in the inventory
        } else if (commandWord == CommandWord.TAKE) {
            // Take item from room and add to inventory. Deletes the item from the room after.
        } else if (commandWord == CommandWord.REST) {
            // Rests and restores 25 HP to the player.
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            
            if(currentRoom.isEnemy())
            {
                currentRoom.startFight(protagonist);
            }
            
            System.out.println(currentRoom.getLongDescription());
            currentRoom.visitCounterPlus(); // When you enter a room the visitcounter increases by 1.
            System.out.println("Times visited room: " + currentRoom.getVisitCounter()); // Test to see if it funktions
            if (currentRoom.getVisitCounter() == 1 && currentRoom.isFirstTimeEvent()) { // If the visit count is 1 and there's a first time event, run first time event.
                currentRoom.firstTimeEvent();
            }
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
