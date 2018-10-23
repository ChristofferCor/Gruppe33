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
public class Attack {

    private double dmg;
    private int castTime;
    private String name;
    private int accuracy;

    public Attack() {
        System.out.println("NOT VALID ATTACK DECLARATION");
    }

    public Attack(String name, double dmg, int accuracy, int castTime) {
        this.dmg = dmg;
        this.castTime = castTime;
        this.name = name;
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
