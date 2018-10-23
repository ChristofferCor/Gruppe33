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

    public double health = 100;
    public int monsterId;
    public double monsterHealth = 10;
    public int status;
    private Scanner speedTyper;

    /**
     * The FightInitiater needs to start a fight with a given monsterId. The
     * monster could be an object in the monster package. OBS no monsters are
     * implemented in this prototype
     *
     * @param monsterId
     */
    public FightInitiater(int monsterId) {
        this.monsterId = monsterId;
        this.status = 0;
    }

    /**
     * This public method manages the fight, initialized with the constructor.
     *
     * @return Returns an integer with a value corresponding to a status id. 0 =
     * running, 1 = dead, 2 = won
     */
    public int fight() {
        String[] array = {"You encountered a Wombat!"};
        CenterText str1 = new CenterText(" You: " + this.health + " HP", "Wombat: " + this.monsterHealth + " HP ", array, 70, '#', '-');
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
        String[] array = {"This is your chance, act quickly!", "", "Avaliable attacks:    Slice    Hack    Chop"};
        CenterText str1 = new CenterText(" You: " + this.health + " HP", "Wombat: " + this.monsterHealth + " HP ", array, 70, '#', '-');
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
        this.health -= dmg; //See dealDamage()
        this.health = (int) (this.health * 10);
        this.health = ((double) this.health) / 10;
        if (this.health <= 0) { //See dealDamage(). Status '1' is defeat.
            this.status = 1;
        }
    }

    private void dealDamage(double dmg) {
        this.monsterHealth -= dmg; //These extra assignments of the variable is used to remove truncating errors, eg: 1.200000003 -> 1.2
        this.monsterHealth = (int) (this.monsterHealth * 10);
        this.monsterHealth = ((double) this.monsterHealth) / 10;
        if (this.monsterHealth <= 0) { //Checks if the monsters HP fell below or hit zero. If so the status is changed to '2' which means victory.
            this.status = 2;
        }
    }
    /**
     * @deprecated
     */
    private void displayStats() {
        System.out.printf("You currently have %.1f HP\nThe Wombat currently have %.1f HP \n", this.health, this.monsterHealth);
    }

    private void theirTurn() {
        double damage = (((double) ((int) (Math.random() * 100))) / 10);
        takeDamage(damage);
        String[] array = {"The Wombat attacked you and dealt " + damage + " DMG"};
        CenterText str1 = new CenterText(" You: " + this.health + " HP", "Wombat: " + this.monsterHealth + " HP ", array, 70, '#', '-');
    }

    private void checkAttack(String attack, long start, long end) {
        attack = attack.toLowerCase(); // Lowers the difficulty, by making the attack word case insensitive
        double dmg;
        String timeOutMessage = "You missed! You need to cast it faster!";
        boolean inTime = (end - start <= 2000); // The number eg. 2000, represents the maximum allowed time in ms for the attacks
        switch (attack) {
            case "slice": {
                dmg = 5;
                if (inTime) {
                    dealDamage(dmg);
                    String[] array = {"Your " + attack + " succeded! You dealt " + dmg + " DMG"};
                    CenterText str1 = new CenterText(" You: " + this.health + " HP", "Wombat: " + this.monsterHealth + " HP ", array, 70, '#', '-');
                } else {
                    String[] array = {timeOutMessage};
                    CenterText str1 = new CenterText(" You: " + this.health + " HP", "Wombat: " + this.monsterHealth + " HP ", array, 70, '#', '-');
                }
                break;
            }
            case "hack": {
                dmg = 2.5;
                if (inTime) {
                    dealDamage(dmg);
                    String[] array = {"Your " + attack + " succeded! You dealt " + dmg + " DMG"};
                    CenterText str1 = new CenterText(" You: " + this.health + " HP", "Wombat: " + this.monsterHealth + " HP ", array, 70, '#', '-');
                } else {
                    String[] array = {timeOutMessage};
                    CenterText str1 = new CenterText(" You: " + this.health + " HP", "Wombat: " + this.monsterHealth + " HP ", array, 70, '#', '-');
                }
                break;
            }
            case "chop": {
                dmg = 2;
                if (inTime) {
                    dealDamage(dmg);
                    String[] array = {"Your " + attack + " succeded! You dealt " + dmg + " DMG"};
                    CenterText str1 = new CenterText(" You: " + this.health + " HP", "Wombat: " + this.monsterHealth + " HP ", array, 70, '#', '-');
                } else {
                    String[] array = {timeOutMessage};
                    CenterText str1 = new CenterText(" You: " + this.health + " HP", "Wombat: " + this.monsterHealth + " HP ", array, 70, '#', '-');
                }
                break;
            }
            default: {
                if (Math.random() <= 0.5) {
                    takeDamage(1);
                    String[] array = {"You drool! That's not a valid attack!", "", "OUCH! You accidentially hit yourself!"};
                    CenterText str1 = new CenterText(" You: " + this.health + " HP", "Wombat: " + this.monsterHealth + " HP ", array, 70, '#', '-');
                } else {
                    String[] array = {"You drool! That's not a valid attack!"};
                    CenterText str1 = new CenterText(" You: " + this.health + " HP", "Wombat: " + this.monsterHealth + " HP ", array, 70, '#', '-');
                }
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
