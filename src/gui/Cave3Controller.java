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
public class Cave3Controller extends GameWindowsController implements Initializable {

    @FXML
    private ImageView trap;
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

        if ("south".equals(GUIController.getOriginDirection())) {
            GridPane.setConstraints(player, 4, 1);
        } else if ("north".equals(GUIController.getOriginDirection())) {
            GridPane.setConstraints(player, 5, 4);
        } else if ("west".equals(GUIController.getOriginDirection())) {
            GridPane.setConstraints(player, 8, 3);
        }
    }

    @Override
    void moveCharacter(Node character, int posX, int posY) {
        if ((posX > 0 && posX < 9 && posY > 0 && posY < 5)) {
            GridPane.setConstraints(character, posX, posY);
        } else if (posX == 4 && posY == 0) {
            GridPane.setConstraints(character, posX, posY);
            super.delayUpdateStage(GUIController.goRoom("north"));
        } else if (posX == 5 && posY == 5) {
            GridPane.setConstraints(character, posX, posY);
            super.delayUpdateStage(GUIController.goRoom("south"));
        } else if (posX == 9 && posY == 3) {
            GridPane.setConstraints(character, posX, posY);
            super.delayUpdateStage(GUIController.goRoom("east"));
        }
    }
}
