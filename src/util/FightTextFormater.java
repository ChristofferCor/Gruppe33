/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import fightsystem.Character;

/**
 * This is a FightTextFormater for the CLI text output
 * It extends the functionality of the TextFormater with custom code for the display of HP in fight.
 * It also includes a way of invoking the old print() method from the super class.
 * @author Simon Holland Flarup
 *
 */
public class FightTextFormater extends TextFormater {

    private Character enemy, protagonist;

    /**
     * This constructor initializes the FightTextFormater with variables describing the form of the box
     * It also stores the current fights involved parties for easier display of the HP in the header.
     * @param width the required with of the box (May be overwritten)
     * @param frame the character wanted as the sides
     * @param lid the character wanted as the top and bottom of the box
     * @param protagonist the character object of the player
     * @param enemy the character object of the enemy
     */
    public FightTextFormater(int width, char frame, char lid, Character protagonist, Character enemy) {
        super(width, frame, lid);
        this.enemy = enemy;
        this.protagonist = protagonist;
    }
    
    /**
     * This method invokes the printing of the box
     * Use this method only when you have specified an body
     * @return returns true if print succeeded, returns false if not.
     */
    @Override
    public boolean print() {
        String[] str = {" You: " + this.protagonist.getHp() + " HP", "" + this.enemy.getName() + ": " + this.enemy.getHp() + " HP "};
        super.setHead(str);
        return super.print();
    }
    
    /**
     * This method invokes the printing of the box from the TextFormater (super) class 
     * Use this method only when you have specified an body
     * @return returns true if print succeeded, returns false if not.
     */
    public boolean oldPrint() {
        return super.print();
    }

}
