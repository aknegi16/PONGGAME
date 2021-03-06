/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dualgame;
import java.awt.*;
import java.applet.*;

/**
 *
 * @author intex
 */
public class ContainerBox extends Applet{
   int minX, maxX, minY, maxY;  // Box's bounds (package access)
   public Color colorFilled;   // Box's filled color (background)
   public Color colorBorder;   // Box's border color
   private static final Color DEFAULT_COLOR_FILLED = Color.GREEN;
   private static final Color DEFAULT_COLOR_BORDER = Color.BLACK;
   
   /** Constructors
     * @param x */
   public ContainerBox(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
      minX = x;
      minY = y;
      maxX = x + width - 1;
      maxY = y + height - 1;
      this.colorFilled = colorFilled;
      this.colorBorder = colorBorder;
   }
   
   /** Constructor with the default color */
   public ContainerBox(int x, int y, int width, int height) {
      this(x, y, width, height, DEFAULT_COLOR_FILLED, DEFAULT_COLOR_BORDER);
   }
   
   /** Set or reset the boundaries of the box. */
   public void set(int x, int y, int width, int height) {
      minX = x;
      minY = y;
      maxX = x + width - 1;
      maxY = y + height - 1;
   }
   
   
   
   public void draw(Graphics g) {
      //g.setColor(colorFilled);
      g.drawImage(BallWorld.image, minX, minY, maxX - minX - 1, maxY - minY - 1, this);
// g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
      g.setColor(colorBorder);
      g.drawRect(minX, minY, maxX - minX -1, maxY - minY-1);
   }
   
   
   
   
   
   
   
}