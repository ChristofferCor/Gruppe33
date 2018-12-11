package fightsystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import framework.Choose;
import gui.fightInformation;
import java.util.ArrayList;
import java.util.Arrays;
import util.FightTextFormater;
import java.util.Scanner;
import java.util.Date;
import java.util.List;

/**
 * This is the main fighting system In here the controls for all fights are
 * located
 *
 * @author Simon Holland Flarup
 */
public class Fight implements fightInformation{

    public static final int RUNNING = 0;
    public static final int DEAD = 1;
    public static final int VICTORY = 2;
    public static final int FLEE = 3;
    
    private static int status;
    private int numAtk = 0;
    private final Character monster, player;
    private Scanner speedTyper;
    private FightTextFormater output;
    private boolean yourTurn;

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
        yourTurn = (Math.random()>= 0.5);

        //this.output = new FightTextFormater(70, '#', '-', this.player, this.monster);

        for (Attack atk : this.monster.getAttacks()) {
            if (atk != null) {
                this.numAtk++;
            }
        }
    }
    
    public int getStatus(){
        return this.status;
    }
    
    public boolean getTurn(){
        return this.yourTurn;
    }

    /**
     * This public method manages the fight, initialized with the constructor.
     *
     * @return Returns an integer with a value corresponding to a status id. 0 =
     * running, 1 = dead, 2 = won
     */
    public String fight() {
        sleep(3);
        if (yourTurn){
            yourTurn = false;
            return yourTurn();
        } else {
            yourTurn = true;
            return theirTurn();
        }
    }

    private String yourTurn() {
        return ("This is your chance, act quickly!");
    }

    private void takeDamage(double dmg) {
        dmg = ((int) (dmg * 10)) / 10d;
        double health = this.player.getHp(); //See dealDamage()
        health -= dmg;
        health = ((int) (health * 10)) / 10d;
        this.player.setHp(health);
        if (this.player.getHp() <= 0) { //See dealDamage(). Status '1' is defeat.
            this.player.setHp(0);
            this.status = DEAD;
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
            this.status = VICTORY;
        }
    }

    private String theirTurn() {
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
            return ("The " + this.monster.getName() + " uses " + this.monster.getAttacks()[atkNumber].getName() + " against you and dealt " + damage + " DMG");
        } else {
            return ("The " + this.monster.getName() + " missed its " + this.monster.getAttacks()[atkNumber].getName() + " against you!");
        }
    }

    public String checkAttack(String attack, long start) {
        boolean correctAtk = false;
        boolean accuracyMiss = false;
        double hitChance;
        long end = new Date().getTime();

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
                    return("Your " + attack + " succeded! You dealt " + damage + " DMG");
                } else if (atk.getName().equals(attack)) {
                    return("You missed! You need to cast it faster! \nAccuracy might be too low!");
                }
            }
        }
        if (!correctAtk) {
            if (Math.random() <= 0.5) {
                takeDamage(1);
                return ("You drool! That's not a valid attack! \nOUCH! You accidentially hit yourself!");
            } else {
                return("You drool! That's not a valid attack!");
            }
        }
        return ("Error 161_Fight");
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
            this.status = FLEE;
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

    @Override
    public String[] getName() {
        String[] string = {"Erik som barn", this.monster.getName()};
        return string;
    }

    @Override
    public double[] getHp() {
        double[] hpArray = {this.player.getHp(), this.monster.getHp()};
        return hpArray;
    }

    @Override
    public String imagePath() {
        return this.monster.getImage();
    }

    @Override
    public String setOutputText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setObject(){
        
    }

    @Override
    public List<String> getAvailableAttacks() {
        List<String> AvailableAttacks = new ArrayList<>();
        for(Attack a : this.player.getAttacks()) {
            AvailableAttacks.add(a.getName());
        }
        return AvailableAttacks;
    }
}
