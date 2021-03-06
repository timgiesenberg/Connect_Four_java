package connectfour;

import connectfour.controllers.FXMLDocumentController;
import java.net.URL;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * @author sebastian.gohres@gmail.com
 * @author tim.giesenberg@me.com
 */
public class ConnectFour extends Application {

    private static ConnectFour instance;
    public static int GAMEMODE_LOCALE = 1;
    public static int GAMEMODE_REMOTE = 2;

    Group group;
    Group dotGroup;
    AnchorPane root;
    Scene scene;
    CFProperties props;
    public Stage stage;
    private FXMLDocumentController mainController;
    int xLines;
    int yLines;
    int cellWidth;
    int activePlayer = 1;
    Player players[];
    private WebView webView;
    private ProgressBar loadProgress;
    private Label progressText;
    private Pane splashLayout;
    private GameLogic g;
    // int moves = 0;
    public IntegerProperty moves = new SimpleIntegerProperty(0);
    private static final int SPLASH_WIDTH = 676;
    private static final int SPLASH_HEIGHT = 227;

    public ConnectFour() {
	instance = this;
	props = new CFProperties();
    }

    public static ConnectFour getInstance() {
	return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {

	CFProperties props = CFProperties.getInstance();
	URL location = getClass().getResource("/connectfour/views/FXMLDocument.fxml");
	FXMLLoader loader = new FXMLLoader();
	root = (AnchorPane) loader.load(location.openStream());
	stage = new Stage(StageStyle.DECORATED);
	stage.setMinHeight(200);
	stage.setMinWidth(200);
	stage.setWidth(600);
	stage.setHeight(600);
	stage.setMaxHeight(1000);
	stage.setMaxWidth(1000);
	root.setMinSize(200, 200);
	root.setMaxSize(1000, 1000);
	root.setPrefSize(600, 600);
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
	this.stage = stage;
	mainController = (FXMLDocumentController) loader.getController();
	mainController.setActivePlayer(activePlayer);

	scene.widthProperty().addListener(new ChangeListener<Number>() {
	    @Override
	    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		centerGrid();
	    }
	});

	scene.heightProperty().addListener(new ChangeListener<Number>() {
	    @Override
	    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		centerGrid();
	    }
	});

	moves.addListener(new ChangeListener() {
	    @Override public void changed(ObservableValue o, Object oldVal,
		    Object newVal) {
		mainController.setMoves(moves.get());
	    }
	});

	players = new Player[2];
	Player p1 = new Player(1, props.getString("player1.name"), Color.web(props.getString("player1.color")));
	Player p2 = new Player(2, props.getString("player2.name"), Color.web(props.getString("player2.color")));
	players[0] = p1;
	players[1] = p2;

	drawGrid();
	centerGrid();

    }

    public void togglePlayer() {
	setActivePlayer(((activePlayer++) % 2) + 1);

    }

    public Player getActivePlayer() {
	return players[activePlayer - 1];
    }

    public void gameOver() {
	Action response = Dialogs.create().actions(Dialog.Actions.NO, Dialog.Actions.YES)
		.owner(stage)
		.title("Draw!")
		.masthead("You got it... NOT!")
		.message("Neither " + players[0].getName() + " nor " + players[1].getName() + " wins this game.\nWould you like to play again?")
		.showConfirm();
	if (response == Dialog.Actions.NO) {
	    stage.close();
	} else {
	    deleteGrid();
	    drawGrid();
	}
    }

    public void setActivePlayer(int player) {
	player = (player == 1) ? 1 : 2;
	activePlayer = player;
	mainController.setActivePlayer(player);
    }

    public Player[] getPlayers() {
	return players;
    }

    public void deleteGrid() {

	group.getChildren().clear();
	moves.set(0);

    }

    public void drawGrid() {
	Grid grid = mainController.getGrid();
	drawGrid(grid);
	centerGrid();
    }

    public int getMoves() {
	return moves.get();
    }

    public void drawGrid(Grid grid) {
	g = new GameLogic(grid);

	int[][] gg = new int[grid.getRows()][grid.getColumns()];
	int h, w, linelengthX, linelengthY;
	double centerX, centerY;

	xLines = gg[0].length;
	yLines = gg.length;
	cellWidth = 50;

	Color gridColor = Color.BLUE;
	Color tokenColor = Color.web("f4f4f4");

	Line gridline;
	Circle circle;
	Rectangle rect = new Rectangle();

	group = new Group();
	group.setLayoutX(0);
	group.setLayoutY(0);
	dotGroup = new Group();

	// draws grid background
	rect.setFill(Color.DODGERBLUE);
	group.getChildren().add(rect);

	rect.setLayoutX(0);
	rect.setLayoutY(0);
	rect.setWidth(xLines * cellWidth);
	rect.setHeight(yLines * cellWidth);
	rect.setId("clickarea");
	CFEventHandler eh = new CFEventHandler(this);
	eh.gl = g;
	rect.setOnMouseClicked((EventHandler<? super MouseEvent>) eh);

	// draws vertical and horizonzal grid lines
	for (int i = 0; i <= Math.max(xLines, yLines); i++) {
	    h = (i * cellWidth);
	    w = (i * cellWidth);

	    if (i <= yLines) {
		linelengthX = (cellWidth * gg[0].length);
		gridline = new Line(0, h, linelengthX, h);
		gridline.setStroke(gridColor);
		gridline.setSmooth(false);
		gridline.setStrokeWidth(2);
		group.getChildren().add(gridline);
	    }

	    if (i <= xLines) {
		linelengthY = (cellWidth * gg.length);
		gridline = new Line(w, 0, w, linelengthY);
		gridline.setStroke(gridColor);
		gridline.setSmooth(false);
		gridline.setStrokeWidth(2);
		group.getChildren().add(gridline);
	    }
	}

	// draws empty game tokens
	for (int y = 0; y < gg.length; y++) {
	    for (int x = 0; x < gg[y].length; x++) {
		centerX = (Math.floor(cellWidth / 2)) + (x * cellWidth);
		centerY = Math.floor(cellWidth / 2) + (y * cellWidth);
		circle = new Circle(centerX, centerY, 20, tokenColor);
		circle.setStrokeWidth(2);
		circle.setMouseTransparent(true);
		dotGroup.getChildren().add(circle);
	    }
	}
	group.getChildren().add(dotGroup);
	root.getChildren().add(group);
    }

    public void centerGrid() {
	MenuBar bar = (MenuBar) scene.lookup("#menubar");
	int bH = (int) bar.getHeight();
	int X = (int) Math.floor((root.getWidth() - (xLines * cellWidth)) / 2);
	int Y = (int) Math.floor(((root.getHeight() - (yLines * cellWidth)) / 2) + Math.floor(bH / 2));

	if (Y < bH) {
	    Y = bH;
	}
	group.setLayoutX(X);
	group.setLayoutY(Y);

    }

    public void drawToken(int row, int col, int player, Color c) {
	//Color c = (player == 1) ? Color.RED : Color.BLUE;
	int index = row * xLines + col;
	Circle circle = (Circle) dotGroup.getChildren().get(index);
	circle.setMouseTransparent(true);
	circle.setFill(c);
	moves.set(moves.get() + 1);
	if (g.getFreeFields() <= 0) {
	    gameOver();
	}
    }

}
