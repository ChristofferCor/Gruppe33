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
public abstract class Item {

    private String name;
    private int id;
    private String description;
    private double value;

    public Item(String name, int id, String description, double value) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.value = value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }
    
    public void setName(String name) {
        if (name != null) {
          this.name = name;   
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public void use(fightsystem.Character protagonist) {
        System.out.println("The item can't be used");
    }

    public boolean droppable() {
        return true;
    }

    public boolean sellable() {
        return true;
    }
    
    public int getScoreValue(){
        return (int)this.value;
    }

}
