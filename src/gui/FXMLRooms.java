/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author corga
 */
public interface FXMLRooms {
    public String getFXMLPath();
    
    public void setController(GameWindowsController controller);
    
    public int[] getItemPos(int index);
    
    public String getItemImage(int index);
}
