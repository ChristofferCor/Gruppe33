/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import fightsystem.*;

/**
 *
 * @author sebastian
 */
public class Consumables extends Item {

    private double potency;

    public Consumables(String name, int id, String description, double value, double potency) {
        super(name, id, description, value);
        this.potency = potency;
    }

    /**
     * @return the potency
     */
    public double getPotency() {
        return potency;
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + this.getPotency();
    }
    
    @Override
    public void use(fightsystem.Character protagonist) {
        protagonist.setHp(protagonist.getHp() + this.potency);
        System.out.println("You drink the nasty potion and feel regenerated. Your HP is " + protagonist.getHp());
    }
}
