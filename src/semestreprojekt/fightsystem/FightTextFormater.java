/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestreprojekt.fightsystem;

/**
 *
 * @author simon1
 *
 */
public class FightTextFormater extends TextFormater {

    private Character enemy, protagonist;

    public FightTextFormater(int width, char frame, char lid, Character protagonist, Character enemy) {
        super(width, frame, lid);
        this.enemy = enemy;
        this.protagonist = protagonist;
    }

    @Override
    public boolean print() {
        String[] str = {" You: " + this.protagonist.getHp() + " HP", "" + this.enemy.getName() + ": " + this.enemy.getHp() + " HP "};
        super.setHead(str);
        return super.print();
    }
    
    public boolean oldPrint() {
        return super.print();
    }

}
