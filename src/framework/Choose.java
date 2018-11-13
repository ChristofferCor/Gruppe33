/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import fightsystem.Attack;
import fightsystem.AttackCatalogue;
import static java.lang.Compiler.command;
import java.util.Scanner;

/**
 *
 * @author corga
 */
public class Choose {
    private static Scanner myScanner = new Scanner(System.in);   
    
    public static void choose() {
        System.out.println("Type start, how to play(?), quit ");
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
            case "?":
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
        System.out.println(
                "The ultimate goal of this game is for you to have fun."
                + "\nTo play this game you need to be fast at typing."
                + "\nYou will have to fight monsters to get the matrial needed to build a new pickaxe."
                + "\nGo through the cave system to find new mobs, items and treasure."
                + "\nTo end the game you wil have to build a pickaxe and get out of the devastating cave system"
        );
        System.out.println("");
        choose();
    }
    public static void quit(){
        System.out.println("Bye, Bye ");
        System.out.println("Thank you for playing. Goodbye.");
    }
}
