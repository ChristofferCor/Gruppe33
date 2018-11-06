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
public class Pickaxe extends KeyItem{
    private KeyItem binder;
    private KeyItem handle;
    private Material head;
    private Material[] extra;
    
    public Pickaxe(String name, int id, String description, double value, KeyItem binder, KeyItem handle, Material head) {
        super(name, id, description, value);
        this.binder = binder;
        this.handle = handle;
        this.head = head;
    }
    
    //Copy constructor (Improper copy method)
    public Pickaxe(Pickaxe other, String name, int id, String description, double value){
        this(name, id, description, value, other.binder, other.handle, other.head);
    }
    
    public void setExtra(Material[] extra){
        this.extra = extra;
    }
    
    @Override
    public int getScoreValue(){
        if (this.extra[0] != null) {
            return (int)(this.head.getScoreValue()*4.5);
        } else {
        return (int)(head.getScoreValue()*3);
        }
    }
    
}
