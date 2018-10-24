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
        //System.out.println(protagonist.getHp());
        Attack[] attack = {browser.getAttack("Hack"), browser.getAttack("Slice"), browser.getAttack("Chop")};
        protagonist.setAttacks(attack);
        Character wombat = new Character("Wombat", 25, 150, 50);
        //System.out.println(wombat.getName() + wombat.getHp() + wombat.getReactionTime());
        wombat.setAttacks(attack);
        
        Fight fight = new Fight(protagonist, wombat);
        fight.fight();
        /*Characters wombat2 = new Characters("Tobias", 2, 75);
        FightInitiater fight2 = new FightInitiater(protagonist, wombat2);
        fight2.fight(); */
    }
}
