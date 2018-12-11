/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Item;
import framework.Game;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
public class Cave2Controller extends GameWindowsController implements Initializable {

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
    private ImageView goldWatch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setPlayer(player);
        GridPane.setConstraints(goldWatch, GUIController.getCurrentRoom().getItemPos(0)[0], GUIController.getCurrentRoom().getItemPos(0)[1]);
        Image image = new Image("/resources/" + GUIController.getCurrentRoom().getItemImage(0) + ".png");
        goldWatch.setImage(image);
    }

    @Override
    void moveCharacter(Node character, int posX, int posY) {
        if ((posX > 0 && posX < 9 && posY > 0 && posY < 5)) {
            GridPane.setConstraints(character, posX, posY);
        } else if (posX == 0 && posY == 3) {
            GridPane.setConstraints(character, posX, posY);
            super.delayUpdateStage(GUIController.goRoom("west"));
        } else if (posX == 4 && posY == 0) {
            GridPane.setConstraints(character, posX, posY);
            super.delayUpdateStage(GUIController.goRoom("north"));
        } else if (posX == 9 && posY == 3) {
            GridPane.setConstraints(character, posX, posY);
            super.delayUpdateStage(GUIController.goRoom("east"));
        }
    }
}
