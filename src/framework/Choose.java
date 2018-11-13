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
    private static String inputLine;
    public static void choose() {
        System.out.println("Type start, how to play(?), quit ");
        wordCheck();
        
        switch (inputLine) {
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
                + "\nYou will have to fight monsters to get the material needed to build a new pickaxe."
                + "\nGo through the cave system to find new mobs, items and treasure."
                + "\nTo win the game you will have to build a pickaxe and get out of the devastating cave system."
        );
        System.out.println("");
        choose();
    }
    public static void quit(){
        System.out.println("Bye, Bye ");
        System.out.println("Thank you for playing. Goodbye.");
    }
    
        public static void wordCheck() 
    {
        
        String word1 = null;
        String word2 = null;
          
        System.out.print("> "); 

        inputLine = myScanner.nextLine();

        Scanner choice = new Scanner(inputLine);
        if(choice.hasNext()) {
            word1 = choice.next();
            if(choice.hasNext()) {
                // Added nextLine to read the rest of the String instead of only the next word.
                //Trim is used to remove spaces in the beginning and end of the String.
                word2 = choice.nextLine().trim();
            }
        }

       
    }
}
