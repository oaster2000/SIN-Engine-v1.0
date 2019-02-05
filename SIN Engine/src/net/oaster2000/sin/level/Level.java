package net.oaster2000.sin.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import net.oaster2000.sin.entity.Entity;
import net.oaster2000.sin.graphics.Screen;
import net.oaster2000.sin.level.tile.AnimatedTile;
import net.oaster2000.sin.level.tile.Tile;

public class Level {

	protected int width;
	protected int height;
	protected int[] tilesInt;
	public int[] tiles;
	
	public List<Entity> entities = new ArrayList<Entity>();
	public List<AnimatedTile> animTiles = new ArrayList<AnimatedTile>();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.tilesInt = new int[width * height];
	}
	
	public Level(String path) {
		loadLevel(path);
	}

	public Level() {
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("EXCEPTION!! Could not load level file!");
		}
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {

		screen.setOffset(xScroll, yScroll);

		int x0 = xScroll >> 6;
		int x1 = (xScroll + screen.width + 64) >> 6;
		int y0 = yScroll >> 6;
		int y1 = (yScroll + screen.height + 64) >> 6;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}

	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.voidTile;
		}
		if (tiles[x + y * width] == 0xff00ff00) {
			return Tile.grassTile;
		}
		return Tile.voidTile;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) {
				solid = true;
			}
		}
		return solid;
	}
	
	public boolean tileVoidCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean isVoid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).isVoid()) {
				isVoid = true;
			}
		}
		return isVoid;
	}
	
	public void add(Entity e){
		e.init(this);
		entities.add(e);
	}
	
	public void update(){
		for(int i = 0; i < entities.size(); i ++){
			entities.get(i).update();
		}
		for(int i = 0; i < animTiles.size(); i ++){
			animTiles.get(i).update();
		}
	}

	public void addTile(AnimatedTile animatedTile) {
		animTiles.add(animatedTile);
	}
	
}
