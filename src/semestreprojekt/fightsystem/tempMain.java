/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestreprojekt.fightsystem;

/**
 *
 * @author corga
 */
public class tempMain {
    public static void main(String[] args) {
        Attacks hack = new Attacks("Hack", 5, 3000);
        Attacks slice = new Attacks("Slice", 7.5, 2500);
        Attacks chop = new Attacks("Chop", 2.5, 4000);
        System.out.println(hack.getDmg());
        System.out.println(slice.getName());
        System.out.println(chop.getCastTime());
        
        Characters protagonist = new Characters();
        System.out.println(protagonist.getHp());
        Attacks[] attack = {hack, slice, chop};
        protagonist.setAttacks(attack);
        Characters wombat = new Characters("Tobias", 25, 200);
        System.out.println(wombat.getName() + wombat.getHp() + wombat.getReactionTime());
        wombat.setAttacks(attack);
        
        FightInitiater fight = new FightInitiater(protagonist, wombat);
        fight.fight();
        /*Characters wombat2 = new Characters("Tobias", 2, 75);
        FightInitiater fight2 = new FightInitiater(protagonist, wombat2);
        fight2.fight(); */
    }
}
