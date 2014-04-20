/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);

        scene.lookup("#gameFieldGrid").addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                        getClickedCol(me);
                    }
                }
        );
        
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
        
       // stage.setScene(scene);
       // stage.show(); 
	
	
	Test test = new Test(g.gamefield);
	test.run();
	ArrayList<FourLine> lines = g.check();
	for (FourLine line : lines) {
	    System.out.println(line.toString());
	}
	
	
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
