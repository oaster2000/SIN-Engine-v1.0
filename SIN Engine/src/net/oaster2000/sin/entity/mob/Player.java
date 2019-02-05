package net.oaster2000.sin.entity.mob;

import net.oaster2000.sin.graphics.Screen;
import net.oaster2000.sin.graphics.Sprite;
import net.oaster2000.sin.input.Keyboard;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	
	public static int scale = 0;

	private boolean walking = false;

	public int delay = 0;

	public int iterate = 0;

	//public static AnimatedSprite up = new AnimatedSprite(null, 32, 32, 4, 10);
	//public static AnimatedSprite down = new AnimatedSprite(null, 32, 32, 4, 10);
	//public static AnimatedSprite left = new AnimatedSprite(null, 32, 32, 4, 10);
	//public static AnimatedSprite right = new AnimatedSprite(null, 32, 32, 4, 10);

	//public static AnimatedSprite animSprite = null;

	public Player(int x, int y, Keyboard input) {
		this.x = x * 16 + 8;
		this.y = y * 16 + 8;
		this.input = input;
		//animSprite = down;

		xBound = 14.0D;
		yBound = 8.0D;
		xOff = 7;
		yOff = -4;
	}

	public boolean solid() {
		return true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void update() {
		iterate++;
		if (iterate >= 60) {
			iterate = 0;
		}
		int xa = 0, ya = 0;

		if (walking) {
			//animSprite.update();
		} else {
			//animSprite.setFrame(0);
			//sprite = animSprite.getSprite();
		}

		if (input.up) {
			//animSprite = up;
			//animSprite.setFrameRate(10);
			ya -= 2;
		}
		if (input.down) {
			//animSprite = down;
			//animSprite.setFrameRate(10);
			ya += 2;
		}
		if (input.left) {
			//animSprite = left;
			//animSprite.setFrameRate(10);
			xa -= 2;
		}
		if (input.right) {
			//animSprite = right;
			//animSprite.setFrameRate(10);
			xa += 2;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		//int mx = x - Game.game.getWidth() / 2 + (Mouse.getX() / 3) - 178;
		//int my = y - Game.game.getHeight() / 2 + (Mouse.getY() / 3) - 89;

		if (delay > 0) {
			delay--;
		}
	}

	public void render(Screen screen) {
		//sprite = animSprite.getSprite();
		sprite = Sprite.basicPlayer;
		screen.renderMob(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2), sprite);
	}

}
