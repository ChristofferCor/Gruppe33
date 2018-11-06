package framework;

import fightsystem.*;
import entities.Item;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import entities.ItemCatalogue;

public class Room {

    private boolean firstTime = false; // Attribute for first time event
    private String description; //Description for the room
    private HashMap<String, Room> exits; // all of the exits in a hashmap
    private int visitCounter = 0; // Attribute for visit counter
    private int firstTimeID = 0; // Attribute for firstTimeID
    private ArrayList<Item> items = new ArrayList<>(); //used for items placed in the rooms.
    private fightsystem.Character enemy = null; //Varibale that holds an enemy
    private int roomID;

    private boolean eventTrigger = false; //checks of the even is ready to be activated
    private int eventNumber = 0; //checks what event to activate

    private Room eventRoom; //A room this room can interact with
    private Room eventRoom2; //Another room this rom can interact with

    // Constructor for Room with 4 variables (used for rooms with a first time event)
    public Room(String description, int roomID, boolean firstTime, int firstTimeID) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.firstTime = firstTime;
        this.firstTimeID = firstTimeID;
        this.roomID = roomID;
    }

    //Constructor for room with 6 variables used for rooms with an event and/or a first time event
    public Room(String description, int roomID, boolean firstTime, int firstTimeID, boolean eventTrigger, int eventNumber) {
        this.description = description;
        this.roomID = roomID;
        exits = new HashMap<String, Room>();
        this.firstTime = firstTime;
        this.firstTimeID = firstTimeID;
        this.eventTrigger = eventTrigger;
        this.eventNumber = eventNumber;

    }

    //Constrictor with 1 varibale used for normal rooms
    public Room(String description, int roomID) {
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

    public void startFight(fightsystem.Character protagonist) {
        Fight fight = new Fight(protagonist, this.enemy);
        if (fight.fight() == 2) {
            for (Item i : this.enemy.getInventory()) {
                protagonist.addToInventory(i);
                System.out.println("You take " + i + " from the enemy.");
            }
        }
    }

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
            if (i.getName().equals(itemName)) {
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

    private String getExitString() {
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

    // Method for first time event in select rooms. Checks the firstTimeID for the rooms, that have a first time event.
    public void firstTimeEvent(fightsystem.Character protagonist, ItemCatalogue catalogue) {
        if (this.firstTimeID == 1) {
            System.out.println("The entrance behind you caved in. Your pickaxe broke too.");
            System.out.println("'Shit happens' you think for yourself");
            System.out.println("You gotta look around in the mine for materials for a new pickaxe");
        } else if (this.firstTimeID == 2) {
            System.out.println("ouch! You stepped on a trap. You take 15 damage. The trap broke after one use, how lucky for you.");
            protagonist.setHp(protagonist.getHp() - 15);
            System.out.println("Your current HP is " + protagonist.getHp());
        } else if (this.firstTimeID == 3) {
            System.out.println("You hear a rumble through the corridors. You figure it must come from the southern part of the mine");
            this.eventRoom.setEventTrigger(true);
            this.eventRoom.setItems(catalogue.getItem(300));
        }
    }

    public void event() {
        if (this.eventNumber == 1) {
            System.out.println("This works");
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
            returnString += " " + i.getName();
        }
        return returnString;
    }

}
