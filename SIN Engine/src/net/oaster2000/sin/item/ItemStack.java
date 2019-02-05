package net.oaster2000.sin.item;

import net.oaster2000.sin.Game;
import net.oaster2000.sin.graphics.Screen;

public class ItemStack {

	public int x, y, amount;
	public Item item;
	public int itemID;

	public ItemStack(int x, int y, int itemID) {
		this.x = x;
		this.y = y;
		this.amount = 1;
		this.itemID = itemID;
		this.item = itemSearch(itemID);
	}

	public ItemStack(int x, int y, int itemID, int amount) {
		this.x = x;
		this.y = y;
		this.itemID = itemID;
		if (amount > 0 && amount <= 64) {
			this.amount = amount;
		} else if (amount > 64) {
			this.amount = 64;
		} else {
			amount = 0;
		}
		this.item = itemSearch(itemID);
	}

	private Item itemSearch(int itemID) {
		switch (itemID) {
		case 0:
			return null;
		}
		return null;
	}

	public Item getItem() {
		return item;
	}

	public void render(Screen screen) {
		if (amount >= 1) {
			if(item != null)screen.renderGUISprite(x, y, item.sprite, false);
			if (amount > 1) {
				Game.font.renderSmall(x + 17, y + 17, 0xffcccccc, amount + "", screen);
			}
		}
	}
}
