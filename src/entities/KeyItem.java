/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author sebastian
 */
public class KeyItem extends Item {

    public KeyItem(String name, int id, String description, double value) {
        super(name, id, description, value);
    }

    @Override
    public boolean droppable() {
        return false;
    }

    @Override
    public boolean sellable() {
        return super.getValue() != 0;
    }
}
