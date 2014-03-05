/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * @author tim.giesenberg@me.com
 * 
 * @param <MouseEvent>
 */
public class MouseClick<MouseEvent> implements EventHandler{

    @Override
    public void handle(Event t) {
        System.out.println("Handle click");
        
        //this line throws an Exception
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
