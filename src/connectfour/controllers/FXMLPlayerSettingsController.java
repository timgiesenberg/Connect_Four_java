/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour.controllers;

import connectfour.CFProperties;
import connectfour.ConnectFour;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class FXMLPlayerSettingsController implements Initializable {

    @FXML private TextField tfPlayername1;
    @FXML private TextField tfPlayername2;
    @FXML private ColorPicker player1ColorPicker;
    @FXML private ColorPicker player2ColorPicker;
    @FXML private ComboBox cbPlayerType;
    private static double maxLength = Math.sqrt(65025 + 65025 + 65025);

    @FXML private void handleOKAction(ActionEvent event) throws Exception {
	CFProperties props = CFProperties.getInstance();
	ConnectFour cf = ConnectFour.getInstance();

	Color color1 = player1ColorPicker.getValue();
	Color color2 = player2ColorPicker.getValue();

	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	Label l1 = (Label) stage.getOwner().getScene().lookup("#lInfoPlayerName1");
	Label l2 = (Label) stage.getOwner().getScene().lookup("#lInfoPlayerName2");
	Rectangle r1 = (Rectangle) stage.getOwner().getScene().lookup("#raInfoPlayerColor1");
	Rectangle r2 = (Rectangle) stage.getOwner().getScene().lookup("#raInfoPlayerColor2");

	if (getColorDifference(color1, color2) < 0.35) {
	    Dialogs.create()
		    .owner(stage)
		    .title("Color blindness?")
		    .masthead(":( \nI'm so sorry...")
		    .message("but the colors are too similar. Please choose another one.")
		    .showError();
	} else if (tfPlayername1.getText().equalsIgnoreCase(tfPlayername2.getText())) {
	    Dialogs.create()
		    .owner(stage)
		    .title("Namesake?")
		    .masthead(":( \nI'm so sorry...")
		    .message("but you cannot use the same name for both players.")
		    .showError();

	} else {
	    Action response = Dialog.Actions.YES;
	    if (cf.getMoves() > 0) {
		response = Dialogs.create().actions(Dialog.Actions.NO, Dialog.Actions.YES)
			.owner(cf.stage)
			.title("Really?")
			.masthead("Game in progress")
			.message("Changing the player settings starts a new game. The current running game will be lost!\nWould you like to continue? ")
			.showConfirm();
	    }
	    if (response == Dialog.Actions.YES) {
		cf.deleteGrid();
		cf.drawGrid();

		props.setProperty("player1.name", tfPlayername1.getText());
		props.setProperty("player2.name", tfPlayername2.getText());
		props.setProperty("player1.color", player1ColorPicker.getValue().toString());
		props.setProperty("player2.color", player2ColorPicker.getValue().toString());
		props.save();
		cf.getPlayers()[0].setColor(color1);
		cf.getPlayers()[1].setColor(color2);
		l1.setText("Player 1: " + tfPlayername1.getText());
		l2.setText("Player 2: " + tfPlayername2.getText());
		r1.setFill(Color.web(player1ColorPicker.getValue().toString()));
		r2.setFill(Color.web(player2ColorPicker.getValue().toString()));
		stage.close();

	    }
	}

    }

    @FXML private void handleCancelAction(ActionEvent event) throws Exception {
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

	CFProperties props = CFProperties.getInstance();

	player1ColorPicker.setValue(Color.web(props.getString("player1.color", "Blue")));
	player2ColorPicker.setValue(Color.web(props.getString("player2.color", "Red")));
	tfPlayername1.setText(props.getString("player1.name", "Player 1"));
	tfPlayername2.setText(props.getString("player2.name", "Player 2"));

    }

    public static double getColorDifference(Color color1, Color color2) {
	return Math.sqrt(
		Math.pow(color1.getRed() - color2.getRed(), 2)
		+ Math.pow(color1.getGreen() - color2.getGreen(), 2)
		+ Math.pow(color1.getBlue() - color2.getBlue(), 2)
	);
    }

}
