package screens;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Bullet;
import entities.Enemy;
import entities.Player;
import entities.Wall;

public class PlayScreen extends BasicGameState {
	
	public static Player player;
	public ArrayList<Bullet> bullets;
	public ArrayList<Enemy> enemies;
	public ArrayList<Wall> walls;
	private int level, alive, shopX, shopY;
	private int enemyCount;
	private Image player_img, outterWall, innerWall, biker, zombie1, zombie2, nextWaveInput, gun_img, gun_firing_img, cursor_img, current_gun_img, background, shopScreenButton;
	private boolean wait_for_input;
	private Rectangle shopRect;
	
	public PlayScreen(int ddd) {
		player = new Player(1052, 153, 80, 160);
		bullets = new ArrayList<>();
		enemies = new ArrayList<>();
		walls = new ArrayList<>();
		level = 0;
		shopX = 1200;
		shopY = 640;
		alive = enemyCount;
		wait_for_input = true;
		shopRect = new Rectangle(shopX, shopY, 80, 80);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		player_img = new Image("res/player.png");
		outterWall = new Image("res/outerwall.png");
		innerWall = new Image("res/innerwall.png");
		biker = new Image("res/biker.png");
		zombie1 = new Image("res/groupie1.png");
		zombie2 = new Image("res/groupie2.png");
		nextWaveInput = new Image("res/nextWaveInput.png");
		gun_img = new Image("res/gun.png");
		current_gun_img = gun_img;
		gun_firing_img = new Image("res/gun_firing.png");
		cursor_img = new Image("res/cursor.png");
		background = new Image("res/background.png");
		shopScreenButton = new Image("res/shopScreenButton.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0, 0);
		
		player_img.draw(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		current_gun_img.draw(player.getX()-80, player.getY()+40);
		outterWall.draw(1016, 257, 24, 462);
		innerWall.draw(1040, 312, 240, 407);
		
		g.setColor(Color.green);
		g.fillRect(0, 690, 1280, 400);
		
		g.setColor(Color.red);
		g.fillRect(440, 40, 2*player.getHealth(), 40);
		g.drawRect(440, 40, 200, 40);
		
		g.setColor(Color.black);
		g.drawString("Health: ", 490, 20);
		
		g.setColor(Color.yellow);
		for (Bullet b : bullets) {
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		
		for (Enemy e : enemies) {
			if (e.getHealth() > 0) {
				if (e.getVariant() == 0)
					biker.draw(e.getX(), e.getY(), e.getWidth(), e.getHeight());
				else if (e.getVariant() == 1)
					zombie1.draw(e.getX(), e.getY(), e.getWidth(), e.getHeight());
				else if (e.getVariant() == 2)
					zombie2.draw(e.getX(), e.getY(), e.getWidth(), e.getHeight());
				else
					zombie1.draw(e.getX(), e.getY(), e.getWidth(), e.getHeight());
			}
		}
		
		if (wait_for_input) {
			nextWaveInput.draw(0, 0);
			shopScreenButton.draw(1200, 640);
		}
		
		g.setColor(Color.black);
		g.drawString("Wave: " + level, 1200, 10);
		g.drawString("$" + player.getMoney(), 1200, 30);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		gc.setMouseCursor(cursor_img, 0, 0);
		
		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
			bullets.add(new Bullet(input.getMouseX(), input.getMouseY(), player.getX(), player.getY()));
			current_gun_img = gun_firing_img;
			if ( (new Rectangle(Mouse.getX(), 720-Mouse.getY(), 80, 80).intersects(shopRect) ) && wait_for_input)
				sbg.enterState(2);
		} else {
			current_gun_img = gun_img;
		}
		
		if (input.isKeyPressed(input.KEY_ESCAPE)) {
			System.exit(0);
		}
		
		if (input.isKeyPressed(input.KEY_SPACE)) {
			if (wait_for_input) {
				nextLevel();
				wait_for_input = false;
			}	
		}
		
		alive = getAlive();
		
		if (alive == 0) {
			wait_for_input = true;
			System.out.println("NEXT LEVEL");
		}
		
		for (Bullet b : bullets) {
			b.update();
			if (b.getX() < 0)
				b.setX(10000);
		}
		
		for (Enemy e : enemies) {
			if (e.getHealth() > 0) 
				e.move();
			
			if ((new Rectangle(1016, 257, 24, 462)).intersects(e.bounds()) && e.getHealth() > 0) {
				e.takeDamage(100);
				player.takeDamage(10);
			}
			for (Bullet b : bullets) {
				if (checkBounds(b, e) && e.getHealth() > 0) {
					e.takeDamage(b.getDmg()+player.getDmgBonus());
					b.setX(100000);
				} 
			}
		}
	}
	
	public void nextLevel() {
		this.level += 1;
		player.giveMoney(this.enemyCount*5);
		this.enemyCount = this.level + 2;
		enemies.clear();
		bullets.clear();
		initEnemies();
		alive = getAlive();
	}
	
	public void initEnemies() {
		int space = 0;
		for (int i = 1; i <= enemyCount; i++) {
			enemies.add(new Enemy(-100 - space, 530, 80, 160));
			space += 100;
		}
	}
	
	public int getAlive() {
		int h = 0;
		for (Enemy e : enemies) {
			if (e.getHealth() > 0)
				h++;
		}
		return h;
	}
	
	public boolean checkBounds(Bullet b, Enemy e) {
		if (b.bounds().intersects(e.bounds()))
			return true;
		else
			return false;
	}

	@Override
	public int getID() {
		return 1;
	}
	
	public static Player getPlayer() {
		return player;
	}
}
