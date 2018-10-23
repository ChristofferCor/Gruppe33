/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestreprojekt.fightsystem;

import java.util.ArrayList;

/**
 *
 * @author Simon Holland Flarup
 */
public class AttackCatalogue {

    private ArrayList<Attack> Catalogue = new ArrayList<Attack>();

    public AttackCatalogue() {
        initiateAttacks();
        //System.out.println(toString());
    }

    private void initiateAttacks() {
        Catalogue.add(new Attack("Hit", 2, 100, 5000)); //Default attack, only added if error
        Catalogue.add(new Attack("Hack", 10, 70, 3000));
        Catalogue.add(new Attack("Slice", 5, 95, 2500));
        Catalogue.add(new Attack("Chop", 7.5, 80, 4000));
        Catalogue.add(new Attack("Slap", 15, 50, 2000));
        Catalogue.add(new Attack("Stomp", 8, 85, 3500));
        Catalogue.add(new Attack("Kick-ass", 5, 100, 3000));
    }
    
    public Attack getAttack(String name){
        for(Attack obj : Catalogue){
            if (name.equals(obj.getName())) {
                return obj;
            }
        }
        return Catalogue.get(0);
    }
    
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
