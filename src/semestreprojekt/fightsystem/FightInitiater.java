package semestreprojekt.fightsystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
import java.util.Date;

/**
 *
 * @author Simon Holland Flarup
 */
public class FightInitiater {

    //public double health = 100;
    //public int monsterId;
    //public double monsterHealth = 10;
    private int status;
    private int numAtk = 0;
    private final Characters monster, player;
    private Scanner speedTyper;

    /**
     * The FightInitiater needs to start a fight with a given monsterId. The
     * monster could be an object in the monster package. OBS no monsters are
     * implemented in this prototype
     *
     * @param player
     * @param monster takes a Character object
     */
    public FightInitiater(Characters player, Characters monster) {
        this.monster = monster;
        this.player = player;
        this.status = 0;

        for (Attacks atk : this.monster.getAttacks()) {
            if (atk != null) {
                this.numAtk++;
            }
        }

    }

    /**
     * This public method manages the fight, initialized with the constructor.
     *
     * @return Returns an integer with a value corresponding to a status id. 0 =
     * running, 1 = dead, 2 = won
     */
    public int fight() {
        String[] array = {"You encountered a " + monster.getName() + "!"};
        CenterText str1 = new CenterText(array, this.monster.getName(), this.player.getHp(), this.monster.getHp());
        sleep(4);
        if (Math.random() <= 0.5) {
            while (this.status == 0) {
                //displayStats();
                yourTurn();
                sleep(3);
                if (this.status != 0) {
                    break;
                }
                theirTurn();
                sleep(3);
            }
        } else {
            while (this.status == 0) {
                //displayStats();
                theirTurn();
                sleep(3);
                if (this.status != 0) {
                    break;
                }
                yourTurn();
                sleep(3);
            }
        }
        return status;
    }

    private void yourTurn() {

        String tempString = "";
        for (Attacks atk : this.player.getAttacks()) {
            if (atk != null) {
                tempString += atk.getName();
                tempString += "    ";
            }
        }
        tempString = tempString.trim();

        String[] array = {"This is your chance, act quickly!", "", "Avaliable attacks:    " + tempString};
        CenterText str1 = new CenterText(array, this.monster.getName(), this.player.getHp(), this.monster.getHp());
        System.out.print("Enter attack: ");
        speedTyper = new Scanner(System.in);
        Date currentTime = new Date();
        long startTime = currentTime.getTime();
        String input = speedTyper.next();
        System.out.println("");
        currentTime = new Date();
        checkAttack(input, startTime, currentTime.getTime());
    }

    private void takeDamage(double dmg) {
        double health = this.player.getHp(); //See dealDamage()
        health -= dmg;
        health = (int) (health * 10);
        health = ((double) health) / 10;
        this.player.setHp(health);
        if (this.player.getHp() <= 0) { //See dealDamage(). Status '1' is defeat.
            this.player.setHp(0);
            this.status = 1;
        }
    }

    private void dealDamage(double dmg) {
        double health = this.monster.getHp();
        health -= dmg; //These extra assignments of the variable is used to remove truncating errors, eg: 1.200000003 -> 1.2
        health = (int) (health * 10);
        health = ((double) health) / 10;
        this.monster.setHp(health);
        if (health <= 0) { //Checks if the monsters HP fell below or hit zero. If so the status is changed to '2' which means victory.
            this.monster.setHp(0);
            this.status = 2;
        }
    }

    private void theirTurn() {
        int rng = (int) (Math.random() * 100);
        int atkNumber = (int) (rng / (100d / this.numAtk));
        double damage = this.monster.getAttacks()[atkNumber].getDmg();
        takeDamage(damage);
        String[] array = {"The " + this.monster.getName() + " uses " + this.monster.getAttacks()[atkNumber].getName() + " against you and dealt " + damage + " DMG"};
        CenterText str1 = new CenterText(array, this.monster.getName(), this.player.getHp(), this.monster.getHp());
    }

    private void checkAttack(String attack, long start, long end) {
        boolean correctAtk = false;
        for (Attacks atk : this.player.getAttacks()) {
            if (atk != null) {
                if (atk.getName().equals(attack) & (end - start) <= (atk.getCastTime() * (this.monster.getReactionTime() / 100d))) {
                    dealDamage(atk.getDmg());

                    String[] array = {"Your " + attack + " succeded! You dealt " + atk.getDmg() + " DMG"};
                    CenterText str1 = new CenterText(array, this.monster.getName(), this.player.getHp(), this.monster.getHp());
                    correctAtk = true;
                    break;
                } else if (atk.getName().equals(attack)) {
                    String[] array = {"You missed! You need to cast it faster!"};
                    CenterText str1 = new CenterText(array, this.monster.getName(), this.player.getHp(), this.monster.getHp());
                    correctAtk = true;
                    break;
                }
            }
        }
        if (!correctAtk) {
            if (Math.random() <= 0.5) {
                takeDamage(1);
                String[] array = {"You drool! That's not a valid attack!", "", "OUCH! You accidentially hit yourself!"};
                CenterText str1 = new CenterText(array, this.monster.getName(), this.player.getHp(), this.monster.getHp());
            } else {
                String[] array = {"You drool! That's not a valid attack!"};
                CenterText str1 = new CenterText(array, this.monster.getName(), this.player.getHp(), this.monster.getHp());
            }
        }
    }

    /**
     * This private method is used to pause the execution of the code It takes a
     * single parameter, that specifies the length of the pause
     *
     * @param seconds A double indicating number of seconds to pause for.
     */
    private void sleep(double seconds) {
        int timer = (int) (seconds * 1000);
        try {
            Thread.sleep(timer);
        } catch (InterruptedException ex) {
            //Thread.currentThread().interrupt();
            System.out.println("Error slept: " + ex);
        }
    }
}
