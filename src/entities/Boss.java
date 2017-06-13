package entities;

import java.awt.Rectangle;

public class Boss {
	private int x, y, width, height, health, speed;
	private String variant;
	private boolean ready = false;

	public Boss(int x, int y, int width, int height, String n) {
		this.x = x;
		this.y = y-80;
		this.width = width+80;
		this.height = height+80;
		this.variant = n; // 0 - 3
		
		if (this.variant == "Iggy") {
			this.health = 100;
			this.speed = 4;
		}
		if (this.variant == "Ozzy") {
			this.health = 200;
			this.speed = 3;
		} 
		if (this.variant == "Cher"){
			this.health = 300;
			this.speed = 2;
		}
	}
	
	public void move() {
		if (this.x >= 300)
			ready = true;
		if (!ready)
			this.x += this.speed;
		else {
			//Attack
		}
	}
	
	//Getters and Setters
	public Rectangle bounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public void setHealth(int health) {
		this.health = health;
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
