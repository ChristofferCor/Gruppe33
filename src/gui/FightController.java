/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import fightsystem.Fight;
import framework.Game;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author simon
 */
public class FightController implements Initializable {

    private Fight fight;

    @FXML
    private ImageView player;
    @FXML
    private ImageView enemy;
    @FXML
    private GridPane controlGridPane;
    @FXML
    private Text enemyName;
    @FXML
    private Text enemyHP;
    @FXML
    private Text playerName;
    @FXML
    private Text playerHP;
    @FXML
    private Text outputText;
    @FXML
    private TextField attackInput;
    @FXML
    private ListView<String> attackList;
    @FXML
    private Button fleeButton;

    private ObservableList<String> items = FXCollections.observableArrayList();
    private boolean acceptInput = false;
    private long startTime;

    private Runnable fightLoop = new Runnable() {
        public void run() {
            //sleep(1);
            runFight();
        }
    };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fight = GUIController.game.engage();
        Image image = new Image("/resources/" + fight.imagePath() + ".png"); //Could make an interface for this.
        enemy.setImage(image);

        enemyName.setText(fight.getName()[1]);
        playerName.setText(fight.getName()[0]);

        updateHp();

        items.addAll(fight.getAvailableAttacks());
        attackList.setItems(items);

        outputText.setText("You encountered a " + fight.getName()[1] + "!");
        Platform.runLater(fightLoop);
    }

    private void updateHp() {
        enemyHP.setText(fight.getHp()[1] + " HP");
        playerHP.setText(fight.getHp()[0] + " HP");
    }

    private void runFight() {
        outputText.setText(fight.fight());
        switch (fight.getStatus()) {
            case Fight.RUNNING:
                if (!fight.getTurn()) {
                    System.out.println("My Turn!");
                    acceptInput = true;
                    startTime = new Date().getTime();
                } else {
                    System.out.println("Their Turn!");
                    Platform.runLater(fightLoop);
                }
            break;
            case Fight.DEAD:
            case Fight.VICTORY:
            case Fight.FLEE:
        }
        updateHp();
    }

    /*
            switch (this.status) {
            case Fight.DEAD:
                output.setBody(new String[]{"The " + this.monster.getName() + " defeated you", "", "It had " + this.monster.getHp() + " HP left, what a shame!"});
                output.setHead(new String[]{"You unfortunately died of slowness"});
                output.oldPrint();
                Choose.choose();
                break;
            case Fight.VICTORY:
                output.setBody(new String[]{"You speedtyped your way around the " + this.monster.getName(), "", "You made it out with " + this.player.getHp() + " HP left"});
                output.setHead(new String[]{"Congratulations! You won"});
                output.oldPrint();
                break;
            case Fight.FLEE:
                output.setBody(new String[]{"The " + this.monster.getName() + " felt sorry for you", "", "It had " + this.monster.getHp() + " HP left, what a shame!"});
                output.setHead(new String[]{"You escaped like a coward!"});
                output.oldPrint();
                break;
            default:
                break;
        }
    
     */
    public void setFight() {

    }

    @FXML
    private void attackEntered(KeyEvent event) {
        if (event.getCode() == ENTER) {
            if (this.acceptInput) {
                String text = (fight.checkAttack(attackInput.getText(), this.startTime));
                this.outputText.setText(text);
                System.out.println(text);
                this.acceptInput = false;
                Platform.runLater(fightLoop);
            }
            attackInput.clear();
        }
    }

    @FXML
    private void flee(ActionEvent event) {
        this.outputText.setText("The " + fight.getName()[1] + " felt sorry for you \nIt had " + fight.getHp()[1] + " HP left, what a shame!");
        GUIController.getGui().updateStage(GUIController.getCurrentRoom());
    }

    /**
     * This private method is used to pause the execution of the code It takes a
     * single parameter, that specifies the length of the pause
     *
     * @param seconds A double indicating number of seconds to pause for.
     */
    private void sleep(double seconds) {
        int timer = (int) (seconds * 1000);
        try {
            Thread.sleep(timer);
        } catch (InterruptedException ex) {
            System.out.println("Error slept: " + ex);
        }
    }

}
