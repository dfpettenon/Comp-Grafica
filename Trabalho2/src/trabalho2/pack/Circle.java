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
    
    public void processing(/*Graphics g*/){
        
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
        //sustain(100);
        
        segDefinition(16);
        drawPath(/*g,*/16,steps);
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
    
    public void drawPath(/*Graphics g,*/int s,int steps){        
        //Graphics2D g2d = (Graphics2D) g;
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int j=0, a, width=1200, height=600;
        double stepsDB=steps;
        
        
        segList[s] = new Point();
        segList[s].setX(segList[0].getX());
        segList[s].setY(segList[0].getY());
        
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Image loadedImage;
        BufferedImageDrawer bid = new BufferedImageDrawer(bi,width,height);
        BufferedImage mix;
        
        TriangulatedImage[] tImage = new TriangulatedImage[16];        
        
        Point[][] pointList= new Point[16][5];
        for(a=0;a<=15;a++){
            for(j=0;j<=4;j++){
               pointList[a][j]= new Point(); 
            }
        }
        // Pontos dos triângulos de cada imagem
            //1.png
        pointList[0][0].setX(131);pointList[0][0].setY(58);
        pointList[0][1].setX(157);pointList[0][1].setY(67);
        pointList[0][2].setX(150);pointList[0][2].setY(77);
        pointList[0][3].setX(128);pointList[0][3].setY(94);
        pointList[0][4].setX(153);pointList[0][4].setY(98);
            //2.png
        pointList[1][0].setX(137);pointList[1][0].setY(50);
        pointList[1][1].setX(169);pointList[1][1].setY(46);
        pointList[1][2].setX(159);pointList[1][2].setY(68);
        pointList[1][3].setX(144);pointList[1][3].setY(80);
        pointList[1][4].setX(169);pointList[1][4].setY(80);
            //3.png
        pointList[2][0].setX(113);pointList[2][0].setY(51);
        pointList[2][1].setX(162);pointList[2][1].setY(61);
        pointList[2][2].setX(144);pointList[2][2].setY(67);
        pointList[2][3].setX(109);pointList[2][3].setY(89);
        pointList[2][4].setX(159);pointList[2][4].setY(103);
            //4.png
        pointList[3][0].setX(65);pointList[3][0].setY(38);
        pointList[3][1].setX(102);pointList[3][1].setY(35);
        pointList[3][2].setX(74);pointList[3][2].setY(61);
        pointList[3][3].setX(77);pointList[3][3].setY(80);
        pointList[3][4].setX(99);pointList[3][4].setY(79);
            //5.png
        pointList[4][0].setX(127);pointList[4][0].setY(50);
        pointList[4][1].setX(159);pointList[4][1].setY(66);
        pointList[4][2].setX(152);pointList[4][2].setY(78);
        pointList[4][3].setX(119);pointList[4][3].setY(98);
        pointList[4][4].setX(150);pointList[4][4].setY(107);
            //6.png
        pointList[5][0].setX(87);pointList[5][0].setY(50);
        pointList[5][1].setX(130);pointList[5][1].setY(40);
        pointList[5][2].setX(98);pointList[5][2].setY(69);
        pointList[5][3].setX(98);pointList[5][3].setY(103);
        pointList[5][4].setX(129);pointList[5][4].setY(95);
            //7.png
        pointList[6][0].setX(93);pointList[6][0].setY(33);
        pointList[6][1].setX(128);pointList[6][1].setY(39);
        pointList[6][2].setX(122);pointList[6][2].setY(51);
        pointList[6][3].setX(95);pointList[6][3].setY(70);
        pointList[6][4].setX(132);pointList[6][4].setY(73);
            //8.png
        pointList[7][0].setX(78);pointList[7][0].setY(49);
        pointList[7][1].setX(126);pointList[7][1].setY(36);
        pointList[7][2].setX(119);pointList[7][2].setY(76);
        pointList[7][3].setX(97);pointList[7][3].setY(97);
        pointList[7][4].setX(129);pointList[7][4].setY(89);
            //9.png
        pointList[8][0].setX(142);pointList[8][0].setY(53);
        pointList[8][1].setX(189);pointList[8][1].setY(61);
        pointList[8][2].setX(171);pointList[8][2].setY(84);
        pointList[8][3].setX(131);pointList[8][3].setY(100);
        pointList[8][4].setX(180);pointList[8][4].setY(106);
            //10.png
        pointList[9][0].setX(78);pointList[9][0].setY(59);
        pointList[9][1].setX(121);pointList[9][1].setY(59);
        pointList[9][2].setX(105);pointList[9][2].setY(89);
        pointList[9][3].setX(83);pointList[9][3].setY(108);
        pointList[9][4].setX(118);pointList[9][4].setY(104);
            //11.png
        pointList[10][0].setX(88);pointList[10][0].setY(45);
        pointList[10][1].setX(127);pointList[10][1].setY(30);
        pointList[10][2].setX(98);pointList[10][2].setY(44);
        pointList[10][3].setX(87);pointList[10][3].setY(91);
        pointList[10][4].setX(169);pointList[10][4].setY(76);
            //12.png
        pointList[11][0].setX(150);pointList[11][0].setY(59);
        pointList[11][1].setX(210);pointList[11][1].setY(56);
        pointList[11][2].setX(196);pointList[11][2].setY(77);
        pointList[11][3].setX(149);pointList[11][3].setY(103);
        pointList[11][4].setX(202);pointList[11][4].setY(99);
            //13.png
        pointList[12][0].setX(85);pointList[12][0].setY(47);
        pointList[12][1].setX(156);pointList[12][1].setY(45);
        pointList[12][2].setX(123);pointList[12][2].setY(72);
        pointList[12][3].setX(73);pointList[12][3].setY(95);
        pointList[12][4].setX(164);pointList[12][4].setY(82);
            //14.png
        pointList[13][0].setX(96);pointList[13][0].setY(33);
        pointList[13][1].setX(147);pointList[13][1].setY(45);
        pointList[13][2].setX(130);pointList[13][2].setY(69);
        pointList[13][3].setX(99);pointList[13][3].setY(93);
        pointList[13][4].setX(135);pointList[13][4].setY(102);
            //15.png
        pointList[14][0].setX(85);pointList[14][0].setY(44);
        pointList[14][1].setX(144);pointList[14][1].setY(42);
        pointList[14][2].setX(118);pointList[14][2].setY(57);
        pointList[14][3].setX(83);pointList[14][3].setY(78);
        pointList[14][4].setX(78);pointList[14][4].setY(76);
            //16.png
        pointList[15][0].setX(121);pointList[15][0].setY(52);
        pointList[15][1].setX(162);pointList[15][1].setY(60);
        pointList[15][2].setX(150);pointList[15][2].setY(80);
        pointList[15][3].setX(122);pointList[15][3].setY(94);
        pointList[15][4].setX(153);pointList[15][4].setY(100);
        
        for(j=0;j<=15;j++){
            
            //Generating the triangulated image:
            tImage[j] = new TriangulatedImage();
            //Define the size.
            tImage[j].bi = new BufferedImage(256, 144, BufferedImage.TYPE_INT_RGB);
            
            //Generate the Graphics2D object.
            Graphics2D g2dtImage = tImage[j].bi.createGraphics();
            
            //Load the image and draw it on the corresponding BufferedImage.
            loadedImage = new javax.swing.ImageIcon(j+1 + ".png").getImage();
            //g2dtImage.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2dtImage.drawImage(loadedImage,0,0,null);
            
            //Definition of the points for the triangulation.
            tImage[j].tPoints = new Point2D[13];
            tImage[j].tPoints[0]= new Point2D.Double(0,0);//canto esquerda cima
            tImage[j].tPoints[1]= new Point2D.Double(127,0);//cima
            tImage[j].tPoints[2]= new Point2D.Double(255,0);//canto direita cima
            tImage[j].tPoints[3]= new Point2D.Double(pointList[j][0].getX(),pointList[j][0].getY());//olho esq
            tImage[j].tPoints[4]= new Point2D.Double(pointList[j][1].getX(),pointList[j][1].getY());//olho dir
            tImage[j].tPoints[5]= new Point2D.Double(0,72);//esquerda
            tImage[j].tPoints[6]= new Point2D.Double(pointList[j][2].getX(),pointList[j][2].getY());//nariz
            tImage[j].tPoints[7]= new Point2D.Double(255,72);//direita
            tImage[j].tPoints[8]= new Point2D.Double(pointList[j][3].getX(),pointList[j][3].getY());//canto esq boca
            tImage[j].tPoints[9]= new Point2D.Double(pointList[j][4].getX(),pointList[j][4].getY());//canto dir boca
            tImage[j].tPoints[10]= new Point2D.Double(0,143);//canto esquerda baixo
            tImage[j].tPoints[11]= new Point2D.Double(127,143);//baixo
            tImage[j].tPoints[12]= new Point2D.Double(255,143);//canto direita baixo
            
            if (j==0){
                
                //Definition of the triangles.
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
            
            }
            else{
                //The same for all images.
                tImage[j].triangles = tImage[0].triangles;
            }
        }
        j=0;
        int k=0;
        while (j<s){//quadrados

           // Rectangle2D.Double ret=new Rectangle2D.Double(0,0,1,1);
            
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
            for (a=0; a<=steps; a++){
                TransMeio = new AffineTransform(convexCombination(initialMatrix,finalMatrix,(double)a/stepsDB));
                if(k<15){
                    mix = tImage[k].mixWith(tImage[k+1],a/steps);
                }
                else{
                    mix = tImage[k].mixWith(tImage[0],a/steps);
                }
                bid.g2dbi.drawImage(mix,(int) TransMeio.getTranslateX(),(int) TransMeio.getTranslateY(),null);
                bid.repaint();
                sustain(50);
            }
            k++;
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