

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class HelpScreen {

	public Rectangle infoBox = new Rectangle(50,100,400,350);
	public Rectangle BackBox = new Rectangle(185,460,100,30);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		 
		Font font = new Font("arial",Font.BOLD,30);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("SHELLSHOCK LITE", Game.WIDTH/4-25, 75);
		
		g.setFont(new Font("arial",Font.BOLD,15));
		g.drawString("ShellShock Lite is a two-player turn-based ", infoBox.x + 25, infoBox.y+32);
		g.drawString("shooting game", infoBox.x + 50, infoBox.y+47);
		g.drawString("Press the left and right arrow keys to move", infoBox.x + 25, infoBox.y+77);
		g.drawString("Click the mouse to shoot", infoBox.x + 25, infoBox.y+107);
		g.drawString("Angle and velocity of the bullet are ", infoBox.x + 25, infoBox.y+137);
		g.drawString("determined based on where you click", infoBox.x + 50, infoBox.y+152);
		g.drawString("Be careful! Your own bullets can damage you", infoBox.x + 25, infoBox.y+182);
		g.drawString("You can only move or shoot in one turn", infoBox.x + 25, infoBox.y+212);
		g.drawString("You cannot end your turn if you and your", infoBox.x+25, infoBox.y+242);
		g.drawString("opponents are too close", infoBox.x+50, infoBox.y+257);
		g.drawString("Creators: Sabrina Liu, Jeffrey Cheng, Qing Huang", infoBox.x + 25, infoBox.y+287);
		
		g.setFont(new Font("arial",Font.BOLD,15));
		g.drawString("BACK", BackBox.x+30, BackBox.y+20);
		g2d.draw(infoBox);
		g2d.draw(BackBox);
		
	}
}
