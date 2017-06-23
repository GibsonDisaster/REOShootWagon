package entities;

import java.awt.Rectangle;

public class Rock {
	private int x, y, width, height, health, yDir, dmg;
	private String variant;

	public Rock(int x, int y, String var) {
		this.variant = var;
		this.x = x-80;
		this.y = y + 80;
		this.width = 100;
		this.height = 100;
		this.dmg = 20;
		this.health = 30;
	}
	
	public void update() {
		this.x += 8;
	}
	
	//Getters and Setters
	public int getHealth() {
		return this.health;
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

	public Rectangle bounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	public int getyDir() {
		return yDir;
	}

	public void setyDir(int yDir) {
		this.yDir = yDir;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
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

	public void takeDamage(int i) {
		this.health -= i;
	}
}
