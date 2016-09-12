/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dualgame;

import java.applet.*; 
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author intex
 */
public class MainGame extends Applet implements Runnable {
    
    JButton but; 
    
       
       static public AudioClip Clip;
        static public Image image;
    
        
        boolean bar_up;
   boolean bar_down;
         // A single bouncing Ball's instance
     // The container rectangular box
  private Image dbImage;
	private Graphics dbg;
   //private DrawCanvas canvas; // Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;
        static int state=1;
                
      int appletsize_x = 640; // Größe des Applets in x - Richtung
	int appletsize_y = 400;
        static bars b1,b2;
        static ContainerBox box;
          static Balls ball; 
        
         BallWorld ballworld;
            
        
    public void init()
    {
         box=new ContainerBox(2,2,appletsize_x,appletsize_y);
        b2=new bars(box.maxX-15,100,0); 
        b1=new bars(10,100,0);
       
		setBackground (Color.white);
                
                
                //Random rand = new Random();
      int radius = 20;
     // int x = rand.nextInt(appletsize_x - radius * 2 - 20) + radius + 10;
     // int y = rand.nextInt(appletsize_y - radius * 2 - 20) + radius + 10;
     int x=0;
     int y=0;
      int speed = 5;
     // int angleInDegree = rand.nextInt(360);
      int angleInDegree=45;
      Color mycolor = new Color (228, 141, 42);
      ball = new Balls(x, y, radius, speed, angleInDegree, mycolor);
               
                 ballworld=new BallWorld();
              
    setLayout(new BorderLayout());
                but=new JButton("HELLO"); 
               //making panels
                Panel p1=new Panel();
                
                add(p1);
              
               //
               
               // p1.add(new Welcome(),BorderLayout.NORTH);
                
                 
               p1.add(ballworld,BorderLayout.SOUTH);
               
                p1.setVisible(true);
           // p1.add(but,BorderLayout.EAST);
               resize(appletsize_x,appletsize_y);
    Clip = getAudioClip(getDocumentBase(), "fire.wav");
        image = getImage(getDocumentBase(), "football.png");
               
               
                  
       
               
               
               
               
               
               
    
    }
    
    
    
    
    
    public void start ()
	{    
         
		Thread th = new Thread (this);
		                          
                th.start ();
	}
@Override
        public boolean keyDown (Event e, int key)
	{
		// linke Cursortaste
		if (key == Event.UP)
		{
			// Ball bewegt sich dann nach links
			bar_up=true;
                        bar_down=false;
                       
                        
                }
		// rechte Cursortaste
		else if (key == Event.DOWN)
		{bar_down=true;
                bar_up=false;
			// Ball bewegt sich dann nach rechts
			
		}
		// SpaceBar hat Wert 32
		
               
                
                
		else
		{
			// Ausgabe von gedrüktem Key und Zahlenwert an die Standardausgabe
			System.out.println ("Charakter: " + (char)key + " Integer Value: " + key);
		}

		return true;
	}
        
        public boolean keyReleased (Event e, int key)
         {
             
             if (key == Event.UP||key==Event.DOWN)
             {
             bar_up=false;
             bar_down=false;
             
             }
             
             
             
             
             return true;
         }
        public boolean mouseMove( Event e, int x, int y)
	{
		b2.y_pos=y-50;

		return true;
	}
        
         void movebars()
      {//for up
        if(b1.y_pos==0)
        {     
        bar_up=false;}
      if(b1.y_pos+100==appletsize_y)
      {bar_down=false;
      }  
       if(bar_up)   
       b1.y_pos+=b1.y_speed*-1;
      if(bar_down)
       b1.y_pos+=b1.y_speed;
      
      
      }
        
        /*
	public void destroy()
	{

	}*/
	/*public void stop()
	{

	}
*/
        

        /*
        public void update (Graphics g)
	{
		// Initialisierung des DoubleBuffers
		if (dbImage == null)
		{
			dbImage = createImage (appletsize_x, appletsize_y);
			dbg = dbImage.getGraphics ();
		}

		// Bildschirm im Hintergrund löschen
		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

		// Auf gelöschten Hintergrund Vordergrund zeichnen
		dbg.setColor (getForeground());
		paint (dbg);

		// Nun fertig gezeichnetes Bild Offscreen auf dem richtigen Bildschirm anzeigen
		g.drawImage (dbImage, 0, 0, this);
	}
        */
         public void run ()
	{
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
                    
		// Solange true ist läuft der Thread weiter
		
                while (true)
		{                 
                   
                    
                    
                    
                        
                    
                     ball.moveOneStepWithCollisionDetection(box,b1,b2);
                        movebars();
                       ballworld.repaint();
                    
			try
			{
				// Stoppen des Threads für in Klammern angegebene Millisekunden
				Thread.sleep (1000/55);
			}
			catch (InterruptedException ex)
			{
				// do nothing
			}
                    
			// Zurücksetzen der ThreadPriority auf Maximalwert
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}
    
         
      
   
    
   

	
      
      
   }

  
        
       
        
    
    

