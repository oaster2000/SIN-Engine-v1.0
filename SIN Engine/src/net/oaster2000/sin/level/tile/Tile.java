package net.oaster2000.sin.level.tile;

import net.oaster2000.sin.graphics.Screen;
import net.oaster2000.sin.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile grassTile = new BasicTile(Sprite.grassTile);
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 6, y << 6, sprite);
	}
	
	public boolean solid(){
		return false;
	}
	
	public boolean liquid(){
		return false;
	}

	public boolean hot() {
		return false;
	}
	
	public boolean isVoid(){
		return false;
	}
	
	public boolean portal(){
		return false;
	}
}
