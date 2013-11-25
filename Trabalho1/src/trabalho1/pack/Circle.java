/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.pack;
import java.util.Date;
import java.awt.*;
import java.awt.geom.*;
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
        int R=0,S=0;
        
        System.out.println("Entre com o valor do raio para os círculos: ");
        try {
             R=Integer.parseInt(reader.readLine());
        } catch (IOException ex) {
             Logger.getLogger(Circle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        midPointCircleAlg(R);
        circlePath();
        infinitePath(R);
        
        System.out.println("Trajeto com: "+i+" pontos");
        
        System.out.println("Entre com o valor do segmento para os círculos: ");
        try {
             S=Integer.parseInt(reader.readLine());
        } catch (IOException ex) {
             Logger.getLogger(Circle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sustain(100);
        
        segDefinition(S);
        drawPath(g,S);
        //System.out.println("x= "+list2[314].getX()+" y= "+list2[314].getY()+"\n");
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
            //System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
            
        }       
        int numP=i-1;
        j=numP-1;
        while (j>=0){ //2º quadrante
            list[i] = new Point();
            list[i].setX(list[j].getX());list[i].setY(-list[j].getY());
            //System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
        }
        j=i-2;
        while (j>=numP){ //3º quadrante
            list[i] = new Point();
            list[i].setX(-list[j].getX());list[i].setY(list[j].getY());
            //System.out.println("x= "+list[i].getX()+" y= "+list[i].getY()+"\n");
            i++;
            j--;
        }
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
    }
    
    public void infinitePath(int R){
            
            int offsetX=600-R, offsetY=300;//600-R e 300
            int temp,temp2,k,j=0,secQua=(i/4)+1,thirQua=(2*secQua)-1,fourQua=(3*secQua)-2;
                      
            //1º quadrante
            temp =secQua-1;
            while(j+temp<=thirQua-1){
                list2[j] = new Point();
                list2[j].setX(-list[j+temp].getY()+offsetX);list2[j].setY(-list[j+temp].getX()+offsetY);
                //System.out.println("x= "+list2[j].getX()+" y= "+list2[j].getY()+"\n");
                j++;
            }
            temp2=j;
            k=0;// 2º quadrante
            temp = fourQua-1;
            while(k+temp<=i){
                list2[j] = new Point();
                list2[j].setX(list[k+temp].getX()+offsetX+2*R);list2[j].setY(list[k+temp].getY()+offsetY);
               // System.out.println("x= "+list2[j].getX()+" y= "+list2[j].getY()+"\n");
                k++;
                j++;
            }
            k=0; // 3º, 4º e 5º quadrantes 
            while(k<=fourQua-1){
                list2[j] = new Point();
                list2[j].setX(list[k].getX()+offsetX+2*R);list2[j].setY(list[k].getY()+offsetY);
              //  System.out.println("x= "+list2[j].getX()+" y= "+list2[j].getY()+"\n");
                k++;
                j++;
            }
            k=fourQua+secQua;
            while(temp2<=k){
                list2[j] = new Point();
                list2[j].setX(-(list2[temp2].getX()-offsetX-2*R)+offsetX);list2[j].setY(list2[temp2].getY());
               // System.out.println("x= "+list2[j].getX()+" y= "+list2[j].getY()+"\n");
                temp2++;
                j++;
            }
            i=j-1; //  -1 pelo incremento ao j adicionado no final da última iteração do útlimo while -1 pelo ponto extra no final do trajeto e +1 pelo i começar em 0; 
            
    }
    
    public void drawPath(Graphics g,int s){        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int j=0, xmid, ymid, a=0, steps=10;
        double ang45 = Math.PI/4, stepsDB=steps, scale=50/30;
        boolean ida=true;
        segList[s] = new Point();
        segList[s].setX(segList[0].getX());
        segList[s].setY(segList[0].getY());
        while (j<s){//quadrados

            Rectangle2D.Double ret=new Rectangle2D.Double(segList[j].getX()/2,segList[j].getY()/2,50,50);
            Rectangle2D.Double a1=new Rectangle2D.Double(segList[j].getX(),segList[j].getY(),1,1);
            Rectangle2D.Double a2=new Rectangle2D.Double(segList[j+1].getX(),segList[j+1].getY(),1,1);
           
            AffineTransform TransIni = new AffineTransform();
            TransIni.translate(segList[j].getX()/2,segList[j].getY()/2);
            AffineTransform TransFin = new AffineTransform();
            TransFin.translate(segList[j+1].getX()/2,segList[j+1].getY()/2);
            if(ida){
                xmid=(segList[j].getX()+25)/2;
                ymid=(segList[j].getY()+25)/2;
                TransFin.rotate(ang45,xmid,ymid);
                TransFin.concatenate(scalingWRTXY(xmid,ymid,scale,scale));
            }
            else{
                xmid=(segList[j].getX()+15)/2;
                ymid=(segList[j].getY()+15)/2;
                TransIni.rotate(ang45,xmid,ymid);
                TransIni.concatenate(scalingWRTXY(xmid,ymid,scale,scale));
            }
            ida=!ida;
            double[] initialMatrix = new double[6];
            TransIni.getMatrix(initialMatrix);
            
            double[] finalMatrix = new double[6];
            TransFin.getMatrix(finalMatrix);
            
            AffineTransform TransMeio;
            for (a=0; a<=steps; a++){
                TransMeio = new AffineTransform(convexCombination(initialMatrix,finalMatrix,(double)a/stepsDB));
                sustain(100);
                clearWindow(g2d);
                
                g2d.setPaint(Color.red);
                g2d.draw(a1);
                g2d.setPaint(Color.green);
                g2d.draw(a2);
                
                g2d.setPaint(Color.black);
                g2d.fill(TransMeio.createTransformedShape(ret));
            }
            j++;
        }
        
        /*
        j=0;
        while (j<i-1){//caminho
            Rectangle2D.Double r2d=new Rectangle2D.Double(list2[j].getX(),list2[j].getY(),1,1);
            g2d.fill(r2d);
            sustain(10);
            j++;
        }*/
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
    public static AffineTransform scalingWRTXY(double x, double y, double xs, double ys){
        AffineTransform at = new AffineTransform();
        at.translate(x,y);
        at.scale(xs,ys);
        at.translate(-x,-y);
        return(at);
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
       /* j=0;
        while(j<s){
            System.out.println("x= "+segList[j].getX()+" y= "+segList[j].getY()+"\n");
            j++;
            
        }*/
    }
    
    public static void sustain(long t)
    {
	long finish = (new Date()).getTime() + t;
	while( (new Date()).getTime() < finish ){}
    }
}
