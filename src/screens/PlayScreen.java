package screens;

import java.util.ArrayList;

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
	private int level, alive;
	private int enemyCount;
	private Image player_img, outterWall, innerWall, zombie1, nextWaveInput, gun_img, cursor_img;
	private boolean wait_for_input;
	
	public PlayScreen(int ddd) {
		player = new Player(1052, 153, 80, 160);
		bullets = new ArrayList<>();
		enemies = new ArrayList<>();
		walls = new ArrayList<>();
		level = 0;
		alive = enemyCount;
		nextLevel();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		player_img = new Image("res/player.png");
		outterWall = new Image("res/outerwall.png");
		innerWall = new Image("res/innerwall.png");
		zombie1 = new Image("res/zombie1.png");
		nextWaveInput = new Image("res/nextWaveInput.png");
		gun_img = new Image("res/gun.png");
		cursor_img = new Image("res/cursor.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, 1280, 720);
		
		player_img.draw(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		gun_img.draw(player.getX()-80, player.getY()+40);
		outterWall.draw(1016, 257, 24, 462);
		innerWall.draw(1040, 312, 240, 407);
		
		g.setColor(Color.green);
		g.fillRect(0, 690, 1280, 400);
		
		g.setColor(Color.yellow);
		for (Bullet b : bullets) {
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		
		for (Enemy e : enemies) {
			if (e.getHealth() > 0)
				zombie1.draw(e.getX(), e.getY(), e.getWidth(), e.getHeight());
		}
		
		if (wait_for_input)
			nextWaveInput.draw(0, 0);
		
		g.setColor(Color.black);
		g.drawString("Level: " + level, 1200, 10);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		gc.setMouseCursor(cursor_img, 0, 0);
		
		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON))
			bullets.add(new Bullet(input.getMouseX(), input.getMouseY(), player.getX(), player.getY()));
		
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
		
		System.out.println(alive + " " + enemies.size());
		
		if (alive == 0) {
			wait_for_input = true;
			System.out.println("NEXT LEVEL");
		}
		
		for (Bullet b : bullets) {
			b.update();
		}
		
		for (Enemy e : enemies) {
			if (e.getHealth() > 0) 
				e.move();
			for (Bullet b : bullets) {
				if (checkBounds(b, e) && e.getHealth() > 0) {
					e.takeDamage(b.getDmg());
					b.setX(100000);
				} 
			}
		}
	}
	
	public void nextLevel() {
		this.level += 1;
		this.enemyCount = this.level + 2;
		enemies.clear();
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

}
