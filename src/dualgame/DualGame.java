/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dualgame;
import javax.swing.*;  
import java.awt.*;  
  
import java.util.*;  

public class DualGame {

    
     JFrame f;
     String s1="hello",s2="BYE";
     JLabel j1,j2,j3,j4;
     
     DualGame() throws InterruptedException
     {f=new JFrame();
     
         j1=new JLabel("DUAL GAME", JLabel.CENTER); 
         j2=new JLabel ("made by akash negi",JLabel.CENTER);
          j3=new JLabel ("Score A",JLabel.CENTER);
          j4=new JLabel ("Score B",JLabel.CENTER);
           f.setSize(500,500);
           /*j1.setSize(100, 100);
            j2.setSize(150, 150);*/
    
    
    f.setVisible(true); 
      f.add(j1,BorderLayout.NORTH);  
    f.add(j2,BorderLayout.SOUTH);  
    f.add(j4,BorderLayout.EAST);  
    f.add(j3,BorderLayout.WEST);  
    //f.add(b5,BorderLayout.CENTER); 
     
     
     
     }
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws InterruptedException {
        new DualGame();
    }
    
}
