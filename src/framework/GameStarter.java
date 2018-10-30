/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import fightsystem.Attack;
import fightsystem.AttackCatalogue;

/**
 *
 * @author sebastian
 */
public class GameStarter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AttackCatalogue browser = new AttackCatalogue();
        
        fightsystem.Character protagonist = new fightsystem.Character();
        Attack[] attack = {browser.getAttack("Hack"), browser.getAttack("Slice"), browser.getAttack("Chop")};
        protagonist.setAttacks(attack);
        
        Game game = new Game(protagonist, browser);
        game.play();
    }
    
}
