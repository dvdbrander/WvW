package online;

public class SendMessages {
	private Connection connection;

	public SendMessages(Connection connection) {
		this.connection = connection;
	}

	public void ping() {
		connection.getOut().println("1|1|" + System.currentTimeMillis());
		connection.getOut().flush();
		connection.setLastPinged(System.currentTimeMillis());
	}

	public void join() {
		getID();
	}

	public void getID() {
		connection.getOut().println("2|1");
		connection.getOut().flush();
		System.out.println("Requesting id");
	}
}
