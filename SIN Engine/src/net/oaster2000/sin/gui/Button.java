package net.oaster2000.sin.gui;

import net.oaster2000.sin.Game;
import net.oaster2000.sin.graphics.Screen;
import net.oaster2000.sin.graphics.Sprite;
import net.oaster2000.sin.input.Mouse;

public class Button{

	public boolean visible = false;
	public boolean mouseInButton = false;
	public boolean clicked = false;
	protected Sprite sprite;

	protected int x, y;
	
	private int sizeX;
	private int sizeY;
	public Sprite spriteUnSelected;
	public Sprite spriteSelected;
	public Sprite spriteClick;

	public Button(int x, int y, int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.x = x;
		this.y = y;
		spriteUnSelected = new Sprite(0, 0, 0xff7F6230);
		sprite = spriteUnSelected;
	}

	public void update() {
		int scale = Game.scale;
		if (Mouse.getX() <= (x + sizeX) * scale && Mouse.getY() <= (y + sizeY) * scale && Mouse.getX() >= x * scale
				&& Mouse.getY() >= y * scale) {
			mouseInButton = true;
			if (Mouse.getButton() == 1) {
				clicked = true;
				sprite = spriteClick;
			} else {
				clicked = false;
				sprite = spriteSelected;
			}
		}else{
			mouseInButton = false;
			this.sprite = spriteUnSelected;
		}
	}

	public void render(Screen screen) {
		if(visible) {
			screen.renderGUISprite(x, y, sprite, false);
		}
	}

}
