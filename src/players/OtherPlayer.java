package players;

import launcher.GameObject;
import launcher.MainThread;

public class OtherPlayer extends GameObject{

	public OtherPlayer(MainThread main) {
		super(main);
		img = main.imgLoader.loadBufferedImage("player.png");
	}
	
	
}
