package net.oaster2000.sin.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y, width, height;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	//voidSprite is a necessary picture
	public static Sprite voidSprite = new Sprite(64, 0xff112358);
	public static Sprite basicPlayer = new Sprite(128, 0, 0, SpriteSheet.player);
	
	public static Sprite grassTile = new Sprite(64, 0, 0, SpriteSheet.tiles);
	
	public Sprite(SpriteSheet sheet, int height, int width) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		this.pixels = new int[this.width * this.height];
	}
	
	public Sprite(int size, int x, int y, SpriteSheet ss) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = ss;
		LoadSprite();
	}
	
	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[this.width * this.height];
		setColor(color);
	}
	
	public Sprite(int size, int color){
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		for(int i = 0; i < pixels.length; i++){
			this.pixels[i] = pixels[i];
		}
	}

	private void setColor(int color) {
		for(int i = 0; i < width * height; i++){
			pixels[i] = color;
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	private void LoadSprite(){
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}		
		}
	}

	public static Sprite[] split(SpriteSheet sheet) {
		int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT);
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];
		
		for(int yp=0; yp < sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++){
			for(int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++){
				
				for(int y = 0; y < sheet.SPRITE_HEIGHT; y++){
					for(int x = 0; x < sheet.SPRITE_WIDTH; x++){
						int xo = x + xp * sheet.SPRITE_WIDTH;
						int yo = y + yp * sheet.SPRITE_HEIGHT;
						pixels[x + y * sheet.SPRITE_WIDTH] = sheet.getPixels()[xo + yo * sheet.getWidth()];
					}	
				}
				
				sprites[current++] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
			}
		}
		
		return sprites;
	}
	
}
