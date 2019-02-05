package net.oaster2000.sin.util;

import net.oaster2000.sin.gui.slot.Slot;
import net.oaster2000.sin.item.ItemStack;

public class ItemMovingHandler {

	public static void moveItem(ItemStack item, int x, int y){
		item.x = x;
		item.y = y;
	}
	
	public static void moveItemToSlot(ItemStack item, Slot slot){
		if(item != null){
			item.x = slot.x;
			item.y = slot.y;
		}
	}
	
}
