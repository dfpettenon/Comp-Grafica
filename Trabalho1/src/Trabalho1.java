
import java.awt.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Trabalho1 extends Frame {

    Trabalho1()
    {
        addWindowListener(new MyFinishWindow());
    }
    
    public static void main(String[] args) {
         Trabalho1 f = new Trabalho1();
         f.setTitle("Trabalho 1");
         f.setSize(800,600);
         f.setVisible(true);
    }

       
}
