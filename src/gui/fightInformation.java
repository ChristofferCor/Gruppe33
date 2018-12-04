/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;

/**
 *
 * @author simon
 */
public interface fightInformation {
    public String[] getName();
    
    public double[] getHp();
    
    public String imagePath();
    
    public String setOutputText();
    
    public List<String> getAvailableAttacks();
    
    public void setObject();
}
