package framework;

import fightsystem.*;
import entities.Item;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import entities.ItemCatalogue;
import gui.FXMLRooms;
import gui.GameWindowsController;

public class Room implements FXMLRooms {

    private boolean firstTime = false; // Attribute for first time event
    private String description; //Description for the room
    private HashMap<String, Room> exits; // all of the exits in a hashmap
    private int visitCounter = 0; // Attribute for visit counter
    private int firstTimeID = 0; // Attribute for firstTimeID
    private ArrayList<Item> items = new ArrayList<>(); //used for items placed in the rooms.
    private fightsystem.Character enemy = null; //Variable that holds an enemy
    private int roomID;
    private String FXMLPath;
    private GameWindowsController controller;

    private boolean eventTrigger = false; //checks of the even is ready to be activated
    private int eventNumber = 0; //checks what event to activate

    private Room eventRoom; //A room this room can interact with
    private Room eventRoom2; //Another room this room can interact with

    // Constructor for Room with 4 variables (used for rooms with a first time event)
    public Room(String description, int roomID, boolean firstTime, int firstTimeID, String FXMLPath) {
        this(description, roomID, FXMLPath);
        exits = new HashMap<String, Room>();
        this.firstTime = firstTime;
        this.firstTimeID = firstTimeID;
    }

    //Constructor for room with 6 variables used for rooms with an event and/or a first time event
    public Room(String description, int roomID, boolean firstTime, int firstTimeID, boolean eventTrigger, int eventNumber, String FXMLPath) {
        this(description, roomID, firstTime, firstTimeID, FXMLPath);
        exits = new HashMap<String, Room>();
        this.eventTrigger = eventTrigger;
        this.eventNumber = eventNumber;

    }

    //Constrictor with 1 varibale used for normal rooms
    public Room(String description, int roomID, String FXMLPath) {
        this.FXMLPath = FXMLPath;
        this.description = description;
        this.roomID = roomID;
        exits = new HashMap<String, Room>();
    }

    public boolean isEnemy() {
        if (this.enemy == null) {
            return false;
        } else {
            return true;
        }
    }

    /*public boolean startFight(fightsystem.Character protagonist) {
        Fight fight = new Fight(protagonist, this.enemy);
        int fightStatus = fight.fight();
        if (fightStatus == 2) {
            for (Item i : this.enemy.getInventory()) {
                protagonist.addToInventory(i);
                System.out.println("You take " + i + " from the enemy.");
            }
        } else if (fightStatus == 1) {
            return true;
        }
        return false;
    }*/
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    // adds item to room.
    public void setItems(Item newItem) {
        items.add(newItem);
    }

    // Removes items from room
    public void removeItems(Item newItem) {
        items.remove(newItem);
    }

    // Checks if the room has item
    public Item hasItem(String itemName) {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                return i;
            }
        }
        return null;
    }

    public void setEnemy(fightsystem.Character enemy) {
        this.enemy = enemy;
    }

    //Sets 1 room as an eventroom
    public void setEventRoom(Room eventRoom) {
        this.eventRoom = eventRoom;
    }

    //Sets 2 rooms as eventrooms
    public void setEventRoom(Room eventRoom, Room eventRoom2) {
        this.eventRoom = eventRoom;
        this.eventRoom2 = eventRoom2;
    }

    public void setEventTrigger(boolean trigger) {
        this.eventTrigger = trigger;
    }

    public int getRoomID() {
        return this.roomID;
    }

    public String getStartDescription() {
        return description;
    }

    public String getEndString() {
        return this.getItemList() + "\n" + getExitString();
    }

    public String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public fightsystem.Character getEnemy() {
        return this.enemy;
    }

    // Added an visit counter to the rooms
    public void visitCounterPlus() {
        visitCounter++;
    }

    // Added a get method for the visitcounter
    public int getVisitCounter() {
        return visitCounter;
    }

    //Gets the name of the enemy in the room
    public String getEnemyName() {
        return enemy.getName();
    }

    @Override
    public String getFXMLPath() {
        return this.FXMLPath;
    }

    @Override
    public void setController(GameWindowsController controller) {
        this.controller = controller;
    }

    public GameWindowsController getController() {
        return this.controller;
    }

    // Method for first time event in select rooms. Checks the firstTimeID for the rooms, that have a first time event.
    public void firstTimeEvent(fightsystem.Character protagonist) {
        if (this.firstTimeID == 1) {
            System.out.println("The entrance behind you caved in. Your pickaxe broke too.");
            System.out.println("'Shit happens' you think for yourself");
            System.out.println("You gotta look around in the mine for materials for a new pickaxe");
        } else if (this.firstTimeID == 2) {
            //System.out.println("ouch! You stepped on a trap. You take 15 damage. The trap broke after one use, how lucky for you.");
            //protagonist.setHp(protagonist.getHp() - 15);
            //System.out.println("Your current HP is " + protagonist.getHp());
        } else if (this.firstTimeID == 3) {
            System.out.println("You hear a rumble through the corridors. You figure it must come from the southern part of the mine");
            this.eventRoom.setEventTrigger(true);
            this.eventRoom.setItems(ItemCatalogue.getItem(300));
        }
    }

    public void event() {
        if (this.eventNumber == 1) {
            this.eventTrigger = false;
        }
    }

    // Checks if the room has a first time event.
    public boolean isFirstTimeEvent() {
        return firstTime;
    }

    public boolean isEventTrigger() {
        return this.eventTrigger;
    }

    // Method for displaying items in the room.
    public String getItemList() {
        String returnString = "Items in room: ";
        for (Item i : items) {
            returnString += "" + i.getName();
        }
        return returnString;
    }

    @Override
    public int[] getItemPos(int index) {
        if (!items.isEmpty()) {
            return items.get(index).getPosition();
        } else {
            return null;
        }

    }

    @Override
    public String getItemImage(int index) {
        return items.get(index).getImgPath();
    }

    public Item collectItem(int posX, int posY) {
        for (Item item : this.items) {
            if (item.getPosition()[0] == posX && item.getPosition()[1] == posY) {
                this.removeItems(item);
                return item;
            }
        }
        return null;
    }

}
