package net.oaster2000.sin.graphics;

public class Font {

	//private static SpriteSheet font = new SpriteSheet("/fonts/main.png", 16);
	//private static Sprite[] characters = Sprite.split(font);
	
	//private static SpriteSheet fontSmall = new SpriteSheet("/fonts/small.png", 8);
	//private static Sprite[] charactersSmall = Sprite.split(fontSmall);

	private static String charIndex = "ABCDEFGHIJKLM" + 
			"NOPQRSTUVWXYZ" +
			"abcdefghijklm" +
			"nopqrstuvwxyz" +
			"0123456789.,'" +
			"\":;!@$%()-+?£";

	public Font() {

	}
	
	public void render(int x, int y, String text, Screen screen) {
		render(x, y, 0xff000000, -5, text, screen);
	}
	
	public void render(int x, int y, int color, String text, Screen screen) {
		render(x, y, color, -5, text, screen);
	}

	public void render(int x, int y, int color, int spacing, String text, Screen screen) {
		int xOffset = 0;
		int line = 0;
		for(int i = 0; i < text.length(); i++){
			xOffset += 14 + spacing;
			int yOffset = 0;
			char currentChar = text.charAt(i);
			if(currentChar == 'g' || currentChar == 'y' || currentChar == 'p' || currentChar == 'q' || currentChar == 'j') yOffset = 4;
			if(currentChar == ',') yOffset = 2;
			if(currentChar == '\n'){
				line++;
				xOffset = 0;
			}
			int index = charIndex.indexOf(currentChar);
			if(index == -1) continue;
			//screen.renderTextInColor(x + xOffset, y + line * 20 + yOffset, characters[index], color, false);
		}
	}
	
	public void renderSmall(int x, int y, String text, Screen screen) {
		renderSmall(x, y, 0xff000000, 0, text, screen);
	}
	
	public void renderSmall(int x, int y, int color, String text, Screen screen) {
		renderSmall(x, y, color, 0, text, screen);
	}

	public void renderSmall(int x, int y, int color, int spacing, String text, Screen screen) {
		int xOffset = 0;
		int line = 0;
		for(int i = 0; i < text.length(); i++){
			xOffset += 6 + spacing;
			int yOffset = 0;
			char currentChar = text.charAt(i);
			if(currentChar == 'g' || currentChar == 'y' || currentChar == 'p' || currentChar == 'q' || currentChar == 'j') yOffset = 2;
			if(currentChar == ',') yOffset = 1;
			if(currentChar == '\n'){
				line++;
				xOffset = 0;
			}
			int index = charIndex.indexOf(currentChar);
			if(index == -1) continue;
			//screen.renderTextInColor(x + xOffset, y + line * 20 + yOffset, charactersSmall[index], color, false);
		}
	}
}
