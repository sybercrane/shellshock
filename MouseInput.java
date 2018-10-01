
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
	Handler handler;
	//public static int clickLogger = 0;
	public boolean newGame = false;

	public MouseInput(Handler handler) {
		this.handler = handler;
	}
	/**
	 * overrides mouseClicked to change states or shoot in game
	 */
	public void mouseClicked(MouseEvent e) {
		if (Game.State == Game.STATE.GAME) {
			Tank tank = (Tank)(handler.tanks.get(handler.getWhoseTurn()));
			int clickx = e.getX();
			int clicky = e.getY();
			if (clicky>=450 && clicky<=475) {
				if (clickx>=90 && clickx<=240) {
					Game.State = Game.STATE.MENU;
				} else if (clickx>=260 && clickx<=410) {
					//System.out.println("checktwo");
					newGame = true;
				}
			} else if (handler.keyLogger == 0 && handler.clickLogger==0) {
				//System.out.println(tank.getX());
				float xVel = (float)(.01*Math.sqrt(Math.pow((e.getX()-tank.getX()),2) + Math.pow((e.getY()-tank.getY()), 2)));
				//System.out.println(-1*(e.getY()-tank.getY())/(e.getX()-tank.getx()));
				double angle = (Math.atan2(-1*(e.getY()-tank.getY()),(e.getX()-tank.getX())));
				//System.out.println(angle / Math.PI * 180);
				handler.addObject(new Bullet(tank.getX()+5,tank.getY()-10,xVel,angle,handler,tank.getmap()));
				handler.clickLogger++;
			}
		} else if (Game.State == Game.STATE.MENU) {
			int clickx = e.getX();
			int clicky = e.getY();
			if (clickx >= 200 && clickx<= 300) {
				if (clicky>=150 && clicky<=200) {
					Game.State = Game.STATE.GAME;
				}
				if (clicky>=250 && clicky<=300) {
					Game.State = Game.STATE.HELPSCREEN;
				}
				if (clicky>=350 && clicky<=400) {
					System.exit(0);
				}
			}
		} else if (Game.State == Game.State.HELPSCREEN) {
			int clickx = e.getX();
			int clicky = e.getY();
			if (clickx >= 185 && clickx<= 285) {
				if (clicky>=460 && clicky<=490) {
					Game.State = Game.STATE.MENU;
				}
			}
		} else if (Game.State == Game.State.ENDSCREEN) {
			int clickx = e.getX();
			int clicky = e.getY();
			if (clicky>=300 && clicky<=325) {
				if (clickx>=90 && clickx<=240) {
					Game.State = Game.STATE.MENU;
				} else if (clickx>=260 && clickx<=410) {
					//System.out.println("checktwo");
					newGame = true;
				}
			}
		}
	}
}
