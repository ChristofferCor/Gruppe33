/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestreprojekt;

/**
 *
 * @author sebastian
 */
public class DefenseItem extends Item {

    private double value;
    private double potency;

    public DefenseItem(String name, int id, String description, double value, double potency) {
        super(name, id, description);
        this.value = value;
        this.potency = potency;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
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
    public void use() {
        // HP += potency
        // Delete item after use
    }
}
