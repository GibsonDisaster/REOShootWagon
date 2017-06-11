package entities;

import java.awt.Rectangle;

public class Enemy {
	private int x, y, width, height, health;

	public Enemy(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = 20;
	}
	
	public void move() {
		this.x += 2;
	}
	
	//Getters and Setters
	public Rectangle bounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public int getHealth() {
		return health;
	}

	public void takeDamage(int d) {
		this.health -= d;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
