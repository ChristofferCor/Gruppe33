package fightsystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import framework.Choose;
import util.FightTextFormater;
import java.util.Scanner;
import java.util.Date;

/**
 * This is the main fighting system In here the controls for all fights are
 * located
 *
 * @author Simon Holland Flarup
 */
public class Fight {

    private static final int RUNNING = 0;
    private static final int DEAD = 1;
    private static final int VICTORY = 2;
    private static final int FLEE = 3;
    
    private static int status;
    private int numAtk = 0;
    private final Character monster, player;
    private Scanner speedTyper;
    private FightTextFormater output;

    /**
     * The Fight needs to start a fight with two given Character objects.
     *
     * @param player takes a Character object of the player
     * @param monster takes a Character object of the enemy
     */
    public Fight(Character player, Character monster) {
        this.monster = monster;
        this.player = player;
        this.status = this.RUNNING;

        this.output = new FightTextFormater(70, '#', '-', this.player, this.monster);

        for (Attack atk : this.monster.getAttacks()) {
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
        output.setBody("You encountered a " + monster.getName() + "!");
        output.print();
        sleep(4);
        if (Math.random() <= 0.5) {
            while (this.status == this.RUNNING) {
                //displayStats();
                yourTurn();
                if (this.status != this.RUNNING) {
                    break;
                }
                sleep(3);
                theirTurn();
                sleep(3);
            }
        } else {
            while (this.status == this.RUNNING) {
                //displayStats();
                theirTurn();
                if (this.status != this.RUNNING) {
                    break;
                }
                sleep(3);
                yourTurn();
                sleep(3);
            }
        }
        switch (this.status) {
            case Fight.DEAD:
                output.setBody(new String[]{"The " + this.monster.getName() + " defeated you", "", "It had " + this.monster.getHp() + " HP left, what a shame!"});
                output.setHead(new String[]{"You unfortunately died of slowness"});
                output.oldPrint();
                Choose.choose();
                break;
            case Fight.VICTORY:
                output.setBody(new String[]{"You speedtyped your way around the " + this.monster.getName(), "", "You made it out with " + this.player.getHp() + " HP left"});
                output.setHead(new String[]{"Congratulations! You won"});
                output.oldPrint();
                break;
            case Fight.FLEE:
                output.setBody(new String[]{"The " + this.monster.getName() + " felt sorry for you", "", "It had " + this.monster.getHp() + " HP left, what a shame!"});
                output.setHead(new String[]{"You escaped like a coward!"});
                output.oldPrint();
                break;
            default:
                break;
        }
        return status;
    }

    private void yourTurn() {

        String tempString = "";
        for (Attack atk : this.player.getAttacks()) {
            if (atk != null) {
                tempString += atk.getName();
                tempString += "    ";
            }
        }
        tempString = tempString.trim();

        output.setBody(new String[]{"This is your chance, act quickly!", "", "Avaliable attacks:    " + tempString, "Enter 'Flee' to attempt it"});
        output.print();
        System.out.println("Enter attack: ");
        System.out.print("> ");
        speedTyper = new Scanner(System.in);
        Date currentTime = new Date();
        long startTime = currentTime.getTime();
        String input = speedTyper.next();
        System.out.println("");
        currentTime = new Date();
        if (input.equals("Flee")) {
            flee(startTime, currentTime.getTime());
        } else {
            checkAttack(input, startTime, currentTime.getTime());
        }
    }

    private void takeDamage(double dmg) {
        dmg = ((int) (dmg * 10)) / 10d;
        double health = this.player.getHp(); //See dealDamage()
        health -= dmg;
        health = ((int) (health * 10)) / 10d;
        this.player.setHp(health);
        if (this.player.getHp() <= 0) { //See dealDamage(). Status '1' is defeat.
            this.player.setHp(0);
            this.status = 1;
        }
    }

    private void dealDamage(double dmg) {
        dmg = (double) ((int) (dmg * 10)) / 10d;
        double health = this.monster.getHp();
        health -= dmg; //These extra assignments of the variable is used to remove truncating errors, eg: 1.200000003 -> 1.2
        health = ((int) (health * 10)) / 10d;
        this.monster.setHp(health);
        if (health <= 0) { //Checks if the monsters HP fell below or hit zero. If so the status is changed to '2' which means victory.
            this.monster.setHp(0);
            this.status = 2;
        }
    }

    private void theirTurn() {
        int rng = (int) (Math.random() * 100);
        int atkNumber = (int) (rng / (100d / this.numAtk));
        double tempRng = Math.random() * 0.5; //Halfing the Math.random() value for balacing purposes.
        //System.out.println("RANDOM CHANCE; "+tempRng+" reactiontime: "+this.monster.getReactionTime()+" total: " + tempRng*(this.monster.getReactionTime())/100 + " Spell: " + (this.monster.getAttacks()[atkNumber].getAccuracy() / 100d));
        /*
        * The RNG value is increased or decreased based on the reactionTime attribute from the monster, higher than 100 increases, lower decreases. 
        * This makes for a more complex missing system. The accuracy is held against the accuracy attribute from the chosen spell.
         */
        if ((tempRng * (this.monster.getReactionTime() / 100d)) <= (this.monster.getAttacks()[atkNumber].getAccuracy() / 100d)) {
            double damage = ((this.monster.getAttacks()[atkNumber].getDmg()) * (this.monster.getStrength()) / 100d);
            takeDamage(damage);
            output.setBody(new String[]{"The " + this.monster.getName() + " uses " + this.monster.getAttacks()[atkNumber].getName() + " against you and dealt " + damage + " DMG"});
            output.print();
        } else {
            output.setBody(new String[]{"The " + this.monster.getName() + " missed its " + this.monster.getAttacks()[atkNumber].getName() + " against you!"});
            output.print();
        }
    }

    private void checkAttack(String attack, long start, long end) {
        boolean correctAtk = false;
        boolean accuracyMiss = false;
        double hitChance;

        for (Attack atk : this.player.getAttacks()) {
            if (atk != null) {

                hitChance = atk.getAccuracy() + (atk.getCastTime() - (end - start)) / 100;
                //System.out.println("Accuracy: "+atk.getAccuracy()+" Total hitChance: "+hitChance + " Time used: "+(atk.getCastTime()-(end-start)));
                if (Math.random() > hitChance / 100) {
                    accuracyMiss = true;
                }

                if (atk.getName().equals(attack) & (end - start) <= (atk.getCastTime() * (this.monster.getReactionTime() / 100d)) & !accuracyMiss) {
                    double damage = ((atk.getDmg()) * (this.player.getStrength()) / 100);
                    dealDamage(damage);
                    output.setBody("Your " + attack + " succeded! You dealt " + damage + " DMG");
                    output.print();
                    correctAtk = true;
                    break;
                } else if (atk.getName().equals(attack)) {
                    output.setBody(new String[]{"You missed! You need to cast it faster!", "", "Accuracy might be too low!"});
                    output.print();
                    correctAtk = true;
                    break;
                }
            }
        }
        if (!correctAtk) {
            if (Math.random() <= 0.5) {
                takeDamage(1);
                output.setBody(new String[]{"You drool! That's not a valid attack!", "", "OUCH! You accidentially hit yourself!"});
                output.print();
            } else {
                output.setBody("You drool! That's not a valid attack!");
                output.print();
            }
        }
    }

    private void flee(long start, long end) {
        double rng = Math.random();
        double castTime = 0.1 - (end - start) / 10000d;
        castTime = (castTime < 0 ? castTime : 0);
        //System.out.println("DEBUG: " + rng + " React: " + ((1 + ((this.player.getReactionTime() - this.monster.getReactionTime()) / 100d))+castTime) + " CastTime:" +castTime);
        if (rng >= ((1 + ((this.monster.getReactionTime() - this.player.getReactionTime()) / 100d)) + castTime)) {
            double fleeDmg = -(this.monster.getReactionTime() - this.player.getReactionTime()) / 10d;
            takeDamage(fleeDmg);
            output.setBody(new String[]{"You tried to flee away!", "", "The " + this.monster.getName() + " caught you, dealing you " + fleeDmg + " DMG", "You need to react faster"});
            output.print();
        } else {
            this.status = 3;
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
