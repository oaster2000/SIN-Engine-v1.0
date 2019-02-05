package net.oaster2000.sin.gui;

import net.oaster2000.sin.graphics.Screen;
import net.oaster2000.sin.graphics.Sprite;

public class GUI {

	public int x, y;
	public Sprite sprite;
	public boolean visible;
	
	public GUI(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
	}
}
