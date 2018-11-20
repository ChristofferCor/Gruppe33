/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
    private void startGame(ActionEvent event) {
    }

    @FXML
    private void startQuit(ActionEvent event) {
    }

    @FXML
    private void startHelp(ActionEvent event) {
    }
    
}
