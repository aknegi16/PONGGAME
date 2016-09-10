/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dualgame;
import java.applet.*; 
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
/**
 * The control logic and main display panel for game.
 */
public class BallWorld extends Applet implements Runnable {
   
   boolean bar_up;
   boolean bar_down;
   Balls ball;         // A single bouncing Ball's instance
     // The container rectangular box
  
   //private DrawCanvas canvas; // Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;
         
                
      int appletsize_x = 640; // Größe des Applets in x - Richtung
	int appletsize_y = 400;
        bars b1,b2;
        ContainerBox box;
        
	public void init()
	{
        resize(appletsize_x,appletsize_y);
        box=new ContainerBox(2,2,appletsize_x,appletsize_y);
        b2=new bars(box.maxX-15,100); 
        b1=new bars(10,100);
		setBackground (Color.white);
                
                
                Random rand = new Random();
      int radius = 20;
     // int x = rand.nextInt(appletsize_x - radius * 2 - 20) + radius + 10;
     // int y = rand.nextInt(appletsize_y - radius * 2 - 20) + radius + 10;
     int x=0;
     int y=0;
      int speed = 5;
     // int angleInDegree = rand.nextInt(360);
      int angleInDegree=45;
      ball = new Balls(x, y, radius, speed, angleInDegree, Color.BLUE);
                
                
                
                
	}

	public void start ()
	{
		Thread th = new Thread (this);
		th.start ();
	}

	/*public void stop()
	{

	}
*/
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
        
         /*public boolean keyUp (Event e, int key)
         {
             
             if (key == Event.UP||key==Event.DOWN)
             {
             bar_up=false;
             bar_down=false;
             
             }
             
             
             
             
             return true;
         }*/
        public boolean mouseMove( Event e, int x, int y)
	{
		b2.y_pos=y-50;

		return true;
	}
        
        
        
        /*
	public void destroy()
	{

	}*/

	public void run ()
	{
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
                    
		// Solange true ist läuft der Thread weiter
		while (true)
		{
                    
                    
                   
                    
                    
                    
			/*if (x_pos > appletsize_x - radius)
			{
				// Ändern der Richtung des Balles
				x_speed = -x_speed;
			}
			// Ball brührt linken Rand und prallt ab
			 if (x_pos < radius)
			{
				// Ändern der Richtung des Balles
				x_speed = -x_speed;
                                x_pos=radius;
			}

                        else if(x_pos+radius > appletsize_x )
                        
                        {x_speed = -x_speed;
                  x_pos = appletsize_x - radius;
               }
               // May cross both x and y bounds
               if (y_pos - radius < 0) {
                  y_speed = -y_speed;
                  y_pos = radius;
               } else if (y_pos + radius > appletsize_y) {
                  y_speed = -y_speed;
                  y_pos = appletsize_y - radius;
               }
			// Verändern der x- Koordinate
			x_pos += x_speed;
                        y_pos += y_speed;
                        
                        */
                        
                        ball.moveOneStepWithCollisionDetection(box,b1,b2);
                        movebars();
                        repaint();

			try
			{
				// Stoppen des Threads für in Klammern angegebene Millisekunden
				Thread.sleep (1000/50);
			}
			catch (InterruptedException ex)
			{
				// do nothing
			}

			// Zurücksetzen der ThreadPriority auf Maximalwert
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}

        /*
        public void update (Graphics g)
	{
		// Initialisierung des DoubleBuffers
		if (dbImage == null)
		{
			dbImage = createImage (this.getSize().width, this.getSize().height);
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
      
      
      
      
      
   
  
      public void paint (Graphics g)
{    // Paint background
    g.setColor(Color.black);
               
         // Draw the box and the ball
         box.draw(g);
         ball.draw(g);
         b1.draw(g);
         b2.draw(g);
         // Display ball's information
         g.setColor(Color.WHITE);
         g.setFont(new Font("Courier New", Font.PLAIN, 12));
         g.drawString("Ball " + ball.toString(), 20, 30);
      }
  
      
   }

