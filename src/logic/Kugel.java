package logic;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;


    public class Kugel extends Circle 
    {          
        private static ArrayList<Kugel> kugeln = new ArrayList<Kugel>();
        
        private double startX = 100;
        private double startY = 100;
        private boolean canBeDraged;
        
        private double x_delta;
        private double y_delta; 
    
        private double radius;
        private double masse = 25;      //Realistischer Wert?
   
        private double g = 9.81; //Schwerkraft
        private double Vk = 0;
        private double a = masse * g;
        private double winkel = 90;
    
    public Bahn kolBahn;
 
        
    public Kugel(double radius, double x, double y, boolean draged) 
    {        
       super();                                                                 //Greift auf Superklasse zu (hier Circle)
       
       this.radius = radius;
       this.canBeDraged = draged;
       
       if(canBeDraged)
       {
           this.setFill(Color.BLACK);
       }
       else
       {
           this.setFill(Color.RED);
       }
       
       super.setRadius(radius);
       
       startX = x;
       startY = y;
       
       //Start Position der Kugel
       super.setCenterX(startX);                                            
       super.setCenterY(startY);          
       
       kugeln.add(this);
    }    

    public static ArrayList<Kugel> getKugeln()
    {
        return kugeln;
    }
    
    public boolean getCanBeDraged()
    {
        return canBeDraged;
    }
    
    public void setStartX(double newX)
    {
        startX = newX;
    }
    
    public void setStartY(double newY)
    {
        startY = newY;
    }
    
    public double getVk()
    {
        return Vk;
    }
    
    public void setVk(double newVk)
    {
        Vk = newVk;
    }
    
    public double getA()
    {
        return a;
    }
    
    public void setA(double newA)
    {
        a = newA;
    }
    
    public double getXDelta()
    {
        return x_delta;        
    }
    
    public void setXDelta(double newX)
    {
        x_delta = newX;
    }
    
    public double getYDelta()
    {
        return y_delta;
    }
    
    public void setYDelta(double newY)
    {
        y_delta = newY;
    }
    
    public double getWinkel()
    {
        return winkel;
    }
    
    public void setWinkel(double newWinkel)
    {
        winkel = newWinkel;
    }
    
    public double getMasse()
    {
        return masse;
    }
  }