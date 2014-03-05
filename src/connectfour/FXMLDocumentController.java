/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private GridPane gameFieldGrid;
    
    @FXML
    private ImageView iv;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //TODO (ef:ef) loop through gridpane and add an imageview in every cell
        //gameFieldGrid.add(red, 5, 6);
        
        // TODO (ef:tg) integrate relative Uri 
        File oFile = new File("/Users/timbo/Development/ConnectFour/src/img/o_yel.bmp");
        File xFile = new File("/Users/timbo/Development/ConnectFour/src/img/x_red.bmp");
        
        Image o = new Image("file://" + oFile.getAbsolutePath(), true);
        Image x = new Image("file://" + xFile.getAbsolutePath(), true);
        
        for(int i = 0; i <= 6; i++){
            for(int j = 0; j <= 6; j++){
                ImageView iv = new ImageView();
                iv.setId("cell_" + String.valueOf(i) + "_" + String.valueOf(j));
                
                iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClick<MouseEvent>());
                
                gameFieldGrid.add(iv, i, j);
            }
        }

        /**
        gameFieldGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if(arg0.getEventType() == MouseEvent.MOUSE_CLICKED){
                    System.out.println(arg0.getSceneX() + "," + arg0.getSceneY());
                }
            }
        });/**/
        gameFieldGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClick<MouseEvent>());
    }
    
    public void test(){
        //System.out.println(gameFieldGrid.getOnMouseClicked().handle(iv));
        
    }
    
}
