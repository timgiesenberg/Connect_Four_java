/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    
    private int getClickedCol(MouseEvent me) {
        double colWidth = 0.0;
        double clickX = 0.0;
        int clickedCol = -1;
        GridPane gp = null;

        if (me.getTarget().getClass().getCanonicalName().equals("javafx.scene.image.ImageView")) {
            ImageView iv = (ImageView) me.getTarget();
            gp = (GridPane) iv.getParent();
        } else if (me.getTarget().getClass().getCanonicalName().equals("javafx.scene.layout.GridPane")) {
            gp = (GridPane) me.getTarget();
        }

        if (gp != null) {
            colWidth = gp.getColumnConstraints().get(0).getMinWidth();
            clickX = me.getX();
            clickedCol = (int) Math.floor(clickX / colWidth);
            System.out.println("angeklickte Spalte: " + clickedCol);
            me.consume();
        }
        return clickedCol;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        GameLogic g = new GameLogic();
        g.getComputerMove();
        
        AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
	Scene scene = new Scene(root);

	int[][] gg = g.getGameGrid();
	
	int h;
	int w;
	int linelengthX, linelengthY;
	int cellWidth = 50;
	int marginX = 100;
	int marginY = 100;
	int xLines = gg[0].length;
	int yLines = gg.length;
	Line gridline;
	
	
	int useGridType = 1;
	
	// GRID TYPE 1
	
	

	
	if(useGridType == 1) {
	    Rectangle rect = new Rectangle(marginX, marginY, xLines*cellWidth, yLines*cellWidth);
	    
	    
	    root.getChildren().add(rect);
	}
	
	
	// GRID TYPE 2
	if(useGridType == 2 || useGridType == 1) {
	    Color gridColor = (useGridType == 1) ? Color.BLUE : Color.BLACK;
	    
	    for(int i = 0; i <= Math.max(xLines, yLines); i++) {
		h = marginY+(i*cellWidth);
		w = marginX+(i*cellWidth);

		if(i <= yLines) {
		    linelengthX = (cellWidth*gg[0].length)+marginX;
		    gridline = new Line(marginX, h, linelengthX, h);
		    gridline.setStroke(gridColor);
		    gridline.setSmooth(false);
		    gridline.setStrokeWidth(2);
		    root.getChildren().add(gridline);
	
		}

		if(i <= xLines) {
		    linelengthY = (cellWidth*gg.length)+marginY;
		    gridline = new Line(w, marginY, w, linelengthY);
		    gridline.setStroke(gridColor);
		    gridline.setSmooth(false);
		    gridline.setStrokeWidth(2);
		    root.getChildren().add(gridline);    
		}
	    }
	}
	
	

	
	
	
	
	
	/*
	
	final Canvas canvas = new Canvas(500,500);
	GraphicsContext gc = canvas.getGraphicsContext2D();

	
	gc.setLineWidth(2);
	gc.setStroke(Color.BLACK);

	int h;
	int w;
	int linelength;
	int cellWidth = 50;
	int margin = 100;
	for(int i = 0; i <= 7; i++) {
	    h = margin+(i*cellWidth);
	    w = margin+(i*cellWidth);
	    linelength = (cellWidth*7)+margin;
	    gc.strokeLine(margin, h, linelength, h);
	    gc.strokeLine(w, margin, w, linelength);
	}
	
	gc.fillOval(10, 10, 25, 25);
	*/
	
	//
	//gc.fillRect(10,10,210,10);

	//root.getChildren().add(canvas);
	
	stage.setScene(scene);
        stage.show(); 
	
	/*
        Scene scene = new Scene(root);

        scene.lookup("#gameFieldGrid").addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                        getClickedCol(me);
                    }
                }
        );
        
	
	Node gf = scene.lookup("#gameFieldGrid");
	
	
        ImageView iv1 = (ImageView) scene.lookup("#cell_0_1");
        ImageView iv2 = (ImageView) scene.lookup("#cell_4_3");
        
        File oFile = new File("./src/img/o_yel.bmp");
        File xFile = new File("./src/img/x_red.bmp");
        
        InputStream oIs = new BufferedInputStream(new FileInputStream(oFile.getAbsolutePath()));
        InputStream xIs = new BufferedInputStream(new FileInputStream(xFile.getAbsolutePath()));
        
        Image o = new Image(oIs);
        Image x = new Image(xIs);
        
        iv1.setImage(o);
        iv2.setImage(x);
        
        System.out.println(iv2.getId());
        */
    
	
	
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
		    if(gg[y][x] == 0) playerColor = Color.LIGHTGREY;
		} else {
		    if(gg[y][x] == 0) continue;
		}
		
		if(gg[y][x] == 1) {
		    playerColor = Color.RED;
		} else if(gg[y][x] == 2) {
		    playerColor = Color.BLUE;
		}
		centerX = (marginX+Math.floor(cellWidth/2))+(x*cellWidth);
		centerY = marginY+Math.floor(cellWidth/2)+(y*cellWidth);
		 circle = new Circle(centerX,centerY,20,playerColor);
		 circle.setStrokeWidth(2);
		 root.getChildren().add(circle);
	    }
	}
	
	
	
	
        

	
	
	
	//System.exit(0);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
