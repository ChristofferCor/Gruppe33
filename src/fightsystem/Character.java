/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fightsystem;

import entities.Item;
import java.util.ArrayList;
/**
 *
 * @author corga
 */
public class Character {
    private double hp, maxHp;
    private String name;
    private Attack[] attacks = new Attack[4]; //listed attacks for characters to 4
    private int reactionTime;
    private int strength;
    private double cash = 0;
    private ArrayList<Item> inventory = new ArrayList<>(); //Characters has an arraylist containing items.
    private String imagePath;

    /**
     *
     */
    public Character() {
        this(null, 1, 100, 100, "elf_m_idle_anim_f1");
    }
    /**
     *
     * @param hp
     */
    public Character(double hp) {
        this(null, hp, 100, 100, "elf_m_idle_anim_f1");
    }

    /**
     * This constructor is used to create new monsters The given hp is
     * automatically set to the mobs maxHp. If you need to circumsphere this you
     * need to set the HP of the maxHp and call .setHp() with the wished current
     * HP.
     *
     * @param name the name of the monster. eg. Wombat
     * @param hp the health of the monster. eg. 100
     * @param reactionTime the reaction time of the monster. This affects
     * attacks in multiple ways (See attack) The value 100 is default, below is
     * a faster reaction time (percentage) and vice versa.
     * @param strength the strength of the monster. This affects the damage of
     * attacks. the value 100 is default, below is less damage (percentage) and
     * vice versa
     */
    public Character(String name, double hp, int reactionTime, int strength, String imagePath) {
        this.hp = hp;
        this.maxHp = hp;
        this.name = name;
        this.reactionTime = reactionTime;
        this.strength = strength;
        this.imagePath = imagePath;
    }

    /**
     * @return the hp
     */
    public double getHp() {
        return hp;
    }

    /**
     * Sets the HP of the character. If value is higher than the internal maxHp,
     * it defaults to full HP.
     *
     * @param hp the hp to set
     */
    public void setHp(double hp) {
        if (hp <= this.maxHp) {
            this.hp = hp;
        } else {
            this.hp = this.maxHp;
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the attacks
     */
    public Attack[] getAttacks() {
        return attacks;
    }

    /**
     * @param attacks the attacks to set
     */
    public void setAttacks(Attack[] attacks) {
        this.attacks = attacks;
    }

    /**
     * @return the reactionTime
     */
    public int getReactionTime() {
        return reactionTime;
    }

    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }
    
    public void setImage(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void addToInventory(Item item) {
        this.inventory.add(item);
    }

    public void removeFromInventory(Item item) {
        this.inventory.remove(item);
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }
    
    public void setCash(double cash){
        this.cash = cash;
    }
    
    public double getCash(){
        return this.cash;
    }
    
    public String getImage(){
        return this.imagePath;
    }
    
        // Checks if the character has item
    public Item hasItem(String itemName) {
        for (Item i : inventory) {
            if (i.getName().equals(itemName)) {
                return i;
            }
        }
        return null;
    }
}
