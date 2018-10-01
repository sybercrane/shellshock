
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Tank extends GameObject{
  protected double xval;
  protected double yval;
  protected int health = 100;
  private float gravity = 0.05f;
  private boolean falling = true;
  private int width = 10;
  private int height = 5;
  private Map map;
  //0 is facing right, 1 is facing left


  private Handler handler;
  public Tank() {
    super();
  }
  public Tank(float xval, float yval, Handler handler, Map mapval){
    super(xval,yval);
    this.handler = handler;
    map = mapval;
 
  }

  public void setx(double val){
    xval = val;
  }
  public void sety(double val){
    yval = val;
  }
  public double getx()
  {
    return xval;
  }
  public double gety()
  {
    return yval;
  }
  public int getHealth() {
	  return(health);
  }
  public Map getmap(){
	  return map;
  }
  /**
   * controls tank movement as well as gravity
   */
  public void tick(LinkedList<GameObject> object) {
	  x+=velX;
	  y+=velY;
	  if(falling) {
		  velY+=gravity;
	  }
	  Collision(object);
  }
  
  public void render(Graphics g){
	if (health>0) {
		g.setColor(Color.red);
	    g.drawRect((int)x, (int)y, width, height);
	    g.fillRect((int)x, (int)y, width, height);
	    Graphics2D g2d = (Graphics2D) g;
	    //g.setColor(Color.blue);
	    g2d.draw(getBounds());
	    
	    g2d.draw(getBoundsRight());
	    g2d.draw(getBoundsLeft());
	    g2d.draw(getBoundsTop());
	}
  }
  /**
   * following methods return the boundaries of the tank
   * hit box is area that will deal damage to tank if hit
   * other methods draw the bottom, top, right, left boundaries of the tank
   * @return
   */
  public Rectangle getHitBox() {
	  return new Rectangle((int)x-5,(int)y-2,width+10,height+5);
  }
  public Rectangle getBounds() {
	  return new Rectangle((int)(int)x,(int)y+(height/2),(int)width,(int)height/2);
  }
  public Rectangle getBoundsTop() {
	  return new Rectangle((int)(int)x,(int)y,(int)width,(int)height/2);
  }
  public Rectangle getBoundsRight() {
	  return new Rectangle((int)x+width-1,(int)y+1,(int)1,(int)height-1);
  }
  public Rectangle getBoundsLeft() {
	  return new Rectangle((int)x,(int)y+1,(int)1,(int)height-1);
  }
  /**
   * stops the tank if colliding with the ground
   * @param object
   */
  private void Collision(LinkedList<GameObject> object) {
	  cart2d[] mapcoords = map.getCoords();
	  for (int i = 0; i < handler.object.size(); i++) {
		  for (int j = 0; j<mapcoords.length-1; j++) {
			  if(getBounds().intersectsLine(50*j, (int)mapcoords[j].gety(), 50*j+50, (int)mapcoords[j+1].gety())) {
				  velY=0;
				  falling = false;
			  }
			  if(getBoundsRight().intersectsLine(50*j, (int)mapcoords[j].gety(), 50*j+50, (int)mapcoords[j+1].gety())) {
				  velX=0;
			  }
			  if(getBoundsLeft().intersectsLine(50*j, (int)mapcoords[j].gety(), 50*j+50, (int)mapcoords[j+1].gety())) {
				  velX=0;
			  }
			  if (getBoundsLeft().intersectsLine(0, 0, 500, 0) || getBoundsLeft().intersectsLine(500, 0, 500, 500) || getBoundsLeft().intersectsLine(500, 500, 0, 500) || getBoundsLeft().intersectsLine(0, 0, 0, 500)) {
				  velX = 0;
			  }
			  if (getBoundsRight().intersectsLine(0, 0, 500, 0) || getBoundsRight().intersectsLine(500, 0, 500, 500) || getBoundsRight().intersectsLine(500, 500, 0, 500) || getBoundsRight().intersectsLine(0, 0, 0, 500)) {
				  velX = 0;
			  }
			  if (getBounds().intersectsLine(0, 0, 500, 0) || getBounds().intersectsLine(500, 0, 500, 500) || getBounds().intersectsLine(500, 500, 0, 500) || getBounds().intersectsLine(0, 0, 0, 500)) {
				  velX = 0;
			  } 
			  if (getBoundsTop().intersectsLine(0, 0, 500, 0) || getBoundsTop().intersectsLine(500, 0, 500, 500) || getBoundsTop().intersectsLine(500, 500, 0, 500) || getBoundsTop().intersectsLine(0, 0, 0, 500)) {
				  velX = 0;
			  }
		  }
	  }
  }
  /**
   * reduces tank health when hit
   */
  public void removeHealth(){
    health = health - 10;
  }

}
