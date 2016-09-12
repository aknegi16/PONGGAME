/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dualgame;
import java.applet.*;  
import javax.swing.*;  
import java.awt.*;
import java.util.*;
/**
 *
 * @author intex
 */

     public class bars 
{
int x_pos;
int y_pos;
int y_speed;
int score;
bars(int x,int y,int score)
{
this.x_pos=x;
this.y_pos=y;
this.y_speed=5;
this.score=score;
}





public void draw(Graphics g)
{
 g.fillRect(this.x_pos, this.y_pos, 10, 100);
              //  g.fillRect(b2.x_pos, b2.y_pos, 10, 50);


}




public Rectangle getBounds() {
		return new Rectangle(x_pos, y_pos, 10, 100);
	}






}
    

