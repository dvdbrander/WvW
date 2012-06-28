package online;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CopyOnWriteArrayList;

import launcher.MainThread;

import online.SendMessages;

public class Connection extends Thread {
	Socket socket;
	private String host = "localhost";
	private InputStreamReader in;
	private PrintWriter out;
	@SuppressWarnings("unused")
	private Online online;
	private MainThread main;
	private CopyOnWriteArrayList<String> messages = new CopyOnWriteArrayList<String>();
	private long lastPinged = System.currentTimeMillis();
	private long lastPing = 0;
	private SendMessages sendMessages = new SendMessages(this);
	ReceiveMessages receiveMessages = new ReceiveMessages(this);
	public int id = -1;

	public void init(Online online, MainThread main) {
		this.online = online;
		this.setMain(main);
	}

	public void connect() {
		try {
			socket = new Socket(host, 25367);
			in = new InputStreamReader(socket.getInputStream());
			setOut(new PrintWriter(socket.getOutputStream(), true));
		} catch (UnknownHostException e) {
			System.out.println("Could not connect.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect.");
			e.printStackTrace();
		}
		// sendMessages.join();
		System.out.println("Connected");

	}

	public void update() {

	}

	@Override
	public void run() {
		while (getMain().isRunning()) {
			if (System.currentTimeMillis() - getLastPinged() >= 1000) {
				getSendMessages().ping();
				if (id == -1)
					getSendMessages().join();
			}
			if (System.currentTimeMillis() - getLastPinged() >= 10000) {
				System.out.println("Timed out");
				getMain().setRunning(false);
			}
			try {
				if (in.ready()) {
					String string = "";
					int chr = in.read();
					while (chr != -1 && ((char) chr) != '\n') {
						string += Character.toString((char) chr);
						chr = in.read();
					}
					String[] stringArray = (string.replaceAll("\\r|\\n", "")).split("\\|");
					if (stringArray[0] != "") {
						if (!((Integer.parseInt(stringArray[0])) + "").equals(stringArray[0])) {
							System.out.println("Received unreadable message: " + string);
						} else {
							int messageId = Integer.parseInt(stringArray[0]);
							switch (messageId) {
								case 1:
									receiveMessages.ping(stringArray);
								break;

								case 2:
									if (id != -1)
										break;
									receiveMessages.gotID(stringArray);
								break;

								case 3:
									receiveMessages.newPlayer(stringArray);
								break;

								case 4:
									receiveMessages.updatePlayer(stringArray);
								break;

								default:
									System.out.println("Received unreadable message: " + string);
								break;
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public CopyOnWriteArrayList<String> getMessages() {
		return messages;
	}

	public void setMessages(CopyOnWriteArrayList<String> messages) {
		this.messages = messages;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public long getLastPing() {
		return lastPing;
	}

	public void setLastPing(long lastPing) {
		this.lastPing = lastPing;
	}

	public long getLastPinged() {
		return lastPinged;
	}

	public void setLastPinged(long lastPinged) {
		this.lastPinged = lastPinged;
	}

	public MainThread getMain() {
		return main;
	}

	public void setMain(MainThread main) {
		this.main = main;
	}

	public SendMessages getSendMessages() {
		return sendMessages;
	}

	public void setSendMessages(SendMessages sendMessages) {
		this.sendMessages = sendMessages;
	}
}
