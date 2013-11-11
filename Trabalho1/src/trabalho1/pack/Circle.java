/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.pack;
import java.util.Date;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Daniel
 */
public class Circle extends Frame {
   
     private Point[] list;
     private int i;
     
    public Circle(){
        addWindowListener(new MyFinishWindow());
        list = new Point[10000];
    
    }
    
    public void paint(Graphics g){
        
        int R=100;
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
        circlePath(g);
    }
    public void circlePath(Graphics g){
        
        addWindowListener(new MyFinishWindow());
        
        Graphics2D g2d = (Graphics2D) g;
        i++;
        int j=i-3;
        
        while (j>=0){ //1º quadrante
            list[i] = new Point();
            list[i].setX(list[j].getY());list[i].setY(list[j].getX());
            System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
            //System.out.println(i);
        }
        int numP=i-1;
        j=numP-1;
        while (j>=0){ //2º quadrante
            list[i] = new Point();
            list[i].setX(list[j].getX());list[i].setY(-list[j].getY());
            System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
        }
        j=i-2;
        while (j>=numP){ //3º quadrante
            list[i] = new Point();
            list[i].setX(-list[j].getX());list[i].setY(list[j].getY());
            System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
        }
        j=i-2;
        numP=2*numP;
        while (j>=numP){ //4º quadrante
            list[i] = new Point();
            list[i].setX(list[j].getX());list[i].setY(-list[j].getY());
            System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
        }
        i--;
        System.out.println(i);
    
        j=0;
        while (j<=i){
            Rectangle2D.Double r2d=new Rectangle2D.Double(list[j].getX()+300,list[j].getY()+200,1,1);
            g2d.fill(r2d);
            sustain(50);
            j++;
        }
    
    }
    public static void sustain(long t)
    {
	long finish = (new Date()).getTime() + t;
	while( (new Date()).getTime() < finish ){}
    }
}
