package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class MainThread implements Runnable {
	private Canvas canvas;
	private Graphics g;
	private FPS fps = new FPS();
	private Dimension size;
	private int timer = 0;
	private int width,height;

	public MainThread(Canvas canvas, Dimension size) {
		this.canvas = canvas;
		this.size = size;
		width = ((Double)size.getWidth()).intValue();
		height = ((Double)size.getHeight()).intValue();
	}

	public void init() {
		g = canvas.getBufferStrategy().getDrawGraphics();
		canvas.setSize(size);
	}

	@Override
	public void run() {
		init();
		while (true) {
			fps.updateFPS();
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			
			if (timer <= 60) {
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

			try {
				Thread.sleep(fps.getSleepmillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
