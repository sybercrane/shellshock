
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	protected float x,y;
	protected float velX=0, velY=0;
	
	
	public GameObject() {
		x=0;
		y=0;
	} 
	public GameObject(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public float setX(float xval) {
		x = xval;
		return (x);
	}
	public float setY(float yval) {
		y = yval;
		return(y);
	}
	
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}
	public float setVelX(float xval) {
		velX = xval;
		return velX;
	}
	public float setVelY(float yval){
		velY = yval;
		return velY;
	}
	
}
