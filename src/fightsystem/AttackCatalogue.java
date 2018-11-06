/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fightsystem;

import java.util.ArrayList;

/**
 * This is the AttackCatalogue
 * This class is essential an storage of all the avaliable attacks in the game.
 * It serves as a point for creating new attacks.
 * @author Simon Holland Flarup
 */
public class AttackCatalogue {

    public static ArrayList<Attack> Catalogue = new ArrayList<Attack>();

    /**
     * The no-arg constructor only invokes a single method that creates all the attacks in the game
     */
    public AttackCatalogue() {
        initiateAttacks();
    }

    private void initiateAttacks() {
        Catalogue.add(new Attack("hit", 2, 100, 5000)); //Default attack, only added if error
        Catalogue.add(new Attack("hack", 10, 70, 3000));
        Catalogue.add(new Attack("slice", 5, 95, 2500));
        Catalogue.add(new Attack("chop", 7.5, 80, 4000));
        Catalogue.add(new Attack("slap", 15, 50, 2000));
        Catalogue.add(new Attack("stomp", 8, 85, 3500));
        Catalogue.add(new Attack("kick-ass", 5, 100, 3000));
    }
    
    /**
     * This method searches through the Catalogue for the attack with the given name.
     * @param name the name of the attack
     * @return the attack object if found, otherwise defaults to the Hit attack.
     */
    public Attack getAttack(String name){
        for(Attack obj : Catalogue){
            if (name.equals(obj.getName())) {
                return obj;
            }
        }
        return Catalogue.get(0);
    }
    /**
     * For testing purposes
     * @return a String (Testing purposes)
     */
    @Override
    public String toString(){
        String printString = "[";
        for (Attack obj : Catalogue) {
            printString += obj.getName() + ", ";
        }
        printString = printString.substring(0, (printString.length() - 2));
        printString += "]";
        return printString;
    }
}
