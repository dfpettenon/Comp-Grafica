/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.pack;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Daniel
 */
public class Circle {
   
     private Point[] list;
     private int i;
     
    public Circle(){
        list = new Point[10000];
    }
    
    public void midPointAlg(int R){
        //Graphics2D g2d = (Graphics2D) g;
        
        
        int x = 0 ; int y = R;
        int d,dif;
        d = 1 -R;  
        
        list[i] = new Point();
        list[i].setX(x);list[i].setY(y);
        System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
        
        //Rectangle2D.Double r2d=new Rectangle2D.Double(x,y,30,30);
       // g2d.fill(r2d);
        while (x<y) {
            
            if (d > 0) {  /* SE chosen */
		dif=2*x - 2*y +5;
		x++;y--;
            } else {      /* E chosen */
		dif=2*x +3;
		x++; 
            }
            i++;
            list[i] = new Point();
            list[i].setX(x);list[i].setY(y);
            System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            d=d+dif;
           
            // g2d.fill(r2d);
        }
        
    }
    public void circlePath(){
        i++;
        int j=i-3;
        while (j>=0){
            list[i] = new Point();
            list[i].setX(list[j].getY());list[i].setY(list[j].getX());
            System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
        }
        //System.out.println(i);
    }
    
}
