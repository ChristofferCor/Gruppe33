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
public class GameWindowsController implements Initializable {
    @FXML
    private Button buttonHelp;
    @FXML
    private Button buttonInventory;
    @FXML
    private Button buttonRest;
    @FXML
    private Button buttonCraft;
    @FXML
    private TextArea outputText;
    @FXML
    private GridPane controlGridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void startHelp(ActionEvent event) {
        String outputText = "The ultimate goal of this game is for you to have fun."
                + "\nTo play this game you need to be fast at typing."
                + "\nYou will have to fight monsters to get the material needed to build a new pickaxe."
                + "\nGo through the cave system to find new mobs, items and treasure."
                + "\nTo win the game you will have to build a pickaxe and get out of the devastating cave system";
        setOutputText(outputText);
    }

    @FXML
    private void startInventory(ActionEvent event) {
    }

    @FXML
    private void startRest(ActionEvent event) {
    }

    @FXML
    private void startCraft(ActionEvent event) {
    }

    @FXML
    private void captureKeys(KeyEvent event) {
        switch (event.getCode()) {
            case DOWN:
                goDown();
                break;
            case UP:
                goUp();
                break;
            case LEFT:
                goLeft();
                break;
            case RIGHT:
                goRight();
                break;
            case S:
                goDown();
                break;
            case W:
                goUp();
                break;
            case A:
                goLeft();
                break;
            case D:
                goRight();
                break;
        }
    }
    
    private void goDown() {
        setOutputText("NORTH");
    }
    
    private void goUp(){
        setOutputText("SOUTH");
    }
    
    private void goLeft(){
        setOutputText("EAST");
    }
    
    private void goRight(){
        setOutputText("WEST");
    }
    
    public void setOutputText(String outputText) {
        this.outputText.setText(outputText);
    }
    
}
