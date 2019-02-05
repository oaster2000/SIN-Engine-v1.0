package net.oaster2000.sin.item;

import net.oaster2000.sin.graphics.Sprite;

public abstract class Item{
	
	public static int id;
	public Sprite sprite;
	public Sprite spriteNorm;
	public Sprite spriteClick;
	public Sprite spriteBroke;
	public String name;
	public String entity;
	
	public Item() {
		sprite = spriteNorm;
	}
	
	public abstract void update();

	public int getId() {
		return id;
	}	
	
	
}
