/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author Simon Holland Flarup
 */
public class ItemCatalogue {
    public static ArrayList<Item> Catalogue = new ArrayList<>();
    
    public ItemCatalogue() {
        initiateItems();
    }
    
    private void initiateItems(){
        //KeyItems (Quest items, not to be dropped)
        Catalogue.add(new KeyItem("Deformed Key", 0, "This is your housekey, it really is useless for you, right now", 0)); //Test item
        Catalogue.add(new KeyItem("Iron Pickaxe", 1, "Pickaxe of high quality, your inner voice tells you to use it on the cave in!", 100));
        Catalogue.add(new KeyItem("Strong Iron Pickaxe", 2, "Pickaxe of really high quality, it mines through rocks like a warm knife through butter!", 0));
        Catalogue.add(new KeyItem("Iron Handle", 3, "Casted Iron, used for tool crafting", 30));
        Catalogue.add(new KeyItem("Iron Binder", 4, "Casted Iron, used for tool crafting", 25));
        Catalogue.add(new KeyItem("Tool Schematics", 5, "A todler drew this schematic of an Pickaxe, how useful!", 0));
        //Miscs
        Catalogue.add(new Misc("Gold Watch", 100, "Someone dropped this, now it's yours!", 48));
        Catalogue.add(new Misc("Platinum Ring", 101, "Someone dropped this, now it's yours!", 72));
        //Consumables
        Catalogue.add(new Consumables("Health Potion", 200, "Eww! It looks like blood, wonder if I should drik it!", 10, 50 ));
        //Materials
        Catalogue.add(new Materials("Iron", 300, "Valuable raw material, used for tool crafting", 24, 250));
    }
    
    public Item getItem(int id){
        for(Item obj : Catalogue){
            if (id == obj.getId()) {
                return obj;
            }
        }
        return null;
    }
}
