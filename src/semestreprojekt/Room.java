package semestreprojekt;

import fightsystem.*;
import entities.Item;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room 
{
    private boolean firstTime = false; // Attribute for first time event
    private String description;
    private HashMap<String, Room> exits;
    private int visitCounter = 0; // Attribute for visit counter
    private int id = 0; // Attribute for id
    private ArrayList<Item> items = new ArrayList<>(); //used for items placed in the rooms.
    private fightsystem.Character enemy = null;

    // Constructor for Room with 3 variables (used for rooms with a first time event)
    public Room(String description, boolean firstTime, int id) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.firstTime = firstTime;
        this.id = id;
    }
    
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public boolean isEnemy()
    {
        if(this.enemy == null)
        {
            return false;
        }else
        {
            return true;
        }
    }
    
    public void startFight(fightsystem.Character protagonist)
    {
        Fight fight = new Fight(protagonist, this.enemy);
        fight.fight();
    }
            
    
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    // adds item to room.
    public void setItems(Item newItem) {
        items.add(newItem);
    }

    public void setEnemy(fightsystem.Character enemy)
    {
        this.enemy = enemy;
    }
    
    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return description + ".\n" + getExitString() + "\n" + getItemList();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
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
    
    // Method for first time event in select rooms. Checks the id for the rooms, that have a first time event.
    public void firstTimeEvent() {
        if (this.id == 1) {
            System.out.println("The entrance behind you caved in.");
        } else if (this.id == 2) {
            System.out.println("You stepped on a trap. You take 3 damage.");
        }
    }
    
    // Checks if the room has a first time event.
    public boolean isFirstTimeEvent() {
        return firstTime;
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

