

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(185,150,100,50);
	public Rectangle helpButton = new Rectangle(185,250,100,50);
	public Rectangle quitButton = new Rectangle(185,350,100,50);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		 
		
		Font font = new Font("arial",Font.BOLD,30);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("SHELLSHOCK LITE", Game.WIDTH/4-25, 75);
		
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("PLAY", playButton.x + 25, playButton.y+32);
		g.drawString("HELP", helpButton.x + 25, helpButton.y+32);
		g.drawString("QUIT", quitButton.x + 25, quitButton.y+32);
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
		
	}
}
