/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour.controllers;

import connectfour.CFProperties;
import connectfour.ConnectFour;
import connectfour.Grid;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    @FXML private ToggleGroup tgGridSize;
    @FXML private Label lInfoPlayerName1;
    @FXML private Label lInfoPlayerName2;
    @FXML private Rectangle raInfoPlayerColor1;
    @FXML private Rectangle raInfoPlayerColor2;
    @FXML private ImageView ivActivePlayer;

    private int activePlayer = 1;

    @FXML
    private void handleAboutAction(ActionEvent event) throws Exception {
	Stage stage = new Stage();
	Parent about;
	about = FXMLLoader.load(getClass().getResource("/connectfour/views/FXMLAbout.fxml"));

	stage.setScene(new Scene(about));
	stage.setTitle("About the game");
	stage.initStyle(StageStyle.UTILITY);
	stage.initModality(Modality.WINDOW_MODAL);
	stage.initOwner(mainMenu.getScene().getWindow());
	stage.setResizable(false);
	stage.show();
	stage.centerOnScreen();

    }

    @FXML
    private void handlePlayerSettingsAction(ActionEvent event) throws Exception {
	Stage stage = new Stage();
	Parent about;
	about = FXMLLoader.load(getClass().getResource("/connectfour/views/FXMLPlayerSettings.fxml"));

	stage.setScene(new Scene(about));
	stage.setTitle("About the game");
	stage.initStyle(StageStyle.UTILITY);
	stage.initModality(Modality.WINDOW_MODAL);
	stage.initOwner(mainMenu.getScene().getWindow());
	stage.setResizable(false);
	stage.show();
	stage.centerOnScreen();

    }

    @FXML
    private void handleOkAction(ActionEvent event) throws Exception {
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.close();
    }

    @FXML
    private void handleCloseAction(ActionEvent event) throws Exception {
	Stage stage = (Stage) (mainMenu.getScene().getWindow());
	stage.close();
    }

    @FXML
    private void handleGridSizeAction(ActionEvent event) throws Exception {
	Grid grid = new Grid(6, 7, 1);
	for (MenuItem item : menuGridSize.getItems()) {
	    if (((RadioMenuItem) item).isSelected()) {
		grid = new Grid(
			Integer.valueOf(item.getProperties().get("rows").toString()),
			Integer.valueOf(item.getProperties().get("columns").toString()),
			Integer.valueOf(item.getProperties().get("gamemode").toString()));
	    }

	}
	ConnectFour cf = ConnectFour.getInstance();

	cf.deleteGrid();
	cf.drawGrid(grid);
	cf.centerGrid();
    }

    public void test() {
	System.out.println("TEST");
    }

    public void togglePlayer() {
	setActivePlayer(((activePlayer++) % 2) + 1);

    }

    public void setActivePlayer(int player) {
	player = (player == 1) ? 1 : 2;

	ivActivePlayer.setImage(new Image(getClass().getResourceAsStream("/img/arrow.png")));
	ivActivePlayer.setLayoutY(23 + (20 * player));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	CFProperties props = CFProperties.getInstance();

	lInfoPlayerName1.setText("Player 1: " + props.getString("player1.name", "Player 1"));
	lInfoPlayerName2.setText("Player 2: " + props.getString("player2.name", "Player 2"));
	raInfoPlayerColor1.setFill(Color.web(props.getString("player1.color")));
	raInfoPlayerColor2.setFill(Color.web(props.getString("player2.color")));
	setActivePlayer(1);
	/*
	 for (int i = 0; i < menuGridSize.getItems().size(); i++) {
	 RadioMenuItem item = (RadioMenuItem) menuGridSize.getItems().get(i);

	 item.setAccelerator(new KeyCodeCombination(KeyCode.getKeyCode(String.valueOf(i + 1)), KeyCombination.CONTROL_DOWN));
	 if (item.getText().contains("(Classic)")) {
	 item.setSelected(true);
	 }
	 }
	 */
    }
}
