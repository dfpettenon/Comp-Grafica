package trabalho2.pack;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.TimerTask;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Teste{

   
  
   private static int width=200;
   private static int height=250;
   
  
   
   
    public static void morph(String[] args)
    {
        double alpha=0.0;
        double deltaAlpha=0.005;
        
        
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Image loadedImage; 
        BufferedImageDrawer bid = new BufferedImageDrawer(bi,width,height);
        bid.setTitle("Transforming shape and colour");
        BufferedImage mix;
        
        
        
        TriangulatedImage t1; 
        t1 = new TriangulatedImage();
        t1.bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
       
        TriangulatedImage t2; 
        t2 = new TriangulatedImage();
        t2.bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        
        
        Graphics2D g2dt1 = t1.bi.createGraphics();
        loadedImage = new javax.swing.ImageIcon("face1.jpg").getImage();
        g2dt1.drawImage(loadedImage,0,0,null);
        
        t1.tPoints = new Point2D[8];
        t1.tPoints[0] = new Point2D.Double(0,0);
        t1.tPoints[1] = new Point2D.Double(0,125);
        t1.tPoints[2] = new Point2D.Double(0,250);
        t1.tPoints[3] = new Point2D.Double(200,0);
        t1.tPoints[4] = new Point2D.Double(200,125);
        t1.tPoints[5] = new Point2D.Double(200,250);
        t1.tPoints[6] = new Point2D.Double(100,115);
        t1.tPoints[7] = new Point2D.Double(100,165);
        

        //Definition of the triangles.
        t1.triangles = new int[8][3];

        t1.triangles[0][0] = 0;
        t1.triangles[0][1] = 1;
        t1.triangles[0][2] = 6;
        
        t1.triangles[1][0] = 0;
        t1.triangles[1][1] = 6;
        t1.triangles[1][2] = 3;
        
        t1.triangles[2][0] = 6;
        t1.triangles[2][1] = 3;
        t1.triangles[2][2] = 4;
        
        t1.triangles[3][0] = 1;
        t1.triangles[3][1] = 6;
        t1.triangles[3][2] = 7;
        
        t1.triangles[4][0] = 6;
        t1.triangles[4][1] = 4;
        t1.triangles[4][2] = 7;
        
        t1.triangles[5][0] = 1;
        t1.triangles[5][1] = 2;
        t1.triangles[5][2] = 7;
        
        t1.triangles[6][0] = 4;
        t1.triangles[6][1] = 7;
        t1.triangles[6][2] = 5;
        
        t1.triangles[7][0] = 2;
        t1.triangles[7][1] = 7;
        t1.triangles[7][2] = 5;
        
        t2.bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2dt2 = t2.bi.createGraphics();
        loadedImage = new javax.swing.ImageIcon("face2.jpg").getImage();
        g2dt2.drawImage(loadedImage,0,0,null);

        t2.tPoints = new Point2D[8];
        t2.tPoints[0] = new Point2D.Double(0,0);
        t2.tPoints[1] = new Point2D.Double(0,125);
        t2.tPoints[2] = new Point2D.Double(0,250);
        t2.tPoints[3] = new Point2D.Double(200,0);
        t2.tPoints[4] = new Point2D.Double(200,125);
        t2.tPoints[5] = new Point2D.Double(200,250);
        t2.tPoints[6] = new Point2D.Double(100,115);
        t2.tPoints[7] = new Point2D.Double(100,160);
        


        //The indexing for the triangles must be the same as in the
        //the first image.
        t2.triangles = t1.triangles;

     while (alpha>=0 && alpha<=1)
     {
      //Generate the interpolated image.
      mix = t1.mixWith(t2,alpha);

      //Draw the interpolated image on the BufferedImage.
      bid.g2dbi.drawImage(mix,0,0,null);

      //Call the method for updating the window.
      bid.repaint();
      alpha = alpha+deltaAlpha;
      
     }

   

    }
    
    
    
    
    }




