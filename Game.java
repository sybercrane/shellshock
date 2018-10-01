
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	//game loop to keep the game running
	private boolean running = false;
	private Thread thread;
	
	//the map, changed every time the user presses play again
	private Map map;
	
	//classes render different pages
	private Menu menu;
	private EndScreen endScreen;
	private HelpScreen helpScreen;
	
	//width and height of the window
	public static int WIDTH,HEIGHT;
	
	//handler controls all game objects and tanks
	Handler handler;
	//takes in input from mouse
	private MouseInput mouse;
	
	public enum STATE {
		MENU,
		HELPSCREEN,
		GAME,
		ENDSCREEN
	};
	public static STATE State = STATE.MENU;
	
	
	/**
	 * initializes all the objects/variables
	 */
	private void init() {
		handler = new Handler();
		menu = new Menu();
		endScreen = new EndScreen(handler);
		helpScreen = new HelpScreen();
		map = new Map(2);
		Tank tank1 = new Tank(60,600-(int)map.findheight(60),handler,map);
		Tank tank2 = new Tank(430,600-(int)map.findheight(60),handler,map);
		handler.addObject(tank1);
		handler.addObject(tank2);
		handler.addTank(tank1);
		handler.addTank(tank2);
		WIDTH = getWidth();
		HEIGHT = getHeight();
		mouse = new MouseInput(handler);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(mouse);
	}
	/**
	 * creates objects specific to one game
	 * allows for play again
	 */
	public void initgame() {
		while (handler.object.size()>0)
			handler.object.removeFirst();
		while (handler.tanks.size()>0)
			handler.tanks.removeFirst();
		//System.out.println("checkone");
		map = new Map(2);
		Tank tank1 = new Tank(60,600-(int)map.findheight(60),handler,map);
		Tank tank2 = new Tank(430,600-(int)map.findheight(60),handler,map);
		handler.addObject(tank1);
		handler.addObject(tank2);
		handler.addTank(tank1);
		handler.addTank(tank2);
	}
	/**
	 * begins game
	 */
	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	/**
	 * game loop
	 */
	public void run() {
		init();
		initgame();
		this.requestFocus();
		long lastTIme = System.nanoTime();
		double amountofTicks = 60.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTIme)/ns;
			lastTIme=now;
			while(delta>=1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer+=1000;
				//fps=frames;
				//ticks=updates;
				frames=0;
				updates=0;
			}
			if (mouse.newGame==true) {
				initgame();
				State = STATE.GAME;
				mouse.newGame=false;
			}
		}
	}
	private void tick() {
		if (State == STATE.GAME)
			handler.tick();
	}
	
	/**
	 * if there is only one tank left alive, changes state to endscreen
	 */
	public void isOver() {
		int liveTanks = 0;
		for (int i = 0; i < handler.object.size() && handler.object.get(i).getClass()==(new Tank()).getClass(); i++) {
			if (((Tank) (handler.object.get(i))).getHealth()>0) {
				liveTanks++;
			}
		}
		if (liveTanks==1) {
			//System.out.println("check");
			State = STATE.ENDSCREEN;
		}
	}
	/**
	 * draws the game based on which state (menu/game/helpscreen/endscreen)
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		if (State == STATE.MENU) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, getWidth(), getHeight());
			menu.render(g);
		} else if (State == STATE.GAME) {
			
 			g.setColor(Color.white);
 			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.green);
			g.fillRect(0, 5, 240, 25);
			g.fillRect(260, 5, 240, 25);
			g.setColor(Color.red);
			g.fillRect((int) (240-240/100.0*(100-((Tank) handler.tanks.get(0)).getHealth())), 5, (int) (240/100.0*(100-((Tank) handler.tanks.get(0)).getHealth())), 25);
			g.fillRect(260, 5, (int) (240/100.0*(100-((Tank) handler.tanks.get(1)).getHealth())), 25);
			g.setColor(Color.black);
			g.drawString("Player 1: " + (((Tank) (handler.tanks.get(0))).getHealth()) + "/100", 20, 50);
			g.drawString("Player 2: " + (((Tank) (handler.tanks.get(1))).getHealth()) + "/100", 375, 50);
			
			Graphics2D g2d = (Graphics2D)g;
			Rectangle toMenu = new Rectangle(90,450,150,25);
			Rectangle playAgain = new Rectangle(260,450,150,25);
			g.setFont(new Font("arial",Font.BOLD,15));
			g.drawString("BACK TO MENU", toMenu.x+15, toMenu.y+18);
			g.drawString("RESTART", playAgain.x+40, playAgain.y+18);
			
			g.setColor(Color.gray);
			g2d.draw(toMenu);
			g2d.draw(playAgain);
			
	        cart2d[] coords = map.getCoords();
	        g.setColor(Color.black);
	        for(int i = 0; i<coords.length-1; i++){
	        	g.drawLine(50*i, (int)(coords[i].gety()), 50*i+50, (int)(coords[i+1].gety()));
	        }
			handler.render(g);
			isOver();
		} else if (State == STATE.HELPSCREEN) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, getWidth(), getHeight());
			helpScreen.render(g);
		} else if (State == STATE.ENDSCREEN) {
			g.setColor(Color.black);
			g.fillRect(60, 200, 380, 150);
			endScreen.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	/**
	 * starts game
	 * @param args
	 */
	public static void main(String[] args) {
		new Window(500,500, "Shell Shock LITE", new Game());
	}
	
}
