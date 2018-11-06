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
public class SuperPickaxe extends Pickaxe {
    
    private Material extra;
    
    //Copy constructor (Improper copy method)
    public SuperPickaxe(Pickaxe other, String name, int id, String description, double value, Material extra) {
        super(name, id, description, value, other.binder, other.handle, other.head);
        this.extra = extra;
    }
    
    @Override
    public int getScoreValue() {
        return (int) (250 * 4.5);
    }

}
