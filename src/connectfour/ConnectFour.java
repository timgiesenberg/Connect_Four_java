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
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class ConnectFour extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        GameLogic g = new GameLogic();
        g.getComputerMove();
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);

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
        
        stage.setScene(scene);
        stage.show();
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
