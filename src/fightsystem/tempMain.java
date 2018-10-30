/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fightsystem;

/**
 *
 * @author corga
 */
public class tempMain {

    public static void main(String[] args) {
        AttackCatalogue browser = new AttackCatalogue();
        Character protagonist = new Character();
        Attack[] attack = {browser.getAttack("Hack"), browser.getAttack("Slice"), browser.getAttack("Chop")};
        protagonist.setAttacks(attack);
        Character wombat = new Character("Wombat", 25, 75, 50);
        wombat.setAttacks(attack);
        //protagonist.removeFromInventory(item);
        //System.out.println(protagonist.getInventory().toString());
        //protagonist.addToInventory(item);
        //System.out.println(protagonist.getInventory().toString());

        Fight fight = new Fight(protagonist, wombat);
        fight.fight();
    }
}
