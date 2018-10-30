/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import entities.Item;
import fightsystem.Character;

/**
 *
 * @author corga
 */
public class Score {
    private static int score;
    private static long startTime;
    private static int rest;
    
    public Score(long startTime){
        this.startTime = startTime;
        this.score = 0;
    }
    
    public int calculateScore(long endTime, Character protagonist){ //NEEDS FIXING!
        int score = this.score;
        for(Item obj : protagonist.getInventory()){
            score += obj.getScoreValue();
        }
        this.score = score;
        return this.score;
    }
}
