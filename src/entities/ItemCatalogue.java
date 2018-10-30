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
    public static ArrayList<Item> Catalogue;
    
    public ItemCatalogue() {
        initiateItems();
    }
    
    private void initiateItems(){
        Catalogue.add(new KeyItem1("Nøgle", 1, "Dette er en ubrugelig nøgle")); //Test item
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
