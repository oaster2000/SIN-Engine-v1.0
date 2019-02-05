package net.oaster2000.sin.graphics;

public class AnimatedSprite extends Sprite{

	private int frame = 0;
	private Sprite sprite;
	private int rate = 5;
	private int time = 0;
	private int length = -1;
	
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length, int rate) {
		super(sheet, width, height);
		this.length = length;
		this.rate = rate;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length) System.err.println("Error: Length of Animation is too large");
	}
	
	public void update(){
		time++;
		if(time % rate == 0){
			if(frame >= length - 1) frame = 0;
			else frame++;
			sprite = sheet.getSprites()[frame];
		}
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public int getFrame(){
		return frame;
	}
	
	public void setFrameRate(int frames){
		rate = frames;
	}
	
	public void setFrame(int index){
		if (index > sheet.getSprites().length - 1){
			System.err.println("Index out of Bounds in " + this);
		}
		sprite = sheet.getSprites()[index];
	}
}
