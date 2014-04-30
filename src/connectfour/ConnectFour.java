package connectfour;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author sebastian.gohres@gmail.com
 * @author tim.giesenberg@me.com
 */
public class ConnectFour extends Application {

    static int GAMEMODE_LOCALE = 1;
    static int GAMEMODE_REMOTE = 2;
    
    Group group;
    Group dotGroup;
    AnchorPane root;
    Scene scene;
    int xLines;
    int yLines;
    int cellWidth;
   
    @FXML private Menu menuGridSize;
    

    private void centerGrid() {
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

    @Override
    public void start(Stage stage) throws Exception {
	root = (AnchorPane) FXMLLoader.load(getClass().getResource("/connectfour/views/FXMLDocument.fxml"));
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

        drawGrid(3, 4, GAMEMODE_LOCALE);
	centerGrid();
    }
    
    private Point2D getGridSize() {
        Point2D gridSize = new Point2D(3,3);
        //menuGridSize.getItems()
        return gridSize;
    }
    
    public void drawGrid(int rows, int cols, int mode) {
        GameLogic g = new GameLogic();
        
        int[][] gg = new int[rows][cols];
        int h, w, linelengthX, linelengthY;
        double centerX, centerY;
        
        xLines = gg[0].length;
	yLines = gg.length;
        cellWidth = 50;
                
	Color gridColor = Color.BLUE;
        Color tokenColor = Color.web("f4f4f4");
        
        Line gridline;
	Circle circle;
        Rectangle rect = (Rectangle) scene.lookup("#recta");
       
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
		//root.getChildren().add(gridline);
		group.getChildren().add(gridline);
	    }

	    if (i <= xLines) {
		linelengthY = (cellWidth * gg.length);
		gridline = new Line(w, 0, w, linelengthY);
		gridline.setStroke(gridColor);
		gridline.setSmooth(false);
		gridline.setStrokeWidth(2);
		//root.getChildren().add(gridline);    
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

    public void drawToken(int row, int col, int player) {
	double centerX, centerY;
	Color c = (player == 1) ? Color.RED : Color.BLUE;
	int index = row * xLines + col;
	Circle circle = (Circle) dotGroup.getChildren().get(index);
	circle.setMouseTransparent(true);
	circle.setFill(c);
    }

}
