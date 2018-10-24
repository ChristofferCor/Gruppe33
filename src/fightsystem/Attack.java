/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fightsystem;

/**
 * Import about accuracy
 * Accuracy is currently implemented in two ways. One for the protagonist, and another for NPCs
 * For the player the accuracy determines the chance of hitting with a given attack, but you also get a bonus accuracy for speedtyping faster than the castTime (1 second faster equals 10 accuracy)
 * The above mechanism can be used to remove the timed check, and instead give a accuracy penalty if you use more time than the attacks castTime. (1 second slower equal -10 accuracy)
 * 
 * For NPC's the accuracy determines the chance of hitting with a given attack, but it's also depending on the NPC's reaction time.
 * This means that monsters with lower reaction time (This is better) will have a greater chance of NOT missing an attack.
 * The reaction time is bound to a Math.random() and simply just adjusts it according to the reactionTime converted to percentage. 
 * (50 reactionTime and a random value of 0.5 gives an random value of 0.25. This means that for that current turn the attack must have an accuracy above 25 to succeed.)
 * @author corga & Simon Holland Flarup
 */
public class Attack {

    private double dmg;
    private int castTime;
    private String name;
    private int accuracy;

    public Attack() {
        System.out.println("NOT VALID ATTACK DECLARATION");
    }
    
    
   /**
    * This constructor is used to create a new attack
    * @param name the name of the attack. Eg. Hack
    * @param dmg the damage of the attack (Limited to one decimal precision)
    * @param accuracy the accuracy of the attack supplied in percentage from 0 to 100.
    * @param castTime the cast time of the attack, meaning the maximum time it must take to complete the attack (Speedtyping it)
    */
    public Attack(String name, double dmg, int accuracy, int castTime) {
        this.dmg = dmg;
        this.castTime = castTime;
        this.name = name;
        this.accuracy = accuracy;
    }

    /**
     * @return the dmg
     */
    public double getDmg() {
        return this.dmg;
    }

    /**
     * @return the castTime
     */
    public int getCastTime() {
        return this.castTime;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the accuracy
     */
    public int getAccuracy() {
        return this.accuracy;
    }

}
