/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import fightsystem.Attack;
import fightsystem.AttackCatalogue;
import framework.Game;
import framework.Room;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author corga
 */
public class GUIController {

    static Game game;
    private static Stage stage;
    private static GUIController gui;
    private static Scene previousScene;
    private static String originDirection;

    public GUIController(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/StartWindow.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        this.stage = stage;
        stage.show();
    }

    public static void startGame() {
        AttackCatalogue browser = new AttackCatalogue();
        fightsystem.Character protagonist = new fightsystem.Character();
        Attack[] attack = {browser.getAttack("slap"), browser.getAttack("slice"), browser.getAttack("chop")};
        protagonist.setAttacks(attack);
        GUIController.game = new Game(protagonist, browser);
        GUIController.game.play();
    }

    public void updateStage(FXMLRooms currentRoom) {
        String FXMLPath = currentRoom.getFXMLPath();
        Stage stage = GUIController.stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPath));
        Parent root;
        try {
            root = loader.load();
            currentRoom.setController(loader.getController());
            GUIController.setPreviousScene(stage.getScene());
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setGui(GUIController gui) {
        GUIController.gui = gui;
    }

    public static GUIController getGui() {
        return GUIController.gui;
    }

    public static FXMLRooms goRoom(String direction) {
        GUIController.originDirection = direction;
        return game.goRoom(direction);
    }
    
    public static String getOriginDirection() {
        if (GUIController.originDirection != null) {
            return GUIController.originDirection;
        }
        return "start";
    }
    
    public static FXMLRooms getCurrentRoom(){
        return game.getCurrentRoom();
    }
    
    public static void setPreviousScene(Scene scene){
        GUIController.previousScene = scene;
    }
    
    public static Scene getPreviousScene() {
        return GUIController.previousScene;
    }
}
