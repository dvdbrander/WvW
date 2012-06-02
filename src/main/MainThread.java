package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MainThread extends JPanel implements Runnable{
	private static final long serialVersionUID = -1804148473991244440L;
	private JPanel canvas;
	private FPS fps = new FPS();
	private Dimension size;
	private int width, height;
	private int timer = 0;
	private Graphics g;

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
		g = canvas.getGraphics();
	}

	@Override
	public void run() {
		init();
		while (true) {
			fps.updateFPS();
			repaint();
			
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

}
