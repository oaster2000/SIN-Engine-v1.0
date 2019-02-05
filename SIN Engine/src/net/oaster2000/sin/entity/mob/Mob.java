package net.oaster2000.sin.entity.mob;

import net.oaster2000.sin.entity.Entity;
import net.oaster2000.sin.graphics.Screen;
import net.oaster2000.sin.level.Level;

public abstract class Mob extends Entity {
	
	public boolean swimming = false;
	public boolean burning = false;
	public int dir = 1;
    	protected int time = 0;
	protected boolean moving = false;
	protected String URL;
	public boolean dead;
	public int entityHealth;

	public Mob player;

	public abstract void update();

	public abstract void render(Screen screen);

	protected boolean collision(double xa, double ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (int) (((x + xa) + c % 2 * 13 - 7) / 16);
			int yt = (int) (((y + ya) + c / 2 * 7 + 4) / 16);
			if (level.getTile(xt, yt).solid()) {
				solid = true;
			}
		}
		return solid;
	}

	public void move(double xa, double ya)
	   {
		 if ((xa != 0.0D) && (ya != 0.0D)) {
	        move(xa, 0.0D);
	        move(0.0D, ya);
			return;
	     }
	     
	     if (xa > 0.0D)
	       dir = 3;
	     if (xa < 0.0D)
	       dir = 2;
	     if (ya > 0.0D)
	       dir = 1;
	     if (ya < 0.0D)
	       dir = 0;

			for (int x = 0; x < Math.abs(xa); x++) {
				if ((!collision(abs(xa), ya)) && (!tileVoidCollision(abs(xa), ya))) {
					this.x += abs(xa);
				}
			}

			for (int y = 0; y < Math.abs(ya); y++) {
				if ((!collision(xa, abs(ya)) && (!tileVoidCollision(abs(xa), ya)))) {
					this.y += abs(ya);
				}
			}
	     
	     if(!swim(xa, ya)){
	    	 swimming = false;
	     }else if (swim(xa, ya)){
	    	 swimming = true;
	     }
	     
	     if(!burn(xa, ya)){
	    	 burning = false;
	     }else if(burn(xa, ya)){
	    	 burning = true;
	     }
	   }

	private double abs(double value) {
		if (value < 0) return -1;
		return 1;
	}

	public boolean swim(double xa, double ya) {
		boolean liquid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (int) (((x + xa) + c % 2 * 5 - 5) / 16);
			int yt = (int) (((y + ya) + c / 2 * 5 + 6) / 16);
			if (level.getTile(xt, yt).liquid()) {
				liquid = true;
			}
		}
		return liquid;
	}
	
	public boolean burn(double xa, double ya) {
		boolean burn = false;
		for (int c = 0; c < 4; c++) {
			int xt = (int) (((x + xa) + c % 2 * 5 - 5) / 16);
			int yt = (int) (((y + ya) + c / 2 * 5 + 6) / 16);
			if (level.getTile(xt, yt).hot()) {
				burn = true;
			}
		}
		return burn;
	}
	
	public boolean tileVoidCollision(double xa, double ya) {
		boolean isVoid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (int) (((x + xa) - c % 2 * 18) /16);
			int yt = (int) (((y + ya) - c / 2 * 5) /16);
			if (level.getTile(xt, yt).isVoid()) {
				isVoid = true;
			}
		}
		return isVoid;
	}
	
	public void init(Level level) {
		this.level = level;
	}
}
