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
public class Materials extends Item {

    private int scoreValue;

    public Materials(String name, int id, String description, double value, int scoreValue) {
        super(name, id, description, value);
        this.scoreValue = scoreValue;
    }

    @Override
    public int getScoreValue() {
        return this.scoreValue;
    }
}
