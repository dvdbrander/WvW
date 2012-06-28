package online;

import players.OtherPlayer;
import launcher.GameObject;

public class ReceiveMessages {

	private Connection connection;

	public ReceiveMessages(Connection connection) {
		this.connection = connection;
	}

	public void ping(String[] stringArray) {
		connection.setLastPing(System.currentTimeMillis() - Long.parseLong(stringArray[1]));
		connection.getSendMessages().ping();
	}

	public void updatePlayer(String[] stringArray) {
		for (GameObject currentObject : connection.getMain().getGameObjects().values()) {
			if (currentObject.id == Integer.parseInt(stringArray[2])) {
				currentObject.x.set(Integer.parseInt(stringArray[3]));
				currentObject.y.set(Integer.parseInt(stringArray[4]));
				currentObject.lastupdated = System.currentTimeMillis();
			}
		}		
	}

	public void newPlayer(String[] stringArray) {
		OtherPlayer op = new OtherPlayer(connection.getMain());
		connection.getMain().addGameObject(op);
		op.id = Integer.parseInt(stringArray[1]);
		System.out.println("New player joined on ID: "+op.id);
	}

	public void gotID(String[] stringArray) {
		connection.id = Integer.parseInt(stringArray[1]);
		for (GameObject currentObject : connection.getMain().getGameObjects().values()) {
			if (currentObject.id.equals(-2)) {
				currentObject.id = Integer.parseInt(stringArray[1]);
				break;
			}
		}
		System.out.println("Got ID: "+connection.id);		
	}

}
