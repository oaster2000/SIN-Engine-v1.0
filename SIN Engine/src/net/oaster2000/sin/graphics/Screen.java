package net.oaster2000.sin.graphics;

import net.oaster2000.sin.util.ColorUtils;
import net.oaster2000.sin.util.MathsUtils;

public class Screen {
	
	public int width, height;
	public int[] pixels;
	public final int Map_Size = 32;
	public final int Map_Size_Mask = Map_Size - 1;
	public int[] tiles = new int[Map_Size * Map_Size];
	public int xOffset, yOffset;
		
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	
	public void renderTile(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < sprite.SIZE; y++){
			int ya = y + yp; 
			for(int x = 0; x < sprite.SIZE; x++){
				int xa = x + xp; 
				if(xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0 ) xa = 0;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
			}
		}
	}
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
	     if (fixed) {
	       xp -= xOffset;
	       yp -= yOffset;
	     }
	     
	     for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
	    	 int ya = y + yp;
	         for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
	        	 int xa = x + xp;
	        	 if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
	        	 pixels[(xa + ya * width)] = sheet.pixels[(x + y * sheet.SPRITE_WIDTH)];
	         }
	     }
	}
	
	public void renderTextInColor(int xp, int yp, Sprite sprite, int color, boolean fixed) {
	    if(fixed){   
	    	xp -= xOffset;
	       yp -= yOffset;
	    }
	     
	     for (int y = 0; y < sprite.getHeight(); y++) {
	    	 int ya = y + yp;
	         for (int x = 0; x < sprite.getWidth(); x++) {
	        	 int xa = x + xp;
	        	 if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
	        	 int col = sprite.pixels[x + y * sprite.getWidth()];
	        	 if(col != 0xffff00ff) pixels[(xa + ya * width)] = color;
	         }
	     }
	}

	public void renderGUISprite(int xp, int yp, Sprite sprite, boolean fixed) {
	    if(fixed){   
	    	xp -= xOffset;
	       yp -= yOffset;
	    }
	     
	     for (int y = 0; y < sprite.getHeight(); y++) {
	    	 int ya = y + yp;
	         for (int x = 0; x < sprite.getWidth(); x++) {
	        	 int xa = x + xp;
	        	 if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
	        	 int col = sprite.pixels[x + y * sprite.getWidth()];
	        	 if(col != 0xffff00ff)pixels[(xa + ya * width)] = col;
	         }
	     }
	}
	
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
	     if (fixed) {
	       xp -= xOffset;
	       yp -= yOffset;
	     }
	     
	     for (int y = 0; y < sprite.getHeight(); y++) {
	    	 int ya = y + yp;
	         for (int x = 0; x < sprite.getWidth(); x++) {
	        	 int xa = x + xp;
	        	 if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
	        	 pixels[(xa + ya * width)] = sprite.pixels[(x + y * sprite.getWidth())];
	         }
	     }
	}
	
	public void renderMob(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < sprite.SIZE; y++){
			int ya = y + yp; 
			for(int x = 0; x < sprite.SIZE; x++){
				int xa = x + xp; 
				if(xa < sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0 ) xa = 0;
				int col = sprite.pixels[x + y * sprite.SIZE];
				if(col != 0xffff00ff)pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed) {
		if (fixed) {
		       xp -= xOffset;
		       yp -= yOffset;
		}
		
		for(int x = xp; x < xp + width; x++){
			if(x < 0 || x >= this.width || yp >=this.height) continue;
			if(yp > 0)pixels[x + yp * this.width] = color;
			if(yp + height >= this.height) continue;
			if(yp + height > 0)pixels[x + (yp + height) * this.width] = color;
		}
		for(int y = yp; y <= yp + height; y++){
			if(xp >= this.width || y < 0 || y >=this.height) continue;
			if(xp > 0)pixels[xp + y * this.width] = color;
			if(xp + width >= this.width) continue;
			if(xp + width > 0)pixels[(xp + width) + y * this.width] = color;
		}
	}
	
	public void fillRect(int xp, int yp, int width, int height, int color, boolean fixed) {
		if (fixed) {
		       xp -= xOffset;
		       yp -= yOffset;
		}
		
		for(int y = 0; y < height; y++){
			int yo = yp + y;
			if(yo < 0 || yo >= this.height)
				continue;
			for(int x = 0; x < width; x++){
				int xo= xp + x;
				if(xo < 0 || xo >= this.width)
					continue;
				pixels[xo + yo * this.width] = color;
			}
		}
	}
	
		public void fillRectFade(int xp, int yp, int width, int height, int color, boolean fixed, int timeToFade) {
		int time = 0;
		time++;
		if (time >= 500) {
			time = 0;
		}

		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		for (int y = 0; y < height; y++) {
			int yo = yp + y;
			if (yo < 0 || yo >= this.height)
				continue;
			for (int x = 0; x < width; x++) {
				int xo = xp + x;
				if (xo < 0 || xo >= this.width)
					continue;

				if (time <= timeToFade) {
					pixels[xo + yo * this.width] = color;
				}else {
					float fraction = 1 / 60;
					pixels[xo + yo * this.width] = (int) MathsUtils.lerp(color, 0xff000000, fraction);
				}
			}
		}
	}

	public void drawTextBox(int xp, int yp, int width, int height, int r, int g, int b, int brightness, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < height; y++) {
			int yo = yp + y;
			if (yo < 0 || yo >= this.height)
				continue;
			for (int x = 0; x < width; x++) {
				int xo = xp + x;
				if (xo < 0 || xo >= this.width)
					continue;
				pixels[xo + yo * this.width] = ColorUtils
						.changeBrightness(ColorUtils.tint(pixels[xo + yo * this.width], r, g, b), brightness);
			}
		}
	}
}
