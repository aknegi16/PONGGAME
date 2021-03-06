/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dualgame;

import java.awt.*;



public class Balls {  
  
    
   int x_pos ;		
	int y_pos;	
	int radius ;
        int angle;
private Image dbImage;
	private Graphics dbg;
        float x_speed;
        float y_speed ;// Geschwindigkeit des Balles in x - Richtung
	Color color;		// Radius des Balles
	float speed;
         public Balls(int x, int y, int radius, float speed, int angleInDegree,
         Color color) {
      this.x_pos = x;
      this.y_pos = y;
      this.angle=angleInDegree;
      this.speed=speed;
      // Convert (speed, angle) to (x, y), with y-axis inverted
      this.x_speed = (float)(speed * 2*Math.cos(Math.toRadians(angle)));
      this.y_speed = (float)(speed * Math.sin(Math.toRadians(angle)));
      //this.x_speed =xspeed;
      //this.y_speed =yspeed;
      this.radius = radius;
      this.color = color;
   }
        

	public void draw(Graphics g)
	{ /*box 
            g.setColor(box.colorFilled);
      g.fillRect(box.minX, box.minY, box.maxX - box.minX - 1, box.maxY - box.minY - 1);
      g.setColor(box.colorBorder);
      g.drawRect(box.minX, box.minY, box.maxX - box.minX - 1, box.maxY - box.minY - 1);
            */
                           
            
            
           // g.setColor  (Color.blue);
            
           // g.fillRect(0, 0,appletsize_x, appletsize_y);
		g.setColor  (color);

		g.fillOval (x_pos , y_pos , 2 * radius, 2 * radius);
                
	}

        
        
        
        public void moveOneStepWithCollisionDetection(ContainerBox box,bars b1,bars b2) {
      // Get the ball's bounds, offset by the radius of the ball
     // int ballMinX = box.minX + radius;
      int ballMinY = box.minY + radius;
     // int ballMaxX = box.maxX - radius;
      int ballMaxY = box.maxY - radius;
   
      // Calculate the ball's new position
      x_pos += x_speed;
      y_pos += y_speed;
      
      // Check if the ball moves over the bounds. If so, adjust the position and speed.
    //May cross both x and y bounds*/
      /*if (y_pos +radius< ballMinY) {
         y_speed = -y_speed;
         y_pos = ballMinY-radius;
      }*/
      if (y_pos < box.minY) {
         y_speed = -y_speed;
         y_pos = box.minY;
      }
      
      
      else if (y_pos +radius > ballMaxY) {
         y_speed = -y_speed;
         y_pos = ballMaxY-radius;
      }
      
      
   if(collision(b1))
   { BallWorld.hit.play();
   x_speed = -x_speed;
     x_pos = b1.x_pos +10;  
    
    
                       
                        
                       
                        
   }
   else if(collision(b2))
   {BallWorld.hit.play();
   x_speed = -x_speed;
   x_pos = b2.x_pos  -2*radius;   
    
   }
    
      
      
      
      
      
   }
        public boolean isOUT(ContainerBox box,bars b1,bars b2)
        {
         if (x_pos+radius+radius+10< box.minX) {
         //x_speed = -x_speed; 
         
          BallWorld.miss.play();
// Reflect along normal
        // x_pos = ballMinX-radius;  
         b2.score++;
         if(b2.score>10)
         { BallWorld.back.stop();
             BallWorld.win.play();}
             
         return true;
          
      } else if (x_pos -10 > box.maxX) {
        // x_speed = -x_speed;
         BallWorld.miss.play();
        // x_pos = ballMaxX-radius;
         b1.score++;
         if(b1.score>10)
            { BallWorld.back.stop();
             BallWorld.win.play();}
         return true;
      }
        
      else return false;
        }
        
    private boolean collision(bars b) {
		return b.getBounds().intersects(getBounds());
	}

	
	
	public Rectangle getBounds() {
		return new Rectangle(x_pos, y_pos, 2*radius, 2*radius);
	}    
        
        
        
        
        
        
        
        
        
}