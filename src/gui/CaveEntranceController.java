/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author corga
 */
public class CaveEntranceController extends GameWindowsController implements Initializable {

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
    @FXML
    private ImageView boulder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setPlayer(player);
        
        if (GUIController.game.getIsDug()) {
            boulder.setImage(null);
        }
        
        if ("south".equals(GUIController.getOriginDirection())) {
            GridPane.setConstraints(player, 4, 1);
        } else if ("west".equals(GUIController.getOriginDirection())) {
            GridPane.setConstraints(player, 8, 2);
        }
    }

    @Override
    void moveCharacter(Node character, int posX, int posY) {
        if ((posX > 0 && posX < 9 && posY > 0 && posY < 5)) {
            GridPane.setConstraints(character, posX, posY);
        } else if (posX == 9 && posY == 2) {
            GridPane.setConstraints(character, posX, posY);
            super.delayUpdateStage(GUIController.goRoom("east"));
        } else if (posX == 4 && posY == 0 && GUIController.game.getIsDug()) {
            System.out.println("Victory!");
            GridPane.setConstraints(character, posX, posX);
            super.delayUpdateStage(GUIController.goRoom("north"));
        }
    }
    
    @Override
    public void updateRoom(){
        boulder.setImage(null);
    }

}
