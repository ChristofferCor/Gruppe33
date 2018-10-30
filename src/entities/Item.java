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
public abstract class Item {

    private String name;
    private int id;
    private String description;

    public Item(String name, int id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
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
    
    @Override
    public String toString() {
        return name;
    }
    
    public void use() {
        System.out.println("The item can't be used");
    }

}
