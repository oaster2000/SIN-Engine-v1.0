package net.oaster2000.sin;

import javax.swing.JApplet;

public class AppletRun extends JApplet{

	private static final long serialVersionUID = 1L;
	
	public void init() {
		Game game = new Game();
		add(game);
		game.start();
	}


}
