/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2.pack;

/**
 *
 * @author Daniel
 */
public class Point {
    
  private int x;
  private int y;
  
  public Point(){};
  
  public Point(int x,int y){
      this.x=x;
      this.y=y;
  }  
   
  public void setX(int x){
      this.x=x;
  }
  
  public void setY(int y){
      this.y=y;
  }
  public int getX(){
      return this.x;
  }
  public int getY(){
      return this.y;
  }
}

