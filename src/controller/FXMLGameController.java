package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import logic.*;



public class FXMLGameController implements Initializable 
{
    @FXML   
    private GridPane mainPane;
    private Button simButton;
    private Label timer;
     
    @FXML private Pane simPane;
    private VBox elementsBox, controllsBox;
    private final Integer startTime=60;
    private Integer seconds=startTime;
    @FXML 
    private AnchorPane rootPane;
    @FXML
    public Button level1;



    private ArrayList<Object> dragList = new ArrayList<Object>();
    private DragDrop drag = new DragDrop();

    private final double radius = 20;
    
    private Simulation sim;
        
    
    @FXML
    private void handleSimButtonAction(ActionEvent event) 
    {      
        KeyFrame k = new KeyFrame(Duration.millis(10),
            e->
            {
                sim.move();                                   
            });
       
       Timeline t= new Timeline(k);
            t.setCycleCount(Timeline.INDEFINITE);
            t.play();
 
    }
   
    private void load () 
    { 
   //Bahn bahn = new Bahn(50, 150, 250, 250, true);//Negative Steigung   x1 y1 x2 y2   
    //Bahn bahn1 = new Bahn(300, 50, 450, 250, false);//Negative Steigung   x1 y1 x2 y2  
    //Unterschied?
    Bahn bahn = new Bahn(250, 250, 50, 150, true);//Negative Steigung   x1 y1 x2 y2   
    Bahn bahn1 = new Bahn(450, 250, 300, 50, false);//Negative Steigung   x1 y1 x2 y2 
    //Bahn bahn = new Bahn(400, 250, 150, 350, true);
    
    Kugel kugel = new Kugel(radius, 100, 100, false);
    Kugel kugel1 = new Kugel(radius, 300, 100, true);
    sim = new Simulation();   
       
  
    ArrayList<Bahn> bahnen = Bahn.getBahnen();
    ArrayList<Kugel> kugeln = Kugel.getKugeln();
        
        for(int i = 0; i < bahnen.size(); i++)
                {
                    simPane.getChildren().add(bahnen.get(i));
                }
                
                for(int i = 0; i < kugeln.size(); i++)
                {
                    simPane.getChildren().add(kugeln.get(i));
                }
        //DragAndDrop Aufrufen       
       //Objekte die ROT sind können nicht bewegt werden!!
       for(int i = 0; i < kugeln.size(); i++)
       {          
           Kugel k = kugeln.get(i);
           
           if(k.getCanBeDraged())
           {
                drag.dragKugel(k);
           }
       }
       
       for(int i = 0; i < bahnen.size(); i++)
       {
           Bahn b = bahnen.get(i);
           
           if(b.getCanBeDraged())
           {
            drag.dragBahn(b);
           }
       }     
    //Drag And Drop END
    }
    
    //~~~~~~~~~NEW~~~~~~~~
    /*
        Änderungen in
        FMXLGameController.java
        DragDrop.java
        Bahn.java
        FXMLGame.fxml
        */
    
    @FXML
    private void handlePlus(ActionEvent event)
    {
        Bahn b = drag.getBahn();
        
        if(b.getDeltaX() < 500 && b.getDeltaX() > -500)
        {
        if(b.getKleineresX() == b.getStartX())
        {
            b.setStartX(b.getStartX() - 5);            
            b.setEndX(b.getEndX() + 5);            
        }
        else
        {
            b.setStartX(b.getStartX() + 5);
            b.setEndX(b.getEndX() - 5);
        }
        
        b.setStartY(b.getFunktionNachX(b.getStartX()));
        b.setEndY(b.getFunktionNachX(b.getEndX()));
        
        b.werteBerechnen();       
        }
    }
    
    @FXML
    private void handleMinus(ActionEvent event)
    {
        Bahn b = drag.getBahn();
        
        if(b.getDeltaX() > 50 || b.getDeltaX() < -50)
        {
        if(b.getKleineresX() == b.getStartX())
        {
            b.setStartX(b.getStartX() + 5);            
            b.setEndX(b.getEndX() - 5);            
        }
        else
        {
            b.setStartX(b.getStartX() - 5);
            b.setEndX(b.getEndX() + 5);
        }
        
        b.setStartY(b.getFunktionNachX(b.getStartX()));
        b.setEndY(b.getFunktionNachX(b.getEndX()));
        
        b.werteBerechnen();
        }        
    }
    
    @FXML
    private void handleLeft(ActionEvent event) 
    {                   
        Bahn b = drag.getBahn();
       
       //Routieren
       b.setRot(b.getRot() - 5);
       b.setRotate(b.getRot());
            

       b.werteBerechnen();       
    }
    
    @FXML
    private void handleRight(ActionEvent event)
    {
        Bahn b = drag.getBahn();
       
       //Routieren
       b.setRot(b.getRot() + 5);
       b.setRotate(b.getRot());
            

       b.werteBerechnen();  
    }
    //~~~~~~~~~~~END~~~~~~~~~~~~~
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       //timer();
       load();
       
    }                
}

        
//  @FXML           
//    public void timer() {
//        Timeline time = new Timeline();
//        time.setCycleCount(Timeline.INDEFINITE);
//      
//       
//       KeyFrame frame=new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
//           seconds--;
//           timer.setText(seconds.toString());
//           if(seconds<=0){
//               time.stop();
//           }
//        });
//        
//    time.playFromStart();
//    
//}