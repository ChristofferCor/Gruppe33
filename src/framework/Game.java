package framework;

import entities.*;
import fightsystem.*;
import gui.GUIController;
import gui.HomeController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.TextFormater;

/**
 * This class is the games center frame. You use it to create a new game object
 * and start the game from it. It contains two private attributes: parser and
 * currentRoom.
 *
 * @author Gruppe 33
 */
public class Game {

    //Attributes
    private Parser parser;
    private Room currentRoom;
    private fightsystem.Character protagonist;
    private fightsystem.AttackCatalogue browser;
    private ItemCatalogue catalogue = new ItemCatalogue();
    private Room victoryRoom;
    private boolean finished = false;
    private Score score;
    private boolean isDug = false;
    private boolean hasPickaxe = false;

    //Constructor
    /**
     * This constructor takes no arguments but constructs a object, runs the
     * private method createRooms and assigns the attribute: parser, a new
     * object from the Parser class
     *
     * @param protagonist
     * @param browser
     */
    public Game(fightsystem.Character protagonist, fightsystem.AttackCatalogue browser) {
        this.browser = browser;
        this.protagonist = protagonist;
        createRooms();
        parser = new Parser();
        this.score = new Score();
    }

    /**
     * This is a private method, it cannot return and it takes no arguments.
     * It's functionality is to create the rooms used in the game.
     */
    private void createRooms() {
        Room home, townSquare, caveEntrance, cave1, cave2, cave3, cave4, cave5, cave6, cave7, cave8, cave9, cave10, cave11;
        Attack[] attack = {browser.getAttack("hit"), browser.getAttack("stomp"), browser.getAttack("kick-ass")};

        home = new Room("You are in your loving home.", 1, "/gui/Home.fxml");
        townSquare = new Room("You are in the town square.", 2, "/gui/TownSquare.fxml");
        caveEntrance = new Room("You are at the mine entrance.", 3, true, 1, "/gui/CaveEntrance.fxml"); // displays description, boolean for first time event and room id to distinguish between different first time event rooms.
        cave1 = new Room("You stand in the first mine room.", 4, "/gui/Cave1.fxml");
        cave3 = new Room("You stand in the third mine room.", 5, true, 2, "/gui/Cave3.fxml");
        cave2 = new Room("You stand in the second mine room.", 6, "/gui/Cave2.fxml");
        cave4 = new Room("You stand in the fourth mine room.", 7, "/gui/Cave4.fxml");
        cave5 = new Room("You stand in the fifth mine room.", 8, "/gui/Cave5.fxml");
        cave6 = new Room("You stand in the sixth mine room.", 9, "/gui/Cave6.fxml");
        cave7 = new Room("You stand in the seventh mine room. The exit to the east has a 'WARNING' sign besides it.", 10, "/gui/Cave7.fxml");
        cave8 = new Room("You stand in the eighth mine room.", 11, "/gui/Cave8.fxml");
        cave9 = new Room("You stand in the ninth mine room.", 12, "/gui/Cave9.fxml");
        cave10 = new Room("You stand in the tenth mine room.", 13, true, 3, "/gui/Cave10.fxml");
        cave11 = new Room("You stand in the eleventh mine room.", 14, "/gui/Cave11.fxml");
        victoryRoom = new Room("You are back at the town square. Nobody noticed you were gone, but you won, hurray!", 15, "/gui/Victory.fxml");

        home.setExit("east", townSquare);

        townSquare.setExit("west", home);
        townSquare.setExit("south", caveEntrance);

        caveEntrance.setExit("east", cave1);

        cave1.setExit("west", caveEntrance);
        cave1.setExit("north", cave3);
        cave1.setExit("south", cave2);

        cave2.setExit("west", cave11);
        cave2.setExit("east", cave8);
        cave2.setExit("north", cave1);
        cave2.setItems(ItemCatalogue.getItem(100));

        cave3.setExit("south", cave1);
        cave3.setExit("north", cave4);
        cave3.setExit("east", cave5);

        cave4.setExit("south", cave3);
        cave4.setExit("east", cave6);
        fightsystem.Character crazyMiner = new fightsystem.Character("Crazy miner", 25, 75, 50);
        crazyMiner.addToInventory(ItemCatalogue.getItem(1));
        crazyMiner.setAttacks(attack);
        cave4.setEnemy(crazyMiner);

        cave5.setExit("west", cave3);
        cave5.setExit("north", cave6);
        cave5.setExit("south", cave7);
        fightsystem.Character childhoodBully = new fightsystem.Character("Childhood Bully", 40, 150, 125);
        childhoodBully.addToInventory(ItemCatalogue.getItem(2));
        childhoodBully.addToInventory(ItemCatalogue.getItem(300));
        childhoodBully.setAttacks(attack);
        cave5.setEnemy(childhoodBully);

        cave6.setExit("north", cave10);
        cave6.setExit("west", cave4);
        cave6.setExit("south", cave5);

        cave7.setExit("east", cave9);
        cave7.setExit("north", cave5);
        cave7.setExit("south", cave8);
        cave7.setItems(ItemCatalogue.getItem(200));

        cave8.setExit("north", cave7);
        cave8.setExit("west", cave2);
        cave8.setItems(ItemCatalogue.getItem(300));

        cave9.setExit("west", cave7);
        fightsystem.Character trappedLunatic = new fightsystem.Character("Trapped lunatic", 60, 75, 50);
        trappedLunatic.addToInventory(ItemCatalogue.getItem(3));
        trappedLunatic.setAttacks(attack);
        cave9.setEnemy(trappedLunatic);

        cave10.setExit("south", cave6);
        cave10.setItems(ItemCatalogue.getItem(101));
        cave10.setEventRoom(cave11);

        cave11.setExit("east", cave2);

        currentRoom = home;
        try {
            GUIController.getGui().updateStage(this.currentRoom);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * This is a public method, it cannot return and it takes no arguments. It's
     * functionality is to start the game, and check if game is completed
     */
    public void play() {
        printWelcome();

        //while (!finished) {
        //Command command = parser.getCommand();
        //finished = processCommand(command);
        //}
    }

    private void printWelcome() {
        String outputText = "";
        outputText += ("Welcome to The Keybinder - Master of Keys!\n\n");
        outputText += ("The Keybinder - Master of Keys is a new, incredibly amazing adventure game.\n");
        outputText += ("You are a young dwarf and is finally old enough to work in the local mines.\n");
        outputText += ("This is a big day for you. You are becoming a man.\n");
        outputText += ("You start in your loving home.\n");
        outputText += ("Type '" + CommandWord.HELP + "' if you need help.\n");
        outputText += "\n";
        outputText += (currentRoom.getStartDescription() + "\n");
        outputText += (currentRoom.getEndString());
        //currentRoom.getController().setOutputText(outputText);
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        if (currentRoom == victoryRoom) {
            return true;
        }

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            wantToQuit = goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.INVENTORY) {
            System.out.println(protagonist.getInventory());

        } else if (commandWord == CommandWord.USE) {
            if (command.hasSecondWord()) {
                String itemName = command.getSecondWord();
                Item tempItem = protagonist.hasItem(itemName);
                if (protagonist.getInventory().isEmpty()) {
                    System.out.println("You have no items");
                } else {
                    String noItemFits = "You search your pockets, but find nothing fitting that name. You feel stupid.";
                    if (tempItem != null) {
                        tempItem.use(protagonist);
                        if (tempItem instanceof Consumables) {
                            protagonist.removeFromInventory(tempItem);
                        }
                    } else if (itemName.equalsIgnoreCase("pickaxe")) {
                        if (hasPickaxe == true) {
                            if (currentRoom.getRoomID() == 3) {
                                if (isDug == false) {
                                    currentRoom.setExit("north", victoryRoom);
                                    System.out.println("You dig a small hole through the rubble for you to fit through. You hear birds singing and see the sun shining. You can now leave the mine.");
                                    System.out.println(currentRoom.getExitString());
                                    isDug = true;
                                } else {
                                    System.out.println("You already dug through the rubble.");
                                }
                            } else {
                                System.out.println("You try and mine something, but get nowhere. You feel stupid.");
                            }
                        } else {
                            System.out.println(noItemFits);
                        }
                    } else {
                        System.out.println(noItemFits);
                    }
                }
            } else {
                System.out.println("Use what???");
            }
        } else if (commandWord == CommandWord.TAKE) {

            String itemName = command.getSecondWord();
            Item tempItem = currentRoom.hasItem(itemName);
            if (tempItem != null) {
                protagonist.addToInventory(tempItem);
                System.out.println("You take " + itemName);
                currentRoom.removeItems(tempItem);
            } else {
                System.out.println("No items fit that name, why would you take something that doesn't exist? You rethink your life.");
            }

        } else if (commandWord == CommandWord.REST) {
            Score.increaseRest();
            this.protagonist.setHp(this.protagonist.getHp() + 25); // Adds 25 HP to the player
            System.out.println("(DAY " + (Score.getRest() + 1) + ") You rested and got 25 HP. Your total HP is " + protagonist.getHp());
        } else if (commandWord == CommandWord.CRAFT) {
            if (this.currentRoom.getRoomID() == 3) {
                Crafting crafter = new Crafting(protagonist);
                ArrayList<Item> inv = protagonist.getInventory();
                if (inv.contains(ItemCatalogue.getItem(3))) {
                    boolean hasPickaxe = false;
                    for (Item item : inv) {
                        if (item instanceof Pickaxe && !(item instanceof SuperPickaxe)) {
                            hasPickaxe = true;
                        }
                    }
                    if (hasPickaxe) {
                        crafter.craftAddExtra();
                    } else {
                        this.hasPickaxe = crafter.craftPickaxe();
                    }
                } else {
                    System.out.println("You haven't learned how to craft yet!");
                }
            } else {
                System.out.println("There is no furnace in this room!");
                return false;
            }
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("You feel sorry for yourself.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private boolean goRoom(Command command) {
        boolean wantsToQuit = false;

        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return false;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no passage!");
        } else {

            currentRoom = nextRoom;
            if (currentRoom != victoryRoom) {

                currentRoom.visitCounterPlus();

                if (currentRoom.getVisitCounter() == 1 && currentRoom.isFirstTimeEvent()) { // If the visit count is 1 and there's a first time event, run first time event.
                    currentRoom.firstTimeEvent(protagonist);
                }

                System.out.println(currentRoom.getStartDescription()); //prints description

                if (currentRoom.isEnemy()) //if there is an enemy run this thing
                {
                    //asks if the player want to fight
                    System.out.println("You see a " + currentRoom.getEnemyName() + " do you want to engage? Yes or no?");

                    //input
                    Command command2 = parser.getCommand();

                    //reads input
                    CommandWord commandWord = command2.getCommandWord();

                    while ((commandWord == CommandWord.YES || commandWord == CommandWord.NO) == false) {
                        System.out.println("That is not a valid command here. Typo?");
                        //input
                        command2 = parser.getCommand();

                        //reads input
                        commandWord = command2.getCommandWord();

                    }
                    if (commandWord == CommandWord.YES) //if input is yes start the fight
                    {
                        wantsToQuit = currentRoom.startFight(protagonist);
                    } else if (commandWord == CommandWord.NO) //if no ignore
                    {
                        System.out.println("You ignored the enemy");
                    }
                }

                if (currentRoom.isEventTrigger()) //if the room has an event ready to activate run the event
                {
                    currentRoom.event();
                }

                System.out.println(currentRoom.getEndString()); //prints the end string (items and exits)
                return wantsToQuit;
            } else {
                victory();
                return true;
            }
        }
        return false;
    }

    private void victory() {
        int calculateScore = score.calculateScore(new Date().getTime(), this.protagonist);
        TextFormater output = new TextFormater(70, '#', '-');
        output.setBothPrint(new String[]{"You feel the burning hot sun on your forehead", "", "You used " + (Score.getRest() + 1) + " days", "Your total score is: " + calculateScore, ""}, new String[]{"Congratulations you escaped the cave!"});
        Choose.choose();
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            System.out.println("Thank you for playing. Goodbye.");
            return true;
        }
    }
}
