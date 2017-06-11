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

import entities.Player;
import logistics.ShopItem;

public class ShopScreen extends BasicGameState {
	
	private Image background, playButtonImg, health_img, damage_img;
	int nothing = 0;
	private Player player;
	private ArrayList<ShopItem> shopitems;

	public ShopScreen(int shopScreen) {
		player = PlayScreen.getPlayer();
		shopitems = new ArrayList<>();
		shopitems.add(new ShopItem(80, 80, 80, 80, 50, "Health"));
		shopitems.add(new ShopItem(180, 80, 80, 80, 50, "Higher Damage"));
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/shopBackground.png");
		playButtonImg = new Image("res/playScreenButton.png");
		health_img = new Image("res/healthShopItem.png");
		damage_img = new Image("res/higherDamageShop.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0, 0);
		playButtonImg.draw(1200, 640);
		g.drawString("$" + Integer.toString(player.getMoney()), 1200,30);
		
		g.setColor(Color.red);
		g.fillRect(440, 40, 2*player.getHealth(), 40);
		g.drawRect(440, 40, 200, 40);
		
		g.setColor(Color.black);
		g.drawString("Health: ", 490, 20);
		
		for (ShopItem s : shopitems) {
			if (s.getName() == "Health")
				health_img.draw(s.getX(), s.getY());
			if (s.getName() == "Higher Damage")
				damage_img.draw(s.getX(), s.getY());
			g.drawString("$" + s.getCost(), s.getX()+30, s.getY() + 90);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
			if ( (new Rectangle(Mouse.getX(), 720-Mouse.getY(), 80, 80).intersects(new Rectangle(1200, 640, 80, 80)) ) )
				sbg.enterState(1);
			
			for (ShopItem s : shopitems) {
				if ((new Rectangle(Mouse.getX(), 720 - Mouse.getY(), 80, 80)).intersects(s.bounds())) {
					if (player.getMoney() - s.getCost() >= 0) {
						if (s.getName() == "Health") {
							if (player.getHealth()+10 <= player.getMaxHealth()) {
								player.giveHealth(10);
								player.giveMoney(-s.getCost());
							}
						}
						if (s.getName() == "Higher Damage") {
							player.upgradeDmgBonus(2);
							player.giveMoney(-s.getCost());
						}
					}
				}
			}
		}
		
		if (input.isKeyPressed(input.KEY_SPACE))
			nothing += 1;
		if (input.isKeyPressed(input.KEY_ESCAPE))
			nothing -= 1;
	}

	@Override
	public int getID() {
		return 2;
	}
	
}
