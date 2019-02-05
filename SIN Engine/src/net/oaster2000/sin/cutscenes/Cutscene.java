package net.oaster2000.sin.cutscenes;

import net.oaster2000.sin.graphics.Screen;
import net.oaster2000.sin.input.Keyboard;

public class Cutscene {

	//public static Cutscene introTutorial = new Cutscene(0, new int[] {0, 1, 2}, 3); - Initialization Example
	
	public int delay = 0;

	public int id;
	public int[] frameIDs;
	public int frames;
	public int currentFrame = 0;
	public int borderColor = 0xff222222;
	public int bgRed = 60;
	public int bgGreen = 60;
	public int bgBlue = 60;
	public int bgBrightness = -15;
	public int textColor = 0xffcccccc;
	
	public Cutscene(int id, int[] framesIDs, int frames) {
		this.id = id;
		this.frameIDs = framesIDs;
		this.frames = frames;
	}

	public void render(Screen screen) {
		screen.drawTextBox(10, 130, 340, 50, bgRed, bgGreen, bgBlue, bgBrightness, false);
		screen.drawRect(10, 130, 340, 50, borderColor, false);

		if (frameIDs[currentFrame] == 0) {

			//Game.font.renderSmall(10, 136, textColor, "This is just an example of how a single", screen);
			//Game.font.renderSmall(10, 146, textColor, "page of a cutscene is created so that you", screen);
			//Game.font.renderSmall(10, 156, textColor, "are able to do alot more in a single frame", screen);
			//Game.font.renderSmall(10, 166, textColor, "you have up to 4 lines;", screen);

		}
	}

	public void update(Keyboard input) {
		int framesLeft = frames - currentFrame;
		if (framesLeft <= 0) {
			currentFrame = 0;
		}
		
		if (input.enter) {
			if (framesLeft > 0 && delay == 0) {
				currentFrame++;
				delay = 20;
			}
		}

		if (delay > 0) {
			delay--;
		}
	}

	public int getFramesLeft() {
		return frames - currentFrame;
	}

}
