/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author corga
 */
public class HomeController extends GameWindowsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void goDown() {
        editText("NORTH");
    }
    
    private void goUp(){
        editText("SOUTH");
    }
    
    private void goLeft(){
        editText("EAST");
    }
    
    private void goRight(){
        editText("WEST");
    }
    
    public void editText(String outputText) {
        super.setOutputText(outputText);
    }
    
}
