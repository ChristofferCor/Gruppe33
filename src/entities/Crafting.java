/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import fightsystem.Character;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author corga
 */
public class Crafting {

    private Character protagonist;
    private Scanner input;

    public Crafting(Character protagonist) {
        this.protagonist = protagonist;
    }

    public boolean craftPickaxe() {
        ArrayList<Item> craftables = protagonist.getInventory();
        if (craftables.contains(ItemCatalogue.getItem(1)) && craftables.contains(ItemCatalogue.getItem(2)) && craftables.contains(ItemCatalogue.getItem(300))) {
            input = new Scanner(System.in);
            while (true) {
                System.out.println("Do you want to craft a new pickaxe? (Yes/Abort)");
                String answer = input.next();
                if (answer.equalsIgnoreCase("Yes")) {
                    protagonist.removeFromInventory(ItemCatalogue.getItem(1));
                    protagonist.removeFromInventory(ItemCatalogue.getItem(2));
                    protagonist.removeFromInventory(ItemCatalogue.getItem(300));
                    protagonist.addToInventory(ItemCatalogue.getItem(1000));
                    System.out.println("You crafted a shiny new " + ItemCatalogue.getItem(1000).getName());
                    return true;
                } else if (answer.equalsIgnoreCase("Abort")) {
                    return false;
                } else {
                    System.out.println("Could not understand what you wanted to do.");
                }
            }
        } else {
            System.out.println("You don't have enough materials, gather more!");
            return false;
        }
    }

    public boolean craftAddExtra() {
        input = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to enchance your Pickaxe, or to craft a new one? (Yes/New/Abort)");
            String answer = input.next();
            if (answer.equalsIgnoreCase("Yes")) {
                break;
            } else if (answer.equalsIgnoreCase("New")) {
                return this.craftPickaxe();
            } else if (answer.equalsIgnoreCase("Abort")) {
                return false;
            } else {
                System.out.println("Could not understand what you wanted to do.");
            }
        }

        int itemCount = 0;
        Item extra = null;
        for (Item item : protagonist.getInventory()) {
            if (item.getId() == 300) {
                extra = item;
                itemCount++;
            }
        }
        if (itemCount >= 2) {
            input = new Scanner(System.in);
            while (true) {
                System.out.println("Do you want to add " + extra.getName() + " to your Pickaxe (Yes/Abort)");
                String answer = input.next();
                if (answer.equalsIgnoreCase("Yes")) {
                    for (int i = 0; i < 2; i++) {
                        protagonist.removeFromInventory(extra);
                    }
                    protagonist.removeFromInventory(ItemCatalogue.getItem(1000));
                    protagonist.addToInventory(ItemCatalogue.getItem(1001));
                    System.out.println("You crafted a shiny new " + ItemCatalogue.getItem(1001).getName());
                    return true;
                } else if (answer.equalsIgnoreCase("Abort")) {
                    return false;
                } else {
                    System.out.println("Could not understand what you wanted to do.");
                }
            }
        } else {
            System.out.println("You don't have enough materials, gather more!");
            return false;
        }
    }
}
