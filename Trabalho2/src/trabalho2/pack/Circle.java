/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2.pack;
import java.util.Date;
import java.awt.*;
import java.awt.geom.*;
import java.awt.Image.*;
import java.awt.image.BufferedImage;
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
      private Point[] segList;
     private int i;
    private int n;
     
    public Circle(){
        
        list = new Point[2000];
        list2 = new Point[4000];
        segList = new Point[4000];
    }
    
    public void paint(Graphics g){
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int R=0,steps=0;
        
        System.out.println("Raio: ");
        try {
             R=Integer.parseInt(reader.readLine());
        } catch (IOException ex) {
             Logger.getLogger(Circle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        midPointCircleAlg(R);
        circlePath();
        infinitePath(R);
        
        System.out.println("Trajeto com: "+i+" pontos");

        System.out.println("Steps: ");
        try {
             steps=Integer.parseInt(reader.readLine());
        } catch (IOException ex) {
             Logger.getLogger(Circle.class.getName()).log(Level.SEVERE, null, ex);
        }
        sustain(100);
        
        segDefinition(16);
        drawPath(g,16,steps);
        System.out.println("Aperte a tecla 'Enter' para encerrar o programa."); 
        try { 
             System.in.read();
         } catch (IOException ex) {
             Logger.getLogger(Circle.class.getName()).log(Level.SEVERE, null, ex);
         }
        Runtime.getRuntime().exit(n);
    }
    
    public void midPointCircleAlg(int R){
        int x = 0 ; int y = R;
        int d,dif;
        d = R -1;     
        
        i=0;
        list[i] = new Point();
        list[i].setX(x);list[i].setY(y);
        
       
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
            d=d+dif;
           
            
        }
    }
    
    
    
    public void circlePath(){
        
        
       
        i++;
        int j=i-3;
        
        while (j>=0){ //1º quadrante
            list[i] = new Point();
            list[i].setX(list[j].getY());list[i].setY(list[j].getX());
            i++;
            j--;
            
        }       
        int numP=i-1;
        j=numP-1;
        while (j>=0){ //2º quadrante
            list[i] = new Point();
            list[i].setX(list[j].getX());list[i].setY(-list[j].getY());
            i++;
            j--;
        }
        j=i-2;
        while (j>=numP){ //3º quadrante
            list[i] = new Point();
            list[i].setX(-list[j].getX());list[i].setY(list[j].getY());
            i++;
            j--;
        }
        j=i-2;
        numP=2*numP;
        while (j>=numP){ //4º quadrante
            list[i] = new Point();
            list[i].setX(list[j].getX());list[i].setY(-list[j].getY());
            i++;
            j--;
        }
        i--;
    }
    
    public void infinitePath(int R){
            
            int offsetX=600-R, offsetY=300;//600-R e 300
            int temp,temp2,k,j=0,secQua=(i/4)+1,thirQua=(2*secQua)-1,fourQua=(3*secQua)-2;
                      
            //1º quadrante
            temp =secQua-1;
            while(j+temp<=thirQua-1){
                list2[j] = new Point();
                list2[j].setX(-list[j+temp].getY()+offsetX);list2[j].setY(-list[j+temp].getX()+offsetY);
                j++;
            }
            temp2=j;
            k=0;// 2º quadrante
            temp = fourQua-1;
            while(k+temp<=i){
                list2[j] = new Point();
                list2[j].setX(list[k+temp].getX()+offsetX+2*R);list2[j].setY(list[k+temp].getY()+offsetY);
                k++;
                j++;
            }
            k=0; // 3º, 4º e 5º quadrantes 
            while(k<=fourQua-1){
                list2[j] = new Point();
                list2[j].setX(list[k].getX()+offsetX+2*R);list2[j].setY(list[k].getY()+offsetY);
                k++;
                j++;
            }
            k=fourQua+secQua;
            while(temp2<=k){
                list2[j] = new Point();
                list2[j].setX(-(list2[temp2].getX()-offsetX-2*R)+offsetX);list2[j].setY(list2[temp2].getY());
                temp2++;
                j++;
            }
            i=j-1; //  -1 pelo incremento ao j adicionado no final da última iteração do útlimo while -1 pelo ponto extra no final do trajeto e +1 pelo i começar em 0; 
            
    }
    
    public void drawPath(Graphics g,int s,int steps){        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int j=0, a, width=256, height=144;
        double stepsDB=steps;
        
        
        segList[s] = new Point();
        segList[s].setX(segList[0].getX());
        segList[s].setY(segList[0].getY());
        
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Image loadedImage;
        BufferedImageDrawer bid = new BufferedImageDrawer(bi,width,height);
        BufferedImage mix;        
        
        TriangulatedImage[] tImage = new TriangulatedImage[16];        
        tImage[0] = new TriangulatedImage();
        tImage[0].bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        
        
        //Pontos aqui
        
        
        tImage[0].triangles = new int[16][3];            
       
        tImage[0].triangles[0][0] = 0;
        tImage[0].triangles[0][1] = 1;
        tImage[0].triangles[0][2] = 3;

        tImage[0].triangles[1][0] = 1;
        tImage[0].triangles[1][1] = 2;
        tImage[0].triangles[1][2] = 4;

        tImage[0].triangles[2][0] = 0;
        tImage[0].triangles[2][1] = 3;
        tImage[0].triangles[2][2] = 5;

        tImage[0].triangles[3][0] = 2;
        tImage[0].triangles[3][1] = 4;
        tImage[0].triangles[3][2] = 7;

        tImage[0].triangles[4][0] = 3;
        tImage[0].triangles[4][1] = 5;
        tImage[0].triangles[4][2] = 6;

        tImage[0].triangles[5][0] = 4;
        tImage[0].triangles[5][1] = 6;
        tImage[0].triangles[5][2] = 7;

        tImage[0].triangles[6][0] = 1;
        tImage[0].triangles[6][1] = 3;
        tImage[0].triangles[6][2] = 6;

        tImage[0].triangles[7][0] = 1;
        tImage[0].triangles[7][1] = 4;
        tImage[0].triangles[7][2] = 6;

        tImage[0].triangles[8][0] = 5;
        tImage[0].triangles[8][1] = 6;
        tImage[0].triangles[8][2] = 8;

        tImage[0].triangles[9][0] = 6;
        tImage[0].triangles[9][1] = 7;
        tImage[0].triangles[9][2] = 9;

        tImage[0].triangles[10][0] = 6;
        tImage[0].triangles[10][1] = 8;
        tImage[0].triangles[10][2] = 11;

        tImage[0].triangles[11][0] = 6;
        tImage[0].triangles[11][1] = 9;
        tImage[0].triangles[11][2] = 11;

        tImage[0].triangles[12][0] = 5;
        tImage[0].triangles[12][1] = 8;
        tImage[0].triangles[12][2] = 10;

        tImage[0].triangles[13][0] = 7;
        tImage[0].triangles[13][1] = 9;
        tImage[0].triangles[13][2] = 12;

        tImage[0].triangles[14][0] = 8;
        tImage[0].triangles[14][1] = 10;
        tImage[0].triangles[14][2] = 11;

        tImage[0].triangles[15][0] = 9;
        tImage[0].triangles[15][1] = 11;
        tImage[0].triangles[15][2] = 12;
         
        
        loadedImage = new javax.swing.ImageIcon(  "0.png").getImage();
        Graphics2D g2dtImage = tImage[0].bi.createGraphics();
        g2dtImage.drawImage(loadedImage,0,0,null);
        
        int k=1;
        while (k<16){
            
            //Pontos aqui
            
            tImage[k] = new TriangulatedImage();
            tImage[k].bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            tImage[k].triangles = tImage[0].triangles;
            
            loadedImage = new javax.swing.ImageIcon( k + ".png").getImage();
            g2dtImage = tImage[k].bi.createGraphics();
            g2dtImage.drawImage(loadedImage,0,0,null);
            
            k++;
        }
        
        
        
        
        
        
        while (j<s){//quadrados

            Rectangle2D.Double ret=new Rectangle2D.Double(0,0,1,1);
            
            AffineTransform TransIni = new AffineTransform();
            TransIni.translate(segList[j].getX(),segList[j].getY());
            
            j++;

            AffineTransform TransFin = new AffineTransform();
            TransFin.translate(segList[j].getX(),segList[j].getY());
            
            double[] initialMatrix = new double[6];
            TransIni.getMatrix(initialMatrix);
            
            double[] finalMatrix = new double[6];
            TransFin.getMatrix(finalMatrix);
            
            AffineTransform TransMeio;
            
            k=0;
            for (a=0; a<=steps; a++){
                TransMeio = new AffineTransform(convexCombination(initialMatrix,finalMatrix,(double)a/stepsDB));
                mix = tImage[k].mixWith(tImage[k+1],a);
                bid.g2dbi.drawImage(mix,(int) TransMeio.getTranslateX(),(int) TransMeio.getTranslateY(),null);
                bid.repaint();
                sustain(100);
            }
        }
    }
    public static void clearWindow(Graphics2D g){
        g.setPaint(Color.white);
        g.fill(new Rectangle(0,0,1200,600));
        g.setPaint(Color.black);
    }
    public static double[] convexCombination(double[] a, double[] b, double alpha){
        double[] result = new double[a.length];
        for (int i=0; i<result.length; i++){
            result[i] = (1-alpha)*a[i] + alpha*b[i];
        }
        return(result);
    }
    public void segDefinition(int s){
        int segTam,j,temp;
        segTam=i/s;
        temp=0;
        j=0;
        
        while(j<s){
            segList[j] = new Point();
            segList[j].setX(list2[temp].getX());
            segList[j].setY(list2[temp].getY());
            System.out.println(temp);
            j++;
            temp+=segTam;
            
        }
    }
    
    public static void sustain(long t)
    {
	long finish = (new Date()).getTime() + t;
	while( (new Date()).getTime() < finish ){}
    }
}