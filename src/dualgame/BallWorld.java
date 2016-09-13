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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 * The control logic and main display panel for game.
 */
public class BallWorld extends Applet implements Runnable {
   
   private Image dbImage;
	private Graphics dbg;
   boolean bar_up1, bar_down1,bar_up2,bar_down2;
   Balls ball;         // A single bouncing Ball's instance
     // The container rectangular box
  
   //private DrawCanvas canvas; // Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;
        static public AudioClip hit,miss,win,back;
        static public Image image;
                
      int appletsize_x = 640; // Größe des Applets in x - Richtung
	int appletsize_y = 400;
        bars b1,b2;
        ContainerBox box;
        Cursor c; 
        boolean isStoped = true;
        
        
        enum WINNER {b1,b2};
        WINNER winner;
        int point1;
        int point2;
        
        //int xspeed=10;
       // int yspeed=2;
        private int speed;	
        
        int radius = 20;
         int x;
     int y;
      float bspeed ;
      int angleInDegree;
      Random rand = new Random(); 
	public void init()
	{ bspeed=5;
        angleInDegree=45;
        x=321;
        y=402;
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
        b2=new bars(box.maxX-15,100,10); 
        b1=new bars(10,100,0);
		setBackground (Color.white);
                
                
             winner=null;   
      
     // int x = rand.nextInt(appletsize_x - radius * 2 - 20) + radius + 10;
     // int y = rand.nextInt(appletsize_y - radius * 2 - 20) + radius + 10;
    
     //int angleInDegree = rand.nextInt(360);
      
      ball = new Balls(x, y, radius,bspeed, angleInDegree, Color.BLUE);
               hit = getAudioClip(getDocumentBase(), "fire.wav");
               back = getAudioClip(getDocumentBase(), "back.wav");
               miss = getAudioClip(getDocumentBase(), "miss.wav");
               win = getAudioClip(getDocumentBase(), "winner.wav");
        image = getImage(getDocumentBase(), "football.png");
                
                int point1=b1.score;
        int point2=b2.score;
                
	}

	public void start ()
	{back.play();
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
			bar_up2=true;
                        //bar_down=false;
                       
                        
                }
		// rechte Cursortaste
		else if (key == Event.DOWN)
		{bar_down2=true;
                //bar_up=false;
			// Ball bewegt sich dann nach rechts
			
		}
		// SpaceBar hat Wert 32
		
               if (key == 97)
		{
			// Ball bewegt sich dann nach links
			bar_up1=true;
                        //bar_down=false;
                       
                        
                }
		// rechte Cursortaste
		else if (key == 122)
		{bar_down1=true;
                //bar_up=false;
			// Ball bewegt sich dann nach rechts
			
		}
		// SpaceBar hat Wert 32
                
                
		

		return true;
	}
        public boolean keyUp (Event e, int key)
	{if (key == Event.UP)
		{
			// Ball bewegt sich dann nach links
			bar_up2=false;
                        //bar_down=false;
                       
                        
                }
		// rechte Cursortaste
		else if (key == Event.DOWN)
		{bar_down2=false;
                //bar_up=false;
			// Ball bewegt sich dann nach rechts
			
		}
		// SpaceBar hat Wert 32
		
               if (key ==97)
		{
			// Ball bewegt sich dann nach links
			bar_up1=false;
                        //bar_down=false;
                       
                        
                }
		// rechte Cursortaste
		else if (key == 122)
		{bar_down1=false;
                //bar_up=false;
			// Ball bewegt sich dann nach rechts
			
		}
                
		// Wenn Spiel noch nicht gestartet ist, oder wieder gestartet wird
		if (key == 32)
		{
		    // Alle wichtigen Werte zurücksetzen
			isStoped = false;
                         
			init ();
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
       /* public boolean mouseMove( Event e, int x, int y)
	{
		b2.y_pos=y-50;

		return true;
	}
        */
        
        
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
                        if(ball.isOUT(box,b1,b2))
                        {   try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(BallWorld.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            int n=lastpoint();
                        if(n==1)    
                        {
                            
                            angleInDegree = 135;
                                
                            ball=new Balls(500, 0, radius, (bspeed=bspeed+0.2f), angleInDegree, Color.BLUE);}
                        if(n==2)    
                        {
                            angleInDegree = 45;
                            ball=new Balls(100, 0, radius, (bspeed=bspeed+0.2f), angleInDegree, Color.BLUE);
                        
                        }
                        
                        }
                        
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
        bar_up1=false;}
      if(b1.y_pos+100==appletsize_y)
      {bar_down1=false;
      }  
       if(bar_up1)   
       b1.y_pos+=b1.y_speed*-1;
      if(bar_down1)
       b1.y_pos+=b1.y_speed;
      
      
      
      
      
      
      //for 
      if(b2.y_pos==0)
        {     
        bar_up2=false;}
      if(b2.y_pos+100==appletsize_y)
      {bar_down2=false;
      }  
       if(bar_up2)   
       b2.y_pos+=b2.y_speed*-1;
      if(bar_down2)
       b2.y_pos+=b2.y_speed;
      
      
      
      
      
      
      }
      
      boolean winner()
      {
      if(b1.score>10)
      {winner=WINNER.b1;
      
      return true;}
      else if(b2.score>10)
      {winner=WINNER.b2;
      
      return true;}
      else 
          return false;
      }
      
      
       int lastpoint()
      {
      if(b1.score>point1)
      {point1=b1.score;
      return 1;
      }
      if(b2.score>point2)
      {point2=b2.score;
      return 2;
      }else return 0;
      
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
         
         g.setColor((b1.score==10||b2.score==10)?Color.RED:Color.WHITE);
         g.setFont(new Font("Berlin Sans FB", Font.PLAIN, 70));
         
         g.drawString(""+b1.score, 100, 100);
         g.drawString(""+b2.score, 500, 100);
   
    
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

