/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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
 *
 * @author tim.giesenberg@me.com
 */
public class ConnectFour extends Application {
    
    Group group;
    Group dotGroup;
    AnchorPane root;
    Scene scene;
    int xLines;
    int yLines;
    int cellWidth;
    
    private void centerGrid() {
	MenuBar bar = (MenuBar) scene.lookup("#menubar");
	int bH = (int) bar.getHeight();
	int X = (int)Math.floor((root.getWidth() - (xLines*cellWidth))/2);
	int Y = (int)Math.floor(((root.getHeight() - (yLines*cellWidth))/2)+Math.floor(bH/2));
	
	if(Y < bH) Y = bH;
	group.setLayoutX(X);
	group.setLayoutY(Y);
	
	
	//if(stage.getWidth() < stage.getMinWidth()) stage.setWidth(stage.getMinWidth());
	//if(stage.getHeight() < stage.getMinHeight()) stage.setHeight(stage.getMinHeight());
	
	
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        GameLogic g = new GameLogic();
        g.getComputerMove();
        
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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
	
	

	MenuBar menuBar = (MenuBar) scene.lookup("#menubar");
	Node menu;
	menu = scene.lookup("#mGame");
	MenuItem aboutMenuItem =
            MenuItemBuilder.create()
                           .text("Close")
                           .onAction(
                               new EventHandler<ActionEvent>()
                               {
                                  @Override public void handle(ActionEvent e)
                                  {
                                     System.exit(0);
                                  }
                               })
                           .accelerator(
                               new KeyCodeCombination(
                                  KeyCode.Q, KeyCombination.CONTROL_DOWN))
                           .build();             
         menuBar.getMenus().get(0).getItems().add(aboutMenuItem);
	
	 
	 
	stage.setScene(scene);
	
        stage.show(); 
	
	
	
	//rect2.setStroke(Color.RED);
	int[][] gg = g.getGameGrid();
	
	int h;
	int w;
	int linelengthX, linelengthY;
	cellWidth = 50;
	
	int marginY = 0;
	xLines = gg[0].length;
	yLines = gg.length;
	int marginX = 0; //(int)Math.floor((root.getWidth() - (xLines*cellWidth))/2);
	
	

	Line gridline;
	group = new Group();
	group.setLayoutX(0);
	group.setLayoutY(0);
	dotGroup = new Group();
	
	Rectangle rect = (Rectangle) scene.lookup("#recta");
	rect.setFill(Color.DODGERBLUE);
	group.getChildren().add(rect); 

	
	int useGridType = 1;
	
	// GRID TYPE 1
	
	if(useGridType == 1) {
	    //Rectangle rect = new Rectangle(marginX, marginY, xLines*cellWidth, yLines*cellWidth);
	    rect.setLayoutX(0);
	    rect.setLayoutY(0);
	    rect.setWidth(xLines*cellWidth);
	    rect.setHeight(yLines*cellWidth);
	    rect.setId("clickarea");
	    CFEventHandler eh = new CFEventHandler(this);
	    eh.gl = g;
	    rect.setOnMouseClicked((EventHandler<? super MouseEvent>) eh);
	    //root.getChildren().add(rect);
	}
		
	// GRID TYPE 2
	if(useGridType == 2 || useGridType == 1) {
	    Color gridColor = (useGridType == 1) ? Color.BLUE : Color.BLACK;
	    
	    for(int i = 0; i <= Math.max(xLines, yLines); i++) {
		h = (i*cellWidth);
		w = (i*cellWidth);

		if(i <= yLines) {
		    linelengthX = (cellWidth*gg[0].length);
		    gridline = new Line(0, h, linelengthX, h);
		    gridline.setStroke(gridColor);
		    gridline.setSmooth(false);
		    gridline.setStrokeWidth(2);
		    //root.getChildren().add(gridline);
		    group.getChildren().add(gridline);
		}

		if(i <= xLines) {
		    linelengthY = (cellWidth*gg.length);
		    gridline = new Line(w, 0, w, linelengthY);
		    gridline.setStroke(gridColor);
		    gridline.setSmooth(false);
		    gridline.setStrokeWidth(2);
		    //root.getChildren().add(gridline);    
		    group.getChildren().add(gridline);
		}
	    }
	}
	
	
	
	
	Test test = new Test(g.gamefield);
	test.run();
	ArrayList<FourLine> lines = g.check();
	for (FourLine line : lines) {
	    System.out.println(line.toString());
	}
	
	test.clearGrid();
	test.fillRandom();
	test.printGrid();
	lines = g.check();
	test.printResult(lines);
	test.clearGrid();
	test.fillRandom2();
	test.printGrid();
	lines = g.check();
	test.printResult(lines);
	test.clearGrid();
	
	
	
	for(int i = 0; i < g.gamefield[0].length;i++) {
	    System.out.println("Naechste freie Zeile in Spalte "+i+": "+g.getNextRow(i));
	
	}
	
	int m = 1;
	
	double centerX, centerY;
	Circle circle;
	Color playerColor = Color.YELLOW;
	System.out.println(gg.length + " "+ gg[0].length);
	
	
	for(int y = 0; y < gg.length; y++) {
	    for(int x  = 0; x < gg[y].length; x++) {
		if(useGridType==1) {
		    if(gg[y][x] == 0) playerColor = Color.web("f4f4f4");
		} else {
		    if(gg[y][x] == 0) continue;
		}
		
		if(gg[y][x] == 1) {
		    playerColor = Color.RED;
		} else if(gg[y][x] == 2) {
		    playerColor = Color.BLUE;
		}
		centerX = (Math.floor(cellWidth/2))+(x*cellWidth);
		centerY = Math.floor(cellWidth/2)+(y*cellWidth);
		 circle = new Circle(centerX,centerY,20,playerColor);
		 circle.setStrokeWidth(2);
		 circle.setMouseTransparent(true);
		 
		 dotGroup.getChildren().add(circle);
	    }
	}
	group.getChildren().add(dotGroup);
	root.getChildren().add(group); 
	
	scene.widthProperty().addListener(new ChangeListener<Number>() {
	    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		 centerGrid();
		 
	    }
	});
	
	scene.heightProperty().addListener(new ChangeListener<Number>() {
	    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		 centerGrid();
	    }
	});
	
        centerGrid();

	//System.exit(0);
    }
    
    
    public void drawToken(int row, int col, int player) {
	double centerX, centerY;
	Color c = (player == 1) ? Color.RED : Color.BLUE;
		
	//centerX = (Math.floor(cellWidth/2))+(col*cellWidth);
	//centerY = Math.floor(cellWidth/2)+(row*cellWidth);
	int index = row*xLines+col;
	System.out.println("ROW: "+ row +  " COL: " + col + " Ylines: " + yLines + " Xlines: " + xLines + " Index: "+ index);
	
	Circle circle = (Circle) dotGroup.getChildren().get(index);
	circle.setMouseTransparent(true);
	circle.setFill(c);
	
    }
    





    
    
}
