package online;

import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

import launcher.GameObject;
import launcher.MainThread;

public class Online extends GameObject{
	public Online(MainThread main) {
		super(main);
	}

	private Connection connection = new Connection();
	public PrintWriter out;
	
	
	@Override
	public void init(){
		getConnection().init(this, main);
		connect();
		super.init();
	}
	
	public void connect(){
		getConnection().connect();
		((Thread)getConnection()).start();
		out = getConnection().getOut();
	}
	
	@Override
	public void update() {
		ConcurrentHashMap<Integer, GameObject> gameObjects = main.getGameObjects();
		for (GameObject object:gameObjects.values()){
			if (object.getSync()){
				String string = "4|0|"+connection.id;
				for (Object obj: object.getToSync()){
					string += "|";
					string += obj.toString();
				}
				getConnection().getOut().println(string);
			}
		}
		super.update();
	}
	
	public long getPing() {
		return getConnection().getLastPing();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
