package online;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CopyOnWriteArrayList;

import launcher.MainThread;

public class Connection extends Thread{
	Socket socket;
	private String host = "localhost";
	private InputStreamReader in;
	private PrintWriter out;
	@SuppressWarnings("unused")
	private Online online;
	private MainThread main;
	private CopyOnWriteArrayList<String> messages = new CopyOnWriteArrayList<String>();
	private long lastPinged;
	
	public void init(Online online, MainThread main){
		this.online = online;
		this.main = main;
	}

	public void connect() {
		try {
			socket = new Socket(host ,25367);
			in = new InputStreamReader(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(),true);
		} catch (UnknownHostException e) {
			System.out.println("Could not connect.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect.");
			e.printStackTrace();
		}
		System.out.println("Connected");
		
	}

	public void ping() {
		out.println("1|"+System.currentTimeMillis());
		out.flush();
		System.out.println("ping...");
		lastPinged = System.currentTimeMillis();
	}
	
	public void update(){
		
	}

	@Override
	public void run() {
		while (main.isRunning()){
			if (System.currentTimeMillis() - lastPinged >= 1000){
				ping();
			}
			try {
				if (in.ready()){
					String string = "";
					int chr = in.read();
					while (chr != -1){
						string += Character.toString((char) chr);
					}
					messages.add(string);
					System.out.println(string);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
