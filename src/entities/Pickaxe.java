/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author corga
 */
public class Pickaxe extends KeyItem {

    KeyItem binder;
    KeyItem handle;
    Material head;

    public Pickaxe(String name, int id, String description, double value, KeyItem binder, KeyItem handle, Material head) {
        super(name, id, description, value);
        this.binder = binder;
        this.handle = handle;
        this.head = head;
    }

    @Override
    public int getScoreValue() {
        return (int) (250 * 3);
    }
    
    @Override
    public String use(fightsystem.Character protagonist) {
        //Todo
        return ("Pickaxe");
    }
}
