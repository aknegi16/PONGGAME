/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dualgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import javax.swing.*;

public class Welcome extends Canvas {

    public Welcome () {
         setBackground (Color.WHITE);
         setSize(300, 300);
         
      }

 public void update(Graphics g)
 {
 paint(g);
 }
    public void paint(Graphics g) {
        //super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("PONG GAME",10 , 10);
        
     
        }  
    }
