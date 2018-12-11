/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Item;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView player;
    @FXML
    private ListView<String> listViewInventory;
    
    private FXMLRooms room;
    private ObservableList<String> items = FXCollections.observableArrayList();
    
    private Runnable delayUpdateStage = new Runnable() {
        public void run() {
            try {
                Thread.sleep(50);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        GUIController.getGui().updateStage(room);
                    }
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(GameWindowsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //items.addAll(GUIController.game.getInventory());
        //listViewInventory.setItems(items);
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

    void goDown() {
        int posX = GridPane.getColumnIndex(this.player);
        int posY = GridPane.getRowIndex(this.player) + 1;
        moveCharacter(this.player, posX, posY);
    }

    void goUp() {
        int posX = GridPane.getColumnIndex(this.player);
        int posY = GridPane.getRowIndex(this.player) - 1;
        moveCharacter(this.player, posX, posY);
    }

    void goLeft() {
        int posX = GridPane.getColumnIndex(this.player) - 1;
        int posY = GridPane.getRowIndex(this.player);
        moveCharacter(this.player, posX, posY);
    }

    void goRight() {
        int posX = GridPane.getColumnIndex(this.player) + 1;
        int posY = GridPane.getRowIndex(this.player);
        moveCharacter(this.player, posX, posY);
    }

    void setPlayer(ImageView player) {
        this.player = player;
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

    public void setOutputText(String outputText) {
        this.outputText.setText(outputText);
    }

    void moveCharacter(Node character, int posX, int posY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void startFight() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/Fight.fxml"));
            GUIController.setPreviousScene(this.player.getScene());
            this.player.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GameWindowsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delayUpdateStage(FXMLRooms room) {
        this.room = room;
        new Thread(delayUpdateStage).start();
    }

}
