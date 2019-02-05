package net.oaster2000.sinserver;

public class SinServer {

	public static void main(String[] args) {
		Server server = new Server(2481);

		//other stuff here
		
		server.start();
	}

}
