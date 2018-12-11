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
        Catalogue.add(new KeyItem("Iron Handle", 1, "Casted Iron, used for tool crafting", 30));
        Catalogue.add(new KeyItem("Iron Binder", 2, "Casted Iron, used for tool crafting", 25));
        Catalogue.add(new KeyItem("Tool Schematics", 3, "A todler drew this schematic of an Pickaxe, how useful!", 0));
        //Miscs
        Catalogue.add(new Misc("Gold Watch", 100, "Someone dropped this, now it's yours!", 48, 5, 3, "../Resources/coin_anim_f3.png"));
        Catalogue.add(new Misc("Platinum Ring", 101, "Someone dropped this, now it's yours!", 72, 4, 3, "../Resources/coin_anim_f1.png"));
        //Consumables
        Catalogue.add(new Consumables("Health Potion", 200, "Eww! It looks like blood, wonder if I should drink it!", 10, 50, 5, 3, "../Resources/flask_big_red.png"));
        //Materials
        Catalogue.add(new Material("Iron", 300, "Valuable raw material, used for tool crafting", 24, 250, 5, 3, "../Resources/coin_anim_f1.png"));
        Catalogue.add(new Material("Iron", 301, "Valuable raw material, used for tool crafting", 24, 250, 3, 1, "../Resources/coin_anim_f1.png"));
        //Pickaxes
        Catalogue.add(new Pickaxe("Simple Iron Pickaxe", 1000, "Pickaxe of high quality, your inner voice tells you to use it on the cave in!", 100, (KeyItem)this.getItem(2), (KeyItem)this.getItem(1), (Material)this.getItem(300)));
        Catalogue.add(new SuperPickaxe((Pickaxe)this.getItem(1000), "Strong Iron Pickaxe", 1001, "Pickaxe of really high quality, it mines through rocks like a warm knife through butter!", 250, (Material)this.getItem(300)));
    }
    
    public static Item getItem(int id){
        for(Item obj : Catalogue){
            if (id == obj.getId()) {
                return obj;
            }
        }
        return null;
    }
}
