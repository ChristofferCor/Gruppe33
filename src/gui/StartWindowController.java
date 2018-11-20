/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import fightsystem.Attack;
import fightsystem.AttackCatalogue;
import framework.Game;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author corga
 */
public class StartWindowController implements Initializable {

    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonQuit;
    @FXML
    private Button buttonHelp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        AttackCatalogue browser = new AttackCatalogue();
        fightsystem.Character protagonist = new fightsystem.Character();
        Attack[] attack = {browser.getAttack("slap"), browser.getAttack("slice"), browser.getAttack("chop")};
        protagonist.setAttacks(attack);
        Game game = new Game(protagonist, browser);
        game.play();

    }

    @FXML
    private void startQuit(ActionEvent event) {
    }

    @FXML
    private void startHelp(ActionEvent event) {
    }

}
