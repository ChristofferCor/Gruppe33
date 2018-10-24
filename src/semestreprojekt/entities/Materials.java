/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestreprojekt.entities;

/**
 *
 * @author sebastian
 */
public class Materials extends Item {

    private double value;

    public Materials(String name, int id, String description, double value) {
        super(name, id, description);
        this.value = value;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }
}
