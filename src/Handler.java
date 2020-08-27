
import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	//object holds all objects that need to be rendered (tanks and bullets)
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	//tanks holds all tanks
	public LinkedList<GameObject> tanks = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	private int whoseTurn = 0;
	public int keyLogger=0;
	public int clickLogger=0;
	
	public void tick() {
		for (int i = 0; i<object.size();i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	/**
	 * changes turn
	 */
	public void endTurn() {
		//System.out.println(whoseTurn + " " + object.size());
		clickLogger=0;
		if (whoseTurn==tanks.size()-1) {
			whoseTurn=0;
		} else {
			whoseTurn++;
		}
	}
	public int getWhoseTurn() {
		return(whoseTurn);
	}
	public void setWhoseTurn(int index) {
		whoseTurn=index;
	}
	public void render(Graphics g) {
		for (int i = 0; i<object.size();i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void addTank(Tank tank) {
		tanks.add(tank);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
