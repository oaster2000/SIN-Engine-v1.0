package net.oaster2000.sin.level.tile;

import net.oaster2000.sin.graphics.Sprite;

public class BasicTile extends Tile {

	public BasicTile(Sprite sprite) {
		super(sprite);
	}

	public boolean solid() {
		return false;
	}
	
	public boolean isVoid() {
		return false;
	}
}
