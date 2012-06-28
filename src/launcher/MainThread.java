package launcher;

import graphics.ImageLoader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JPanel;

import online.Online;
import players.Player;
/*	Package id's: 
 * 1: ping
 * 2: player's position
 * 3: new player
 * 4: getting player's ID
 * 
 * */
public class MainThread extends JPanel implements Runnable, KeyListener{
	private static final long serialVersionUID = -1804148473991244440L;
	private JPanel panel;
	private FPS fps = new FPS(this);
	private Dimension size;
	private int width, height;
	private int timer = 0;
	public Online online = new Online(this);
	private ConcurrentHashMap<Integer,GameObject> gameObjects = new ConcurrentHashMap<Integer,GameObject>();
	private boolean run = true;
	public ImageLoader imgLoader = new ImageLoader(this);
	boolean[] keys = new boolean[525];
	

	public MainThread(Dimension size) {
		this.setPanel(this);
		this.size = size;
		setSize(size);
	}

	public void init() {
		getPanel().setSize(size);
		getPanel().setVisible(true);
		getPanel().setFocusable(true);
		getPanel().requestFocusInWindow();
		width = ((Double)size.getWidth()).intValue();
		height = ((Double)size.getHeight()).intValue();
		addGameObject(online);
		online.init();
		Player player = new Player(this);
		addGameObject(player);
		player.init();
		getPanel().addKeyListener(this);
		System.out.println("Initialized");
	}

	@Override
	public void run() {
		init();
		while (isRunning() ) {
			fps.updateFPS();
			repaint();
			for (GameObject object:gameObjects.values()){
				object.update();
			}
			
			try {
				Thread.sleep(fps.getSleepmillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		
		if (timer  <= 60) {
			for (int x = 0; x <= 480; x += 10) {
				g.drawRect(x, x, 10, 10);
			}
		} else {
			for (int x = 0; x <= 480; x += 10) {
				g.drawRect(x, 480 - x, 10, 10);
			}
		}
		timer ++;
		if (timer >= 120){
			timer = 0;
		}
		

		for (GameObject object:gameObjects.values()){
			object.paint(g);
		}
	}

	public void addGameObject(GameObject object){
		for (int i=0; true; i += 1){
			if (!getGameObjects().containsKey(i)){
				getGameObjects().put(i,object);
//				object.id = i;
				break;
			}
		}
	}
	
	public ConcurrentHashMap<Integer, GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(ConcurrentHashMap<Integer, GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public boolean isRunning() {
		return run;
	}

	public void setRunning(boolean run) {
		this.run = run;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel canvas) {
		this.panel = canvas;
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
