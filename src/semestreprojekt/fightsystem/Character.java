/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestreprojekt.fightsystem;

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
    //private ArrayList<Items> = new ArrayList<Items>(); //Characters has an arraylist containing items.

    public Character(){
        this(null, 100, 100, 100);
    }
    
    public Character(double hp){
        this(null, hp, 100, 100);
    }
    
    public Character(String name, double hp, int reactionTime, int strength){
        this.hp = hp;
        this.maxHp = hp;
        this.name = name;
        this.reactionTime = reactionTime;
        this.strength = strength;
    }

    /**
     * @return the hp
     */
    public double getHp() {
        return hp;
    }

    /**
     * @param hp the hp to set
     */
    public void setHp(double hp) {
        this.hp = hp;
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

    /**
     * @param strength the strength to set
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }
    
    
}
