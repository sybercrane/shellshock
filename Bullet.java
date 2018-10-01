
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
public class Bullet extends GameObject{
	public float xcoord;
	public float ycoord;
	public float xVel;
	public float yVel;
	public double initialAngle;
	private float gravity = .05f;
	private boolean falling = true;
	Handler handler;
	Map map;
	
	public Bullet(float x, float y, float vel, double angle, Handler handler, Map givenmap) {
		super(x,y);
		initialAngle = angle;
		xVel = (float) (vel*Math.cos(initialAngle));
		yVel = (float) (vel*Math.sin(initialAngle));
		//System.out.println(xVel + " " + yVel);
		this.handler = handler;
		map = givenmap;
	}
	public Bullet() {
		
	}
	/**
	 * controls bullet movement and gravity
	 */
	public void tick(LinkedList<GameObject> object) {
		x+=xVel;
		y-=yVel;
		if(falling) {
			 yVel-=gravity;
		}
		Collision(object);
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, 2, 2);
	}

	/**
	 * returns the bounds of the bullet to be used in collisions
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y,2,2);
	}
	/**
	 * deletes the bullet if it collides with the window boundaries, map, or tank
	 * reduces health of tank if colliding with a tank
	 * ends turn
	 * @param object
	 */
	private void Collision(LinkedList<GameObject> object) {
		cart2d[] mapcoords = map.getCoords();
		if (getBounds().intersectsLine(0, 0, 500, 0) || getBounds().intersectsLine(500, 0, 500, 500) || getBounds().intersectsLine(500, 500, 0, 500) || getBounds().intersectsLine(0, 0, 0, 500)) {
			while(handler.object.getLast().getClass()==(new Bullet()).getClass()) {
				 handler.removeObject(handler.object.getLast());
			}
			handler.endTurn();
		}
		for (int i = 0; i < handler.tanks.size();i++) {
			if(getBounds().intersects(((Tank) handler.tanks.get(i)).getHitBox())) {
				while(handler.object.getLast().getClass()==(new Bullet()).getClass()) {
					 handler.removeObject(handler.object.getLast());
				}
				((Tank)(handler.tanks.get(i))).removeHealth();
				handler.endTurn();
			}
		}
		 for (int j = 0; j<mapcoords.length-1; j++) {
			 if(getBounds().intersectsLine(50*j, (int)mapcoords[j].gety(), 50*j+50, (int)mapcoords[j+1].gety())) {
				 while(handler.object.getLast().getClass()==(new Bullet()).getClass()) {
					 handler.removeObject(handler.object.getLast());
				 }
				 handler.endTurn();
			 }
		 }
		 if (x>=0 && x<=500) {
			 if ((int)y >= map.findheight(x) + 2 ) {
				 while(handler.object.getLast().getClass()==(new Bullet()).getClass()) {
					 handler.removeObject(handler.object.getLast());
				 }
				 handler.endTurn();
			 }
		 }
	}
}
