package launcher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import online.Online;

public class MainThread extends JPanel implements Runnable{
	private static final long serialVersionUID = -1804148473991244440L;
	private JPanel canvas;
	private FPS fps = new FPS();
	private Dimension size;
	private int width, height;
	private int timer = 0;
	private Online online = new Online(this);
	private CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<GameObject>();
	private boolean run = true;

	public MainThread(Dimension size) {
		this.canvas = this;
		this.size = size;
		setSize(size);
	}

	public void init() {
		canvas.setSize(size);
		canvas.setVisible(true);
		width = ((Double)size.getWidth()).intValue();
		height = ((Double)size.getHeight()).intValue();
		getGameObjects().add(online);
		online.init();
	}

	@Override
	public void run() {
		init();
		while (isRunning() ) {
			fps.updateFPS();
			repaint();
			for (GameObject object:gameObjects){
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
	}

	public CopyOnWriteArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(CopyOnWriteArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public boolean isRunning() {
		return run;
	}

	public void setRunning(boolean run) {
		this.run = run;
	}

}
