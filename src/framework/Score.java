/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import entities.Item;
import fightsystem.Character;
import java.util.Date;

/**
 *
 * @author corga
 */
public class Score {
    private static int score;
    private static long startTime;
    private static int rest;

    public static void increaseRest() {
        Score.rest++;
    }
    
    public static int getRest(){
        return Score.rest;
    }
    
    public Score(){
        this.startTime = new Date().getTime();
        this.score = 0;
        this.rest = 0;
    }
    
    public int calculateScore(long endTime, Character protagonist){ //NEEDS FIXING!
        int score = this.score;
        for(Item obj : protagonist.getInventory()){
            score += obj.getScoreValue();
        }
        score -= score*(this.rest/100);
        score -= score * ((endTime-Score.startTime)/21600000);
        this.score = score;
        return this.score;
    }
}
