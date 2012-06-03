package online;

import launcher.GameObject;
import launcher.MainThread;

public class Online extends GameObject{
	private Connection connection = new Connection();
	private MainThread main;
	
	public Online(MainThread main) {
		this.main = main;
	}
	
	@Override
	public void init(){
		connection.init(this, main);
		connect();
		super.init();
	}
	
	public void connect(){
		connection.connect();
		((Thread)connection).start();
	}
	
	@Override
	public void update() {
		
		super.update();
	}
}
