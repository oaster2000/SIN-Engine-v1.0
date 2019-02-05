package net.oaster2000.sin.gui.slot;

import net.oaster2000.sin.graphics.Screen;
import net.oaster2000.sin.graphics.Sprite;

public abstract class Slot {

	public int x;
	public int y;
	protected Sprite sprite = null;
	
	public abstract void update();
	
	public void render(Screen screen) {
		screen.renderGUISprite(x, y, sprite, false);
	}
	
}
