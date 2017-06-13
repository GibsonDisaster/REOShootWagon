package entities;

import java.awt.Rectangle;

public class Bullet {
	private int x, y, width, height, xDir, yDir, dmg;

	public Bullet(int mx, int my, int x, int y) {
		this.x = x-80;
		this.y = y + 80;
		this.width = 10;
		this.height = 10;
		this.xDir = mx-x;
		this.yDir = my-y;
		this.dmg = 10;
	}
	
	public void update() {
		this.x += xDir/30;
		this.y += yDir/30;
	}
	
	//Getters and Setters
	public Rectangle bounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public int getxDir() {
		return xDir;
	}

	public void setxDir(int xDir) {
		this.xDir = xDir;
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
}
