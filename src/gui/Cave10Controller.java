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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author corga
 */
public class Cave10Controller extends GameWindowsController implements Initializable {

    @FXML
    private ImageView platinumRing;
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
        if (GUIController.getCurrentRoom().getItemPos(0) != null) {
            GridPane.setConstraints(platinumRing, GUIController.getCurrentRoom().getItemPos(0)[0], GUIController.getCurrentRoom().getItemPos(0)[1]);
            Image image = new Image("/resources/" + GUIController.getCurrentRoom().getItemImage(0) + ".png");
            platinumRing.setImage(image);
        } else {
            platinumRing.setImage(null);
        }
    }

    @Override
    void moveCharacter(Node character, int posX, int posY) {
        if ((posX > 0 && posX < 9 && posY > 0 && posY < 5)) {
            if (GUIController.getCurrentRoom().getItemPos(0) != null) {
                if (posX == GUIController.getCurrentRoom().getItemPos(0)[0] && posY == GUIController.getCurrentRoom().getItemPos(0)[1]) {
                    GUIController.game.collectItem(posX, posY);
                    platinumRing.setImage(null);
                    super.updateInventory();
                }
            }
            GridPane.setConstraints(character, posX, posY);
        } else if (posX == 6 && posY == 5) {
            GridPane.setConstraints(character, posX, posY);
            super.delayUpdateStage(GUIController.goRoom("south"));
        }
    }

}
