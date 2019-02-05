package net.oaster2000.sin.entity;

import java.util.Random;

import net.oaster2000.sin.graphics.Screen;
import net.oaster2000.sin.graphics.Sprite;
import net.oaster2000.sin.level.Level;

public abstract class Entity {
	

	public int x;
	public int y;
	protected Sprite sprite;
	public boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	public double eColX;   
	public double eColY;
	public int entityCheck;
	public double xBound;
	public double yBound;
	public int xOff;
	public int yOff;
	public boolean entCol;
	public int xMod;
	public int yMod;
	
	
	public Entity(){
		level = new Level();
	}

	public void update(){}
	
	public void render(Screen screen){
		if(sprite != null)screen.renderSprite(x, y, sprite, true);
	}
	
	public void remove(){
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
	public void init(Level level){
		this.level = level;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public double getoX() {
		return x - xOff;
	}
		   
	public double getoY() {
		return y - yOff;
	}
		   
	public double getoXB() {
		return x + xBound - xOff;
	}
		   
	public double getoYB() {
		return y + yBound - yOff;
	}
	
	public int convert(double value) {
		return value < 0.0D ? -1 : 1;
    }
	
	 public boolean solid()
	 {
		 return false;
	 }
	 
	 public void setPosition(int x, int y){
		 this.x = x * 16;
		 this.y = y * 16;
	 }
}