/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dualgame;
import java.applet.*; 
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Random;
import javax.swing.*;
/**
 * The control logic and main display panel for game.
 */
public class BallWorld extends Applet implements Runnable {
   
   private Image dbImage;
	private Graphics dbg;
   boolean bar_up;
   boolean bar_down;
   Balls ball;         // A single bouncing Ball's instance
     // The container rectangular box
  
   //private DrawCanvas canvas; // Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;
        static public AudioClip Clip;
        static public Image image;
                
      int appletsize_x = 640; // Größe des Applets in x - Richtung
	int appletsize_y = 400;
        bars b1,b2;
        ContainerBox box;
        Cursor c; 
        boolean isStoped = true;
        
        
        enum WINNER {b1,b2};
        WINNER winner=null;
        
        
        
        private int speed;	
        
        
	public void init()
	{
if (getParameter ("speed") != null)
		{
			speed = Integer.parseInt(getParameter("speed"));
		}
		else speed = 15;
// generate a Crosshair cursor 
c = new Cursor (Cursor.CROSSHAIR_CURSOR); 
// set this cursor as the standard cursor of the applet 
this.setCursor (c); 
        resize(appletsize_x,appletsize_y);
        box=new ContainerBox(2,2,appletsize_x,appletsize_y);
        b2=new bars(box.maxX-15,100,0); 
        b1=new bars(10,100,0);
		setBackground (Color.white);
                
                
                Random rand = new Random(); 
      int radius = 20;
     // int x = rand.nextInt(appletsize_x - radius * 2 - 20) + radius + 10;
     // int y = rand.nextInt(appletsize_y - radius * 2 - 20) + radius + 10;
     int x=321;
     int y=402;
      int bspeed = 5;
     // int angleInDegree = rand.nextInt(360);
      int angleInDegree=45;
      ball = new Balls(x, y, radius, bspeed, angleInDegree, Color.BLUE);
                Clip = getAudioClip(getDocumentBase(), "fire.wav");
        image = getImage(getDocumentBase(), "football.png");
                
                
                
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
        
        
        public boolean mouseDown (Event e, int x, int y)
	{
		// Spiel läuft
		
		// Wenn Spiel noch nicht gestartet ist, oder wieder gestartet wird
		if (isStoped && e.clickCount == 2)
		{
		    // Alle wichtigen Werte zurücksetzen
			isStoped = false;
                         
			init ();
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
                                     
                   
                    
                        if (!winner() && !isStoped )
                        {ball.moveOneStepWithCollisionDetection(box,b1,b2);
                        movebars();
                        repaint();
                                                                
                         
                 
                      
         
                        
                        }

			try
			{
				// Stoppen des Threads für in Klammern angegebene Millisekunden
				Thread.sleep (speed);
			}
			catch (InterruptedException ex)
			{
				// do nothing
			}

			// Zurücksetzen der ThreadPriority auf Maximalwert
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}

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
      
      boolean winner()
      {
      if(b1.score>10)
      {winner=WINNER.b1;return true;}
      else if(b2.score>10)
      {winner=WINNER.b2;return true;}
      else 
          return false;
      }
      
      
      
   
  
      public void paint (Graphics g)
{    // Paint background
    if (isStoped)
			{
				g.setColor (Color.BLACK);
				g.drawString ("Doubleclick to start Game!", 40, 200);
			}
    else if (!winner())
    {g.setColor(Color.black);
               
         // Draw the box and the ball
         box.draw(g);
         ball.draw(g);
         b1.draw(g);
         b2.draw(g);
         // Display ball's information
         
         g.setColor(Color.WHITE);
         g.setFont(new Font("Courier New", Font.PLAIN, 40));
         g.drawString(b1.score+ " " +b2.score, 200, 100);
   
    
    }
       else if (winner())
		{         box.draw(g);
			g.setColor (Color.BLACK);

			// Erreichte Punkte und game over
                         g.setFont(new Font("Courier New", Font.PLAIN, 40));
			g.drawString ("GAME OVER", 210, 100);
			 g.setFont(new Font("Courier New", Font.PLAIN, 70));
                        g.drawString ( ""+b1.score , 105, 201);
                        g.drawString ( ""+b2.score , 480, 201);
                        g.setFont(new Font("Courier New", Font.PLAIN, 50));
                        if(winner==WINNER.b1)
                            g.drawString ( "YOU WON" , 80, 300);
                        if(winner==WINNER.b2)
                            g.drawString ( "YOU WON" , 400, 300);
            //g.drawString ("Doubleclick on the Applet, to play again!", 20, 220);

			isStoped = true;
                }
  
      
   }}

