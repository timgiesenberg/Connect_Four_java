/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private MenuItem miAbout;
    @FXML private MenuItem miClose;
    @FXML private MenuBar mainMenu;
    @FXML private Menu menuGridSize;
    
    @FXML private void handleAboutAction(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        Parent about;
        about = FXMLLoader.load(getClass().getResource("/views/FXMLAbout.fxml"));
        
        stage.setScene(new Scene(about));
        stage.setTitle("About the game");
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainMenu.getScene().getWindow());
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
       
    }
    
    @FXML private void handleOkAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ( (Node) event.getSource() ).getScene().getWindow();
        stage.close();
    }
    
    @FXML private void handleCloseAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ( mainMenu.getScene().getWindow() );
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        miClose.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        ToggleGroup tgGridSize = new ToggleGroup();
        
        for(int i = 0; i < menuGridSize.getItems().size(); i++) {
            RadioMenuItem item = (RadioMenuItem)menuGridSize.getItems().get(i);
            item.setToggleGroup(tgGridSize);
            item.setAccelerator(new KeyCodeCombination(KeyCode.getKeyCode(String.valueOf(i+1)), KeyCombination.CONTROL_DOWN));
            if(item.getText().contains("(Classic)")) {
                item.setSelected(true);
            }
        }
    }
}