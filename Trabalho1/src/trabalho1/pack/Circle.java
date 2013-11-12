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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Circle extends Frame {
   
     private Point[] list;
      private Point[] list2;
     private int i;
    private int n;
     
    public Circle(){
        addWindowListener(new MyFinishWindow());
        list = new Point[2000];
        list2 = new Point[4000];
    }
    
    public void paint(Graphics g){
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int R=0;
        
        System.out.println("Entre com o valor do raio para os círculos: ");
        try {
             R=Integer.parseInt(reader.readLine());
        } catch (IOException ex) {
             Logger.getLogger(Circle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int x = 0 ; int y = R;
        int d,dif;
        d = R -1;     
        
        i=0;
        list[i] = new Point();
        list[i].setX(x);list[i].setY(y);
        //System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
        
        //Rectangle2D.Double r2d=new Rectangle2D.Double(x,y,30,30);
       // g2d.fill(r2d);
        while (x<y) { // Calcula o primeiro octante do círculo
            
            if (d > 0) {  // SE escolhido 
		dif=2*x - 2*y +5;
		x++;y--;
            } else {      // E escolhido 
		dif=2*x +3;
		x++; 
            }
            i++;
            list[i] = new Point();
            list[i].setX(x);list[i].setY(y);
            //System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            d=d+dif;
           
            // g2d.fill(r2d);
        }
        circlePath(g,R);
        System.out.println("Aperte a tecla 'Enter' para encerrar o programa."); 
        try { 
             System.in.read();
         } catch (IOException ex) {
             Logger.getLogger(Circle.class.getName()).log(Level.SEVERE, null, ex);
         }
        Runtime.getRuntime().exit(n);
    }
    public void circlePath(Graphics g,int R){
        
        addWindowListener(new MyFinishWindow());
        
        Graphics2D g2d = (Graphics2D) g;
        i++;
        int j=i-3;
        
        while (j>=0){ //1º quadrante
            list[i] = new Point();
            list[i].setX(list[j].getY());list[i].setY(list[j].getX());
            //System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
            
        }
        //System.out.println(i);
       
        int numP=i-1;
        j=numP-1;
        while (j>=0){ //2º quadrante
            list[i] = new Point();
            list[i].setX(list[j].getX());list[i].setY(-list[j].getY());
            //System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
        }
        System.out.println(i);
        j=i-2;
        while (j>=numP){ //3º quadrante
            list[i] = new Point();
            list[i].setX(-list[j].getX());list[i].setY(list[j].getY());
            //System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
        }
        System.out.println(i);
        j=i-2;
        numP=2*numP;
        while (j>=numP){ //4º quadrante
            list[i] = new Point();
            list[i].setX(list[j].getX());list[i].setY(-list[j].getY());
            //System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
        }
        i--;
        System.out.println(i);
        infinitePath(R);
        sustain(100);
        
      
        j=0;
        while (j<=i-1){
            Rectangle2D.Double r2d=new Rectangle2D.Double(list2[j].getX(),list2[j].getY(),3,3);
            g2d.fill(r2d);
            sustain(10);
            j++;
        }
        
        
        
    }
    public void infinitePath(int R){
            int offsetX=400, offsetY=300;
            int temp,temp2,k=0,j=0,firQua=0,secQua=(i/4)+1,thirQua=(2*secQua)-1,fourQua=(3*secQua)-2;
                      
            //1º quadrante
            temp =secQua-1;
            while(j+temp<=thirQua-1){
                list2[j] = new Point();
                list2[j].setX(-list[j+temp].getY()+offsetX);list2[j].setY(-list[j+temp].getX()+offsetY);
                System.out.println("x= "+list2[j].getX()+" y= "+list2[j].getY()+"\n");
                j++;
                
            }
            temp2=j;
            k=0;// 2º quadrante
            temp = fourQua-1;
            while(k+temp<=i){
                list2[j] = new Point();
                list2[j].setX(list[k+temp].getX()+offsetX+2*R);list2[j].setY(list[k+temp].getY()+offsetY);
                System.out.println("x= "+list2[j].getX()+" y= "+list2[j].getY()+"\n");
                k++;
                j++;
            }
            k=0; // 3º, 4º e 5º quadrantes 
            temp=j;
            while(k<=fourQua-1){
                list2[j] = new Point();
                list2[j].setX(list[k].getX()+offsetX+2*R);list2[j].setY(list[k].getY()+offsetY);
                System.out.println("x= "+list2[j].getX()+" y= "+list2[j].getY()+"\n");
                k++;
                j++;
            }
            k=j;
            while(temp2<=k){
                list2[j] = new Point();
                list2[j].setX(-(list2[temp2].getX()-offsetX-2*R)+offsetX);list2[j].setY(list2[temp2].getY());
                System.out.println("x= "+list2[j].getX()+" y= "+list2[j].getY()+"\n");
                temp2++;
                j++;
            }
            
            
            i=j;
            
    }
    
    
    public static void sustain(long t)
    {
	long finish = (new Date()).getTime() + t;
	while( (new Date()).getTime() < finish ){}
    }
}
