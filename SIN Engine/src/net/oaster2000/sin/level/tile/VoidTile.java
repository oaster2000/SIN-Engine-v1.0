package net.oaster2000.sin.level.tile;

import net.oaster2000.sin.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	public boolean solid() {
		return true;
	}
	
	public boolean isVoid() {
		return true;
	}
}
