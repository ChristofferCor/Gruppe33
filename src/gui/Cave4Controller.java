/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author simon
 */
public class Cave4Controller extends GameWindowsController implements Initializable {

    @FXML
    private ImageView player;
    @FXML
    private GridPane controlGridPane;
    @FXML
    private TextArea outputText;
    @FXML
    private Button buttonHelp;
    @FXML
    private Button buttonInventory;
    @FXML
    private Button buttonRest;
    @FXML
    private Button buttonCraft;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setPlayer(player);
    }

    @Override
    void moveCharacter(Node character, int posX, int posY) {
        if ((posX > 0 && posX < 9 && posY > 0 && posY < 5)) {
            GridPane.setConstraints(character, posX, posY);
            if (posX == 3 && posY == 2) {
                //Start fight
                super.startFight();
            }
        } else if (posX == 9 && posY == 2) {
            GridPane.setConstraints(character, posX, posY);
            super.setOutputText("Loading next room");
            GUIController.getGui().updateStage(GUIController.goRoom("west"));
        } else if (posX == 4 && posY == 5) {
            GridPane.setConstraints(character, posX, posY);
            super.setOutputText("Loading next room");
            GUIController.getGui().updateStage(GUIController.goRoom("south"));
        }
    }

}
