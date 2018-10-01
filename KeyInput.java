
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	Handler handler;

	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	/**
	 * press escape to quit game
	 * controls movement of the player whose turn it is after the previous player's bullet disappears
	 * press enter to end turn
	 */
	public void keyPressed(KeyEvent e) {
		handler.keyLogger++;
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (Game.State == Game.STATE.GAME && handler.clickLogger==0) {
			Tank tank;
			Tank opponent;
			if (handler.getWhoseTurn()==0) {
				tank = (Tank)(handler.object.get(handler.getWhoseTurn()));
				opponent = (Tank)(handler.object.get(handler.getWhoseTurn()+1));
			} else {
				tank = (Tank)(handler.object.get(handler.getWhoseTurn()));
				opponent = (Tank)(handler.object.get(handler.getWhoseTurn()-1));
			}
			if (key == KeyEvent.VK_RIGHT) {
				if (tank.getX()<=485) {
					tank.setX(tank.getX()+5);
					tank.setY((float)tank.getmap().findheight((double)tank.getX())-5);
				}
			}
			if (key == KeyEvent.VK_LEFT) {
				if (tank.getX()>=5) {
					tank.setX(tank.getX()-5);
					tank.setY((float)tank.getmap().findheight((double)tank.getX())-5);
				}
			}
			if (key == KeyEvent.VK_ENTER && (tank.getX() <= opponent.getX()-15 || tank.getX() >=opponent.getx()+15) ) {
				handler.endTurn();
				handler.keyLogger=0;
			}
		}
	}
	public void keyReleased(KeyEvent e) {

	}
}
