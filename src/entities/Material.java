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
public class Material extends Item {

    private int scoreValue;

    public Material(String name, int id, String description, double value, int scoreValue, int posX, int posY, String imgPath) {
        super(name, id, description, value, posX, posY, imgPath);
        this.scoreValue = scoreValue;
    }
}
