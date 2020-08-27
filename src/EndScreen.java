

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EndScreen {
	Handler handler;
	public Rectangle toMenu = new Rectangle(90,300,150,25);
	public Rectangle playAgain = new Rectangle(260,300,150,25);
	
	public EndScreen(Handler handler){
		this.handler = handler;
	}
	
	/**
	 * returns the player who won the game
	 * @return
	 */
	public int getWinner() {
		for (int i = 0; i < handler.tanks.size(); i++) {
			if (((Tank) (handler.tanks.get(i))).getHealth()>0) {
				return(i+1);
			}
		}
		return(-1);
	}
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		 
		Font font = new Font("arial",Font.BOLD,35);
		g.setFont(font);
		g.setColor(Color.red);
		
		g.drawString("PLAYER " + getWinner() + " WINS", 115, 250);
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,15));
		g.drawString("BACK TO MENU", toMenu.x+15, toMenu.y+18);
		g.drawString("PLAY AGAIN", playAgain.x+30, playAgain.y+18);
		
		g.setColor(Color.gray);
		g2d.draw(toMenu);
		g2d.draw(playAgain);
		
	}
}
