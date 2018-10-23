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
public class Attacks {
    private double dmg;
    private int castTime;
    private String name;
    
    public Attacks(){
        System.out.println("NOT VALID ATTACK DECLARATION");
    }
    
    public Attacks(String name, double dmg, int castTime){
        this.dmg = dmg;
        this.castTime = castTime;
        this.name = name;
    }

    /**
     * @return the dmg
     */
    public double getDmg() {
        return dmg;
    }

    /**
     * @return the castTime
     */
    public int getCastTime() {
        return castTime;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    
}
