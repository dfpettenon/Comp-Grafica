package trabalho1.pack;


import java.awt.*;
import java.awt.geom.Rectangle2D;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Trabalho1 extends Frame {

    
    private static Point inicial = new Point(300,200);
    private static Point central = new Point(400,300);
    private static int raio = 100;
    private static Graphics g;
    
    
    
    public static void main(String[] args) {
         

         Circle c = new Circle();
         c.setTitle("Trabalho 1");
         c.setSize(800,600);
         c.setVisible(true);
         
    }
    
}
       
