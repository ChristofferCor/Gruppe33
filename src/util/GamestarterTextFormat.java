/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import fightsystem.Attack;
import fightsystem.AttackCatalogue;
import framework.Game;
import java.util.Scanner;

/**
 *
 * @author corga
 */
public class GamestarterTextFormat {
private Scanner myScanner = new Scanner(System.in);   
    
    public void choose() {
        System.out.println("Type start, how to play, quit ");
        String choice = myScanner.next();
        
        if (choice.equals("start")) {
            AttackCatalogue browser = new AttackCatalogue();
        
            fightsystem.Character protagonist = new fightsystem.Character();
            Attack[] attack = {browser.getAttack("slap"), browser.getAttack("slice"), browser.getAttack("chop")};
            protagonist.setAttacks(attack);
        
            Game game = new Game(protagonist, browser);
            game.play();
        } else if (choice.equals("how to play")) {
            
        } else if (choice.equals("quit")) {
            
        }else{
            System.out.println(" What you typed is not valid ");
            choose();
        }
    }
}
