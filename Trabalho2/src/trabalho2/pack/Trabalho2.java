package trabalho2.pack;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexandre
 */
public class Trabalho2 extends Frame {

    
    private static Point inicial = new Point(300,200);
    private static Point central = new Point(400,300);
    private static int raio = 100;
    private static Graphics g;
    
    
    
    public static void main(String[] args) {
         

         Circle c = new Circle();
         c.setTitle("Trabalho 2");
         c.setSize(1200,600);
         c.setVisible(true);
         
    }
    
}
       

