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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class FXMLAboutController implements Initializable {

    @FXML
    private void handleOkAction(ActionEvent event) throws Exception {
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	System.out.println("Howdy");

    }
}
