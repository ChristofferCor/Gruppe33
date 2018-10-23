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
public class Characters {
    private double hp, maxHp;
    private String name;
    private Attacks[] attacks = new Attacks[4]; //listed attacks for characters to 4
    private int reactionTime;
    private int strength; // Not used yet
    //Characters has an arraylist containing items.

    public Characters(){
        this(null, 100, 100);
    }
    
    public Characters(double hp){
        this(null, hp, 100);
    }
    
    public Characters(String name, double hp, int reactionTime){
        this.hp = hp;
        this.maxHp = hp;
        this.name = name;
        this.reactionTime = reactionTime;
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
    public Attacks[] getAttacks() {
        return attacks;
    }

    /**
     * @param attacks the attacks to set
     */
    public void setAttacks(Attacks[] attacks) {
        this.attacks = attacks;
    }

    /**
     * @return the reactionTime
     */
    public int getReactionTime() {
        return reactionTime;
    }
    
    
}
