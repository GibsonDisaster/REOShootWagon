package entities;

import java.awt.Rectangle;

public class Player {
	private int x, y, width, height, money, health, dmgBonus;

	public Player(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.money = 0;
		this.health = 100;
		this.dmgBonus = 0;
	}
	
	//Getters and Setters
	public Rectangle bounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public int getDmgBonus() {
		return dmgBonus;
	}

	public void upgradeDmgBonus(int dmgBonus) {
		this.dmgBonus += dmgBonus;
	}
	
	public int getMaxHealth() {
		return 100;
	}

	public int getHealth() {
		return health;
	}
	
	public void giveHealth(int h) {
		this.health += h;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void takeDamage(int d) {
		this.health -= d;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void giveMoney(int money) {
		this.money += money;
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
