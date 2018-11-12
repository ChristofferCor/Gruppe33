/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import fightsystem.Attack;
import fightsystem.AttackCatalogue;
import java.util.Scanner;

/**
 *
 * @author corga
 */
public class Choose {
    private static Scanner myScanner = new Scanner(System.in);   
    
    public static void choose() {
        System.out.println("Type start, how to play(h2p), quit ");
        String choice = myScanner.next();
        
        switch (choice) {
            case "start":
                AttackCatalogue browser = new AttackCatalogue();
                fightsystem.Character protagonist = new fightsystem.Character();
                Attack[] attack = {browser.getAttack("slap"), browser.getAttack("slice"), browser.getAttack("chop")};
                protagonist.setAttacks(attack);
                Game game = new Game(protagonist, browser);
                game.play();
                break;
            case "h2p":
                h2p();
                break;
            case "quit":
                quit();
                break;
            default:
                System.out.println(" What you typed is not valid ");
                choose();
                break;
        }
    }
    
    public static void h2p(){
        System.out.println("");
        choose();
    }
    public static void quit(){
        System.out.println("Thank you for playing. Goodbye.");
    }
}
