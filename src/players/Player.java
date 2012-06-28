package players;

import java.util.ArrayList;

import launcher.ControllableGameObject;
import launcher.MainThread;

public class Player extends ControllableGameObject {
	public Player(MainThread main) {
		super(main);
	}
	
	@Override
	public void init() {
		img = main.imgLoader.loadBufferedImage("player.png");
		sync = true;
		toSync = new ArrayList<Object>();
		toSync.add(x);
		toSync.add(y);
		id = -2;
		super.init();
	}
}
