package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class MainThread implements Runnable {
	private Canvas canvas;
	private Graphics g;
	private BufferStrategy bufferStrategy;
	private FPS fps = new FPS();

	public MainThread(Canvas canvas) {
		this.canvas = canvas;
	}

	public void init() {
		bufferStrategy = canvas.getBufferStrategy();
		g = bufferStrategy.getDrawGraphics();

	}

	@Override
	public void run() {
		init();
		while (true) {
			fps.updateFPS();
			g.setColor(Color.black);
			g.drawRect(0, 0, 10, 10);
			bufferStrategy.show();

			try {
				Thread.sleep(fps.getSleepmillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
