package net.oaster2000.sin.level.tile;

import net.oaster2000.sin.Game;
import net.oaster2000.sin.graphics.AnimatedSprite;
import net.oaster2000.sin.graphics.Screen;
import net.oaster2000.sin.graphics.Sprite;

public class AnimatedTile extends Tile{

	private Sprite sprite;
	private AnimatedSprite anim_sprite;
	
	public AnimatedTile(AnimatedSprite sprite) {
		super(sprite);
		this.anim_sprite = sprite;
		Game.lvl.addTile(this);
	}

	public void update() {
		anim_sprite.update();
	}
	
	public void render(int x, int y, Screen screen) {
		sprite = anim_sprite.getSprite();
		screen.renderTile(x / (Game.tileSize * Game.scale), y / (Game.tileSize * Game.scale), sprite);
	}
	
}
